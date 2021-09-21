/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.fdir.core.markov.algorithm;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;


/**
 * Class for performing Bisimulation taking a state of 
 * base type MarkovState 
 * @author rama_vi
 *
 * @param <S>
 */
public class Bisimulation<S extends MarkovState> {
	
	protected MarkovAutomaton<S> ma;
	protected Map<S, Set<S>> mapStateToBlock;

	/**
	 * Constructor class which takes Markov Automaton as a parameter
	 * 
	 * @param ma
	 */
	public Bisimulation(MarkovAutomaton<S> ma) {
		this.ma = ma;
	}

	/**
	 * Computes Equivalence Classes and quotient
	 */
	public void minimize() {
		computeEquivalenceClasses();
		computeQuotient();
	}

	/**
	 * Assigns the states to respective blocks when they have similar labels
	 * 
	 * @return blocks
	 */
	protected Set<Set<S>> createInitialBlocks() {
		Set<Set<S>> blocks = new HashSet<>();
		mapStateToBlock = new HashMap<>();
		for (S state : ma.getStates()) {
			Set<S> block = getBlock(state, blocks);
			if (!blocks.remove(block)) {
				block = new HashSet<>();
			}
			block.add(state);
			blocks.add(block);
			mapStateToBlock.put(state, block);
		}
		return blocks;
	}

	/**
	 * It either returns a null value or set of States (Block) to which a state
	 * belongs to
	 * 
	 * @param state
	 * @param blocks
	 * @return block The block to which a state belongs to
	 */
	private Set<S> getBlock(S state, Set<Set<S>> blocks) {
		for (Set<S> block : blocks) {
			if (belongsToBlock(block, state)) {
				return block;
			}
		}
		return null;
	}

	/**
	 * checks whether a state belongs to block or not
	 * 
	 * @param block
	 * @param state
	 * @return boolean outputs true or false
	 */
	protected boolean belongsToBlock(Set<S> block, S state) {
		List<Object> stateLabels = new ArrayList<Object>();
		List<Object> stateRates = new ArrayList<Object>();
		List<Object> blockLabels = new ArrayList<Object>();
		List<Object> blockRates = new ArrayList<Object>();
		boolean isequal;
		List<MarkovTransition<S>> stateTransitions = ma.getSuccTransitions(state);
		List<MarkovTransition<S>> blockTransitions = ma.getSuccTransitions(block.iterator().next());
		for (MarkovTransition<S> statetransition : stateTransitions) {
			if (state.isProbabilisic() || state.isMarkovian()) {
				stateRates.add(statetransition.getRate());
				if (!stateLabels.contains(statetransition.getEvent())) {
					stateLabels.add(statetransition.getEvent());
				}
			} else {
				if (!stateLabels.contains(statetransition.getEvent())) {
					stateLabels.add(statetransition.getEvent());
				}
			}
		}
		for (MarkovTransition<S> blocktransition : blockTransitions) {
			if (block.iterator().next().isProbabilisic() || block.iterator().next().isMarkovian()) {
				blockRates.add(blocktransition.getRate());
				if (!blockLabels.contains(blocktransition.getEvent())) {
					blockLabels.add(blocktransition.getEvent());
				}
			} else {
				if (!blockLabels.contains(blocktransition.getEvent())) {
					blockLabels.add(blocktransition.getEvent());
				}
			}
		}
		if (stateRates.isEmpty() && blockRates.isEmpty()) {
			isequal = blockLabels.equals(stateLabels);
		}
		isequal = blockLabels.equals(stateLabels) && stateRates.equals(blockRates) && state.getFailLabels().equals(block.iterator().next().getFailLabels());
		return isequal;
	}

	/**
	 * @param blocks takes as input the blocks created from the
	 * createInitialBlocks() method and further refines them
	 */
	public void refineBlocks(Set<Set<S>> blocks) {
		Queue<Set<S>> blocksToProcess = new LinkedList<>(blocks);
		while (!blocksToProcess.isEmpty()) {
			Set<S> block = blocksToProcess.poll();
			if (block.size() <= 1) {
				continue;
			}
			Set<Set<S>> refinedBlocks = refineBlock(block);
			if (refinedBlocks.size() > 1) {
				blocks.remove(block);
				applyRefinement(blocks, refinedBlocks);
				Set<Set<S>> outdatedBlocks = getOutDatedBlocks(refinedBlocks);
				for (Set<S> outdatedBlock : outdatedBlocks) {
					if (!blocksToProcess.contains(outdatedBlock)) {
						blocksToProcess.offer(outdatedBlock);
					}
				}
			}
		}
	}

	/**
	 * The Set of States will be further refined based on their respective outgoing
	 * blocks
	 * 
	 * @param block takes the individual block from queue
	 * @return blocks refines the block into blocks further if necessary
	 */
	public Set<Set<S>> refineBlock(Set<S> block) {
		Map<Map<Object, Set<S>>, Set<S>> mapBlockReachabilityMapToRefinedBlock = new HashMap<>();
		Set<Set<S>> refinedBlocks = new HashSet<>();
		for (S state : block) {
			Map<Object, Set<S>> mapLabelsOfAStateToOutgoingBlock = createBlockReachabilityMapForAState(block,
					state);
			Set<S> refinedBlock = getEquivalentBlock(mapBlockReachabilityMapToRefinedBlock,
					mapLabelsOfAStateToOutgoingBlock);
			if (refinedBlock == null) {
				refinedBlock = new HashSet<S>();
				refinedBlocks.add(refinedBlock);
				mapBlockReachabilityMapToRefinedBlock.put(mapLabelsOfAStateToOutgoingBlock, refinedBlock);
			}
			refinedBlock.add(state);
		}
		return refinedBlocks;
	}

	/**
	 * A set of States are outputted if we have a map from a state to its outgoing
	 * blocks
	 * 
	 * @param mapBlockReachabilityMapToRefinedBlock
	 * @param mapLabelsToBlock
	 * @return refinedblock outputs a block if we already a
	 *         mapblockreachabilityofState
	 */
	private Set<S> getEquivalentBlock(
			Map<Map<Object, Set<S>>, Set<S>> mapBlockReachabilityMapToRefinedBlock,
			Map<Object, Set<S>> mapLabelsOfAStateToOutgoingBlock) {
		Set<S> refinedblock = mapBlockReachabilityMapToRefinedBlock.get(mapLabelsOfAStateToOutgoingBlock);
		return refinedblock;
	}

	/**
	 * We create a map for a state to which block it goes with its labelled
	 * transitions
	 * 
	 * @param block
	 * @param state
	 * @return createBlockReachabilityMapForAState map between event(label of a
	 *         transition) and a block
	 */
	private Map<Object, Set<S>> createBlockReachabilityMapForAState(Set<S> block,
			S state) {
		Map<Object, Set<S>> mapLabelsOfAStateToOutgoingBlock = new HashMap<>();
		for (MarkovTransition<S> markovtransition : ma.getSuccTransitions(state)) {
			Set<S> toBlock = mapStateToBlock.get(markovtransition.getTo());
			if (toBlock != block) {
				mapLabelsOfAStateToOutgoingBlock.put(markovtransition.getEvent(), toBlock);
			}
		}
		return mapLabelsOfAStateToOutgoingBlock;
	}

	/**
	 * Updates the the current blocks with the new refined blocks
	 * 
	 * @param blocks        the current blocks
	 * @param refinedBlocks a list of refined blocks
	 */
	private void applyRefinement(Set<Set<S>> blocks, Set<Set<S>> refinedBlocks) {
		for (Set<S> refinedBlock : refinedBlocks) {
			blocks.add(refinedBlock);
			for (S state : refinedBlock) {
				mapStateToBlock.put(state, refinedBlock);
			}
		}
	}

	/**
	 * Gets the blocks that now have to be rechecked after the given refinement has
	 * been applied
	 * 
	 * @param refinedBlocks a refinement of the blocks
	 * @return all outdated blocks that need to be rechecked for refinement
	 */
	private Set<Set<S>> getOutDatedBlocks(Set<Set<S>> refinedBlocks) {
		Set<Set<S>> outdatedBlocks = new HashSet<>();
		for (Set<S> refinedBlock : refinedBlocks) {
			for (S state : refinedBlock) {
				for (MarkovTransition<S> markovtransition : ma.getPredTransitions(state)) {
					Set<S> fromBlock = mapStateToBlock.get(markovtransition.getFrom());
					outdatedBlocks.add(fromBlock);
				}
			}
		}
		return outdatedBlocks;
	}

	/**
	 * This method merges each equivalence class states into a single representative
	 * state
	 * 
	 * @param blocks the equivalence class blocks that are to be merged are given as
	 *               input
	 */
	public void mergeBlocks(Set<Set<S>> blocks) {
		double rate = 0;

		for (Set<S> block : blocks) {
			S state = block.iterator().next();
			List<MarkovTransition<S>> stateOutgoingTransitions = ma.getSuccTransitions(state);
			for (int i = stateOutgoingTransitions.size() - 1; i >= 0; i--) {
				MarkovTransition<S> stateOutgoingTransition = stateOutgoingTransitions.get(i);
				S toState = stateOutgoingTransition.getTo();
				S blockRepresentative = mapStateToBlock.get(toState).iterator().next();
				Object stateEvent = stateOutgoingTransition.getEvent();
				if (state.isMarkovian() || state.isProbabilisic()) {
					rate = stateOutgoingTransition.getRate();
				}
				if (toState != blockRepresentative) {
					if (blockRepresentative != state) {
						if (state.isNondet()) {
							ma.addNondeterministicTransition(stateEvent, state, blockRepresentative);
						} else if (state.isMarkovian()) {
							ma.addMarkovianTransition(stateEvent, state, blockRepresentative, rate);
						} else {
							ma.addProbabilisticTransition(stateEvent, state, blockRepresentative, rate);
						}
					}
				}
			}
		}
		Queue<Set<S>> blocksToProcess = new LinkedList<>(blocks);
		while (!blocksToProcess.isEmpty()) {
			Set<S> block = blocksToProcess.poll();
			S state = block.iterator().next();
			block.remove(state);
			for (S removedState : block) {
				ma.removeState(removedState);
			}
			block.add(state);
		}
		for (Set<S> block : blocks) {
			S state = block.iterator().next();
			ma.removeInvalidTransitions(state);
			ma.removeDuplicateTransitions(state);
		}
		for (int i = 0; i < ma.getStates().size(); i++) {
			S state = ma.getStates().get(i);
			state.setIndex(i);
		}


	}
	

	/**
	 * compute the equivalence classes on the Markov automaton. Each equivalence
	 * class is represented as a "block" list of states.
	 * 
	 * @return the computed block partitions
	 */
	public Set<Set<S>> computeEquivalenceClasses() {
		Set<Set<S>> blocks = createInitialBlocks();
		refineBlocks(blocks);
		return blocks;
	}

	/**
	 * Computes Quotient of the equivalence Classes. Basically all the States in an
	 * equivalence Class are contracted into Single State
	 */
	public void computeQuotient() {
		Set<Set<S>> equivalenceClasses = computeEquivalenceClasses();
		mergeBlocks(equivalenceClasses);
	}
	
}
