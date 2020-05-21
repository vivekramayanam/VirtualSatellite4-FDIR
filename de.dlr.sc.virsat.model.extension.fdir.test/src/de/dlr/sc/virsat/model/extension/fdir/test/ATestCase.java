/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;

import de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader;
import de.dlr.sc.virsat.fdir.core.test.TestResourceGetter;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.fdir.Activator;
import de.dlr.sc.virsat.model.extension.fdir.converter.dft2dft.DFT2BasicDFTConverter;
import de.dlr.sc.virsat.model.extension.fdir.converter.galileo.GalileoDFT2DFT;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeBuilder;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHelper;
import de.dlr.sc.virsat.model.extension.fdir.util.RecoveryAutomatonHelper;

/**
 * Abstract test base class
 * @author muel_s8
 *
 */

public abstract class ATestCase {
	protected static final double TEST_EPSILON =  0.000001;
	
	protected Concept concept;
	protected FaultTreeBuilder ftBuilder;
	protected FaultTreeHelper ftHelper;
	protected RecoveryAutomatonHelper raHelper; 
	protected static TestResourceGetter resourceGetter = new TestResourceGetter(Activator.getPluginId());
	
	@Before
	public void setUp() throws Exception {
	    concept = ConceptXmiLoader.loadConceptFromPlugin(Activator.getPluginId() + "/concept/concept.xmi");
	    ftBuilder = new FaultTreeBuilder(concept);
	    ftHelper = ftBuilder.getFtHelper();
	    raHelper = new RecoveryAutomatonHelper(concept);
	}
	
	protected static final double DELTA = 0.01;
	
	/**
	 * Asserts that an evaluator has computed the correct transition matrix by performing several iterations
	 * and comparing the computed values with values generated by assumed to be correct calculators (DFTCalc in this case).
	 * @param result the result
	 * @param expected the expected result
	 * @param testEpsilon the test epsilon
	 */
	public static void assertIterationResultsEquals(List<Double> results, double[] expected, double testEpsilon) {
		for (int i = 0; i < expected.length; ++i) {
			double failRate = results.get(i + 1);
			assertEquals("Evaluation result is correct after time: " + ((i + 1) * DELTA), expected[i], failRate, testEpsilon);
		}
	}
	
	/**
	 * Asserts that an evaluator has computed the correct transition matrix by performing several iterations
	 * and comparing the computed values with values generated by assumed to be correct calculators (e.g. DFTCalc).
	 * @param results the results
	 * @param expected the expected result
	 */
	public static void assertIterationResultsEquals(List<Double> results, double[] expected) {
		assertIterationResultsEquals(results, expected, TEST_EPSILON);
	}
		
	/**
	 * Test helper for setting up the fault tree data
	 * @param resourcePath the fault tree to load
	 * @param concept the concept to use
	 * @return loaded fault tree
	 * @throws IOException 
	 */
	public static Fault createDFT(String resourcePath, Concept concept) throws IOException {
		InputStream is = resourceGetter.getResourceContentAsStream(resourcePath);
		GalileoDFT2DFT converter = new GalileoDFT2DFT(concept, is);
		return converter.convert();
	}

	/**
	 * Test helper for setting up the fault tree data
	 * @param resourcePath the fault tree to load
	 * @return loaded fault tree
	 * @throws IOException 
	 */
	public Fault createDFT(String resourcePath) throws IOException {
		return createDFT(resourcePath, concept);
	}
	
	/**
	 * Test helper for setting up fault tree data in a normalized basic form
	 * @param resourcePath the fault tree to load
	 * @return loaded fault tree in a normalized basic form
	 * @throws IOException 
	 */
	public FaultTreeNode createBasicDFT(String resourcePath) throws IOException {
		Fault root = createDFT(resourcePath);
		DFT2BasicDFTConverter dft2BasicDFTConverter = new DFT2BasicDFTConverter();
		return dft2BasicDFTConverter.convert(root).getRoot();
	}
}
