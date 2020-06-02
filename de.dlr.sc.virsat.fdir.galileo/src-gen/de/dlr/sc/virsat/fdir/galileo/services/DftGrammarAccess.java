/*
 * generated by Xtext 2.18.0.M3
 */
package de.dlr.sc.virsat.fdir.galileo.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
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
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cLambdaKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_0_1 = (Keyword)cGroup_1_0.eContents().get(1);
		private final Assignment cLambdaAssignment_1_0_2 = (Assignment)cGroup_1_0.eContents().get(2);
		private final RuleCall cLambdaFloatParserRuleCall_1_0_2_0 = (RuleCall)cLambdaAssignment_1_0_2.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final Keyword cProbKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1_1 = (Keyword)cGroup_1_1.eContents().get(1);
		private final Assignment cProbAssignment_1_1_2 = (Assignment)cGroup_1_1.eContents().get(2);
		private final RuleCall cProbFloatParserRuleCall_1_1_2_0 = (RuleCall)cProbAssignment_1_1_2.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDormKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDormAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDormFloatParserRuleCall_2_2_0 = (RuleCall)cDormAssignment_2_2.eContents().get(0);
		private final Assignment cRepairActionsAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRepairActionsGalileoRepairActionParserRuleCall_3_0 = (RuleCall)cRepairActionsAssignment_3.eContents().get(0);
		
		//GalileoBasicEvent GalileoFaultTreeNode:
		//	name=STRING ('lambda' '=' lambda=Float | 'prob' '=' prob=Float) ('dorm' '=' dorm=Float)?
		//	repairActions+=GalileoRepairAction*;
		@Override public ParserRule getRule() { return rule; }
		
		//name=STRING ('lambda' '=' lambda=Float | 'prob' '=' prob=Float) ('dorm' '=' dorm=Float)?
		//repairActions+=GalileoRepairAction*
		public Group getGroup() { return cGroup; }
		
		//name=STRING
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//STRING
		public RuleCall getNameSTRINGTerminalRuleCall_0_0() { return cNameSTRINGTerminalRuleCall_0_0; }
		
		//'lambda' '=' lambda=Float | 'prob' '=' prob=Float
		public Alternatives getAlternatives_1() { return cAlternatives_1; }
		
		//'lambda' '=' lambda=Float
		public Group getGroup_1_0() { return cGroup_1_0; }
		
		//'lambda'
		public Keyword getLambdaKeyword_1_0_0() { return cLambdaKeyword_1_0_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1_0_1() { return cEqualsSignKeyword_1_0_1; }
		
		//lambda=Float
		public Assignment getLambdaAssignment_1_0_2() { return cLambdaAssignment_1_0_2; }
		
		//Float
		public RuleCall getLambdaFloatParserRuleCall_1_0_2_0() { return cLambdaFloatParserRuleCall_1_0_2_0; }
		
		//'prob' '=' prob=Float
		public Group getGroup_1_1() { return cGroup_1_1; }
		
		//'prob'
		public Keyword getProbKeyword_1_1_0() { return cProbKeyword_1_1_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1_1_1() { return cEqualsSignKeyword_1_1_1; }
		
		//prob=Float
		public Assignment getProbAssignment_1_1_2() { return cProbAssignment_1_1_2; }
		
		//Float
		public RuleCall getProbFloatParserRuleCall_1_1_2_0() { return cProbFloatParserRuleCall_1_1_2_0; }
		
		//('dorm' '=' dorm=Float)?
		public Group getGroup_2() { return cGroup_2; }
		
		//'dorm'
		public Keyword getDormKeyword_2_0() { return cDormKeyword_2_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }
		
		//dorm=Float
		public Assignment getDormAssignment_2_2() { return cDormAssignment_2_2; }
		
		//Float
		public RuleCall getDormFloatParserRuleCall_2_2_0() { return cDormFloatParserRuleCall_2_2_0; }
		
		//repairActions+=GalileoRepairAction*
		public Assignment getRepairActionsAssignment_3() { return cRepairActionsAssignment_3; }
		
		//GalileoRepairAction
		public RuleCall getRepairActionsGalileoRepairActionParserRuleCall_3_0() { return cRepairActionsGalileoRepairActionParserRuleCall_3_0; }
	}
	public class GalileoRepairActionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoRepairAction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cRepairKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cRepairAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cRepairFloatParserRuleCall_2_0 = (RuleCall)cRepairAssignment_2.eContents().get(0);
		private final Assignment cNameAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cNameSTRINGTerminalRuleCall_3_0 = (RuleCall)cNameAssignment_3.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cObservationsKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cObservartionsAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final CrossReference cObservartionsGalileoFaultTreeNodeCrossReference_4_1_0 = (CrossReference)cObservartionsAssignment_4_1.eContents().get(0);
		private final RuleCall cObservartionsGalileoFaultTreeNodeSTRINGTerminalRuleCall_4_1_0_1 = (RuleCall)cObservartionsGalileoFaultTreeNodeCrossReference_4_1_0.eContents().get(1);
		
		//GalileoRepairAction:
		//	'repair' '=' repair=Float name=STRING? ('observations' observartions+=[GalileoFaultTreeNode|STRING]*)?;
		@Override public ParserRule getRule() { return rule; }
		
		//'repair' '=' repair=Float name=STRING? ('observations' observartions+=[GalileoFaultTreeNode|STRING]*)?
		public Group getGroup() { return cGroup; }
		
		//'repair'
		public Keyword getRepairKeyword_0() { return cRepairKeyword_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//repair=Float
		public Assignment getRepairAssignment_2() { return cRepairAssignment_2; }
		
		//Float
		public RuleCall getRepairFloatParserRuleCall_2_0() { return cRepairFloatParserRuleCall_2_0; }
		
		//name=STRING?
		public Assignment getNameAssignment_3() { return cNameAssignment_3; }
		
		//STRING
		public RuleCall getNameSTRINGTerminalRuleCall_3_0() { return cNameSTRINGTerminalRuleCall_3_0; }
		
		//('observations' observartions+=[GalileoFaultTreeNode|STRING]*)?
		public Group getGroup_4() { return cGroup_4; }
		
		//'observations'
		public Keyword getObservationsKeyword_4_0() { return cObservationsKeyword_4_0; }
		
		//observartions+=[GalileoFaultTreeNode|STRING]*
		public Assignment getObservartionsAssignment_4_1() { return cObservartionsAssignment_4_1; }
		
		//[GalileoFaultTreeNode|STRING]
		public CrossReference getObservartionsGalileoFaultTreeNodeCrossReference_4_1_0() { return cObservartionsGalileoFaultTreeNodeCrossReference_4_1_0; }
		
		//STRING
		public RuleCall getObservartionsGalileoFaultTreeNodeSTRINGTerminalRuleCall_4_1_0_1() { return cObservartionsGalileoFaultTreeNodeSTRINGTerminalRuleCall_4_1_0_1; }
	}
	public class GalileoNodeTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.GalileoNodeType");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cNamedParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cParametrizedParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cObserverParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//GalileoNodeType:
		//	Named | Parametrized | Observer;
		@Override public ParserRule getRule() { return rule; }
		
		//Named | Parametrized | Observer
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//Named
		public RuleCall getNamedParserRuleCall_0() { return cNamedParserRuleCall_0; }
		
		//Parametrized
		public RuleCall getParametrizedParserRuleCall_1() { return cParametrizedParserRuleCall_1; }
		
		//Observer
		public RuleCall getObserverParserRuleCall_2() { return cObserverParserRuleCall_2; }
	}
	public class NamedElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.Named");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cNamedAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cTypeNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Alternatives cTypeNameAlternatives_1_0 = (Alternatives)cTypeNameAssignment_1.eContents().get(0);
		private final Keyword cTypeNameAndKeyword_1_0_0 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(0);
		private final Keyword cTypeNameOrKeyword_1_0_1 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(1);
		private final RuleCall cTypeNameXOFYTerminalRuleCall_1_0_2 = (RuleCall)cTypeNameAlternatives_1_0.eContents().get(2);
		private final Keyword cTypeNamePandKeyword_1_0_3 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(3);
		private final Keyword cTypeNamePand_iKeyword_1_0_4 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(4);
		private final Keyword cTypeNamePorKeyword_1_0_5 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(5);
		private final Keyword cTypeNamePor_iKeyword_1_0_6 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(6);
		private final Keyword cTypeNameSandKeyword_1_0_7 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(7);
		private final Keyword cTypeNameHspKeyword_1_0_8 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(8);
		private final Keyword cTypeNameWspKeyword_1_0_9 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(9);
		private final Keyword cTypeNameCspKeyword_1_0_10 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(10);
		private final Keyword cTypeNameSeqKeyword_1_0_11 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(11);
		private final Keyword cTypeNameFdepKeyword_1_0_12 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(12);
		
		//Named GalileoNodeType:
		//	{Named} typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq'
		//	| 'fdep');
		@Override public ParserRule getRule() { return rule; }
		
		//{Named} typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' |
		//'fdep')
		public Group getGroup() { return cGroup; }
		
		//{Named}
		public Action getNamedAction_0() { return cNamedAction_0; }
		
		//typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' | 'fdep')
		public Assignment getTypeNameAssignment_1() { return cTypeNameAssignment_1; }
		
		//('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq' | 'fdep')
		public Alternatives getTypeNameAlternatives_1_0() { return cTypeNameAlternatives_1_0; }
		
		//'and'
		public Keyword getTypeNameAndKeyword_1_0_0() { return cTypeNameAndKeyword_1_0_0; }
		
		//'or'
		public Keyword getTypeNameOrKeyword_1_0_1() { return cTypeNameOrKeyword_1_0_1; }
		
		//XOFY
		public RuleCall getTypeNameXOFYTerminalRuleCall_1_0_2() { return cTypeNameXOFYTerminalRuleCall_1_0_2; }
		
		//'pand'
		public Keyword getTypeNamePandKeyword_1_0_3() { return cTypeNamePandKeyword_1_0_3; }
		
		//'pand_i'
		public Keyword getTypeNamePand_iKeyword_1_0_4() { return cTypeNamePand_iKeyword_1_0_4; }
		
		//'por'
		public Keyword getTypeNamePorKeyword_1_0_5() { return cTypeNamePorKeyword_1_0_5; }
		
		//'por_i'
		public Keyword getTypeNamePor_iKeyword_1_0_6() { return cTypeNamePor_iKeyword_1_0_6; }
		
		//'sand'
		public Keyword getTypeNameSandKeyword_1_0_7() { return cTypeNameSandKeyword_1_0_7; }
		
		//'hsp'
		public Keyword getTypeNameHspKeyword_1_0_8() { return cTypeNameHspKeyword_1_0_8; }
		
		//'wsp'
		public Keyword getTypeNameWspKeyword_1_0_9() { return cTypeNameWspKeyword_1_0_9; }
		
		//'csp'
		public Keyword getTypeNameCspKeyword_1_0_10() { return cTypeNameCspKeyword_1_0_10; }
		
		//'seq'
		public Keyword getTypeNameSeqKeyword_1_0_11() { return cTypeNameSeqKeyword_1_0_11; }
		
		//'fdep'
		public Keyword getTypeNameFdepKeyword_1_0_12() { return cTypeNameFdepKeyword_1_0_12; }
	}
	public class ObserverElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.Observer");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cObserverAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cObserverKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cObservablesAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cObservablesGalileoFaultTreeNodeCrossReference_2_0 = (CrossReference)cObservablesAssignment_2.eContents().get(0);
		private final RuleCall cObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1 = (RuleCall)cObservablesGalileoFaultTreeNodeCrossReference_2_0.eContents().get(1);
		private final Keyword cObsRateKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cEqualsSignKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cObservationRateAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cObservationRateFloatParserRuleCall_5_0 = (RuleCall)cObservationRateAssignment_5.eContents().get(0);
		
		//Observer GalileoNodeType:
		//	{Observer} 'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float;
		@Override public ParserRule getRule() { return rule; }
		
		//{Observer} 'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float
		public Group getGroup() { return cGroup; }
		
		//{Observer}
		public Action getObserverAction_0() { return cObserverAction_0; }
		
		//'observer'
		public Keyword getObserverKeyword_1() { return cObserverKeyword_1; }
		
		//observables+=[GalileoFaultTreeNode|STRING]*
		public Assignment getObservablesAssignment_2() { return cObservablesAssignment_2; }
		
		//[GalileoFaultTreeNode|STRING]
		public CrossReference getObservablesGalileoFaultTreeNodeCrossReference_2_0() { return cObservablesGalileoFaultTreeNodeCrossReference_2_0; }
		
		//STRING
		public RuleCall getObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1() { return cObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1; }
		
		//'obsRate'
		public Keyword getObsRateKeyword_3() { return cObsRateKeyword_3; }
		
		//'='
		public Keyword getEqualsSignKeyword_4() { return cEqualsSignKeyword_4; }
		
		//observationRate=Float
		public Assignment getObservationRateAssignment_5() { return cObservationRateAssignment_5; }
		
		//Float
		public RuleCall getObservationRateFloatParserRuleCall_5_0() { return cObservationRateFloatParserRuleCall_5_0; }
	}
	public class ParametrizedElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.fdir.galileo.Dft.Parametrized");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cParametrizedAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cTypeNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Alternatives cTypeNameAlternatives_1_0 = (Alternatives)cTypeNameAssignment_1.eContents().get(0);
		private final Keyword cTypeNameRdepKeyword_1_0_0 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(0);
		private final Keyword cTypeNameDelayKeyword_1_0_1 = (Keyword)cTypeNameAlternatives_1_0.eContents().get(1);
		private final Keyword cEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cParameterAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cParameterFloatParserRuleCall_3_0 = (RuleCall)cParameterAssignment_3.eContents().get(0);
		
		//Parametrized GalileoNodeType:
		//	{Parametrized} typeName=('rdep' | 'delay') '=' parameter=Float;
		@Override public ParserRule getRule() { return rule; }
		
		//{Parametrized} typeName=('rdep' | 'delay') '=' parameter=Float
		public Group getGroup() { return cGroup; }
		
		//{Parametrized}
		public Action getParametrizedAction_0() { return cParametrizedAction_0; }
		
		//typeName=('rdep' | 'delay')
		public Assignment getTypeNameAssignment_1() { return cTypeNameAssignment_1; }
		
		//('rdep' | 'delay')
		public Alternatives getTypeNameAlternatives_1_0() { return cTypeNameAlternatives_1_0; }
		
		//'rdep'
		public Keyword getTypeNameRdepKeyword_1_0_0() { return cTypeNameRdepKeyword_1_0_0; }
		
		//'delay'
		public Keyword getTypeNameDelayKeyword_1_0_1() { return cTypeNameDelayKeyword_1_0_1; }
		
		//'='
		public Keyword getEqualsSignKeyword_2() { return cEqualsSignKeyword_2; }
		
		//parameter=Float
		public Assignment getParameterAssignment_3() { return cParameterAssignment_3; }
		
		//Float
		public RuleCall getParameterFloatParserRuleCall_3_0() { return cParameterFloatParserRuleCall_3_0; }
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
	private final GalileoRepairActionElements pGalileoRepairAction;
	private final GalileoNodeTypeElements pGalileoNodeType;
	private final NamedElements pNamed;
	private final ObserverElements pObserver;
	private final ParametrizedElements pParametrized;
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
		this.pGalileoRepairAction = new GalileoRepairActionElements();
		this.pGalileoNodeType = new GalileoNodeTypeElements();
		this.pNamed = new NamedElements();
		this.pObserver = new ObserverElements();
		this.pParametrized = new ParametrizedElements();
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
	//	name=STRING ('lambda' '=' lambda=Float | 'prob' '=' prob=Float) ('dorm' '=' dorm=Float)?
	//	repairActions+=GalileoRepairAction*;
	public GalileoBasicEventElements getGalileoBasicEventAccess() {
		return pGalileoBasicEvent;
	}
	
	public ParserRule getGalileoBasicEventRule() {
		return getGalileoBasicEventAccess().getRule();
	}
	
	//GalileoRepairAction:
	//	'repair' '=' repair=Float name=STRING? ('observations' observartions+=[GalileoFaultTreeNode|STRING]*)?;
	public GalileoRepairActionElements getGalileoRepairActionAccess() {
		return pGalileoRepairAction;
	}
	
	public ParserRule getGalileoRepairActionRule() {
		return getGalileoRepairActionAccess().getRule();
	}
	
	//GalileoNodeType:
	//	Named | Parametrized | Observer;
	public GalileoNodeTypeElements getGalileoNodeTypeAccess() {
		return pGalileoNodeType;
	}
	
	public ParserRule getGalileoNodeTypeRule() {
		return getGalileoNodeTypeAccess().getRule();
	}
	
	//Named GalileoNodeType:
	//	{Named} typeName=('and' | 'or' | XOFY | 'pand' | 'pand_i' | 'por' | 'por_i' | 'sand' | 'hsp' | 'wsp' | 'csp' | 'seq'
	//	| 'fdep');
	public NamedElements getNamedAccess() {
		return pNamed;
	}
	
	public ParserRule getNamedRule() {
		return getNamedAccess().getRule();
	}
	
	//Observer GalileoNodeType:
	//	{Observer} 'observer' observables+=[GalileoFaultTreeNode|STRING]* 'obsRate' '=' observationRate=Float;
	public ObserverElements getObserverAccess() {
		return pObserver;
	}
	
	public ParserRule getObserverRule() {
		return getObserverAccess().getRule();
	}
	
	//Parametrized GalileoNodeType:
	//	{Parametrized} typeName=('rdep' | 'delay') '=' parameter=Float;
	public ParametrizedElements getParametrizedAccess() {
		return pParametrized;
	}
	
	public ParserRule getParametrizedRule() {
		return getParametrizedAccess().getRule();
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
