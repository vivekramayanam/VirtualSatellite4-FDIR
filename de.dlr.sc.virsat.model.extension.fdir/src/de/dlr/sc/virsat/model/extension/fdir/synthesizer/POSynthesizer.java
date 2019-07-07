/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.synthesizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;
import de.dlr.sc.virsat.fdir.core.markov.scheduler.IMarkovScheduler;
import de.dlr.sc.virsat.fdir.core.markov.scheduler.MarkovScheduler;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.DFTState;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.explicit.ExplicitDFT2MAConverter;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.explicit.po.ExplicitPODFTState;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.explicit.po.PONDDFTSemantics;
import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAutomaton;

/**
 * A synthesizer that considers partial observability.
 * @author muel_s8
 *
 */

public class POSynthesizer extends ASynthesizer {

	/**
	 * Represents a belief state
	 * @author muel_s8
	 *
	 */
	private class BeliefState extends MarkovState {
		Map<ExplicitPODFTState, Double> mapStateToBelief = new HashMap<>();
		ExplicitPODFTState representant;
		
		/**
		 * Standard constructor
		 * @param representant the representant
		 */
		BeliefState(ExplicitPODFTState representant) {
			this.representant = representant;
		}
		
		@Override
		public String toString() {
			String beliefs = mapStateToBelief.entrySet().stream()
					.map(entry -> entry.getValue() + ": " +  entry.getKey().getLabel())
					.collect(Collectors.joining(","));
			
			String label = index + " [label=\"[" + index + beliefs + "]\"";
			if (beliefMa.getFinalStates().contains(this)) {
				label += ", color=\"red\"";
			}
			label += "]";
			
			return label;
		}
	}
	
	private MarkovAutomaton<BeliefState> beliefMa;
	
	@Override
	protected RecoveryAutomaton computeMarkovAutomatonSchedule(DFTState initialMa) {
		beliefMa = new MarkovAutomaton<>();
		
		ExplicitPODFTState initialPo = (ExplicitPODFTState) initialMa;
		BeliefState initialBeliefState = new BeliefState(initialPo);
		initialBeliefState.mapStateToBelief.put(initialPo, 1d);
		beliefMa.addState(initialBeliefState);
		
		Queue<BeliefState> toProcess = new LinkedList<>();
		toProcess.offer(initialBeliefState);
		
		while (!toProcess.isEmpty()) {
			BeliefState beliefState = toProcess.poll();
			Map<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> mapObsertvationSetToTransitions = createMapRepresentantToTransitions(beliefState);
			
			if (beliefState.isMarkovian()) {
				for (Entry<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> entry : mapObsertvationSetToTransitions.entrySet()) {
					double exitRate = getTotalRate(entry.getValue());
					BeliefState beliefSucc = new BeliefState(entry.getKey());
					
					Set<Object> observationSet = new HashSet<>(beliefSucc.representant.getObservedFailedNodes());
					observationSet.removeAll(beliefState.representant.getObservedFailedNodes());
					
					boolean isMarkovian = true;
					boolean isFinal = false;
					for (MarkovTransition<DFTState> transition : entry.getValue()) {
						double prob = beliefState.mapStateToBelief.get(transition.getFrom()) * transition.getRate() / exitRate;
						beliefSucc.mapStateToBelief.put((ExplicitPODFTState) transition.getTo(), prob);
						if (!transition.getTo().isMarkovian()) {
							isMarkovian = false;
						}
						
						isFinal |= ma.getFinalStates().contains(transition.getTo());
					}
					
					normalizeBeliefState(beliefSucc);
					
					BeliefState equivalentbeliefSucc = getEquivalentBeliefState(beliefSucc);
					if (beliefSucc == equivalentbeliefSucc) {
						beliefSucc.setMarkovian(isMarkovian);
						beliefMa.addState(beliefSucc);
						toProcess.offer(beliefSucc);
						
						if (isFinal) {
							beliefMa.getFinalStates().add(beliefSucc);
						}
					}
					beliefMa.addMarkovianTransition(observationSet, beliefState, equivalentbeliefSucc, exitRate);
				}
			} else {
				for (Entry<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> entry : mapObsertvationSetToTransitions.entrySet()) {
					BeliefState beliefSucc = new BeliefState(entry.getKey());
					
					Set<MarkovTransition<DFTState>> succTransitions = entry.getValue();
					boolean isFinal = false;
					for (MarkovTransition<DFTState> succTransition : succTransitions) {
						ExplicitPODFTState succState = (ExplicitPODFTState) succTransition.getTo();
						
						double prob = succTransition.getRate() * beliefState.mapStateToBelief.get(succTransition.getFrom());
						beliefSucc.mapStateToBelief.put(succState, prob);
						
						isFinal |= ma.getFinalStates().contains(succState);
					}
					
					normalizeBeliefState(beliefSucc);
					
					BeliefState equivalentBeliefSucc = getEquivalentBeliefState(beliefSucc);
					if (beliefSucc == equivalentBeliefSucc) {
						beliefMa.addState(beliefSucc);
						toProcess.offer(beliefSucc);
						
						if (isFinal) {
							beliefMa.getFinalStates().add(beliefSucc);
						}
					}
					
					Map<Object, Set<MarkovTransition<DFTState>>> groupedSuccTransitions = succTransitions
							.stream().collect(Collectors.groupingBy(MarkovTransition::getEvent, Collectors.toSet()));
					
					for (Entry<Object, Set<MarkovTransition<DFTState>>> succTransitionGroup : groupedSuccTransitions.entrySet()) {	
						double prob = 0;
						for (MarkovTransition<DFTState> succTransition : succTransitionGroup.getValue()) {
							prob += beliefState.mapStateToBelief.get(succTransition.getFrom());
						}
						beliefMa.addNondeterministicTransition(succTransitionGroup.getKey(), beliefState, equivalentBeliefSucc, prob);
					}
				}
			}
		}
		
		System.out.println("-------------------------------------");
		System.out.println(beliefMa.toDot());
		System.out.println("-------------------------------------");
		
		IMarkovScheduler<BeliefState> scheduler = new MarkovScheduler<>();
		Map<BeliefState, Set<MarkovTransition<BeliefState>>> schedule = scheduler.computeOptimalScheduler(beliefMa, initialBeliefState);
		return new Schedule2RAConverter<>(beliefMa, concept).convert(schedule, initialBeliefState);
	}
	
	/**
	 * Normalizes the beliefs in the belief state
	 * @param beliefState the belief state
	 */
	private void normalizeBeliefState(BeliefState beliefState) {
		double totalBelief = getTotalBelief(beliefState);
		beliefState.mapStateToBelief.replaceAll((state, belief) -> belief / totalBelief);
	}
	
	/**
	 * Computes the total belief in a belief state.
	 * Should be 1 after normalization.
	 * @param beliefState the belief state
	 * @return the total belief in the belief state
	 */
	private double getTotalBelief(BeliefState beliefState) {
		double totalBelief = 0;
		for (Double belief : beliefState.mapStateToBelief.values()) {
			totalBelief += belief;
		}
		return totalBelief;
	}
	
	/**
	 * Checks for all MA states in the belief state how the observation set of their successors look like
	 * and groups all possible successors according to their observation sets
	 * @param beliefState the belief state
	 * @return a mapping from an observation set to the transitions that lead to states with this observation set
	 */
	private Map<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> createMapRepresentantToTransitions(BeliefState beliefState) {
		Map<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> mapRepresentantToTransitions = new HashMap<>();
		for (Entry<ExplicitPODFTState, Double> entry : beliefState.mapStateToBelief.entrySet()) {
			List<MarkovTransition<DFTState>> succTransitions = ma.getSuccTransitions(entry.getKey());
			for (MarkovTransition<DFTState> succTransition : succTransitions) {
				ExplicitPODFTState succState = (ExplicitPODFTState) succTransition.getTo();
				Set<MarkovTransition<DFTState>> transitions = null;
				
				for (Entry<ExplicitPODFTState, Set<MarkovTransition<DFTState>>> representantEntry : mapRepresentantToTransitions.entrySet()) {
					ExplicitPODFTState representant = representantEntry.getKey();
					if (representant.getObservedFailed().equals(succState.getObservedFailed()) 
							&& representant.getMapSpareToClaimedSpares().equals(succState.getMapSpareToClaimedSpares())) {
						transitions = representantEntry.getValue();
					}
				}
				
				if (transitions == null) {
					transitions = new HashSet<>();
					mapRepresentantToTransitions.put(succState, transitions);
				}
			
				transitions.add(succTransition);
			}
		}
		
		return mapRepresentantToTransitions;
	}
	
	/**
	 * Computes the total rate for a transition set
	 * @param transitions a set of transitions
	 * @return the total rate of the transition set
	 */
	private double getTotalRate(Set<MarkovTransition<DFTState>> transitions) {
		double getTotalRate = 0;
		for (MarkovTransition<DFTState> transition : transitions) {
			getTotalRate += transition.getRate();
		}
		return getTotalRate;
	}

	/**
	 * Gets an equivalent belief state
	 * @param state a belief state
	 * @return an equivalent belief state or the input state if no equivalent state exists
	 */
	private BeliefState getEquivalentBeliefState(BeliefState state) {
		for (BeliefState other : beliefMa.getStates()) {
			Set<DFTState> dftStates = new HashSet<>(state.mapStateToBelief.keySet());
			dftStates.addAll(other.mapStateToBelief.keySet());
			boolean isEquivalent = other.representant.getObservedFailed().equals(state.representant.getObservedFailed()) 
					&& other.representant.getMapSpareToClaimedSpares().equals(state.representant.getMapSpareToClaimedSpares());
			
			if (isEquivalent) {
				for (DFTState dftState : dftStates) {
					if (!Objects.equals(state.mapStateToBelief.get(dftState), other.mapStateToBelief.get(dftState))) {
						isEquivalent = false;
						break;
					}
				}
			}
			
			if (isEquivalent) {
				return other;
			}
		}
		
		return state;
	}

	@Override
	protected ExplicitDFT2MAConverter createDFT2MAConverter() {
		ExplicitDFT2MAConverter dft2MAConverter = new ExplicitDFT2MAConverter();
		dft2MAConverter.setSemantics(PONDDFTSemantics.createPONDDFTSemantics());
		return dft2MAConverter;
	}
}
