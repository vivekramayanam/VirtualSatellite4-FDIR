/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.recovery.minimizer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.extension.fdir.model.ClaimAction;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultEventTransition;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNodeType;
import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAutomaton;

/**
 * Class that implements the ComposedMinimizer 
 * @author mika_li
 *
 */
public class ComposedMinimizerTest extends AMinimizerTestCase {

	@Override
	public ARecoveryAutomatonMinimizer createMinimizer() {
		ComposedMinimizer composedMinimizer = ComposedMinimizer.createDefaultMinimizer();
		return composedMinimizer;
	}

	@Test
	public void composedTestForTwoParallelSpareGates() {
		
		final int INITIAL_STATES = 4;
		final int RESULTING_STATES = 1; 
		final int RESULTING_TRANSITIONS = 2; 
		
		Fault fault1 = new Fault(concept);
		fault1.setName("Fault1");
		Fault fault2 = new Fault(concept);
		fault2.setName("Fault2");
		
		RecoveryAutomaton ra = new RecoveryAutomaton(concept);
		recoveryAutomatonHelper.createStates(ra, INITIAL_STATES); 
		ra.setInitial(ra.getStates().get(0));
		
		//CHECKSTYLE:OFF
		FaultEventTransition transition01 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(0), ra.getStates().get(1));
		FaultEventTransition transition02 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(0), ra.getStates().get(2));
		FaultEventTransition transition13 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(1), ra.getStates().get(3));
		FaultEventTransition transition23 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(2), ra.getStates().get(3));
		FaultEventTransition transition11 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(1), ra.getStates().get(1));
		FaultEventTransition transition22 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(2), ra.getStates().get(2));
		FaultEventTransition transition33 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(3), ra.getStates().get(3));
		FaultEventTransition transition33_2 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(3), ra.getStates().get(3));
		//CHECKSTYLE:ON
		
		recoveryAutomatonHelper.assignInputs(transition01, fault1);
		recoveryAutomatonHelper.assignInputs(transition02, fault2);
		recoveryAutomatonHelper.assignInputs(transition13, fault2);
		recoveryAutomatonHelper.assignInputs(transition23, fault1);
		recoveryAutomatonHelper.assignInputs(transition11, fault1);
		recoveryAutomatonHelper.assignInputs(transition22, fault2);
		recoveryAutomatonHelper.assignInputs(transition33, fault1);
		recoveryAutomatonHelper.assignInputs(transition33_2, fault2);
		                                                                                                                                                                                                    
		FaultTreeNode spare1 = faultTreeHelper.createGate(fault1, FaultTreeNodeType.SPARE);
		FaultTreeNode spare2 = faultTreeHelper.createGate(fault2, FaultTreeNodeType.SPARE);
		
		ClaimAction action1 = new ClaimAction(concept);
		action1.setClaimSpare(spare1);
		
		ClaimAction action2 = new ClaimAction(concept);
		action2.setClaimSpare(spare2);
		
		recoveryAutomatonHelper.assignAction(transition01, recoveryAutomatonHelper.copyClaimAction(action1));
		recoveryAutomatonHelper.assignAction(transition02, recoveryAutomatonHelper.copyClaimAction(action2));
		recoveryAutomatonHelper.assignAction(transition13, recoveryAutomatonHelper.copyClaimAction(action2));
		recoveryAutomatonHelper.assignAction(transition23, recoveryAutomatonHelper.copyClaimAction(action1));
		
		System.out.println(ra.toDot());
		
		ARecoveryAutomatonMinimizer minimizer = createMinimizer();
		minimizer.minimize(ra);
		
		System.out.println(ra.toDot());
		
		assertEquals(RESULTING_STATES, ra.getStates().size());
		assertEquals(RESULTING_TRANSITIONS, ra.getTransitions().size());
	} 
	
	@Test
	public void completeTest() {
		
		final int INITIAL_STATES = 5;
		final int RESULTING_STATES = 3; 
		final int RESULTING_TRANSITIONS = 5; 
		
		Fault fault1 = new Fault(concept);
		fault1.setName("Fault1");
		Fault fault2 = new Fault(concept);
		fault2.setName("Fault2");
		Fault fault3 = new Fault(concept);
		fault3.setName("Fault3");
		
		RecoveryAutomaton ra = new RecoveryAutomaton(concept);
		recoveryAutomatonHelper.createStates(ra, INITIAL_STATES); 
		
		//CHECKSTYLE:OFF
		FaultEventTransition transition00 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(0), ra.getStates().get(0));
		FaultEventTransition transition01 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(0), ra.getStates().get(1));
		FaultEventTransition transition02 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(0), ra.getStates().get(2));
		FaultEventTransition transition13 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(1), ra.getStates().get(3));
		FaultEventTransition transition13_2 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(1), ra.getStates().get(3));
		FaultEventTransition transition14 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(1), ra.getStates().get(4));
		FaultEventTransition transition24 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(2), ra.getStates().get(4));
		FaultEventTransition transition24_2 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(2), ra.getStates().get(4));
		FaultEventTransition transition23 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(2), ra.getStates().get(3));	
		FaultEventTransition transition33 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(3), ra.getStates().get(3));
		FaultEventTransition transition33_2 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(3), ra.getStates().get(3));
		FaultEventTransition transition33_3 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(3), ra.getStates().get(3));
		FaultEventTransition transition44 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(4), ra.getStates().get(4));
		FaultEventTransition transition44_2 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(4), ra.getStates().get(4));
		FaultEventTransition transition44_3 = recoveryAutomatonHelper.createFaultEventTransition(ra, ra.getStates().get(4), ra.getStates().get(4));
		//CHECKSTYLE:ON
		
		recoveryAutomatonHelper.assignInputs(transition00, fault3);
		recoveryAutomatonHelper.assignInputs(transition01, fault1);
		recoveryAutomatonHelper.assignInputs(transition02, fault2);
		recoveryAutomatonHelper.assignInputs(transition13, fault1);
		recoveryAutomatonHelper.assignInputs(transition13_2, fault2);
		recoveryAutomatonHelper.assignInputs(transition14, fault3);
		recoveryAutomatonHelper.assignInputs(transition24, fault1);
		recoveryAutomatonHelper.assignInputs(transition24_2, fault2);
		recoveryAutomatonHelper.assignInputs(transition23, fault3);
		recoveryAutomatonHelper.assignInputs(transition33, fault1);
		recoveryAutomatonHelper.assignInputs(transition33_2, fault2);
		recoveryAutomatonHelper.assignInputs(transition33_3, fault3);
		recoveryAutomatonHelper.assignInputs(transition44, fault1);
		recoveryAutomatonHelper.assignInputs(transition44_2, fault2);
		recoveryAutomatonHelper.assignInputs(transition44_3, fault3);
		
		FaultTreeNode spare1 = faultTreeHelper.createGate(fault1, FaultTreeNodeType.SPARE);
		FaultTreeNode spare2 = faultTreeHelper.createGate(fault2, FaultTreeNodeType.SPARE);
		FaultTreeNode spare3 = faultTreeHelper.createGate(fault2, FaultTreeNodeType.SPARE);
		
		ClaimAction action1 = new ClaimAction(concept);
		action1.setClaimSpare(spare1);
		
		ClaimAction action2 = new ClaimAction(concept);
		action2.setClaimSpare(spare2);
		
		ClaimAction action3 = new ClaimAction(concept);
		action3.setClaimSpare(spare3);
		
		recoveryAutomatonHelper.assignAction(transition01, recoveryAutomatonHelper.copyClaimAction(action1));
		recoveryAutomatonHelper.assignAction(transition02, recoveryAutomatonHelper.copyClaimAction(action2));
		recoveryAutomatonHelper.assignAction(transition13, recoveryAutomatonHelper.copyClaimAction(action1));
		recoveryAutomatonHelper.assignAction(transition13_2, recoveryAutomatonHelper.copyClaimAction(action2));
		recoveryAutomatonHelper.assignAction(transition14, recoveryAutomatonHelper.copyClaimAction(action3));
		recoveryAutomatonHelper.assignAction(transition24, recoveryAutomatonHelper.copyClaimAction(action1));
		recoveryAutomatonHelper.assignAction(transition24_2, recoveryAutomatonHelper.copyClaimAction(action2));
		recoveryAutomatonHelper.assignAction(transition23, recoveryAutomatonHelper.copyClaimAction(action3));
		
		System.out.println(ra.toDot());
		
		ARecoveryAutomatonMinimizer minimizer = createMinimizer();
		minimizer.minimize(ra);
		
		assertEquals(RESULTING_STATES, ra.getStates().size());
		assertEquals(RESULTING_TRANSITIONS, ra.getTransitions().size());
	}
	
}
