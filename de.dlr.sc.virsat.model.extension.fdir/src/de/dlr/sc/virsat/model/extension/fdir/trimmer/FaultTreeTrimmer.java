/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.trimmer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import de.dlr.sc.virsat.model.extension.fdir.model.Fault;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTree;
import de.dlr.sc.virsat.model.extension.fdir.model.FaultTreeNode;
import de.dlr.sc.virsat.model.extension.fdir.modularizer.Module;
import de.dlr.sc.virsat.model.extension.fdir.util.FaultTreeHelper;

/**
 * Class for trimming fault trees
 * @author jord_ad
 *
 */
public class FaultTreeTrimmer {

	/**
	 * Trim a fault tree
	 * @param ftNode the fault tree node
	 * @return the trimmed fault tree
	 */
	public FaultTree trimFaultTree(FaultTreeNode ftNode) {
		if (ftNode == null) {
			return null;
		}
		
		FaultTreeHelper ftHelp = new FaultTreeHelper(ftNode.getConcept());
		FaultTree trimmedTree = new FaultTree(ftNode.getConcept());
		
		Stack<FaultTreeNode> stack = new Stack<FaultTreeNode>();
		stack.push(ftNode);
		
		while (!stack.isEmpty()) {
			FaultTreeNode curr = stack.pop();
			List<FaultTreeNode> children = ftHelp.getAllChildren(curr, curr.getFault().getFaultTree());
			
			for (FaultTreeNode child : children) {
				Fault childCopy = ftHelp.copyFault(child.getFault());
			}
		}
		
		return null;
	}
	
	/**
	 * Trim a set of modules from a modularized fault tree
	 * @param modules the modules
	 * @return the trimmed set of modules
	 */
	public Set<Module> trimModules(Set<Module> modules) {
		if (modules == null || modules.isEmpty()) {
			return modules;
		}
		
		Set<Module> trimmedModules = trimDeterministicModules(modules);
		return trimmedModules;
	}
	
	/**
	 * Trim the static modules from a set of modules
	 * @param modules the set of modules
	 * @return the dynamic modules
	 */
	private static Set<Module> trimDeterministicModules(Set<Module> modules) {
		Set<Module> nondeterministicModules = new HashSet<Module>();
		for (Module module : modules) {
			if (module.isNondeterministic()) {
				nondeterministicModules.add(module);
			}
		}
		return nondeterministicModules;
	}
	
}
