/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.experiments.aiaa;

import java.io.IOException;

import org.junit.Test;

import de.dlr.sc.virsat.model.extension.fdir.evaluator.DFTEvaluator;
import de.dlr.sc.virsat.model.extension.fdir.evaluator.FaultTreeEvaluator;
import de.dlr.sc.virsat.model.extension.fdir.experiments.ASynthesizerExperiment;
import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNodeType;
import de.dlr.sc.virsat.model.extension.fdir.model.RecoveryAutomaton;
import de.dlr.sc.virsat.model.extension.fdir.recovery.RecoveryStrategy;
import de.dlr.sc.virsat.model.extension.fdir.recovery.minimizer.ComposedMinimizer;
import de.dlr.sc.virsat.model.extension.fdir.synthesizer.BasicSynthesizer;

/**
 * This class produces the experimental data for the Journal of Aerospace Information Systems (JAI AIAA)
 * in 2018.
 * @author muel_s8
 *
 */

public class AIAA2018Experiments extends ASynthesizerExperiment {
	
	@Test
	public void experimentMemory2WithFDEP() throws IOException {
		System.out.println("--------------------- Experiment: Memory2 With FDEP  ---------------------");
		Fault tle = createGalileoDFT("/resources/aiaa/2018/memory2WithFDEP.dft");
		
		final float DELTA = 0.01f;
		
		FaultTreeEvaluator dftEvaluator = FaultTreeEvaluator.createDefaultFaultTreeEvaluator(false, DELTA, FaultTreeEvaluator.DEFAULT_EPS);
		dftEvaluator.evaluateFaultTree(tle);
		
		System.out.println("--------------------- Evaluation results for Memory2WithFDEP with DFT ---------------------");
		printResults(dftEvaluator, DELTA);
		
		BasicSynthesizer synthesizer = new BasicSynthesizer();
		synthesizer.setMinimizer(null);
		RecoveryAutomaton ra = synthesizer.synthesize(tle);
		saveRA(ra, "aiaa/2018/memory2WithFDEP/synthesized");
		synthesizer = new BasicSynthesizer();
		ra = synthesizer.synthesize(tle);
		saveRA(ra, "aiaa/2018/memory2WithFDEP/synthesizedMinimized");
		
		FaultTreeEvaluator ndDFTftEvaluator = FaultTreeEvaluator.createDefaultFaultTreeEvaluator(true, DELTA, FaultTreeEvaluator.DEFAULT_EPS);
		ndDFTftEvaluator.setRecoveryStrategy(new RecoveryStrategy(ra));
		ndDFTftEvaluator.evaluateFaultTree(tle);
		
		System.out.println("--------------------- Evaluation results for Memory2WithFDEP with NdDFT -----------------------");
		System.out.println("RA #States: " +  ra.getStates().size());
		System.out.println(ra.toDot());
		printResults(ndDFTftEvaluator, DELTA);
	}
	
	@Test
	public void experimentMemory2AndNBackups() {
		System.out.println("--------------------- Experiment: Memory2 and N Backups  ---------------------");
		Fault tle = new Fault(concept);
		
		FaultTreeNode or = ftHelper.createGate(tle, FaultTreeNodeType.OR);
		FaultTreeNode spare1 = ftHelper.createGate(tle, FaultTreeNodeType.SPARE);
		spare1.setName("SPARE1");
		FaultTreeNode spare2 = ftHelper.createGate(tle, FaultTreeNodeType.SPARE);
		spare2.setName("SPARE2");
		final float FAILURE_RATE = 1f;
		Fault memory1 = ftHelper.createBasicFault("B1", FAILURE_RATE);
		Fault memory2 = ftHelper.createBasicFault("B2", FAILURE_RATE);
		
		ftHelper.connect(tle, or, tle);
		ftHelper.connect(tle, spare1, or);
		ftHelper.connect(tle, spare2, or);

		ftHelper.connect(tle, memory1, spare1);
		ftHelper.connect(tle, memory2, spare2);
		
		final float DELTA = 0.01f;
		final int MAX_BACKUPS = 10;
		
		for (int i = 1; i <= MAX_BACKUPS; ++i) {
			Fault backup = ftHelper.createBasicFault("B" + (i + 2), FAILURE_RATE);
			ftHelper.connectSpare(tle, backup, spare1);
			ftHelper.connectSpare(tle, backup, spare2);
			
			BasicSynthesizer synthesizer = new BasicSynthesizer();
			synthesizer.setMinimizer(new ComposedMinimizer());
			RecoveryAutomaton ra = synthesizer.synthesize(tle);
			
			FaultTreeEvaluator ndDFTftEvaluator = FaultTreeEvaluator.createDefaultFaultTreeEvaluator(true, DELTA, FaultTreeEvaluator.DEFAULT_EPS);
			ndDFTftEvaluator.setRecoveryStrategy(new RecoveryStrategy(ra));
			ndDFTftEvaluator.evaluateFaultTree(tle);
			int statesMc = ((DFTEvaluator) ndDFTftEvaluator.getEvaluator()).getMc().getStates().size();
			System.out.println(i + " " + statesMc);
		}
	}
}
