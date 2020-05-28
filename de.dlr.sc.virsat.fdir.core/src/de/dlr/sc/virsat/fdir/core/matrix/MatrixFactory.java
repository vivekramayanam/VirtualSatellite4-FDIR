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

import java.util.List;
import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;

/**
 * @author piet_ci
 * 
 * This class acts as a factory for different types of matrices.
 *
 */
public class MatrixFactory {
	
	private MarkovAutomaton<? extends MarkovState> mc;
	
	/**
	 * @param mc markov chain
	 * @param failStatesAreTerminal failStatesAreTerminal
	 * @param delta delta
	 * @return transition matrix
	 */
	public TransitionMatrix getTransitionMatrix(MarkovAutomaton<? extends MarkovState> mc, boolean failStatesAreTerminal, double delta) {		
		this.mc = mc;
		TransitionMatrix tm = new TransitionMatrix(mc.getStates().size());
		tm = createTransitionMatrix(tm, failStatesAreTerminal, delta);
		return tm;
	}
	/**
	 * @param mc markov chain
	 * @return transition matrix
	 */
	public BellmanMatrix getBellmanMatrix(MarkovAutomaton<? extends MarkovState> mc) {		
		this.mc = mc;
		BellmanMatrix tm = new BellmanMatrix(mc.getStates().size());
		tm = createBellmanMatrix(tm);
		return tm;
	}
	/**
	 * Creates a transition matrix
	 * @param tm transition matrix
	 * @param failStatesAreTerminal failStatesAreTerminal
	 * @param delta delta
	 * @return transition matrix
	 */
	private TransitionMatrix createTransitionMatrix(TransitionMatrix tm, boolean failStatesAreTerminal, double delta) {
		int countStates = mc.getStates().size();
		
		for (Object event : mc.getEvents()) {
			for (MarkovTransition<? extends MarkovState> transition : mc.getTransitions(event)) {
				int fromIndex = transition.getFrom().getIndex();
				if (!failStatesAreTerminal || !mc.getFinalStates().contains(transition.getFrom())) {
					tm.getDiagonal()[fromIndex] -= transition.getRate() * delta;
				}
			}
		}
		
		for (int i = 0; i < countStates; ++i) {
			MarkovState state = mc.getStates().get(i);
			List<?> transitions = mc.getPredTransitions(state);
			
			tm.getStatePredIndices()[state.getIndex()] = new int[transitions.size()];
			tm.getStatePredRates()[state.getIndex()] = new double[transitions.size()];
			for (int j = 0; j < transitions.size(); ++j) {
				@SuppressWarnings("unchecked")
				MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) transitions.get(j);
				if (!failStatesAreTerminal || !mc.getFinalStates().contains(transition.getFrom())) {
					tm.getStatePredIndices()[state.getIndex()][j] = transition.getFrom().getIndex();
					tm.getStatePredRates()[state.getIndex()][j] = transition.getRate() * delta;
				}
			}
		}		
		return tm;
	}
	
	private static final int[] EMPTY_INDEX_LIST = new int[0];
	private static final double[] EMPTY_RATES_LIST = new double[0];
	
	/**
	 * Creates the iteration matrix for computing the Mean Time To Failure (MTTF)
	 * according to the Bellman equation: MTTF(s) = 0 if s is a fail state MTTF(s) =
	 * 1/ExitRate(s) + SUM(s' successor of s: Prob(s, s') * MTTF(s')
	 * 
	 * @param tm TransitionMatrix
	 * @return the matrix representing the fixpoint iteration
	 */
	public BellmanMatrix createBellmanMatrix(BellmanMatrix tm) {
		for (MarkovState state : mc.getStates()) {
			List<?> transitions = mc.getSuccTransitions(state);

			if (state.isMarkovian() && !mc.getFinalStates().contains(state)) {
				tm.getStatePredIndices()[state.getIndex()] = new int[transitions.size()];
				tm.getStatePredRates()[state.getIndex()] = new double[transitions.size()];
				
				double exitRate = mc.getExitRateForState(state);

				for (int j = 0; j < transitions.size(); ++j) {
					@SuppressWarnings("unchecked")
					MarkovTransition<? extends MarkovState> transition = (MarkovTransition<? extends MarkovState>) transitions
							.get(j);
					tm.getStatePredIndices()[state.getIndex()][j] = transition.getTo().getIndex();
					tm.getStatePredRates()[state.getIndex()][j] = transition.getRate() / exitRate;
				}
			} else {
				tm.getStatePredIndices()[state.getIndex()] = EMPTY_INDEX_LIST;
				tm.getStatePredRates()[state.getIndex()] = EMPTY_RATES_LIST;
			}
		}
		return tm;
	}	
}
