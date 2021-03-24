package de.dlr.sc.virsat.fdir.core.markov.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;



import org.eclipse.core.runtime.SubMonitor;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.markov.MarkovTransition;









public class Bisimulation {
	
	protected MarkovAutomaton<MarkovState> ma;
	protected Map<MarkovState, Set<MarkovState>> mapStateToBlock;
	
	public Bisimulation(MarkovAutomaton<MarkovState> ma) {
		this.ma = ma;

	}

	
	
	public void minimize() {
		computeEquivalenceClasses();
		computeQuotient();
	}
	
	protected Set<Set<MarkovState>> createInitialBlocks() {
		Set<Set<MarkovState>> blocks = new HashSet<>();
		mapStateToBlock = new HashMap<>();
		for (MarkovState state : ma.getStates()) {
			Set<MarkovState> block = getBlock(state, blocks);
			if (!blocks.remove(block)) {
				block = new HashSet<>();
			}
	
			block.add(state);	
			blocks.add(block);
			mapStateToBlock.put(state, block);
		}
		
		return blocks;
	}
	
	
	
	private Set<MarkovState> getBlock(MarkovState state, Set<Set<MarkovState>> blocks) {
		for (Set<MarkovState> block : blocks) {
			if (belongsToBlock(block, state)) {
				return block;
			}
		}
		
		return null;
	}
	
	protected boolean belongsToBlock(Set<MarkovState> block, MarkovState state) {
		List<Object> StateLabels = new ArrayList<Object>();
		List<Object> BlockLabels = new ArrayList<Object>();
		
		List<MarkovTransition<MarkovState>> StateTransitions = ma.getSuccTransitions(state);
		
		for (MarkovTransition<MarkovState> Statetransition : StateTransitions) {
			
			
			StateLabels.add(Statetransition.getEvent());
		}
		
		List<MarkovTransition<MarkovState>> BlockTransitions = ma.getSuccTransitions(block.iterator().next());
		
		for (MarkovTransition<MarkovState> Blocktransition : BlockTransitions) {
			
			
			BlockLabels.add(Blocktransition.getEvent());
		}
		
		boolean isequal = BlockLabels.equals(StateLabels);
		
	return isequal;
	
	}
	
	
	/**
	 * It refines the initially created partitions further based on their transitions
	 * @param blocks the current blocks
	 */
	
   public void refineBlocks(Set<Set<MarkovState>> blocks) {
	   
	   Queue<Set<MarkovState>> blocksToProcess = new LinkedList<>(blocks);
	   while (!blocksToProcess.isEmpty()) {
			
			
			
		   Set<MarkovState> block = blocksToProcess.poll();
		
			if (block.size() <= 1) {
				continue;
			}
			
			Set<Set<MarkovState>> refinedBlocks = refineBlock(block);
			if (refinedBlocks.size() > 1) {
				blocks.remove(block);
				
				applyRefinement(blocks, refinedBlocks);
				Set<Set<MarkovState>> outdatedBlocks = getOutDatedBlocks(refinedBlocks);
				for (Set<MarkovState> outdatedBlock  : outdatedBlocks) {
					if (!blocksToProcess.contains(outdatedBlock)) {
						blocksToProcess.offer(outdatedBlock);
					}
				}
			}
			
			
	   }	
	   
	   
	   
   }
	
   
   public Set<Set<MarkovState>> refineBlock(Set<MarkovState> block) {
	   Map<Map<Object, Set<MarkovState>>, Set<MarkovState>> mapBlockReachabilityMapToRefinedBlock = new HashMap<>();
	   Set<Set<MarkovState>> refinedblocks = new HashSet<>();
	   for (MarkovState state : block) {
			Map<Object, Set<MarkovState>> mapLabelsOFaStateToOutgoingBlock = createBlockReachabilityMapForAState(block, state);
			Set<MarkovState> refinedBlock = getEquivalentBlock(mapBlockReachabilityMapToRefinedBlock, mapLabelsOFaStateToOutgoingBlock);
			if (refinedBlock == null) {
				refinedBlock = new HashSet<MarkovState>();
				refinedblocks.add(refinedBlock);
				mapBlockReachabilityMapToRefinedBlock.put(mapLabelsOFaStateToOutgoingBlock, refinedBlock);
			}
			
			refinedBlock.add(state);
		}
		
	    System.out.println(Arrays.asList(mapBlockReachabilityMapToRefinedBlock));
		return refinedblocks;
		
   }
   
   private Set<MarkovState> getEquivalentBlock(Map<Map<Object, Set<MarkovState>>, Set<MarkovState>> mapBlockReachabilityMapToRefinedBlock, Map<Object, Set<MarkovState>> mapLabelsToBlock) {
	   Set<MarkovState> refinedblock = mapBlockReachabilityMapToRefinedBlock.get(mapLabelsToBlock);
	   
	   
	   return refinedblock;
	   
	   
   }
   
   private Map<Object, Set<MarkovState>> createBlockReachabilityMapForAState(Set<MarkovState> block, MarkovState state) {
	   Map<Object, Set<MarkovState>> mapLabelsOFaStateToOutgoingBlock = new HashMap<>();
		for (MarkovTransition<MarkovState> markovtransition : ma.getSuccTransitions(state)) {
			
			Set<MarkovState> toBlock = mapStateToBlock.get(markovtransition.getTo());
			
			
			if (toBlock != block) {
				mapLabelsOFaStateToOutgoingBlock.put(markovtransition.getEvent(), toBlock);
			}
		}
		return mapLabelsOFaStateToOutgoingBlock;
	}
	
   
   private void applyRefinement(Set<Set<MarkovState>> blocks, Set<Set<MarkovState>> refinedBlocks) {
		for (Set<MarkovState> refinedBlock : refinedBlocks) {
			blocks.add(refinedBlock);
			for (MarkovState state : refinedBlock) {
				mapStateToBlock.put(state, refinedBlock);
			}
		}
	
	   
   }
   
   
   
   
   private Set<Set<MarkovState>> getOutDatedBlocks(Set<Set<MarkovState>> refinedBlocks) {
	   Set<Set<MarkovState>> outdatedBlocks = new HashSet<>();
   
		for (Set<MarkovState> refinedBlock : refinedBlocks) {
			for (MarkovState state : refinedBlock) {
				
				for (MarkovTransition<MarkovState> markovtransition : ma.getPredTransitions(state)) {
					Set<MarkovState> fromBlock = mapStateToBlock.get(markovtransition.getFrom());
					outdatedBlocks.add(fromBlock);
				}
			}
		}
		
		return outdatedBlocks;
  
   }
   
	
	public Set<Set<MarkovState>> computeEquivalenceClasses() {

		Set<Set<MarkovState>> blocks = createInitialBlocks();
        refineBlocks(blocks);

		return blocks;
	}

	public void computeQuotient() {
		
	}
}
