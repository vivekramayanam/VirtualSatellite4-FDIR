/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.DFTState;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.GenerationResult;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.IStateGenerator;
import de.dlr.sc.virsat.model.extension.fdir.model.ClaimAction;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.model.FreeAction;
import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAction;
import de.dlr.sc.virsat.model.extension.fdir.model.SPARE;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHolder;

/**
 * This class implements the nondeterministic spare gate semantics
 * based on the default spare gate semantics. In this semantics
 * each spare may be claimed nondeterministically.
 * @author muel_s8
 *
 */

public class NDSPARESemantics extends StandardSPARESemantics {
	
	private Map<FaultTreeNode, Map<FaultTreeNode, ClaimAction>> mapNodeToNodeToClaimAction = new HashMap<>();
	private IStateGenerator stateGenerator;
	
	protected boolean propagateWithoutActions = false;
	
	/**
	 * Standard constructor
	 * @param stateGenerator the state generator
	 */
	public NDSPARESemantics(IStateGenerator stateGenerator) {
		this.stateGenerator = stateGenerator;
	}

	@Override
	protected boolean hasFailed(boolean foundSpare) {
		return true;
	}
	
	@Override
	protected boolean canClaim(DFTState pred, DFTState state, FaultTreeNode node, FaultTreeHolder ftHolder) {
		return !propagateWithoutActions && super.canClaim(pred, state, node, ftHolder);
	}
	
	@Override
	protected boolean performClaim(SPARE node, FaultTreeNode spare, DFTState state,
			GenerationResult generationResult) {
		
		List<RecoveryAction> recoveryActions = generationResult.getMapStateToRecoveryActions().get(state);
		
		ClaimAction ca = getOrCreateClaimAction(node, spare, mapNodeToNodeToClaimAction);
		DFTState newState = stateGenerator.generateState(state);
		ca.execute(newState);
		newState.setFaultTreeNodeFailed(node, false);
		List<RecoveryAction> extendedRecoveryActions = new ArrayList<>(recoveryActions);

		extendedRecoveryActions.add(ca);
		generationResult.getMapStateToRecoveryActions().put(newState, extendedRecoveryActions);
		
		newState.setNodeActivation(spare, true);
		generationResult.getGeneratedStates().add(newState);
		
		return false;
	}
	
	/**
	 * Gets or creates a new claim action
	 * @param gate the gate doing the claiming
	 * @param spare the spare getting claimed
	 * @param mapNodeToNodeToClaimAction a cashing of existing claim actions
	 * @return a claim action
	 */
	protected ClaimAction getOrCreateClaimAction(SPARE gate, FaultTreeNode spare, Map<FaultTreeNode, Map<FaultTreeNode, ClaimAction>> mapNodeToNodeToClaimAction) {
		Map<FaultTreeNode, ClaimAction> mapNodeToClaimAction = mapNodeToNodeToClaimAction.get(gate);
		if (mapNodeToClaimAction == null) {
			mapNodeToClaimAction = new HashMap<>();
			mapNodeToNodeToClaimAction.put(gate, mapNodeToClaimAction);
		}
		
		ClaimAction ca = mapNodeToClaimAction.get(spare);
		if (ca == null) {
			ca = new ClaimAction(spare.getConcept());
			ca.setSpareGate(gate);
			ca.setClaimSpare(spare);
			mapNodeToClaimAction.put(spare, ca);
		}
		
		return ca;
	}
	
	/**
	 * 
	 * @param node
	 *            node
	 * @param spare
	 *            the spare gate claimed
	 * @param state
	 *            state
	 * @param generationResult
	 *            result
	 * @return boolean
	 */
	protected boolean performFree(SPARE node, FaultTreeNode spare, DFTState state, GenerationResult generationResult) {

		List<RecoveryAction> recoveryActions = generationResult.getMapStateToRecoveryActions().get(state);

		FreeAction fa = getOrCreateFreeAction(spare);
		DFTState newState = stateGenerator.generateState(state);
		fa.execute(newState);

		newState.setFaultTreeNodeFailed(node, true); // true or false depending on primary
		List<RecoveryAction> extendedRecoveryActions = new ArrayList<>(recoveryActions);

		extendedRecoveryActions.add(fa);
		generationResult.getMapStateToRecoveryActions().put(newState, extendedRecoveryActions);

		newState.setNodeActivation(spare, false);
		generationResult.getGeneratedStates().add(newState);

		return state.setFaultTreeNodeFailed(node, false);
	}

	/**
	 * 
	 * @param spare
	 *            spare
	 * @return fa
	 */
	protected FreeAction getOrCreateFreeAction(FaultTreeNode spare) {
		FreeAction fa = new FreeAction(spare.getConcept());
		fa.setFreeSpare(spare);
		return fa;
	}

	
	/**
	 * Sets the propagation flag
	 * @param propagateWithoutActions whether this gate should prevent the propagation or not
	 */
	public void setPropagateWithoutActions(boolean propagateWithoutActions) {
		this.propagateWithoutActions = propagateWithoutActions;
	}
}
