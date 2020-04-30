/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.model;

import java.util.Collections;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.DFTState;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public  class FreeAction extends AFreeAction {
	
	/**
	 * Constructor of Concept Class
	 */
	public FreeAction() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate 
	 * a CategoryAssignment in the background from the given concept
	 * @param concept the concept where it will find the correct Category to instantiate from
	 */
	public FreeAction(Concept concept) {
		super(concept);
	}	

	/**
	 * Constructor of Concept Class that can be initialized manually by a given Category Assignment
	 * @param categoryAssignment The category Assignment to be used for background initialization of the Category bean
	 */
	public FreeAction(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}

	private FaultTreeNode freeSpare;
	
	public FaultTreeNode getFreeSpareByUUID(DFTState state) {
		FaultTreeNode freeSpareOriginal = getFreeSpare();
		return state.getFTHolder().getNodes().stream()
				.filter(node -> node.getUuid().equals(freeSpareOriginal.getUuid()))
				.findFirst().get();
	}
	
	@Override
	public void execute(DFTState state) {
		if (freeSpare == null) {
			freeSpare = getFreeSpareByUUID(state);
		}
		
		FaultTreeNode claimingSpareGate = state.getMapSpareToClaimedSpares().get(freeSpare);
		state.getMapSpareToClaimedSpares().remove(freeSpare);
		state.setNodeActivation(freeSpare, false);
		
		List<FaultTreeNode> spares = state.getFTHolder().getMapNodeToSpares().getOrDefault(claimingSpareGate, Collections.emptyList());
		boolean hasClaim = false;
		boolean hasWorkingUnit = false;
		
		for (FaultTreeNode spare : spares) {
			FaultTreeNode claimingSpareGateOther = state.getMapSpareToClaimedSpares().get(spare);
			if (claimingSpareGate != null && claimingSpareGate.equals(claimingSpareGateOther)) {
				hasClaim = true;
				
				if (!state.hasFaultTreeNodeFailed(spare)) {
					hasWorkingUnit = true;
				}
			}
		}
		
		if (!hasClaim) {
			for (FaultTreeNode primary : state.getFTHolder().getMapNodeToChildren().getOrDefault(claimingSpareGate, Collections.emptyList())) {
				state.setNodeActivation(primary, true);
			
				if (!state.hasFaultTreeNodeFailed(primary)) {
					hasWorkingUnit = true;
				}
			}
		}
		
		if (claimingSpareGate != null) {
			state.setFaultTreeNodeFailed(claimingSpareGate, !hasWorkingUnit);
		}
	}
	
	@Override
	public String toString() {
		if (getFreeSpare() == null) {
			return "Free()";
		} else {
			StructuralElementInstance freeSpareSei = VirSatEcoreUtil.getEContainerOfClass(getFreeSpare().getATypeInstance(), StructuralElementInstance.class);
			String sparePrefix = freeSpareSei != null ? (getFreeSpare().getParent().getName() + ".") : "";
			return "Free(" + sparePrefix + getFreeSpare().getName() +  ")";
		}
	}
	
	@Override
	public String getActionLabel() {
		StringBuilder sb = new StringBuilder();
		sb.append("Free(");
		
		FaultTreeNode spareGate = getFreeSpare();
		
		if (spareGate != null) {
			sb.append(spareGate.getUuid());
		} else {
			sb.append("null");
		}
		
		sb.append(")");
		
		return sb.toString();
	}

	@Override
	public RecoveryAction copy() {
		FreeAction copyFreeAction = new FreeAction(getConcept());
		copyFreeAction.setFreeSpare(getFreeSpare());
		return copyFreeAction;
	}
	
	@Override
	public List<FaultTreeNode> getAffectedNodes(DFTState state) {
		if (freeSpare == null) {
			getFreeSpareByUUID(state);
		}
		return state.getFTHolder().getMapNodeToParents().get(freeSpare);
	}
}
