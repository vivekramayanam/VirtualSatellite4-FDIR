/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.core.markov;

/**
 * This class holds the internal statistics of a call to the DFT2MAConverter
 * @author muel_s8
 *
 */

public class MarkovAutomatonBuildStatistics {
	// CHECKSTYLE:OFF
	public long time;
	public int maxStates;
	public int maxTransitions;
	// CHECKSTYLE:ON
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("State space generation Statistics: \n");
		sb.append("\t* Generation Time  :\t\t"	 		+ time + "\n");
		sb.append("\t* Max States       :\t\t" 			+ maxStates + "\n");
		sb.append("\t* Max Transitions  :\t\t" 			+ maxTransitions + "\n");
		return sb.toString();
	}
	
	/**
	 * Composes the statistics with the statistics of another call to the converter
	 * @param other the statistics of another call to the converter
	 */
	public void compose(MarkovAutomatonBuildStatistics other) {
		time += other.time;
		maxStates = Math.max(maxStates, other.maxStates);
		maxTransitions = Math.max(maxTransitions, other.maxTransitions);
	}
}
