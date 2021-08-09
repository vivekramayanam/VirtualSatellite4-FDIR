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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.metrics.FailLabelProvider;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.events.IDFTEvent;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2ma.semantics.DFTSemantics;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.test.ATestCase;

/**
 * Class for testing the DFT2MAConverter
 * @author muel_s8
 *
 */

public class DFT2MAConverterTest extends ATestCase {
	
	protected DFT2MAConverter dft2MaConverter;
	
	@Before
	public void setup() {
		dft2MaConverter = new DFT2MAConverter();
		dft2MaConverter.getStateSpaceGenerator().setSemantics(DFTSemantics.createStandardDFTSemantics());
	}
	
	@Test
	public void testConvertNondeterministicCsp3Symmetric() throws IOException {
		dft2MaConverter.getStateSpaceGenerator().setSemantics(DFTSemantics.createNDDFTSemantics());
		Fault fault = createDFT("/resources/galileo/csp3Symmetric.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(fault);
		
		final int EXPECTED_COUNT_STATES = 12;
		final int EXPECTED_COUNT_TRANSITIONS = 11;
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
	}
	
	@Test
	public void testConvertTransientOrPermanent() throws IOException {
		Fault fault = createDFT("/resources/galileoRepair/transientOrPermanent.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(fault);
		final int EXPECTED_COUNT_STATES = 3;
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
	}
	
	@Test
	public void testConvertTransientToPermanentConversion() throws IOException {
		Fault fault = createDFT("/resources/galileoRepair/transientToPermanentConversion.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(fault);
		final int EXPECTED_COUNT_STATES = 3;
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
	}
	
	@Test
	public void testConvertCsp2Repair2() throws IOException {
		Fault fault = createDFT("/resources/galileoRepair/csp2Repair2BadPrimary.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(fault);
		
		final int EXPECTED_COUNT_STATES = 4;
		final int EXPECTED_COUNT_TRANSITIONS = 7;
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
	}
	
	@Test
	public void testConvertNondeterministicCsp3SymmetricRepair() throws IOException {
		dft2MaConverter.getStateSpaceGenerator().setSemantics(DFTSemantics.createNDDFTSemantics());
		Fault fault = createDFT("/resources/galileoRepair/csp3SymmetricRepair.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(fault);
		
		final int EXPECTED_COUNT_STATES = 32;
		final int EXPECTED_COUNT_TRANSITIONS = 59;
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
	}
	
	@Test
	public void testConvertProb() throws IOException {
		FaultTreeNode root = createBasicDFT("/resources/galileoUniform/prob.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(root);
		
		final int EXPECTED_COUNT_EVENTS = 2;
		final int EXPECTED_COUNT_STATES = 3;
		final int EXPECTED_COUNT_TRANSITIONS = 2;
		final int EXPECTED_COUNT_FINAL_STATES = 1;
		assertEquals(EXPECTED_COUNT_EVENTS, ma.getEvents().size());
		Iterator<Object> itr = ma.getEvents().iterator();
		IDFTEvent event1 = (IDFTEvent) itr.next();
		assertTrue(event1.isImmediate());
		IDFTEvent event2 = (IDFTEvent) itr.next();
		assertTrue(event2.isImmediate());
		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
		assertEquals(EXPECTED_COUNT_FINAL_STATES, ma.getStatesWithLabels(FailLabelProvider.SINGLETON_FAILED).size());
	}
	
	@Test
	public void testConvertCsp2Prob() throws IOException {
		FaultTreeNode root = createBasicDFT("/resources/galileoUniform/csp2Prob.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(root);
		
		final int EXPECTED_COUNT_STATES = 4;
		final int EXPECTED_COUNT_TRANSITIONS = 3;
		final int EXPECTED_COUNT_FINAL_STATES = 1;

		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
		assertEquals(EXPECTED_COUNT_FINAL_STATES, ma.getStatesWithLabels(FailLabelProvider.SINGLETON_FAILED).size());
	}
	
	@Test
	public void testConvertCsp2Repair1Prob1() throws IOException {
		FaultTreeNode root = createBasicDFT("/resources/galileoUniform/csp2Repair1Prob1.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(root);
		
		final int EXPECTED_COUNT_STATES = 6;
		final int EXPECTED_COUNT_TRANSITIONS = 7;
		final int EXPECTED_COUNT_FINAL_STATES = 1;

		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
		assertEquals(EXPECTED_COUNT_FINAL_STATES, ma.getStatesWithLabels(FailLabelProvider.SINGLETON_FAILED).size());
	}
	
	@Test
	public void testConvertOr2Prob2() throws IOException {
		FaultTreeNode root = createBasicDFT("/resources/galileoUniform/or2Prob2.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(root);
		
		final int EXPECTED_COUNT_STATES = 5;
		final int EXPECTED_COUNT_TRANSITIONS = 8;
		final int EXPECTED_COUNT_FINAL_STATES = 1;

		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
		assertEquals(EXPECTED_COUNT_FINAL_STATES, ma.getStatesWithLabels(FailLabelProvider.SINGLETON_FAILED).size());
	}
	
	@Test
	public void testConvertCsp2Prob1Exp1() throws IOException {
		FaultTreeNode root = createBasicDFT("/resources/galileoUniform/csp2Prob1Exp1.dft");
		
		MarkovAutomaton<DFTState> ma = dft2MaConverter.convert(root);
		
		final int EXPECTED_COUNT_STATES = 4;
		final int EXPECTED_COUNT_TRANSITIONS = 3;
		final int EXPECTED_COUNT_FINAL_STATES = 1;

		assertEquals(EXPECTED_COUNT_STATES, ma.getStates().size());
		assertEquals(EXPECTED_COUNT_TRANSITIONS, ma.getTransitions().size());
		assertEquals(EXPECTED_COUNT_FINAL_STATES, ma.getStatesWithLabels(FailLabelProvider.SINGLETON_FAILED).size());
	}
	
	@Test
	public void testReduceMA1() throws IOException {
		Fault fault = createDFT("/resources/galileoUniform/csp2Repair1Prob1.dft");
		MarkovAutomaton<DFTState> dftreducedautomaton = dft2MaConverter.reduceMA(fault);
		
		List<DFTState> dftStates = dftreducedautomaton.getStates();
		
		final int ACTUAL_COUNT_STATES_AFTER_BISIMULATION = dftStates.size();
		final int EXPECTED_COUNT_STATES_AFTER_BISIMULATION = 5;
		final int ACTUAL_COUNT_TRANSITIONS_AFTER_BISIMULATION = dftreducedautomaton.getTransitions().size();
		final int EXPECTED_COUNT_TRANSITIONS_AFTER_BISIMULATION = 6;
		
		List<Object> stateLabels0 = dftreducedautomaton.getSuccEvents(dftStates.get(0));
		List<Object> stateLabels1 = dftreducedautomaton.getSuccEvents(dftStates.get(1));
		List<Object> stateLabels2 = dftreducedautomaton.getSuccEvents(dftStates.get(2));
		List<Object> stateLabels3 = dftreducedautomaton.getSuccEvents(dftStates.get(3));
		List<Object> stateLabels4 = dftreducedautomaton.getSuccEvents(dftStates.get(4));
		List<Object> allStatesLabels = Stream.of(stateLabels0, stateLabels1, stateLabels2, stateLabels3, stateLabels4)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
		
		assertEquals(EXPECTED_COUNT_STATES_AFTER_BISIMULATION, ACTUAL_COUNT_STATES_AFTER_BISIMULATION);
		assertEquals(EXPECTED_COUNT_TRANSITIONS_AFTER_BISIMULATION, ACTUAL_COUNT_TRANSITIONS_AFTER_BISIMULATION);
		List<Object> ExpectedAllStatesLabels = Arrays.asList("F(a)", "F(a)", "F(p)", "Not-F(p)", "R(a)", "R(a)");
		assertTrue(allStatesLabels.containsAll(ExpectedAllStatesLabels));
				
	}
	
	@Test
	public void testReduceMA2() throws IOException {
		Fault fault = createDFT("/resources/galileoUniform/csp2Prob1Exp1.dft");
		MarkovAutomaton<DFTState> dftreducedautomaton = dft2MaConverter.reduceMA(fault);
		
		List<DFTState> dftStates = dftreducedautomaton.getStates();
		final int ACTUAL_COUNT_STATES_AFTER_BISIMULATION = dftStates.size();
		final int EXPECTED_COUNT_STATES_AFTER_BISIMULATION = 3;
		final int ACTUAL_COUNT_TRANSITIONS_AFTER_BISIMULATION = dftreducedautomaton.getTransitions().size();
		final int EXPECTED_COUNT_TRANSITIONS_AFTER_BISIMULATION = 3;
		
		List<Object> stateLabels0 = dftreducedautomaton.getSuccEvents(dftStates.get(0));
		List<Object> stateLabels1 = dftreducedautomaton.getSuccEvents(dftStates.get(1));
		List<Object> stateLabels2 = dftreducedautomaton.getSuccEvents(dftStates.get(2));
		List<Object> allStatesLabels = Stream.of(stateLabels0, stateLabels1, stateLabels2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
		
		assertEquals(EXPECTED_COUNT_STATES_AFTER_BISIMULATION, ACTUAL_COUNT_STATES_AFTER_BISIMULATION);
		assertEquals(EXPECTED_COUNT_TRANSITIONS_AFTER_BISIMULATION, ACTUAL_COUNT_TRANSITIONS_AFTER_BISIMULATION);
		List<Object> ExpectedAllStatesLabels = Arrays.asList("F(a)", "F(p)", "Not-F(p)");
		assertTrue(allStatesLabels.containsAll(ExpectedAllStatesLabels));
		
	}
	
}


