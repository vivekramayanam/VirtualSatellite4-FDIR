/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.modularizer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.test.ATestCase;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHelper;

/**
 * Class to test Modules
 * @author jord_ad
 *
 */
public class ModuleTest extends ATestCase {
	
	protected Concept concept;
	protected Modularizer modularizer;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.fdir/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
		modularizer = new Modularizer();
	}

	@Test
	public void testCopyFaultTreeCMSimple() throws IOException {
		Fault rootCMSimple = createDFT("/resources/galileo/cm_simple.dft");
		Set<Module> modules = modularizer.getModules(rootCMSimple.getFaultTree());
		Iterator<Module> iter = modules.iterator();
		Module module = iter.next();
		
		while (!module.isNondeterministic()) {
			module = iter.next();
		}
		module.constructFaultTreeCopy();
		
		FaultTreeHelper fthelp = new FaultTreeHelper(module.getRootNodeCopy().getConcept());
		assertTrue("Number of nodes in tree copy should be >= number of nodes in module", fthelp.getAllNodes(module.getRootNodeCopy().getFault()).size() >= module.getNodes().size());
	}
	
	@Test
	public void testCopyFaultTreeCM1() throws IOException {
		Fault rootCM1 = createDFT("/resources/galileo/cm1.dft");
		Set<Module> modules = modularizer.getModules(rootCM1.getFaultTree());
		Iterator<Module> iter = modules.iterator();
		Module module = iter.next();
		
		final int NUM_NODES_IN_SHARED_SPARE_MODULE = 8;
		while (module.getNodes().size() < NUM_NODES_IN_SHARED_SPARE_MODULE) {
			module = iter.next();
		}

		module.constructFaultTreeCopy();
		assertEquals("CM1", module.getRootNodeCopy().getName());
		
		FaultTreeHelper fthelp = new FaultTreeHelper(module.getRootNodeCopy().getConcept());
		final int NUM_NODES = 18;
		assertTrue("Number of nodes in tree copy should be >= number of nodes in module", fthelp.getAllNodes(module.getRootNodeCopy().getFault()).size() >= module.getNodes().size());
		assertEquals(NUM_NODES, fthelp.getAllNodes(module.getRootNodeCopy().getFault()).size());
	}
	
	@Test
	public void testCopyFaultTreeCM2() throws IOException {
		Fault rootCM2 = createDFT("/resources/galileo/cm2.dft");
		Set<Module> modules = modularizer.getModules(rootCM2.getFaultTree());
		Iterator<Module> iter = modules.iterator();
		Module module = iter.next();
		final int NUM_NODES_IN_MODULE_DESIRED = 8;
		while (module.getNodes().size() < NUM_NODES_IN_MODULE_DESIRED) {
			module = iter.next();
		}
		module.constructFaultTreeCopy();
		
		assertEquals("CM1", module.getRootNodeCopy().getName());
		
		FaultTreeHelper fthelp = new FaultTreeHelper(module.getRootNodeCopy().getConcept());
		final int NUM_NODES = 24;
		final int NUM_SPARES = 3;
		assertTrue("Number of nodes in tree copy should be >= number of nodes in module", fthelp.getAllNodes(module.getRootNodeCopy().getFault()).size() >= module.getNodes().size());
		assertEquals(NUM_NODES, fthelp.getAllNodes(module.getRootNodeCopy().getFault()).size());
		assertEquals(NUM_SPARES, fthelp.getAllSpares(module.getRootNodeCopy().getFault()).size());
	}

}
