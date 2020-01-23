/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.po;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.DFTState;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHolder;

/**
 * This class extends the ExplicitDFT state by belief information to support
 * partial observability
 * @author muel_s8
 *
 */

public class PODFTState extends DFTState {
	private BitSet observedFailed;
	
	/**
	 * Default constructor
	 * @param ftHolder the fault tree
	 */
	public PODFTState(FaultTreeHolder ftHolder) {
		super(ftHolder);
		observedFailed = new BitSet(ftHolder.getNodes().size());
	}
	
	/**
	 * Gets the data structure holding the observation information
	 * @return the data structure holding the observation information
	 */
	public BitSet getObservedFailed() {
		return observedFailed;
	}
	
	/**
	 * Marks a given node failure as observed / unobserved
	 * @param node the node
	 * @param state the observation state
	 */
	public void setNodeFailObserved(FaultTreeNode node, boolean state) {
		int nodeID = ftHolder.getNodeIndex(node);
		observedFailed.set(nodeID, state);
	}
	
	/**
	 * Checks if a given node failure is observed
	 * @param node the node
	 * @return true iff a node is observed as failed
	 */
	public boolean isNodeFailObserved(FaultTreeNode node) {
		int nodeID = ftHolder.getNodeIndex(node);
		return observedFailed.get(nodeID);
	}
	
	/**
	 * Gets the set of all nodes that have been observed as failed
	 * @return the set of all nodes observed as failed
	 */
	public Set<FaultTreeNode> getObservedFailedNodes() {
		Set<FaultTreeNode> observedFailedNodes = new HashSet<>();
		for (FaultTreeNode node : ftHolder.getNodes()) {
			if (isNodeFailObserved(node)) {
				observedFailedNodes.add(node);
			}
		}
		return observedFailedNodes;
	}
	
	/**
	 * Standard constructor
	 * @param other the other partial observable explicit dft state
	 */
	public PODFTState(PODFTState other) {
		super(other);
		observedFailed = (BitSet) other.observedFailed.clone();
	}
	
	@Override
	public String getLabel() {
		return super.getLabel() + " | O" + getObservedFailedNodes().toString();
	}
	
	@Override
	public boolean isEquivalent(DFTState other) {
		PODFTState poState = (PODFTState) other;
		if (!observedFailed.equals(poState.observedFailed)) {
			return false;
		}
		
		return super.isEquivalent(other);
	}
	
	@Override
	protected boolean removeClaimedSparesOnFailureIfPossible(FaultTreeNode node) {
		return observedFailed.get(ftHolder.getNodeIndex(node)) && super.removeClaimedSparesOnFailureIfPossible(node);
	}
}
