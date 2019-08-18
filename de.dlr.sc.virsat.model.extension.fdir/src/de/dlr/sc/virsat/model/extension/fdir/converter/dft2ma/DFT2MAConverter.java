/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.metrics.FailLabelProvider;
import de.dlr.sc.virsat.fdir.core.metrics.FailLabelProvider.FailLabel;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.po.PODFTState;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.semantics.DFTSemantics;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.semantics.INodeSemantics;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.semantics.NDSPARESemantics;
import de.dlr.sc.virsat.model.extension.fdir.evaluator.FailNodeProvider;
import de.dlr.sc.virsat.model.extension.fdir.model.BasicEvent;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNodeType;
import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAction;
import de.dlr.sc.virsat.model.extension.fdir.recovery.RecoveryStrategy;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHolder;

/**
 * Constructs a markov automaton from a DFT.
 * @author muel_s8
 *
 */

public class DFT2MAConverter {
	private DFTSemantics dftSemantics = DFTSemantics.createNDDFTSemantics();
	private FaultTreeSymmetryChecker symmetryChecker = new FaultTreeSymmetryChecker();
	private FailLabelProvider failLabelProvider;
	private FailNodeProvider failNodeProvider;
	
	private boolean allowsDontCareFailing = true;
	
	private FaultTreeNode root;
	
	private MarkovAutomaton<DFTState> ma;
	private DFTState initial;

	private Set<IDFTEvent> events;
	private Set<FaultTreeNode> transientNodes;
	private Set<BasicEvent> orderDependentBasicEvents;
	private Map<Set<BasicEvent>, List<DFTState>> mapUnorderedBesToMarkovianDFTStates;
	private FaultTreeHolder ftHolder;
	private RecoveryStrategy recoveryStrategy;
	private Map<FaultTreeNode, List<FaultTreeNode>> symmetryReduction;
	private Map<FaultTreeNode, Set<FaultTreeNode>> symmetryReductionInverted;
	
	private DFT2MAConversionStatistics statistics = new DFT2MAConversionStatistics();
	
	/**
	 * Converts a fault tree with the passed node as a root to a
	 * Markov automaton.
	 * @param root a fault tree node used as a root node for the conversion
	 * @param failNodeProvider the nodes that need to fail
	 * @param failLabelProvider the fail label criterion
	 * @return the generated Markov automaton resulting from the conversion
	 */
	public MarkovAutomaton<DFTState> convert(FaultTreeNode root, FailNodeProvider failNodeProvider, FailLabelProvider failLabelProvider) {
		statistics.time = System.currentTimeMillis();
		this.root = root;
		this.failLabelProvider = failLabelProvider != null ? failLabelProvider : new FailLabelProvider(FailLabel.FAILED);
		this.failNodeProvider = failNodeProvider != null ? failNodeProvider : new FailNodeProvider(root);
		
		init();
		staticAnalysis();
		buildMA();
		
		statistics.maxStates = ma.getStates().size();
		statistics.maxTransitions = ma.getTransitions().size();
		statistics.time = System.currentTimeMillis() - statistics.time;
		
		return ma;
	}
	
	/**
	 * Same as {@link DFT2MAConverter#convert(FaultTreeNode, FailLabelProvider)} with a null fail criterion
	 * @param root a fault tree node used as a root node for the conversion
	 * @return the generated Markov automaton resulting from the conversion
	 */
	public MarkovAutomaton<DFTState> convert(FaultTreeNode root) {
		return convert(root, null, null);
	}
	
	/**
	 * Initializes the data structures
	 */
	private void init() {
		orderDependentBasicEvents = new HashSet<>();
		mapUnorderedBesToMarkovianDFTStates = new HashMap<>();
		transientNodes = new HashSet<>();
		
		FaultTreeNode holderRoot = root instanceof BasicEvent ? root.getFault() : root;
		ftHolder = new FaultTreeHolder(holderRoot);
		
		events = dftSemantics.createEventSet(ftHolder);
		for (BasicEvent be : ftHolder.getMapBasicEventToFault().keySet()) {
			if (be.isSetRepairRate() && be.getRepairRate() > 0) {
				transientNodes.add(be);			
			}
		}
		
		if (recoveryStrategy != null) {
			events.addAll(recoveryStrategy.createEventSet());
			INodeSemantics spareSemantics = dftSemantics.getMapTypeToSemantics().get(FaultTreeNodeType.SPARE);
			if (spareSemantics instanceof NDSPARESemantics) {
				((NDSPARESemantics) spareSemantics).setPropagateWithoutClaiming(true);
			}
		}
	}
	
	/**
	 * Perform a static analysis to obtain useful information for improving runtime performance
	 */
	private void staticAnalysis() {
		for (IDFTEvent event : events) {
			if (event instanceof FaultEvent) {
				FaultEvent faultEvent = (FaultEvent) event;
				if (!faultEvent.isRepair()) {
					if (isOrderDependent(event)) {
						orderDependentBasicEvents.add((BasicEvent) faultEvent.getNode());
					}
				}
			}
		}
		
		if (symmetryChecker != null) {
			symmetryReduction = symmetryChecker.computeSymmetryReduction(ftHolder, ftHolder);
			symmetryReductionInverted = symmetryChecker.invertSymmetryReduction(symmetryReduction);
		}
	}
	
	/**
	 * Checks if a failure mode event does not have a semantically different effect
	 * if it appears in a different order. E.g. it may not be used in a PAND gate.
	 * @param event the fault event to check
	 * @return true iff the failure mode is order dependent
	 */
	private boolean isOrderDependent(IDFTEvent event) {
		Fault fault = event.getNode().getFault();
		
		Queue<FaultTreeNode> toProcess = new LinkedList<>();
		toProcess.add(fault);
		Set<FaultTreeNode> processed = new HashSet<>();
		
		while (!toProcess.isEmpty()) {
			FaultTreeNode node = toProcess.poll();
			
			if (!processed.add(node)) {
				continue;
			}
			
			FaultTreeNodeType type = node.getFaultTreeNodeType();
			if (type == FaultTreeNodeType.POR) {
				return true;
			}
			
			List<FaultTreeNode> parents = ftHolder.getMapNodeToParents().get(node);
			toProcess.addAll(parents);
		}
		
		return false;
	}
	
	/**
	 * Constructs the actual automaton
	 */
	private void buildMA() {
		ma = new MarkovAutomaton<DFTState>();
		ma.getEvents().addAll(events);
		
		createInitialState();
		
		Queue<DFTState> toProcess = new LinkedList<>();
		toProcess.offer(initial);
		
		while (!toProcess.isEmpty()) {
			DFTState state = toProcess.poll();
			List<IDFTEvent> occurableEvents = getOccurableEvents(state);
			
			for (IDFTEvent event : occurableEvents) {
				int multiplier = 1;
				
				// Very simple symmetry reduction to get started
				if (symmetryChecker != null) {
					int countBiggerSymmetricEvents = countBiggerSymmetricEvents(event, state);
					if (countBiggerSymmetricEvents == -1) {
						continue;
					} else {
						multiplier += countBiggerSymmetricEvents;
					}
				}
				
				Double rate = event.getRate(state) * multiplier;
				
				DFTState baseSucc = dftSemantics.getStateGenerator().generateState(state);
				baseSucc.setRecoveryStrategy(state.getRecoveryStrategy());
				event.execute(baseSucc, orderDependentBasicEvents, transientNodes);
				
				List<DFTState> succs = new ArrayList<>();
				succs.add(baseSucc);
				
				Map<DFTState, List<RecoveryAction>> mapStateToRecoveryActions = new HashMap<>();
				mapStateToRecoveryActions.put(baseSucc, Collections.emptyList());
				
				List<FaultTreeNode> changedNodes = dftSemantics.updateFaultTreeNodeToFailedMap(ftHolder, state, 
						succs, mapStateToRecoveryActions, event);
				
				statistics.countGeneratedStates += succs.size();
				
				DFTState markovSucc = null;
				if (recoveryStrategy != null) {
					Set<FaultTreeNode> occuredEvents = dftSemantics.extractRecoveryActionInput(ftHolder, baseSucc, event, changedNodes);
					RecoveryStrategy recoveryStrategy = occuredEvents.isEmpty() ? baseSucc.getRecoveryStrategy() : state.getRecoveryStrategy().onFaultsOccured(occuredEvents);
					
					if (!recoveryStrategy.getRecoveryActions().isEmpty()) {
						baseSucc = dftSemantics.getStateGenerator().generateState(state);
						baseSucc.setRecoveryStrategy(recoveryStrategy);
						
						event.execute(baseSucc, orderDependentBasicEvents, transientNodes);
						for (RecoveryAction ra : recoveryStrategy.getRecoveryActions()) {
							ra.execute(baseSucc);
						}
						
						succs.clear();
						succs.add(baseSucc);
						
						changedNodes = dftSemantics.updateFaultTreeNodeToFailedMap(ftHolder, state, succs, mapStateToRecoveryActions, event);
						
						statistics.countGeneratedStates += succs.size();
					} else {
						baseSucc.setRecoveryStrategy(recoveryStrategy);
					}
				} else if (succs.size() > 1) { 
					markovSucc = dftSemantics.getStateGenerator().generateState(baseSucc);
					ma.addState(markovSucc);
					ma.addMarkovianTransition(event, state, markovSucc, rate);	
				}
					
				for (DFTState succ : succs) {
					if (allowsDontCareFailing) {
						succ.failDontCares(changedNodes, orderDependentBasicEvents);
					}
					
					DFTState equivalentState = getEquivalentState(succ);
					
					if (equivalentState == succ) {
						if (symmetryChecker != null) {
							if (event instanceof FaultEvent) {
								succ.createSymmetryRequirements(state, (BasicEvent) event.getNode(), symmetryReduction);
							}
						}
						
						ma.addState(succ);
						toProcess.offer(succ);
						checkFailState(succ);
					}
					
					if (markovSucc != null) {
						List<RecoveryAction> actions = mapStateToRecoveryActions.get(succ);
						ma.addNondeterministicTransition(actions, markovSucc, equivalentState);	
					} else {
						ma.addMarkovianTransition(event, state, equivalentState, rate);
					}
				}
			}
		}
	}

	/**
	 * Creates and initializes the initial state of the Markov Automaton
	 */
	private void createInitialState() {
		initial = dftSemantics.getStateGenerator().generateState(ftHolder);
		mapUnorderedBesToMarkovianDFTStates.put(initial.unorderedBes, new ArrayList<>(Collections.singletonList(initial)));
		initial.activateNode(root.getFault());
		if (!root.equals(root.getFault())) {
			initial.activateNode(root);
		}
		initial.setRecoveryStrategy(recoveryStrategy);
		ma.addState(initial);
	}
	
	/**
	 * Gets a list of all fault events that can occur in a given state
	 * @param state the current state
	 * @return the list of all events that can occur
	 */
	private List<IDFTEvent> getOccurableEvents(DFTState state) {
		List<IDFTEvent> occurableEvents = new ArrayList<>();
		for (IDFTEvent event : events) {
			if (event.canOccur(state)) {
				occurableEvents.add(event);
			}
		}
		return occurableEvents;
	}
	
	/**
	 * Checks if the state is a fail state and sets the associated flags
	 * @param state the state to check
	 */
	private void checkFailState(DFTState state) {
		for (FailLabel failLabel : failLabelProvider.getFailLabels()) {
			for (FaultTreeNode node : failNodeProvider.getFailNodes()) {
				switch (failLabel) {
					case FAILED:
						if (!state.hasFaultTreeNodeFailed(node)) {
							return;
						}
						break;
					case OBSERVED:
						if (!(state instanceof PODFTState) || !((PODFTState) state).isNodeFailObserved(node)) {
							return;
						}
						break;
					case UNOBSERVED:
						if (!(state instanceof PODFTState) || ((PODFTState) state).isNodeFailObserved(node)) {
							return;
						}
						break;
					case PERMANENT:
						if (!state.isFaultTreeNodePermanent(node)) {
							return;
						}
						break;
					default:
						break;
				}
			}
		}
		
		ma.getFinalStates().add(state);
		state.setFailState(true);
	}
	
	/**
	 * Computes the number of events symmetric to the passed one
	 * @param event the event
	 * @param state the current state
	 * @return -1 if there exists a smaller symmetric event, otherwise the number of bigger symmetric events
	 */
	private int countBiggerSymmetricEvents(IDFTEvent event, DFTState state) {
		int symmetryMultiplier = 0;
		
		if (event instanceof FaultEvent) {
			Set<BasicEvent> failedBasicEvents = state.getFailedBasicEvents();
			
			boolean isSymmetryReductionApplicable = isSymmetryReductionApplicable(state, event.getNode());
			if (isSymmetryReductionApplicable && !failedBasicEvents.containsAll(symmetryReductionInverted.getOrDefault(event.getNode(), Collections.emptySet()))) {
				return -1;
			}
			
			List<FaultTreeNode> symmetricNodes = symmetryReduction.getOrDefault(event.getNode(), Collections.emptyList());
			for (FaultTreeNode node : symmetricNodes) {
				if (!failedBasicEvents.contains(node)) {
					if (isSymmetryReductionApplicable(state, node)) {
						symmetryMultiplier++;
					}
				}
			}
		}
		
		return symmetryMultiplier;
	}
	
	/**
	 * Checks if symmetry reduction is applicable for a given node
	 * @param state the current state
	 * @param node the node
	 * @return true iff symmetry reduction is applicable
	 */
	private boolean isSymmetryReductionApplicable(DFTState state, FaultTreeNode node) {
		Map<FaultTreeNode, Set<FaultTreeNode>> mapParentToSymmetryRequirements = state.getMapParentToSymmetryRequirements();
		
		Set<FaultTreeNode> allParents = ftHolder.getMapNodeToAllParents().get(node);
		for (FaultTreeNode parent : allParents) {
			Set<FaultTreeNode> symmetryRequirements = mapParentToSymmetryRequirements.getOrDefault(parent, Collections.emptySet());
			for (FaultTreeNode symmetryRequirement : symmetryRequirements) {
				if (!state.hasFaultTreeNodeFailed(symmetryRequirement)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Check if there is an equivalent state of the passed state and return it.
	 * @param state the state for which we want to check if an equivalent one exists
	 * @return an equivalent state to the passed one or the state itself if not equivalent state exists
	 */
	private DFTState getEquivalentState(DFTState state) {
		List<DFTState> states = mapUnorderedBesToMarkovianDFTStates.get(state.unorderedBes);
		if (states == null) {
			List<DFTState> dftStates = new ArrayList<>();
			dftStates.add(state);
			mapUnorderedBesToMarkovianDFTStates.put(state.unorderedBes, dftStates);
			return state;
		}
		
		for (DFTState other : states) {
			if (state.isEquivalent(other)) {
				return other;
			}
		}
		
		states.add(state);
		
		return state;
	}

	/**
	 * Get the initial state of the markov automaton
	 * @return the initial state of the markov automaton
	 */
	public DFTState getInitial() {
		return initial;
	}
	
	/**
	 * Sets the node semantics for the converter
	 * @param dftSemantics the node semantics of the dft nodes
	 */
	public void setSemantics(DFTSemantics dftSemantics) {
		this.dftSemantics = dftSemantics;
	}
	
	/**
	 * Sets the recovery strategy
	 * @param recoveryStrategy the recovery strategy
	 */
	public void setRecoveryStrategy(RecoveryStrategy recoveryStrategy) {
		this.recoveryStrategy = recoveryStrategy;
	}
	
	/**
	 * Configures the symmetry checker
	 * @param symmetryChecker the symmetry checker
	 */
	public void setSymmetryChecker(FaultTreeSymmetryChecker symmetryChecker) {
		this.symmetryChecker = symmetryChecker;
	}
	
	/**
	 * Configugres the propertey whether dont care failing is allowed
	 * @param allowsDontCareFailing set to true to enable dont care failing of states
	 */
	public void setAllowsDontCareFailing(boolean allowsDontCareFailing) {
		this.allowsDontCareFailing = allowsDontCareFailing;
	}
	
	/**
	 * Gets the internal statistics to the last call of the converter
	 * @return the statistics of the last call of the converter
	 */
	public DFT2MAConversionStatistics getStatistics() {
		return statistics;
	}
	
	/**
	 * Gets the internal semantics obeject
	 * @return the internal semantics object
	 */
	public DFTSemantics getDftSemantics() {
		return dftSemantics;
	}
}
