/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.calculation;

import java.util.HashMap;
import java.util.Map;

import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.extension.fdir.model.FMECAEntry;

/**
 * Operation that classifies a quantitative failure rate in terms of
 * a qualitative probability level. 
 * @author muel_s8
 *
 */

public class OpClassifyPL extends AAdvancedFunctionOp {

	public static final int SECONDS_PER_HOUR = 3600;
	public static final double LIMIT_PROBABLE = 1E-1 / SECONDS_PER_HOUR;
	public static final double LIMIT_OCCASIONAL = 1E-3 / SECONDS_PER_HOUR;
	public static final double LIMIT_REMOTE = 1E-5 / SECONDS_PER_HOUR;
	public static final double LIMIT_EXTREMELY_REMOTE = 0;
	
	public static final int PL_UNKNOWN = Integer.valueOf(FMECAEntry.PROBABILITY_Unknown_VALUE);
	public static final int PL_PROBABLE = Integer.valueOf(FMECAEntry.PROBABILITY_Probable_VALUE);
	public static final int PL_OCCASIONAL = Integer.valueOf(FMECAEntry.PROBABILITY_Occasional_VALUE);
	public static final int PL_REMOTE = Integer.valueOf(FMECAEntry.PROBABILITY_Remote_VALUE);
	public static final int PL_EXTREMELY_REMOTE = Integer.valueOf(FMECAEntry.PROBABILITY_ExtremelyRemote_VALUE);
	
	public static final int[] PL_LEVELS = new int[] { PL_PROBABLE, PL_OCCASIONAL, PL_REMOTE, PL_EXTREMELY_REMOTE};
	public static final Double[] DEFAULT_PL_THRESHOLDS = new Double[] { LIMIT_PROBABLE, LIMIT_OCCASIONAL, LIMIT_REMOTE, LIMIT_EXTREMELY_REMOTE };
	
	@Override
	public double apply(double[] inputs) {
		if (inputs.length != PL_LEVELS.length + OpClassifyDL.DL_LEVELS.length + 1) {
			return PL_UNKNOWN;
		}
		
		double failureRate = inputs[0];
		
		for (int i = 1; i < inputs.length - OpClassifyDL.DL_LEVELS.length; ++i) {
			if (failureRate >= inputs[i]) {
				return PL_LEVELS[i - 1];
			}
		}
		
		return PL_UNKNOWN;
	}
	
	@Override
	public Map<AQuantityKind, Double> applyOnQuantityKinds(AAdvancedFunction advancedFunction, Map<AQuantityKind, Double>[] inputQuantityKinds) {
		return new HashMap<>();
	}
}
