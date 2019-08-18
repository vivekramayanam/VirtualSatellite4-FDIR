/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.core.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.dlr.sc.virsat.fdir.core.metrics.FailLabelProvider.FailLabel;

/**
 * This class represents the steady state detectability, which is the time bounded
 * detectability with time bound to infinity.
 * @author muel_s8
 *
 */

public class SteadyStateDetectability implements IDerivedMetric {

	public static final SteadyStateDetectability STEADY_STATE_DETECTABILITY = new SteadyStateDetectability();

	/**
	 * Hidden private constructor
	 */
	private SteadyStateDetectability() {
		
	}
	
	@Override
	public Map<FailLabelProvider, Set<IMetric>> getDerivedFrom() {
		Map<FailLabelProvider, Set<IMetric>> mapFailLabelProviderToMetrics = new HashMap<>();
		mapFailLabelProviderToMetrics.put(new FailLabelProvider(FailLabel.FAILED), Collections.singleton(SteadyStateAvailability.STEADY_STATE_AVAILABILITY));
		mapFailLabelProviderToMetrics.put(new FailLabelProvider(FailLabel.FAILED, FailLabel.OBSERVED), Collections.singleton(SteadyStateAvailability.STEADY_STATE_AVAILABILITY));
		return mapFailLabelProviderToMetrics;
	}

	@Override
	public void accept(IDerivedMetricVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Derives the steady state detectability from the steady state availability values
	 * @param steadyStateAvailability the steady state availability of the unobserved failure
	 * @param observedSteadyStateAvailability the steady state availability of the observed failure
	 * @return the steady state detectability
	 */
	public double derive(double steadyStateAvailability, double observedSteadyStateAvailability) {
		double observedUnavailability = 1 - observedSteadyStateAvailability;
		if (observedUnavailability == 0) {
			return 1;
		}
		
		double unavailability = 1 - steadyStateAvailability;
		double steadyStateDetectability = unavailability / observedUnavailability;
		return steadyStateDetectability;
	}
}
