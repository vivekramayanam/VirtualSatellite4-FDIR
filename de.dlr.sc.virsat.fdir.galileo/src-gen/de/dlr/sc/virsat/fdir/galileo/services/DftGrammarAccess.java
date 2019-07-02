/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.galileo.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class DftGrammarAccess extends AbstractGrammarElementFinder {
	
	public class GalileoDftElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoDft");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cToplevelKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRootAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cRootGalileoFaultTreeNodeCrossReference_1_0 = (CrossReference)cRootAssignment_1.eContents().get(0);
		private final RuleCall cRootGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1 = (RuleCall)cRootGalileoFaultTreeNodeCrossReference_1_0.eContents().get(1);
		private final Keyword cSemicolonKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Alternatives cAlternatives_3 = (Alternatives)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cAlternatives_3.eContents().get(0);
		private final Assignment cGatesAssignment_3_0_0 = (Assignment)cGroup_3_0.eContents().get(0);
		private final RuleCall cGatesGalileoGateParserRuleCall_3_0_0_0 = (RuleCall)cGatesAssignment_3_0_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_0_1 = (Keyword)cGroup_3_0.eContents().get(1);
		private final Group cGroup_3_1 = (Group)cAlternatives_3.eContents().get(1);
		private final Assignment cBasicEventsAssignment_3_1_0 = (Assignment)cGroup_3_1.eContents().get(0);
		private final RuleCall cBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0 = (RuleCall)cBasicEventsAssignment_3_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1_1 = (Keyword)cGroup_3_1.eContents().get(1);
		
		//GalileoDft:
		//	'toplevel' root=[GalileoFaultTreeNode|STRING] ';' (gates+=GalileoGate ';' | basicEvents+=GalileoBasicEvent ';')*;
		@Override public ParserRule getRule() { return rule; }
		
		//'toplevel' root=[GalileoFaultTreeNode|STRING] ';' (gates+=GalileoGate ';' | basicEvents+=GalileoBasicEvent ';')*
		public Group getGroup() { return cGroup; }
		
		//'toplevel'
		public Keyword getToplevelKeyword_0() { return cToplevelKeyword_0; }
		
		//root=[GalileoFaultTreeNode|STRING]
		public Assignment getRootAssignment_1() { return cRootAssignment_1; }
		
		//[GalileoFaultTreeNode|STRING]
		public CrossReference getRootGalileoFaultTreeNodeCrossReference_1_0() { return cRootGalileoFaultTreeNodeCrossReference_1_0; }
		
		//STRING
		public RuleCall getRootGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1() { return cRootGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1; }
		
		//';'
		public Keyword getSemicolonKeyword_2() { return cSemicolonKeyword_2; }
		
		//(gates+=GalileoGate ';' | basicEvents+=GalileoBasicEvent ';')*
		public Alternatives getAlternatives_3() { return cAlternatives_3; }
		
		//gates+=GalileoGate ';'
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//gates+=GalileoGate
		public Assignment getGatesAssignment_3_0_0() { return cGatesAssignment_3_0_0; }
		
		//GalileoGate
		public RuleCall getGatesGalileoGateParserRuleCall_3_0_0_0() { return cGatesGalileoGateParserRuleCall_3_0_0_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3_0_1() { return cSemicolonKeyword_3_0_1; }
		
		//basicEvents+=GalileoBasicEvent ';'
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//basicEvents+=GalileoBasicEvent
		public Assignment getBasicEventsAssignment_3_1_0() { return cBasicEventsAssignment_3_1_0; }
		
		//GalileoBasicEvent
		public RuleCall getBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0() { return cBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3_1_1() { return cSemicolonKeyword_3_1_1; }
	}
	public class GalileoGateElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoGate");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameSTRINGTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Assignment cTypeAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cTypeGalileoNodeTypeParserRuleCall_1_0 = (RuleCall)cTypeAssignment_1.eContents().get(0);
		private final Assignment cChildrenAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cChildrenGalileoFaultTreeNodeCrossReference_2_0 = (CrossReference)cChildrenAssignment_2.eContents().get(0);
		private final RuleCall cChildrenGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1 = (RuleCall)cChildrenGalileoFaultTreeNodeCrossReference_2_0.eContents().get(1);
		
		//GalileoGate GalileoFaultTreeNode:
		//	name=STRING type=GalileoNodeType children+=[GalileoFaultTreeNode|STRING]*;
		@Override public ParserRule getRule() { return rule; }
		
		//name=STRING type=GalileoNodeType children+=[GalileoFaultTreeNode|STRING]*
		public Group getGroup() { return cGroup; }
		
		//name=STRING
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//STRING
		public RuleCall getNameSTRINGTerminalRuleCall_0_0() { return cNameSTRINGTerminalRuleCall_0_0; }
		
		//type=GalileoNodeType
		public Assignment getTypeAssignment_1() { return cTypeAssignment_1; }
		
		//GalileoNodeType
		public RuleCall getTypeGalileoNodeTypeParserRuleCall_1_0() { return cTypeGalileoNodeTypeParserRuleCall_1_0; }
		
		//children+=[GalileoFaultTreeNode|STRING]*
		public Assignment getChildrenAssignment_2() { return cChildrenAssignment_2; }
		
		//[GalileoFaultTreeNode|STRING]
		public CrossReference getChildrenGalileoFaultTreeNodeCrossReference_2_0() { return cChildrenGalileoFaultTreeNodeCrossReference_2_0; }
		
		//STRING
		public RuleCall getChildrenGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1() { return cChildrenGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1; }
	}
	public class GalileoBasicEventElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoBasicEvent");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameSTRINGTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cLambdaKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cLambdaAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cLambdaFloatParserRuleCall_3_0 = (RuleCall)cLambdaAssignment_3.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cDormKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Keyword cEqualsSignKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final Assignment cDormAssignment_4_2 = (Assignment)cGroup_4.eContents().get(2);
		private final RuleCall cDormFloatParserRuleCall_4_2_0 = (RuleCall)cDormAssignment_4_2.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cRepairKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Keyword cEqualsSignKeyword_5_1 = (Keyword)cGroup_5.eContents().get(1);
		private final Assignment cRepairAssignment_5_2 = (Assignment)cGroup_5.eContents().get(2);
		private final RuleCall cRepairFloatParserRuleCall_5_2_0 = (RuleCall)cRepairAssignment_5_2.eContents().get(0);
		
		//GalileoBasicEvent GalileoFaultTreeNode:
		//	name=STRING 'lambda' '=' lambda=Float ('dorm' '=' dorm=Float)? ('repair' '=' repair=Float)?;
		@Override public ParserRule getRule() { return rule; }
		
		//name=STRING 'lambda' '=' lambda=Float ('dorm' '=' dorm=Float)? ('repair' '=' repair=Float)?
		public Group getGroup() { return cGroup; }
		
		//name=STRING
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//STRING
		public RuleCall getNameSTRINGTerminalRuleCall_0_0() { return cNameSTRINGTerminalRuleCall_0_0; }
		
		//'lambda'
		public Keyword getLambdaKeyword_1() { return cLambdaKeyword_1; }
		
		//'='
		public Keyword getEqualsSignKeyword_2() { return cEqualsSignKeyword_2; }
		
		//lambda=Float
		public Assignment getLambdaAssignment_3() { return cLambdaAssignment_3; }
		
		//Float
		public RuleCall getLambdaFloatParserRuleCall_3_0() { return cLambdaFloatParserRuleCall_3_0; }
		
		//('dorm' '=' dorm=Float)?
		public Group getGroup_4() { return cGroup_4; }
		
		//'dorm'
		public Keyword getDormKeyword_4_0() { return cDormKeyword_4_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_4_1() { return cEqualsSignKeyword_4_1; }
		
		//dorm=Float
		public Assignment getDormAssignment_4_2() { return cDormAssignment_4_2; }
		
		//Float
		public RuleCall getDormFloatParserRuleCall_4_2_0() { return cDormFloatParserRuleCall_4_2_0; }
		
		//('repair' '=' repair=Float)?
		public Group getGroup_5() { return cGroup_5; }
		
		//'repair'
		public Keyword getRepairKeyword_5_0() { return cRepairKeyword_5_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_5_1() { return cEqualsSignKeyword_5_1; }
		
		//repair=Float
		public Assignment getRepairAssignment_5_2() { return cRepairAssignment_5_2; }
		
		//Float
		public RuleCall getRepairFloatParserRuleCall_5_2_0() { return cRepairFloatParserRuleCall_5_2_0; }
	}
	public class GalileoNodeTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoNodeType");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cNamedTypeParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cObserverTypeParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cRDEPTypeParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//GalileoNodeType:
		//	NamedType | ObserverType | RDEPType;
		@Override public ParserRule getRule() { return rule; }
		
		//NamedType | ObserverType | RDEPType
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//NamedType
		public RuleCall getNamedTypeParserRuleCall_0() { return cNamedTypeParserRuleCall_0; }
		
		//ObserverType
		public RuleCall getObserverTypeParserRuleCall_1() { return cObserverTypeParserRuleCall_1; }
		
		//RDEPType
		public RuleCall getRDEPTypeParserRuleCall_2() { return cRDEPTypeParserRuleCall_2; }
	}
	public class NamedTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.NamedType");
		private final Assignment cTypeNameAssignment = (Assignment)rule.eContents().get(1);
		private final Alternatives cTypeNameAlternatives_0 = (Alternatives)cTypeNameAssignment.eContents().get(0);
		private final Keyword cTypeNameAndKeyword_0_0 = (Keyword)cTypeNameAlternatives_0.eContents().get(0);
		private final Keyword cTypeNameOrKeyword_0_1 = (Keyword)cTypeNameAlternatives_0.eContents().get(1);
		private final RuleCall cTypeNameXOFYTerminalRuleCall_0_2 = (RuleCall)cTypeNameAlternatives_0.eContents().get(2);
		private final Keyword cTypeNamePandKeyword_0_3 = (Keyword)cTypeNameAlternatives_0.eContents().get(3);
		private final Keyword cTypeNamePand_iKeyword_0_4 = (Keyword)cTypeNameAlternatives_0.eContents().get(4);
		private final Keyword cTypeNamePorKeyword_0_5 = (Keyword)cTypeNameAlternatives_0.eContents().get(5);
		private final Keyword cTypeNamePor_iKeyword_0_6 = (Keyword)cTypeNameAlternatives_0.eContents().get(6);
		private final Keyword cTypeNameSandKeyword_0_7 = (Keyword)cTypeNameAlternatives_0.eContents().get(7);
		private final Keyword cTypeNameHspKeyword_0_8 = (Keyword)cTypeNameAlternatives_0.eContents().get(8);
		private final Keyword cTypeNameWspKeyword_0_9 = (Keyword)cTypeNameAlternatives_0.eContents().get(9);
		private final Keyword cTypeNameCspKeyword_0_10 = (Keyword)cTypeNameAlternatives_0.eContents().get(10);
		private final Keyword cTypeNameSeqKeyword_0_11 = (Keyword)cTypeNameAlternatives_0.eContents().get(11);
		private final Keyword cTypeNameFdepKeyword_0_12 = (Keyword)cTypeNameAlternatives_0.eContents().get(12);
		
		//NamedType GalileoNodeType:
		//	typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' |
		//	'fdep');
		@Override public ParserRule getRule() { return rule; }
		
		//typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' | 'fdep')
		public Assignment getTypeNameAssignment() { return cTypeNameAssignment; }
		
		//('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' | 'fdep')
		public Alternatives getTypeNameAlternatives_0() { return cTypeNameAlternatives_0; }
		
		//'and'
		public Keyword getTypeNameAndKeyword_0_0() { return cTypeNameAndKeyword_0_0; }
		
		//'or'
		public Keyword getTypeNameOrKeyword_0_1() { return cTypeNameOrKeyword_0_1; }
		
		//XOFY
		public RuleCall getTypeNameXOFYTerminalRuleCall_0_2() { return cTypeNameXOFYTerminalRuleCall_0_2; }
		
		//'pand'
		public Keyword getTypeNamePandKeyword_0_3() { return cTypeNamePandKeyword_0_3; }
		
		//'pand_i'
		public Keyword getTypeNamePand_iKeyword_0_4() { return cTypeNamePand_iKeyword_0_4; }
		
		//'por'
		public Keyword getTypeNamePorKeyword_0_5() { return cTypeNamePorKeyword_0_5; }
		
		//'por_i'
		public Keyword getTypeNamePor_iKeyword_0_6() { return cTypeNamePor_iKeyword_0_6; }
		
		//'sand'
		public Keyword getTypeNameSandKeyword_0_7() { return cTypeNameSandKeyword_0_7; }
		
		//'hsp'
		public Keyword getTypeNameHspKeyword_0_8() { return cTypeNameHspKeyword_0_8; }
		
		//'wsp'
		public Keyword getTypeNameWspKeyword_0_9() { return cTypeNameWspKeyword_0_9; }
		
		//'csp'
		public Keyword getTypeNameCspKeyword_0_10() { return cTypeNameCspKeyword_0_10; }
		
		//'seq'
		public Keyword getTypeNameSeqKeyword_0_11() { return cTypeNameSeqKeyword_0_11; }
		
		//'fdep'
		public Keyword getTypeNameFdepKeyword_0_12() { return cTypeNameFdepKeyword_0_12; }
	}
	public class ObserverTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.ObserverType");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cObserverKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cObservablesAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cObservablesGalileoFaultTreeNodeCrossReference_1_0 = (CrossReference)cObservablesAssignment_1.eContents().get(0);
		private final RuleCall cObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1 = (RuleCall)cObservablesGalileoFaultTreeNodeCrossReference_1_0.eContents().get(1);
		private final Keyword cObsRateKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Keyword cEqualsSignKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cObservationRateAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cObservationRateFloatParserRuleCall_4_0 = (RuleCall)cObservationRateAssignment_4.eContents().get(0);
		
		//ObserverType GalileoNodeType:
		//	'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float;
		@Override public ParserRule getRule() { return rule; }
		
		//'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float
		public Group getGroup() { return cGroup; }
		
		//'observer'
		public Keyword getObserverKeyword_0() { return cObserverKeyword_0; }
		
		//observables+=[GalileoFaultTreeNode|STRING]*
		public Assignment getObservablesAssignment_1() { return cObservablesAssignment_1; }
		
		//[GalileoFaultTreeNode|STRING]
		public CrossReference getObservablesGalileoFaultTreeNodeCrossReference_1_0() { return cObservablesGalileoFaultTreeNodeCrossReference_1_0; }
		
		//STRING
		public RuleCall getObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1() { return cObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1; }
		
		//'obsRate'
		public Keyword getObsRateKeyword_2() { return cObsRateKeyword_2; }
		
		//'='
		public Keyword getEqualsSignKeyword_3() { return cEqualsSignKeyword_3; }
		
		//observationRate=Float
		public Assignment getObservationRateAssignment_4() { return cObservationRateAssignment_4; }
		
		//Float
		public RuleCall getObservationRateFloatParserRuleCall_4_0() { return cObservationRateFloatParserRuleCall_4_0; }
	}
	public class RDEPTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.RDEPType");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cRdepKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cRateFactorKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cRateFactorAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cRateFactorFloatParserRuleCall_2_0 = (RuleCall)cRateFactorAssignment_2.eContents().get(0);
		
		//RDEPType GalileoNodeType:
		//	'rdep' 'rateFactor' rateFactor=Float;
		@Override public ParserRule getRule() { return rule; }
		
		//'rdep' 'rateFactor' rateFactor=Float
		public Group getGroup() { return cGroup; }
		
		//'rdep'
		public Keyword getRdepKeyword_0() { return cRdepKeyword_0; }
		
		//'rateFactor'
		public Keyword getRateFactorKeyword_1() { return cRateFactorKeyword_1; }
		
		//rateFactor=Float
		public Assignment getRateFactorAssignment_2() { return cRateFactorAssignment_2; }
		
		//Float
		public RuleCall getRateFactorFloatParserRuleCall_2_0() { return cRateFactorFloatParserRuleCall_2_0; }
	}
	public class FloatElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.Float");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_2_1 = (RuleCall)cGroup_2.eContents().get(1);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cEKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Keyword cHyphenMinusKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final RuleCall cINTTerminalRuleCall_3_2 = (RuleCall)cGroup_3.eContents().get(2);
		
		//Float:
		//	'-'? INT ('.' INT)? ('e' '-'? INT)?;
		@Override public ParserRule getRule() { return rule; }
		
		//'-'? INT ('.' INT)? ('e' '-'? INT)?
		public Group getGroup() { return cGroup; }
		
		//'-'?
		public Keyword getHyphenMinusKeyword_0() { return cHyphenMinusKeyword_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_1() { return cINTTerminalRuleCall_1; }
		
		//('.' INT)?
		public Group getGroup_2() { return cGroup_2; }
		
		//'.'
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_2_1() { return cINTTerminalRuleCall_2_1; }
		
		//('e' '-'? INT)?
		public Group getGroup_3() { return cGroup_3; }
		
		//'e'
		public Keyword getEKeyword_3_0() { return cEKeyword_3_0; }
		
		//'-'?
		public Keyword getHyphenMinusKeyword_3_1() { return cHyphenMinusKeyword_3_1; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_3_2() { return cINTTerminalRuleCall_3_2; }
	}
	
	
	private final GalileoDftElements pGalileoDft;
	private final GalileoGateElements pGalileoGate;
	private final GalileoBasicEventElements pGalileoBasicEvent;
	private final GalileoNodeTypeElements pGalileoNodeType;
	private final NamedTypeElements pNamedType;
	private final ObserverTypeElements pObserverType;
	private final RDEPTypeElements pRDEPType;
	private final FloatElements pFloat;
	private final TerminalRule tXOFY;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public DftGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pGalileoDft = new GalileoDftElements();
		this.pGalileoGate = new GalileoGateElements();
		this.pGalileoBasicEvent = new GalileoBasicEventElements();
		this.pGalileoNodeType = new GalileoNodeTypeElements();
		this.pNamedType = new NamedTypeElements();
		this.pObserverType = new ObserverTypeElements();
		this.pRDEPType = new RDEPTypeElements();
		this.pFloat = new FloatElements();
		this.tXOFY = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.XOFY");
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("de.dlr.sc.virsat.fdir.galileo.Dft".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//GalileoDft:
	//	'toplevel' root=[GalileoFaultTreeNode|STRING] ';' (gates+=GalileoGate ';' | basicEvents+=GalileoBasicEvent ';')*;
	public GalileoDftElements getGalileoDftAccess() {
		return pGalileoDft;
	}
	
	public ParserRule getGalileoDftRule() {
		return getGalileoDftAccess().getRule();
	}
	
	//GalileoGate GalileoFaultTreeNode:
	//	name=STRING type=GalileoNodeType children+=[GalileoFaultTreeNode|STRING]*;
	public GalileoGateElements getGalileoGateAccess() {
		return pGalileoGate;
	}
	
	public ParserRule getGalileoGateRule() {
		return getGalileoGateAccess().getRule();
	}
	
	//GalileoBasicEvent GalileoFaultTreeNode:
	//	name=STRING 'lambda' '=' lambda=Float ('dorm' '=' dorm=Float)? ('repair' '=' repair=Float)?;
	public GalileoBasicEventElements getGalileoBasicEventAccess() {
		return pGalileoBasicEvent;
	}
	
	public ParserRule getGalileoBasicEventRule() {
		return getGalileoBasicEventAccess().getRule();
	}
	
	//GalileoNodeType:
	//	NamedType | ObserverType | RDEPType;
	public GalileoNodeTypeElements getGalileoNodeTypeAccess() {
		return pGalileoNodeType;
	}
	
	public ParserRule getGalileoNodeTypeRule() {
		return getGalileoNodeTypeAccess().getRule();
	}
	
	//NamedType GalileoNodeType:
	//	typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' |
	//	'fdep');
	public NamedTypeElements getNamedTypeAccess() {
		return pNamedType;
	}
	
	public ParserRule getNamedTypeRule() {
		return getNamedTypeAccess().getRule();
	}
	
	//ObserverType GalileoNodeType:
	//	'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float;
	public ObserverTypeElements getObserverTypeAccess() {
		return pObserverType;
	}
	
	public ParserRule getObserverTypeRule() {
		return getObserverTypeAccess().getRule();
	}
	
	//RDEPType GalileoNodeType:
	//	'rdep' 'rateFactor' rateFactor=Float;
	public RDEPTypeElements getRDEPTypeAccess() {
		return pRDEPType;
	}
	
	public ParserRule getRDEPTypeRule() {
		return getRDEPTypeAccess().getRule();
	}
	
	//Float:
	//	'-'? INT ('.' INT)? ('e' '-'? INT)?;
	public FloatElements getFloatAccess() {
		return pFloat;
	}
	
	public ParserRule getFloatRule() {
		return getFloatAccess().getRule();
	}
	
	//terminal XOFY:
	//	INT 'of' INT;
	public TerminalRule getXOFYRule() {
		return tXOFY;
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' | "'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
