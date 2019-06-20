/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.galileo.serializer;

import com.google.inject.Inject;
import de.dlr.sc.virsat.fdir.galileo.dft.DftPackage;
import de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft;
import de.dlr.sc.virsat.fdir.galileo.dft.GalileoFaultTreeNode;
import de.dlr.sc.virsat.fdir.galileo.dft.GalileoNodeType;
import de.dlr.sc.virsat.fdir.galileo.services.DftGrammarAccess;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class DftSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private DftGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == DftPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case DftPackage.GALILEO_DFT:
				sequence_GalileoDft(context, (GalileoDft) semanticObject); 
				return; 
			case DftPackage.GALILEO_FAULT_TREE_NODE:
				if (rule == grammarAccess.getGalileoBasicEventRule()) {
					sequence_GalileoBasicEvent(context, (GalileoFaultTreeNode) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getGalileoGateRule()) {
					sequence_GalileoGate(context, (GalileoFaultTreeNode) semanticObject); 
					return; 
				}
				else break;
			case DftPackage.GALILEO_NODE_TYPE:
				if (rule == grammarAccess.getNamedTypeRule()) {
					sequence_NamedType(context, (GalileoNodeType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getGalileoNodeTypeRule()) {
					sequence_NamedType_ObserverType_RDEPType(context, (GalileoNodeType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getObserverTypeRule()) {
					sequence_ObserverType(context, (GalileoNodeType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getRDEPTypeRule()) {
					sequence_RDEPType(context, (GalileoNodeType) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     GalileoBasicEvent returns GalileoFaultTreeNode
	 *
	 * Constraint:
	 *     (name=STRING lambda=Float dorm=Float? repair=Float?)
	 */
	protected void sequence_GalileoBasicEvent(ISerializationContext context, GalileoFaultTreeNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     GalileoDft returns GalileoDft
	 *
	 * Constraint:
	 *     (root=[GalileoFaultTreeNode|STRING] (gates+=GalileoGate | basicEvents+=GalileoBasicEvent)*)
	 */
	protected void sequence_GalileoDft(ISerializationContext context, GalileoDft semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     GalileoGate returns GalileoFaultTreeNode
	 *
	 * Constraint:
	 *     (name=STRING type=GalileoNodeType children+=[GalileoFaultTreeNode|STRING]*)
	 */
	protected void sequence_GalileoGate(ISerializationContext context, GalileoFaultTreeNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     NamedType returns GalileoNodeType
	 *
	 * Constraint:
	 *     (
	 *         typeName='and' | 
	 *         typeName='or' | 
	 *         typeName=XOFY | 
	 *         typeName='pand' | 
	 *         typeName='pand_i' | 
	 *         typeName='por' | 
	 *         typeName='por_i' | 
	 *         typeName='sand' | 
	 *         typeName='hsp' | 
	 *         typeName='wsp' | 
	 *         typeName='csp' | 
	 *         typeName='seq' | 
	 *         typeName='fdep'
	 *     )
	 */
	protected void sequence_NamedType(ISerializationContext context, GalileoNodeType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     GalileoNodeType returns GalileoNodeType
	 *
	 * Constraint:
	 *     (
	 *         typeName='and' | 
	 *         typeName='or' | 
	 *         typeName=XOFY | 
	 *         typeName='pand' | 
	 *         typeName='pand_i' | 
	 *         typeName='por' | 
	 *         typeName='por_i' | 
	 *         typeName='sand' | 
	 *         typeName='hsp' | 
	 *         typeName='wsp' | 
	 *         typeName='csp' | 
	 *         typeName='seq' | 
	 *         typeName='fdep' | 
	 *         (observables+=[GalileoFaultTreeNode|STRING]* observationRate=Float) | 
	 *         rateFactor=Float
	 *     )
	 */
	protected void sequence_NamedType_ObserverType_RDEPType(ISerializationContext context, GalileoNodeType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ObserverType returns GalileoNodeType
	 *
	 * Constraint:
	 *     (observables+=[GalileoFaultTreeNode|STRING]* observationRate=Float)
	 */
	protected void sequence_ObserverType(ISerializationContext context, GalileoNodeType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     RDEPType returns GalileoNodeType
	 *
	 * Constraint:
	 *     rateFactor=Float
	 */
	protected void sequence_RDEPType(ISerializationContext context, GalileoNodeType semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, DftPackage.Literals.GALILEO_NODE_TYPE__RATE_FACTOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, DftPackage.Literals.GALILEO_NODE_TYPE__RATE_FACTOR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRDEPTypeAccess().getRateFactorFloatParserRuleCall_2_0(), semanticObject.getRateFactor());
		feeder.finish();
	}
	
	
}
