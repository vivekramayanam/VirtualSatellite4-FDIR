/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.model;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.fdir.core.markov.modelchecker.ModelCheckingResult;
import de.dlr.sc.virsat.fdir.core.metrics.PointAvailability;
import de.dlr.sc.virsat.fdir.core.metrics.SteadyStateAvailability;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.fdir.evaluator.FaultTreeEvaluator;
import de.dlr.sc.virsat.model.extension.fdir.recovery.RecoveryStrategy;

// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * 
 * 
 */
public class AvailabilityAnalysis extends AAvailabilityAnalysis {
	private static final double EPS = 0.0001;

	/**
	 * Constructor of Concept Class
	 */
	public AvailabilityAnalysis() {
		super();
	}

	/**
	 * Constructor of Concept Class which will instantiate a CategoryAssignment in
	 * the background from the given concept
	 * 
	 * @param concept
	 *            the concept where it will find the correct Category to instantiate
	 *            from
	 */
	public AvailabilityAnalysis(Concept concept) {
		super(concept);
	}

	/**
	 * Constructor of Concept Class that can be initialized manually by a given
	 * Category Assignment
	 * 
	 * @param categoryAssignment
	 *            The category Assignment to be used for background initialization
	 *            of the Category bean
	 */
	public AvailabilityAnalysis(CategoryAssignment categoryAssignment) {
		super(categoryAssignment);
	}

	/**
	 * Gets the first fault attached to the same structural element instance
	 * 
	 * @return the top level fault to be analysed
	 */
	public Fault getFault() {
		IBeanStructuralElementInstance parent = new BeanStructuralElementInstance(
				(StructuralElementInstance) getTypeInstance().eContainer());
		return parent.getFirst(Fault.class);
	}

	/**
	 * 
	 * @param ed
	 *            the editing domain
	 * @param monitor
	 *            the progress monitor
	 * @return a command that sets the availability analysis
	 */
	public Command perform(TransactionalEditingDomain ed, IProgressMonitor monitor) {
		FaultTreeNode fault = getFault();
		
		if (fault == null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		monitor.setTaskName("Availability Analysis");
		final int COUNT_TASKS = 3;
		SubMonitor subMonitor = SubMonitor.convert(monitor, COUNT_TASKS);
		subMonitor.split(1);
		subMonitor.setTaskName("Creating Data Model");
		
		double delta = getTimestepBean().getValueToBaseUnit();

		IBeanStructuralElementInstance parent = new BeanStructuralElementInstance(
				(StructuralElementInstance) getTypeInstance().eContainer());
		RecoveryAutomaton ra = parent.getFirst(RecoveryAutomaton.class);

		FaultTreeEvaluator ftEvaluator = FaultTreeEvaluator.createDefaultFaultTreeEvaluator(ra != null, delta, EPS);
		if (ra != null) {
			ftEvaluator.setRecoveryStrategy(new RecoveryStrategy(ra));
		}
		
		double maxTime = getRemainingMissionTimeBean().getValueToBaseUnit();
		if (monitor.isCanceled()) {
			return UnexecutableCommand.INSTANCE;
		}
		subMonitor.split(1);
		subMonitor.setTaskName("Performing Model Checking");
		
		ModelCheckingResult result = ftEvaluator
				.evaluateFaultTree(fault, new PointAvailability(maxTime), SteadyStateAvailability.STEADY_STATE_AVAILABILITY);
		
		if (monitor.isCanceled()) {
			return UnexecutableCommand.INSTANCE;
		}
		subMonitor.split(1);
		subMonitor.setTaskName("Updating Results");
		
		double steadyStateAvailability = result.getSteadyStateAvailability();
		return new RecordingCommand(ed, "Availability Analysis") {
			@Override
			protected void doExecute() {
				getSteadyStateAvailabilityBean().setValueAsBaseUnit(steadyStateAvailability);
				if (result.getPointAvailability() != null) {
					getPointAvailabilityCurve().clear();
					for (int i = 0; i < result.getPointAvailability().size(); ++i) {
						createNewAvailabilityCurveEntry(result.getPointAvailability().get(i));
					}
				}
			}
		};
	}

	/**
	 * Creates a new entry for the reliability curve
	 * 
	 * @param value
	 *            the new entry in the reliability curve
	 */
	private void createNewAvailabilityCurveEntry(double value) {
		CategoryInstantiator ci = new CategoryInstantiator();
		APropertyInstance pi = ci.generateInstance(getPointAvailabilityCurve().getArrayInstance());
		BeanPropertyFloat newBeanProperty = new BeanPropertyFloat();
		newBeanProperty.setTypeInstance((UnitValuePropertyInstance) pi);
		newBeanProperty.setValueAsBaseUnit(value);
		getPointAvailabilityCurve().add(newBeanProperty);
	}
}
