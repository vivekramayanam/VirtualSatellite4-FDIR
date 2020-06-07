/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.fdir.core.matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;
import de.dlr.sc.virsat.fdir.core.matrix.iterator.BellmanIterator;
import de.dlr.sc.virsat.fdir.core.matrix.iterator.IMatrixIterator;

public class BellmanMatrix extends TransitionMatrix {

	public BellmanMatrix(int countStates) {
		super(countStates);
	}
	
	@Override
	public IMatrixIterator getIterator(double[] probabilityDistribution, double eps) {
		return new BellmanIterator(this, probabilityDistribution);
	}
		
	/**
	 * Gets the initial MTTF according to the Bellman equations with
	 * MTTF(s) = 0 if s is a fail state and 
	 * MTTF(s) = 1/ExitRate(s) if s is not a fail state
	 * @param mc the markov chain
	 * @return the initial probability distribution
	 */
	public static double[] getNonFailSoujornTimes(MarkovAutomaton<? extends MarkovState> mc) {
		int countStates = mc.getStates().size();
		double[] inititalVector = new double[countStates];

		Set<MarkovState> failReachableStates = getStatesWithReachableFailState(mc);

		for (int i = 0; i < countStates; ++i) {
			MarkovState state = mc.getStates().get(i);
			if (state.isMarkovian() && !mc.getFinalStates().contains(state)) {
				inititalVector[i] = failReachableStates.contains(state) ? 1 / mc.getExitRateForState(state) : Double.POSITIVE_INFINITY;
			}
		}
		return inititalVector;
	}
	
	/**
	 * Gets an array with only the soujourn times of fail states.
	 * @param mc the markoc chain
	 * @return the soujourn times
	 */
	public static double[] getFailSoujournTimes(MarkovAutomaton<? extends MarkovState> mc) {
		int countStates = mc.getStates().size();
		double[] inititalVector = new double[countStates];

		for (int i = 0; i < countStates; ++i) {
			MarkovState state = mc.getStates().get(i);
			if (state.isMarkovian() && mc.getFinalStates().contains(state)) {
				inititalVector[i] = 1 / mc.getExitRateForState(state);
			}
		}
		return inititalVector;
	}
	
	/**
	 * Gets the soujourn time of any markovian state.
	 * @param mc the markoc chain
	 * @return the soujourn times
	 */
	public static double[] getSoujournTimes(MarkovAutomaton<? extends MarkovState> mc) {
		int countStates = mc.getStates().size();
		double[] inititalVector = new double[countStates];

		for (int i = 0; i < countStates; ++i) {
			MarkovState state = mc.getStates().get(i);
			if (state.isMarkovian()) {
				inititalVector[i] = 1 / mc.getExitRateForState(state);
			}
		}
		return inititalVector;
	}

	/**
	 * Computes the set of states that can reach a fail state
	 * @param mc the markov chain
	 * @return the set of states that can reach a fail state
	 */
	private static Set<MarkovState> getStatesWithReachableFailState(MarkovAutomaton<? extends MarkovState> mc) {
		Queue<MarkovState> toProcess = new LinkedList<>();
		toProcess.addAll(mc.getFinalStates());
		Set<MarkovState> failReachableStates = new HashSet<>();

		while (!toProcess.isEmpty()) {
			MarkovState state = toProcess.poll();
			if (failReachableStates.add(state)) {
				List<?> transitions = mc.getPredTransitions(state);
				for (int j = 0; j < transitions.size(); ++j) {
					@SuppressWarnings("unchecked")
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) transitions
							.get(j);
					toProcess.add(transition.getFrom());
				}
			}
		}
		return failReachableStates;
	}
}
