/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.core.markov.modelchecker;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.fdir.core.markov.MarkovState;
import de.dlr.sc.virsat.fdir.core.metrics.MTTF;
import de.dlr.sc.virsat.fdir.core.metrics.PointAvailability;
import de.dlr.sc.virsat.fdir.core.metrics.Reliability;
import de.dlr.sc.virsat.fdir.core.metrics.SteadyStateAvailability;

/**
 * 
 * @author yoge_re
 *
 */
public class MarkovModelCheckerTest {
	private static final double DELTA = 0.01;
	private static final double EPSILON =  0.001;
	
	@Test 
	public void testReliability() {
		final double EXPECTED_FAIL_RATE = 0.9975212478; 
		final double EXPECTED_MTTF = 0.166666666;
		MarkovModelChecker modelChecker = new MarkovModelChecker(DELTA, EPSILON * EPSILON);

		MarkovAutomaton<MarkovState> ma = new MarkovAutomaton<>();
		MarkovState state1 = new MarkovState();
		MarkovState state2 = new MarkovState();
		final double RATE = 6;
		ma.getEvents().add("a");
		ma.getEvents().add("b");
		ma.addState(state1);
		ma.addState(state2);
		ma.getFinalStates().add(state2);
		ma.addMarkovianTransition("a", state1, state2, RATE);
		ma.addMarkovianTransition("b", state2, state1, 1);
		modelChecker.checkModel(ma, Reliability.UNIT_RELIABILITY, MTTF.MTTF);
		
		int timepoint = (int) (1 / DELTA) - 1;
		assertEquals(EXPECTED_MTTF, modelChecker.getMeanTimeToFailure(), EPSILON);
		assertEquals(EXPECTED_FAIL_RATE, modelChecker.getFailRates().get(timepoint), EPSILON);
	}

	@Test
	public void testAvailability() {
		final List<Double> EXPECTED_POINT_AVAILABILITY = new ArrayList<>();
		final double AVAIL_RATE = 0.5453589766372955;
		EXPECTED_POINT_AVAILABILITY.add((double) 1);
		EXPECTED_POINT_AVAILABILITY.add(AVAIL_RATE);
		final double EXPECTED_STEADY_STATE_AVAILABILITY = 0.5453;
		MarkovModelChecker modelChecker = new MarkovModelChecker(1, 1);

		MarkovAutomaton<MarkovState> ma = new MarkovAutomaton<>();
		ma.getEvents().add("a");
		ma.getEvents().add("b");
		MarkovState state1 = new MarkovState();
		MarkovState state2 = new MarkovState();
		ma.addState(state1);
		ma.addState(state2);
		ma.getFinalStates().add(state2);

		final double RATE1 = 1.2;
		final double RATE2 = 1.2;
		ma.addMarkovianTransition("a", state1, state2, RATE1);
		ma.addMarkovianTransition("b", state2, state1, RATE2);

		modelChecker.checkModel(ma, PointAvailability.UNIT_POINTAVAILABILITY,
				SteadyStateAvailability.STEADY_STATE_AVAILABILITY);

		assertEquals(EXPECTED_STEADY_STATE_AVAILABILITY, modelChecker.getSteadyStateAvailability(), EPSILON);
		assertEquals(EXPECTED_POINT_AVAILABILITY, modelChecker.getPointAvailability()); 
	}
}
