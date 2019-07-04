/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.ui.diagram.ra;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;

/**
 * The recovery automaton diagram type provider delivers all feature providers
 * for recovery automaton diagrams.
 * 
 * @author muel_s8
 *
 */

public class RecoveryAutomatonDiagramTypeProvider extends AbstractDiagramTypeProvider {

	/**
	 * Default Constructor
	 */
	public RecoveryAutomatonDiagramTypeProvider() {
		super();
		setFeatureProvider(new RecoveryAutomatonFeatureProvider(this));
	}
}
