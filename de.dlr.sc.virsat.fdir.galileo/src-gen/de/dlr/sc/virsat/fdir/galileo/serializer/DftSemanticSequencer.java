/*
 * generated by Xtext 2.18.0.M3
 */
package de.dlr.sc.virsat.fdir.galileo.serializer;

import com.google.inject.Inject;
import de.dlr.sc.virsat.fdir.galileo.dft.DftPackage;
import de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft;
import de.dlr.sc.virsat.fdir.galileo.dft.GalileoFaultTreeNode;
import de.dlr.sc.virsat.fdir.galileo.dft.Named;
import de.dlr.sc.virsat.fdir.galileo.dft.Observer;
import de.dlr.sc.virsat.fdir.galileo.dft.Parametrized;
import de.dlr.sc.virsat.fdir.galileo.services.DftGrammarAccess;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;

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
			case DftPackage.NAMED:
				sequence_Named(context, (Named) semanticObject); 
				return; 
			case DftPackage.OBSERVER:
				sequence_Observer(context, (Observer) semanticObject); 
				return; 
			case DftPackage.PARAMETRIZED:
				sequence_Parametrized(context, (Parametrized) semanticObject); 
				return; 
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
	 *     GalileoNodeType returns Named
	 *     Named returns Named
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
	protected void sequence_Named(ISerializationContext context, Named semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     GalileoNodeType returns Observer
	 *     Observer returns Observer
	 *
	 * Constraint:
	 *     (observables+=[GalileoFaultTreeNode|STRING]* observationRate=Float)
	 */
	protected void sequence_Observer(ISerializationContext context, Observer semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     GalileoNodeType returns Parametrized
	 *     Parametrized returns Parametrized
	 *
	 * Constraint:
	 *     ((typeName='rdep' | typeName='delay') parameter=Float)
	 */
	protected void sequence_Parametrized(ISerializationContext context, Parametrized semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
