/*
 * generated by Xtext 2.18.0.M3
 */
grammar InternalDft;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package de.dlr.sc.virsat.fdir.galileo.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.fdir.galileo.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.dlr.sc.virsat.fdir.galileo.services.DftGrammarAccess;

}

@parser::members {

 	private DftGrammarAccess grammarAccess;

    public InternalDftParser(TokenStream input, DftGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "GalileoDft";
   	}

   	@Override
   	protected DftGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleGalileoDft
entryRuleGalileoDft returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGalileoDftRule()); }
	iv_ruleGalileoDft=ruleGalileoDft
	{ $current=$iv_ruleGalileoDft.current; }
	EOF;

// Rule GalileoDft
ruleGalileoDft returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='toplevel'
		{
			newLeafNode(otherlv_0, grammarAccess.getGalileoDftAccess().getToplevelKeyword_0());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGalileoDftRule());
					}
				}
				otherlv_1=RULE_STRING
				{
					newLeafNode(otherlv_1, grammarAccess.getGalileoDftAccess().getRootGalileoFaultTreeNodeCrossReference_1_0());
				}
			)
		)
		otherlv_2=';'
		{
			newLeafNode(otherlv_2, grammarAccess.getGalileoDftAccess().getSemicolonKeyword_2());
		}
		(
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getGalileoDftAccess().getGatesGalileoGateParserRuleCall_3_0_0_0());
						}
						lv_gates_3_0=ruleGalileoGate
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getGalileoDftRule());
							}
							add(
								$current,
								"gates",
								lv_gates_3_0,
								"de.dlr.sc.virsat.fdir.galileo.Dft.GalileoGate");
							afterParserOrEnumRuleCall();
						}
					)
				)
				otherlv_4=';'
				{
					newLeafNode(otherlv_4, grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_0_1());
				}
			)
			    |
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getGalileoDftAccess().getBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0());
						}
						lv_basicEvents_5_0=ruleGalileoBasicEvent
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getGalileoDftRule());
							}
							add(
								$current,
								"basicEvents",
								lv_basicEvents_5_0,
								"de.dlr.sc.virsat.fdir.galileo.Dft.GalileoBasicEvent");
							afterParserOrEnumRuleCall();
						}
					)
				)
				otherlv_6=';'
				{
					newLeafNode(otherlv_6, grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_1_1());
				}
			)
		)*
	)
;

// Entry rule entryRuleGalileoGate
entryRuleGalileoGate returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGalileoGateRule()); }
	iv_ruleGalileoGate=ruleGalileoGate
	{ $current=$iv_ruleGalileoGate.current; }
	EOF;

// Rule GalileoGate
ruleGalileoGate returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_name_0_0=RULE_STRING
				{
					newLeafNode(lv_name_0_0, grammarAccess.getGalileoGateAccess().getNameSTRINGTerminalRuleCall_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGalileoGateRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_0_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getGalileoGateAccess().getTypeGalileoNodeTypeParserRuleCall_1_0());
				}
				lv_type_1_0=ruleGalileoNodeType
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGalileoGateRule());
					}
					set(
						$current,
						"type",
						lv_type_1_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.GalileoNodeType");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGalileoGateRule());
					}
				}
				otherlv_2=RULE_STRING
				{
					newLeafNode(otherlv_2, grammarAccess.getGalileoGateAccess().getChildrenGalileoFaultTreeNodeCrossReference_2_0());
				}
			)
		)*
	)
;

// Entry rule entryRuleGalileoBasicEvent
entryRuleGalileoBasicEvent returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGalileoBasicEventRule()); }
	iv_ruleGalileoBasicEvent=ruleGalileoBasicEvent
	{ $current=$iv_ruleGalileoBasicEvent.current; }
	EOF;

// Rule GalileoBasicEvent
ruleGalileoBasicEvent returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_name_0_0=RULE_STRING
				{
					newLeafNode(lv_name_0_0, grammarAccess.getGalileoBasicEventAccess().getNameSTRINGTerminalRuleCall_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGalileoBasicEventRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_0_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		otherlv_1='lambda'
		{
			newLeafNode(otherlv_1, grammarAccess.getGalileoBasicEventAccess().getLambdaKeyword_1());
		}
		otherlv_2='='
		{
			newLeafNode(otherlv_2, grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getGalileoBasicEventAccess().getLambdaFloatParserRuleCall_3_0());
				}
				lv_lambda_3_0=ruleFloat
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGalileoBasicEventRule());
					}
					set(
						$current,
						"lambda",
						lv_lambda_3_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.Float");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='dorm'
			{
				newLeafNode(otherlv_4, grammarAccess.getGalileoBasicEventAccess().getDormKeyword_4_0());
			}
			otherlv_5='='
			{
				newLeafNode(otherlv_5, grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_4_1());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getGalileoBasicEventAccess().getDormFloatParserRuleCall_4_2_0());
					}
					lv_dorm_6_0=ruleFloat
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getGalileoBasicEventRule());
						}
						set(
							$current,
							"dorm",
							lv_dorm_6_0,
							"de.dlr.sc.virsat.fdir.galileo.Dft.Float");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getGalileoBasicEventAccess().getRepairActionsGalileoRepairActionParserRuleCall_5_0());
				}
				lv_repairActions_7_0=ruleGalileoRepairAction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGalileoBasicEventRule());
					}
					add(
						$current,
						"repairActions",
						lv_repairActions_7_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.GalileoRepairAction");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleGalileoRepairAction
entryRuleGalileoRepairAction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGalileoRepairActionRule()); }
	iv_ruleGalileoRepairAction=ruleGalileoRepairAction
	{ $current=$iv_ruleGalileoRepairAction.current; }
	EOF;

// Rule GalileoRepairAction
ruleGalileoRepairAction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='repair'
		{
			newLeafNode(otherlv_0, grammarAccess.getGalileoRepairActionAccess().getRepairKeyword_0());
		}
		otherlv_1='='
		{
			newLeafNode(otherlv_1, grammarAccess.getGalileoRepairActionAccess().getEqualsSignKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getGalileoRepairActionAccess().getRepairFloatParserRuleCall_2_0());
				}
				lv_repair_2_0=ruleFloat
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getGalileoRepairActionRule());
					}
					set(
						$current,
						"repair",
						lv_repair_2_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.Float");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				lv_name_3_0=RULE_STRING
				{
					newLeafNode(lv_name_3_0, grammarAccess.getGalileoRepairActionAccess().getNameSTRINGTerminalRuleCall_3_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGalileoRepairActionRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_3_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)?
		(
			otherlv_4='observations'
			{
				newLeafNode(otherlv_4, grammarAccess.getGalileoRepairActionAccess().getObservationsKeyword_4_0());
			}
			(
				(
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getGalileoRepairActionRule());
						}
					}
					otherlv_5=RULE_STRING
					{
						newLeafNode(otherlv_5, grammarAccess.getGalileoRepairActionAccess().getObservartionsGalileoFaultTreeNodeCrossReference_4_1_0());
					}
				)
			)*
		)?
	)
;

// Entry rule entryRuleGalileoNodeType
entryRuleGalileoNodeType returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGalileoNodeTypeRule()); }
	iv_ruleGalileoNodeType=ruleGalileoNodeType
	{ $current=$iv_ruleGalileoNodeType.current; }
	EOF;

// Rule GalileoNodeType
ruleGalileoNodeType returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getGalileoNodeTypeAccess().getNamedParserRuleCall_0());
		}
		this_Named_0=ruleNamed
		{
			$current = $this_Named_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getGalileoNodeTypeAccess().getParametrizedParserRuleCall_1());
		}
		this_Parametrized_1=ruleParametrized
		{
			$current = $this_Parametrized_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getGalileoNodeTypeAccess().getObserverParserRuleCall_2());
		}
		this_Observer_2=ruleObserver
		{
			$current = $this_Observer_2.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleNamed
entryRuleNamed returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNamedRule()); }
	iv_ruleNamed=ruleNamed
	{ $current=$iv_ruleNamed.current; }
	EOF;

// Rule Named
ruleNamed returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getNamedAccess().getNamedAction_0(),
					$current);
			}
		)
		(
			(
				(
					lv_typeName_1_1='and'
					{
						newLeafNode(lv_typeName_1_1, grammarAccess.getNamedAccess().getTypeNameAndKeyword_1_0_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_1, null);
					}
					    |
					lv_typeName_1_2='or'
					{
						newLeafNode(lv_typeName_1_2, grammarAccess.getNamedAccess().getTypeNameOrKeyword_1_0_1());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_2, null);
					}
					    |
					lv_typeName_1_3=RULE_XOFY
					{
						newLeafNode(lv_typeName_1_3, grammarAccess.getNamedAccess().getTypeNameXOFYTerminalRuleCall_1_0_2());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed(
							$current,
							"typeName",
							lv_typeName_1_3,
							"de.dlr.sc.virsat.fdir.galileo.Dft.XOFY");
					}
					    |
					lv_typeName_1_4='pand'
					{
						newLeafNode(lv_typeName_1_4, grammarAccess.getNamedAccess().getTypeNamePandKeyword_1_0_3());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_4, null);
					}
					    |
					lv_typeName_1_5='pand_i'
					{
						newLeafNode(lv_typeName_1_5, grammarAccess.getNamedAccess().getTypeNamePand_iKeyword_1_0_4());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_5, null);
					}
					    |
					lv_typeName_1_6='por'
					{
						newLeafNode(lv_typeName_1_6, grammarAccess.getNamedAccess().getTypeNamePorKeyword_1_0_5());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_6, null);
					}
					    |
					lv_typeName_1_7='por_i'
					{
						newLeafNode(lv_typeName_1_7, grammarAccess.getNamedAccess().getTypeNamePor_iKeyword_1_0_6());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_7, null);
					}
					    |
					lv_typeName_1_8='sand'
					{
						newLeafNode(lv_typeName_1_8, grammarAccess.getNamedAccess().getTypeNameSandKeyword_1_0_7());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_8, null);
					}
					    |
					lv_typeName_1_9='hsp'
					{
						newLeafNode(lv_typeName_1_9, grammarAccess.getNamedAccess().getTypeNameHspKeyword_1_0_8());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_9, null);
					}
					    |
					lv_typeName_1_10='wsp'
					{
						newLeafNode(lv_typeName_1_10, grammarAccess.getNamedAccess().getTypeNameWspKeyword_1_0_9());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_10, null);
					}
					    |
					lv_typeName_1_11='csp'
					{
						newLeafNode(lv_typeName_1_11, grammarAccess.getNamedAccess().getTypeNameCspKeyword_1_0_10());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_11, null);
					}
					    |
					lv_typeName_1_12='seq'
					{
						newLeafNode(lv_typeName_1_12, grammarAccess.getNamedAccess().getTypeNameSeqKeyword_1_0_11());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_12, null);
					}
					    |
					lv_typeName_1_13='fdep'
					{
						newLeafNode(lv_typeName_1_13, grammarAccess.getNamedAccess().getTypeNameFdepKeyword_1_0_12());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getNamedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_13, null);
					}
				)
			)
		)
	)
;

// Entry rule entryRuleObserver
entryRuleObserver returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getObserverRule()); }
	iv_ruleObserver=ruleObserver
	{ $current=$iv_ruleObserver.current; }
	EOF;

// Rule Observer
ruleObserver returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getObserverAccess().getObserverAction_0(),
					$current);
			}
		)
		otherlv_1='observer'
		{
			newLeafNode(otherlv_1, grammarAccess.getObserverAccess().getObserverKeyword_1());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getObserverRule());
					}
				}
				otherlv_2=RULE_STRING
				{
					newLeafNode(otherlv_2, grammarAccess.getObserverAccess().getObservablesGalileoFaultTreeNodeCrossReference_2_0());
				}
			)
		)*
		otherlv_3='obsRate'
		{
			newLeafNode(otherlv_3, grammarAccess.getObserverAccess().getObsRateKeyword_3());
		}
		otherlv_4='='
		{
			newLeafNode(otherlv_4, grammarAccess.getObserverAccess().getEqualsSignKeyword_4());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getObserverAccess().getObservationRateFloatParserRuleCall_5_0());
				}
				lv_observationRate_5_0=ruleFloat
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getObserverRule());
					}
					set(
						$current,
						"observationRate",
						lv_observationRate_5_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.Float");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleParametrized
entryRuleParametrized returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getParametrizedRule()); }
	iv_ruleParametrized=ruleParametrized
	{ $current=$iv_ruleParametrized.current; }
	EOF;

// Rule Parametrized
ruleParametrized returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getParametrizedAccess().getParametrizedAction_0(),
					$current);
			}
		)
		(
			(
				(
					lv_typeName_1_1='rdep'
					{
						newLeafNode(lv_typeName_1_1, grammarAccess.getParametrizedAccess().getTypeNameRdepKeyword_1_0_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getParametrizedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_1, null);
					}
					    |
					lv_typeName_1_2='delay'
					{
						newLeafNode(lv_typeName_1_2, grammarAccess.getParametrizedAccess().getTypeNameDelayKeyword_1_0_1());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getParametrizedRule());
						}
						setWithLastConsumed($current, "typeName", lv_typeName_1_2, null);
					}
				)
			)
		)
		otherlv_2='='
		{
			newLeafNode(otherlv_2, grammarAccess.getParametrizedAccess().getEqualsSignKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getParametrizedAccess().getParameterFloatParserRuleCall_3_0());
				}
				lv_parameter_3_0=ruleFloat
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getParametrizedRule());
					}
					set(
						$current,
						"parameter",
						lv_parameter_3_0,
						"de.dlr.sc.virsat.fdir.galileo.Dft.Float");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleFloat
entryRuleFloat returns [String current=null]:
	{ newCompositeNode(grammarAccess.getFloatRule()); }
	iv_ruleFloat=ruleFloat
	{ $current=$iv_ruleFloat.current.getText(); }
	EOF;

// Rule Float
ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			kw='-'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFloatAccess().getHyphenMinusKeyword_0());
			}
		)?
		this_INT_1=RULE_INT
		{
			$current.merge(this_INT_1);
		}
		{
			newLeafNode(this_INT_1, grammarAccess.getFloatAccess().getINTTerminalRuleCall_1());
		}
		(
			kw='.'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFloatAccess().getFullStopKeyword_2_0());
			}
			this_INT_3=RULE_INT
			{
				$current.merge(this_INT_3);
			}
			{
				newLeafNode(this_INT_3, grammarAccess.getFloatAccess().getINTTerminalRuleCall_2_1());
			}
		)?
		(
			kw='e'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFloatAccess().getEKeyword_3_0());
			}
			(
				kw='-'
				{
					$current.merge(kw);
					newLeafNode(kw, grammarAccess.getFloatAccess().getHyphenMinusKeyword_3_1());
				}
			)?
			this_INT_6=RULE_INT
			{
				$current.merge(this_INT_6);
			}
			{
				newLeafNode(this_INT_6, grammarAccess.getFloatAccess().getINTTerminalRuleCall_3_2());
			}
		)?
	)
;

RULE_XOFY : RULE_INT 'of' RULE_INT;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
