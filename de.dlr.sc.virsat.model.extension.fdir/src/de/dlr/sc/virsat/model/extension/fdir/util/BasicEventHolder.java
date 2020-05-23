/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.fdir.core.markov.MarkovAutomaton;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.extension.fdir.model.BasicEvent;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.model.RepairAction;

/**
 * This class provides simple access to basic data relevant for basic events
 * @author muel_s8
 *
 */
public class BasicEventHolder {
	private Fault fault;
	private double hotFailureRate;
	private double coldFailureRate;
	private Map<List<FaultTreeNode>, Double> repairRates;
	
	/**
	 * Standard constructor
	 * @param basicEvent the basic event
	 */
	public BasicEventHolder(BasicEvent basicEvent) {
		repairRates = new HashMap<>();
		fault = basicEvent.getFault();
		hotFailureRate = getRateValue(basicEvent.getHotFailureRateBean());
		coldFailureRate = getRateValue(basicEvent.getColdFailureRateBean());
		
		double transientRepairRate = getRateValue(basicEvent.getRepairRateBean());
		transientRepairRate = Double.isNaN(transientRepairRate) ? 0 : transientRepairRate;
		repairRates.put(Collections.emptyList(), transientRepairRate);
				
		for (RepairAction repairAction : basicEvent.getRepairActions()) {
			List<FaultTreeNode> observations = new ArrayList<>(repairAction.getObservations());
			double repairRate = getRateValue(repairAction.getRepairRateBean());
			repairRates.put(observations, repairRate + transientRepairRate);
		}
	}
	
	/**
	 * Gets the value of the given bean as base unit if it is defined and NaN if the value is not defined.
	 * @param rateBean the bean
	 * @return the rate in the base unit or NaN if no value is defined
	 */
	public static double getRateValue(BeanPropertyFloat rateBean) {
		return rateBean.isSet() ? rateBean.getValueToBaseUnit() : Double.NaN;
	}
	
	/**
	 * Checks if the basic event defines a valid failure event
	 * @return true iff either a hot failure or a cold failure has a valid markovian rate
	 */
	public boolean isFailureDefined() {
		return MarkovAutomaton.isRateDefined(hotFailureRate) || MarkovAutomaton.isRateDefined(coldFailureRate);
	}
	
	/**
	 * Checks if the basic event defines a valid repair event
	 * @return true iff at least one repair rate is a valid markvoian rate
	 */
	public boolean isRepairDefined() {
		for (double repairRate : repairRates.values()) {
			if (MarkovAutomaton.isRateDefined(repairRate)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the parent fault of the basic event
	 * @return the parent fault of the basic event
	 */
	public Fault getFault() {
		return fault;
	}
	
	/**
	 * Gets the hot failure rate
	 * @return the hot failure rate
	 */
	public double getHotFailureRate() {
		return hotFailureRate;
	}
	
	/**
	 * Gets the cold failure rate
	 * @return the cold failure rate
	 */
	public double getColdFailureRate() {
		return coldFailureRate;
	}
	/**
	 * Gets all repair rates
	 * @return a mapping from required obersvations to repair rates
	 */
	public Map<List<FaultTreeNode>, Double> getRepairRates() {
		return repairRates;
	}
}
