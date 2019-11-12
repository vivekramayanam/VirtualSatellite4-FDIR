/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.core.markov.modelchecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import org.eclipse.core.runtime.SubMonitor;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;
import de.dlr.sc.virsat.fdir.core.matrix.Matrix;
import de.dlr.sc.virsat.fdir.core.matrix.MatrixFactory;
import de.dlr.sc.virsat.fdir.core.matrix.MatrixIterator;
import de.dlr.sc.virsat.fdir.core.matrix.IMatrix;
import de.dlr.sc.virsat.fdir.core.metrics.Availability;
import de.dlr.sc.virsat.fdir.core.metrics.IBaseMetric;
import de.dlr.sc.virsat.fdir.core.metrics.MTTF;
import de.dlr.sc.virsat.fdir.core.metrics.MinimumCutSet;
import de.dlr.sc.virsat.fdir.core.metrics.Reliability;
import de.dlr.sc.virsat.fdir.core.metrics.SteadyStateAvailability;

/**
 * Class for implementing Markov Model Checker Logic
 * @author yoge_re
 *
 */
public class MarkovModelChecker implements IMarkovModelChecker {

	/**
	 * Gets the initial MTTF according to the Bellman equations with
	 * MTTF(s) = 0 if s is a fail state and 
	 * MTTF(s) = 1/ExitRate(s) if s is not a fail state
	 * @return the initial probability distribution
	 */
	private double[] getInitialMTTFVector() {
		int countStates = mc.getStates().size();
		double[] inititalVector = new double[countStates];

		Queue<MarkovState> toProcess = new LinkedList<>();
		toProcess.addAll(mc.getFinalStates());
		Set<MarkovState> failableStates = new HashSet<>();

		while (!toProcess.isEmpty()) {
			MarkovState state = toProcess.poll();
			if (failableStates.add(state)) {
				List<?> transitions = mc.getPredTransitions(state);
				for (int j = 0; j < transitions.size(); ++j) {
					@SuppressWarnings("unchecked")
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) transitions
							.get(j);
					toProcess.add(transition.getFrom());
				}
			}
		}

		for (int i = 0; i < countStates; ++i) {
			MarkovState state = mc.getStates().get(i);
			if (!mc.getFinalStates().contains(state)) {
				if (!failableStates.contains(state)) {
					inititalVector[i] = Double.POSITIVE_INFINITY;
					continue;
				}

				List<?> transitions = mc.getSuccTransitions(state);
				double exitRate = 0;
				for (int j = 0; j < transitions.size(); ++j) {
					@SuppressWarnings("unchecked")
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) transitions
							.get(j);
					exitRate += transition.getRate();
				}
				inititalVector[i] = 1 / exitRate;
			}
		}

		return inititalVector;
	}

	/* Parameters */

	private double delta;
	private double eps;

	/* Transition System */

	private MarkovAutomaton<? extends MarkovState> mc;
	private Matrix tm;
	private IMatrix tmTerminal;
	private Matrix bellmanMatrix;

	/* Buffers */

	private double[] probabilityDistribution;
	private double[] resultBuffer;

	/* Results */
	private ModelCheckingResult modelCheckingResult;
	private ModelCheckingStatistics statistics;
	
	private MatrixFactory matrixFactory;
	/**
	 * 
	 * @param delta time slice
	 * @param eps precision
	 */
	public MarkovModelChecker(double delta, double eps) {
		this.delta = delta;
		this.eps = eps;
		this.matrixFactory = new MatrixFactory();
	}

	/**
	 * Does model checking on the markov chain
	 * @param mc Markov Chain
	 * @param metrics metrics
	 * 
	 */
	@Override
	public ModelCheckingResult checkModel(MarkovAutomaton<? extends MarkovState> mc, SubMonitor subMonitor, IBaseMetric... metrics) {
		statistics = new ModelCheckingStatistics();
		statistics.time = System.currentTimeMillis();

		tm = null;
		tmTerminal = null;
		bellmanMatrix = null;

		this.mc = mc;
		this.modelCheckingResult = new ModelCheckingResult();

		for (IBaseMetric metric : metrics) {
			metric.accept(this, subMonitor);
		}

		statistics.time = System.currentTimeMillis() - statistics.time;

		return modelCheckingResult;
	}

	@Override
	public ModelCheckingStatistics getStatistics() {
		return statistics;
	}

	@Override
	public void visit(Reliability reliabilityMetric, SubMonitor subMonitor) {
		probabilityDistribution = getInitialProbabilityDistribution();

		if (tmTerminal == null) {
			matrixFactory.setMc(mc);
			tmTerminal = matrixFactory.getTransitionMatrix(true, probabilityDistribution, delta, eps);
		}
		
		final int PROGRESS_COUNT = 100;
		if (subMonitor != null) {
			subMonitor.setTaskName("Running Markov Checker on Model");			
		}
    
		
		MatrixIterator mtxIterator = tmTerminal.getIterator();
		

		if (Double.isFinite(reliabilityMetric.getTime())) {
			int steps = (int) (reliabilityMetric.getTime() / delta);
			
			if (subMonitor != null) {
				subMonitor.setWorkRemaining(steps);
			}
			
			for (int time = 0; time <= steps; ++time) {
				
				if (subMonitor != null) {
					subMonitor.split(1);
				}				
				probabilityDistribution = mtxIterator.getProbabilityDistribution();
				modelCheckingResult.failRates.add(getFailRate());
				mtxIterator.iterate();
			}
		} else {
			double oldFailRate = getFailRate();
			modelCheckingResult.failRates.add(oldFailRate);

			boolean convergence = false;
			while (!convergence) {
				if (subMonitor != null) {
					subMonitor.setWorkRemaining(PROGRESS_COUNT).split(1);
				}
				
				mtxIterator.iterate();
				probabilityDistribution = mtxIterator.getProbabilityDistribution();
				double newFailRate = getFailRate();
				modelCheckingResult.failRates.add(newFailRate);
				double change = Math.abs(newFailRate - oldFailRate);
				oldFailRate = newFailRate;
				double relativeChange = change / newFailRate;
											
				if (relativeChange < eps || !Double.isFinite(change)) {
					convergence = true;
					if (subMonitor != null) {
						subMonitor.split(PROGRESS_COUNT);
					}
				}
			}
		}
	}

	@Override
	public void visit(MTTF mttfMetric) {
		if (bellmanMatrix == null) {
			Matrix matrix = new Matrix(mc);
			bellmanMatrix = matrix.createBellmanMatrix();
		}

		double[] baseMTTFs = getInitialMTTFVector();
		probabilityDistribution = getInitialMTTFVector();
		resultBuffer = new double[probabilityDistribution.length];
		double[] res = probabilityDistribution;

		boolean convergence = false;
		while (!convergence) {
			multiply(bellmanMatrix, res, resultBuffer);

			for (int i = 0; i < baseMTTFs.length; ++i) {
				resultBuffer[i] += baseMTTFs[i];
			}

			double change = Math.abs(res[0] - resultBuffer[0]);

			if (change < eps || Double.isNaN(change)) {
				if (Double.isInfinite(res[0])) {
					resultBuffer[0] = Double.POSITIVE_INFINITY;
				}
				probabilityDistribution = resultBuffer;
				convergence = true;
			} else {
				double[] tmp = res;
				res = resultBuffer;
				resultBuffer = tmp;
			}
		}

		modelCheckingResult.setMeanTimeToFailure(probabilityDistribution[0]);
	}

	@Override
	public void visit(Availability availabilityMetric, SubMonitor subMonitor) {
		if (tm == null) {
			Matrix matrix = new Matrix(mc);
			tm = matrix.createTransitionMatrix(false, delta);
		}
		
		final int PROGRESS_COUNT = 100;
		if (subMonitor != null) {
			subMonitor.setTaskName("Running Markov Checker on Model");
		}
				
		probabilityDistribution = getInitialProbabilityDistribution();
		resultBuffer = new double[probabilityDistribution.length];

		if (Double.isFinite(availabilityMetric.getTime())) {
			int steps = (int) (availabilityMetric.getTime() / delta);
			
			if (subMonitor != null) {
				subMonitor.setWorkRemaining(steps);
			}
			
			for (int time = 0; time <= steps; ++time) {
				if (subMonitor != null) {
					subMonitor.split(1);
				}
				modelCheckingResult.availability.add(1 - getFailRate());
				iterate(tm);
			}
		} else {
			double oldFailRate = getFailRate();
			modelCheckingResult.availability.add(oldFailRate);

			boolean convergence = false;
			while (!convergence) {
				if (subMonitor != null) {
					subMonitor.setWorkRemaining(PROGRESS_COUNT).split(1);
				}
				
				iterate(tm);
				double newFailRate = getFailRate();
				modelCheckingResult.availability.add(1 - newFailRate);
				double change = Math.abs(newFailRate - oldFailRate);
				oldFailRate = newFailRate;
				double relativeChange = change / newFailRate;
				if (relativeChange < eps || !Double.isFinite(change)) {
					convergence = true;
					if (subMonitor != null) {
						subMonitor.split(PROGRESS_COUNT);
					}
				}
			}
		}
	}

	@Override
	public void visit(SteadyStateAvailability steadyStateAvailabilityMetric) {
		if (tm == null) {
			Matrix matrix = new Matrix(mc);
			tm = matrix.createTransitionMatrix(false, delta);
		}

		probabilityDistribution = getInitialProbabilityDistribution();
		resultBuffer = new double[probabilityDistribution.length];
		double oldUnavailability = getFailRate();
		double difference = 0;
		boolean convergence = false;
		while (!convergence) {
			iterate(tm);
			double newUnavailability = getFailRate();
			difference = Math.abs(newUnavailability - oldUnavailability) / newUnavailability;

			if (difference < eps / Math.max(1, delta) || Double.isNaN(difference)) {
				convergence = true;
			}
			oldUnavailability = newUnavailability;
		}
		modelCheckingResult.setSteadyStateAvailability(1 - getFailRate());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void visit(MinimumCutSet minimumCutSet) {
		// Construct the minimum cut sets as follows:
		// MinCuts(s) = UNION_{(s, a, s')} ( {a} \cross MinCuts(s') )
		// MinCuts(f) = \emptyset for any fail state f

		Set<? extends MarkovState> failStates = mc.getFinalStates();
		Queue<MarkovState> toProcess = new LinkedList<>();
		toProcess.addAll(failStates);

		Map<MarkovState, Set<Set<Object>>> mapStateToMinCuts = new HashMap<>();

		while (!toProcess.isEmpty()) {
			MarkovState state = toProcess.poll();

			boolean shouldEnqueuePredecessors = false;
			if (!mc.getFinalStates().contains(state)) {
				// Update the mincuts
				Set<Set<Object>> oldMinCuts = mapStateToMinCuts.get(state);
				Set<Set<Object>> minCuts = new HashSet<>();

				List<?> succTransitions = mc.getSuccTransitions(state);
				for (Object succTransition : succTransitions) {
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) succTransition;
					MarkovState successor = transition.getTo();
					Set<Set<Object>> succMinCuts = mapStateToMinCuts.getOrDefault(successor, Collections.emptySet());

					if (succMinCuts.isEmpty()) {
						Set<Object> minCut = new HashSet<>();
						minCut.add(transition.getEvent());
						minCuts.add(minCut);
					} else {
						for (Set<Object> succMinCut : succMinCuts) {
							if (succMinCut.size() < minimumCutSet.getMaxSize() || minimumCutSet.getMaxSize() == 0) {
								Set<Object> minCut = new HashSet<>(succMinCut);
								minCut.add(transition.getEvent());
								minCuts.add(minCut);
							}
						}
					}

					// Make sure all cuts are mincuts
					Set<Set<Object>> subsumedMinCuts = new HashSet<>();
					for (Set<Object> minCut : minCuts) {
						for (Set<Object> minCutOther : minCuts) {
							if (minCut != minCutOther && minCut.containsAll(minCutOther)) {
								subsumedMinCuts.add(minCut);
							}
						}
					}
					minCuts.removeAll(subsumedMinCuts);
				}

				if (!Objects.equals(oldMinCuts, minCuts)) {
					shouldEnqueuePredecessors = true;
					mapStateToMinCuts.put(state, minCuts);
				}
			} else {
				shouldEnqueuePredecessors = true;
			}

			// Enqueue predecessors if necessary
			if (shouldEnqueuePredecessors) {
				List<?> predTransitions = mc.getPredTransitions(state);
				for (Object predTransition : predTransitions) {
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) predTransition;
					MarkovState predecessor = transition.getFrom();
					if (!toProcess.contains(predecessor) && !mc.getFinalStates().contains(predecessor)) {
						toProcess.add(predecessor);
					}
				}
			}
		}

		Set<Set<Object>> minCuts = mapStateToMinCuts.getOrDefault(mc.getStates().get(0), Collections.emptySet());
		modelCheckingResult.getMinCutSets().addAll(minCuts);
	}

	/**
	 * Gets the fail rate at the current iteration
	 * 
	 * @return the fail rate at the current iteration
	 */
	private double getFailRate() {
		double res = 0;

		for (MarkovState state : mc.getStates()) {
			if (mc.getFinalStates().contains(state)) {
				res += probabilityDistribution[state.getIndex()];
			}
		}

		return res;
	}

	/**
	 * Gets the initial probaility distribution, that is 1 on the initial state
	 * and 0 everywhere else
	 * @return the initial probability distribution
	 */
	private double[] getInitialProbabilityDistribution() {
		double[] inititalVector = new double[mc.getStates().size()];
		inititalVector[0] = 1;
		return inititalVector;
	}

	/**
	 * Performs one update iteration
	 * 
	 * @param tm the transition matrix
	 */
	private void iterate(Matrix tm) {
		double[] res = probabilityDistribution;
		probabilityDistribution = new double[probabilityDistribution.length];

		double lambda = 1;
		int i = 0;
		boolean convergence = false;
		while (!convergence) {
			for (int j = 0; j < probabilityDistribution.length; ++j) {
				probabilityDistribution[j] += res[j] * lambda;
			}

			lambda = lambda / (i + 1);
			double change = lambda * multiply(tm, res, resultBuffer) / delta;

			// Swap the discrete time buffers
			double[] tmp = res;
			res = resultBuffer;
			resultBuffer = tmp;

			if (change < eps * eps || !Double.isFinite(change)) {
				for (int j = 0; j < probabilityDistribution.length; ++j) {
					probabilityDistribution[j] += res[j] * lambda;
				}

				convergence = true;
			} else {
				++i;
			}
		}
	}

	/**
	 * Performs a discrete time abstract step.
	 * @param tm the transition matrix
	 * @param vector the current probability distribution
	 * @param result the vector to put the result into
	 * @return squared length of the result vector
	 */
	private double multiply(Matrix tm, double[] vector, double[] result) {
		int countStates = vector.length;
		double res = 0;

		for (int i = 0; i < countStates; ++i) {
			result[i] = vector[i] * tm.getDiagonal()[i];
			int[] predIndices = tm.getStatePredIndices()[i];
			double[] predRates = tm.getStatePredRates()[i];

			for (int j = 0; j < predIndices.length; ++j) {
				double change = vector[predIndices[j]] * predRates[j];
				res += change * change;
				result[i] += change;
			}
		}

		return res;
	}

	@Override
	public double getDelta() {
		return delta;
	}
}
