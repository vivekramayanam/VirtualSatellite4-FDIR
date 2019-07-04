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

import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAutomaton;
import de.dlr.sc.virsat.model.extension.fdir.util.RecoveryAutomatonHolder;

/**
 * Abstract base class for algorithms for minimizing recovery automata
 * @author muel_s8
 *
 */

public abstract class ARecoveryAutomatonMinimizer {


	/**
	 * Main method to override and perform the actual minimization
	 * @param raHolder the recovery automaton to minimize
	 */
	protected abstract void minimize(RecoveryAutomatonHolder raHolder);
	
	/**
	 * Main interface minimization method
	 * @param ra the recovery automaton to be minimized
	 */
	public void minimize(RecoveryAutomaton ra) {
		minimize(new RecoveryAutomatonHolder(ra));
	}
}
