/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
grammar InternalDft;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package de.dlr.sc.virsat.fdir.galileo.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.fdir.galileo.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import de.dlr.sc.virsat.fdir.galileo.services.DftGrammarAccess;

}
@parser::members {
	private DftGrammarAccess grammarAccess;

	public void setGrammarAccess(DftGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleGalileoDft
entryRuleGalileoDft
:
{ before(grammarAccess.getGalileoDftRule()); }
	 ruleGalileoDft
{ after(grammarAccess.getGalileoDftRule()); } 
	 EOF 
;

// Rule GalileoDft
ruleGalileoDft 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGalileoDftAccess().getGroup()); }
		(rule__GalileoDft__Group__0)
		{ after(grammarAccess.getGalileoDftAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleGalileoGate
entryRuleGalileoGate
:
{ before(grammarAccess.getGalileoGateRule()); }
	 ruleGalileoGate
{ after(grammarAccess.getGalileoGateRule()); } 
	 EOF 
;

// Rule GalileoGate
ruleGalileoGate 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGalileoGateAccess().getGroup()); }
		(rule__GalileoGate__Group__0)
		{ after(grammarAccess.getGalileoGateAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleGalileoBasicEvent
entryRuleGalileoBasicEvent
:
{ before(grammarAccess.getGalileoBasicEventRule()); }
	 ruleGalileoBasicEvent
{ after(grammarAccess.getGalileoBasicEventRule()); } 
	 EOF 
;

// Rule GalileoBasicEvent
ruleGalileoBasicEvent 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGalileoBasicEventAccess().getGroup()); }
		(rule__GalileoBasicEvent__Group__0)
		{ after(grammarAccess.getGalileoBasicEventAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleGalileoNodeType
entryRuleGalileoNodeType
:
{ before(grammarAccess.getGalileoNodeTypeRule()); }
	 ruleGalileoNodeType
{ after(grammarAccess.getGalileoNodeTypeRule()); } 
	 EOF 
;

// Rule GalileoNodeType
ruleGalileoNodeType 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGalileoNodeTypeAccess().getAlternatives()); }
		(rule__GalileoNodeType__Alternatives)
		{ after(grammarAccess.getGalileoNodeTypeAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNamedType
entryRuleNamedType
:
{ before(grammarAccess.getNamedTypeRule()); }
	 ruleNamedType
{ after(grammarAccess.getNamedTypeRule()); } 
	 EOF 
;

// Rule NamedType
ruleNamedType 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameAssignment()); }
		(rule__NamedType__TypeNameAssignment)
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleObserverType
entryRuleObserverType
:
{ before(grammarAccess.getObserverTypeRule()); }
	 ruleObserverType
{ after(grammarAccess.getObserverTypeRule()); } 
	 EOF 
;

// Rule ObserverType
ruleObserverType 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getObserverTypeAccess().getGroup()); }
		(rule__ObserverType__Group__0)
		{ after(grammarAccess.getObserverTypeAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleRDEPType
entryRuleRDEPType
:
{ before(grammarAccess.getRDEPTypeRule()); }
	 ruleRDEPType
{ after(grammarAccess.getRDEPTypeRule()); } 
	 EOF 
;

// Rule RDEPType
ruleRDEPType 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getRDEPTypeAccess().getGroup()); }
		(rule__RDEPType__Group__0)
		{ after(grammarAccess.getRDEPTypeAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFloat
entryRuleFloat
:
{ before(grammarAccess.getFloatRule()); }
	 ruleFloat
{ after(grammarAccess.getFloatRule()); } 
	 EOF 
;

// Rule Float
ruleFloat 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFloatAccess().getGroup()); }
		(rule__Float__Group__0)
		{ after(grammarAccess.getFloatAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Alternatives_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoDftAccess().getGroup_3_0()); }
		(rule__GalileoDft__Group_3_0__0)
		{ after(grammarAccess.getGalileoDftAccess().getGroup_3_0()); }
	)
	|
	(
		{ before(grammarAccess.getGalileoDftAccess().getGroup_3_1()); }
		(rule__GalileoDft__Group_3_1__0)
		{ after(grammarAccess.getGalileoDftAccess().getGroup_3_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoNodeType__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoNodeTypeAccess().getNamedTypeParserRuleCall_0()); }
		ruleNamedType
		{ after(grammarAccess.getGalileoNodeTypeAccess().getNamedTypeParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getGalileoNodeTypeAccess().getObserverTypeParserRuleCall_1()); }
		ruleObserverType
		{ after(grammarAccess.getGalileoNodeTypeAccess().getObserverTypeParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getGalileoNodeTypeAccess().getRDEPTypeParserRuleCall_2()); }
		ruleRDEPType
		{ after(grammarAccess.getGalileoNodeTypeAccess().getRDEPTypeParserRuleCall_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NamedType__TypeNameAlternatives_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameAndKeyword_0_0()); }
		'and'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameAndKeyword_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameOrKeyword_0_1()); }
		'or'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameOrKeyword_0_1()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameXOFYTerminalRuleCall_0_2()); }
		RULE_XOFY
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameXOFYTerminalRuleCall_0_2()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNamePandKeyword_0_3()); }
		'pand'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNamePandKeyword_0_3()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNamePand_iKeyword_0_4()); }
		'pand_i'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNamePand_iKeyword_0_4()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNamePorKeyword_0_5()); }
		'por'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNamePorKeyword_0_5()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNamePor_iKeyword_0_6()); }
		'por_i'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNamePor_iKeyword_0_6()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameSandKeyword_0_7()); }
		'sand'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameSandKeyword_0_7()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameHspKeyword_0_8()); }
		'hsp'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameHspKeyword_0_8()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameWspKeyword_0_9()); }
		'wsp'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameWspKeyword_0_9()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameCspKeyword_0_10()); }
		'csp'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameCspKeyword_0_10()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameSeqKeyword_0_11()); }
		'seq'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameSeqKeyword_0_11()); }
	)
	|
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameFdepKeyword_0_12()); }
		'fdep'
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameFdepKeyword_0_12()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group__0__Impl
	rule__GalileoDft__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getToplevelKeyword_0()); }
	'toplevel'
	{ after(grammarAccess.getGalileoDftAccess().getToplevelKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group__1__Impl
	rule__GalileoDft__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getRootAssignment_1()); }
	(rule__GalileoDft__RootAssignment_1)
	{ after(grammarAccess.getGalileoDftAccess().getRootAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group__2__Impl
	rule__GalileoDft__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_2()); }
	';'
	{ after(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getAlternatives_3()); }
	(rule__GalileoDft__Alternatives_3)*
	{ after(grammarAccess.getGalileoDftAccess().getAlternatives_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoDft__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group_3_0__0__Impl
	rule__GalileoDft__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getGatesAssignment_3_0_0()); }
	(rule__GalileoDft__GatesAssignment_3_0_0)
	{ after(grammarAccess.getGalileoDftAccess().getGatesAssignment_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_0_1()); }
	';'
	{ after(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoDft__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group_3_1__0__Impl
	rule__GalileoDft__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getBasicEventsAssignment_3_1_0()); }
	(rule__GalileoDft__BasicEventsAssignment_3_1_0)
	{ after(grammarAccess.getGalileoDftAccess().getBasicEventsAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoDft__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_1_1()); }
	';'
	{ after(grammarAccess.getGalileoDftAccess().getSemicolonKeyword_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoGate__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoGate__Group__0__Impl
	rule__GalileoGate__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoGateAccess().getNameAssignment_0()); }
	(rule__GalileoGate__NameAssignment_0)
	{ after(grammarAccess.getGalileoGateAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoGate__Group__1__Impl
	rule__GalileoGate__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoGateAccess().getTypeAssignment_1()); }
	(rule__GalileoGate__TypeAssignment_1)
	{ after(grammarAccess.getGalileoGateAccess().getTypeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoGate__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoGateAccess().getChildrenAssignment_2()); }
	(rule__GalileoGate__ChildrenAssignment_2)*
	{ after(grammarAccess.getGalileoGateAccess().getChildrenAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoBasicEvent__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__0__Impl
	rule__GalileoBasicEvent__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getNameAssignment_0()); }
	(rule__GalileoBasicEvent__NameAssignment_0)
	{ after(grammarAccess.getGalileoBasicEventAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__1__Impl
	rule__GalileoBasicEvent__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getLambdaKeyword_1()); }
	'lambda'
	{ after(grammarAccess.getGalileoBasicEventAccess().getLambdaKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__2__Impl
	rule__GalileoBasicEvent__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_2()); }
	'='
	{ after(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__3__Impl
	rule__GalileoBasicEvent__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getLambdaAssignment_3()); }
	(rule__GalileoBasicEvent__LambdaAssignment_3)
	{ after(grammarAccess.getGalileoBasicEventAccess().getLambdaAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__4__Impl
	rule__GalileoBasicEvent__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getGroup_4()); }
	(rule__GalileoBasicEvent__Group_4__0)?
	{ after(grammarAccess.getGalileoBasicEventAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getGroup_5()); }
	(rule__GalileoBasicEvent__Group_5__0)?
	{ after(grammarAccess.getGalileoBasicEventAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoBasicEvent__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_4__0__Impl
	rule__GalileoBasicEvent__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getDormKeyword_4_0()); }
	'dorm'
	{ after(grammarAccess.getGalileoBasicEventAccess().getDormKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_4__1__Impl
	rule__GalileoBasicEvent__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_4_1()); }
	'='
	{ after(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getDormAssignment_4_2()); }
	(rule__GalileoBasicEvent__DormAssignment_4_2)
	{ after(grammarAccess.getGalileoBasicEventAccess().getDormAssignment_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoBasicEvent__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_5__0__Impl
	rule__GalileoBasicEvent__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getRepairKeyword_5_0()); }
	'repair'
	{ after(grammarAccess.getGalileoBasicEventAccess().getRepairKeyword_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_5__1__Impl
	rule__GalileoBasicEvent__Group_5__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_5_1()); }
	'='
	{ after(grammarAccess.getGalileoBasicEventAccess().getEqualsSignKeyword_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_5__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GalileoBasicEvent__Group_5__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__Group_5__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGalileoBasicEventAccess().getRepairAssignment_5_2()); }
	(rule__GalileoBasicEvent__RepairAssignment_5_2)
	{ after(grammarAccess.getGalileoBasicEventAccess().getRepairAssignment_5_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ObserverType__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ObserverType__Group__0__Impl
	rule__ObserverType__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObserverTypeAccess().getObserverKeyword_0()); }
	'observer'
	{ after(grammarAccess.getObserverTypeAccess().getObserverKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ObserverType__Group__1__Impl
	rule__ObserverType__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObserverTypeAccess().getObservablesAssignment_1()); }
	(rule__ObserverType__ObservablesAssignment_1)*
	{ after(grammarAccess.getObserverTypeAccess().getObservablesAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ObserverType__Group__2__Impl
	rule__ObserverType__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObserverTypeAccess().getObsRateKeyword_2()); }
	'obsRate'
	{ after(grammarAccess.getObserverTypeAccess().getObsRateKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ObserverType__Group__3__Impl
	rule__ObserverType__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObserverTypeAccess().getEqualsSignKeyword_3()); }
	'='
	{ after(grammarAccess.getObserverTypeAccess().getEqualsSignKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ObserverType__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getObserverTypeAccess().getObservationRateAssignment_4()); }
	(rule__ObserverType__ObservationRateAssignment_4)
	{ after(grammarAccess.getObserverTypeAccess().getObservationRateAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__RDEPType__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__RDEPType__Group__0__Impl
	rule__RDEPType__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getRDEPTypeAccess().getRdepKeyword_0()); }
	'rdep'
	{ after(grammarAccess.getRDEPTypeAccess().getRdepKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__RDEPType__Group__1__Impl
	rule__RDEPType__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getRDEPTypeAccess().getRateFactorKeyword_1()); }
	'rateFactor'
	{ after(grammarAccess.getRDEPTypeAccess().getRateFactorKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__RDEPType__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getRDEPTypeAccess().getRateFactorAssignment_2()); }
	(rule__RDEPType__RateFactorAssignment_2)
	{ after(grammarAccess.getRDEPTypeAccess().getRateFactorAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Float__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group__0__Impl
	rule__Float__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getHyphenMinusKeyword_0()); }
	('-')?
	{ after(grammarAccess.getFloatAccess().getHyphenMinusKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group__1__Impl
	rule__Float__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getINTTerminalRuleCall_1()); }
	RULE_INT
	{ after(grammarAccess.getFloatAccess().getINTTerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group__2__Impl
	rule__Float__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getGroup_2()); }
	(rule__Float__Group_2__0)?
	{ after(grammarAccess.getFloatAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getGroup_3()); }
	(rule__Float__Group_3__0)?
	{ after(grammarAccess.getFloatAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Float__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group_2__0__Impl
	rule__Float__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getFullStopKeyword_2_0()); }
	'.'
	{ after(grammarAccess.getFloatAccess().getFullStopKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getINTTerminalRuleCall_2_1()); }
	RULE_INT
	{ after(grammarAccess.getFloatAccess().getINTTerminalRuleCall_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Float__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group_3__0__Impl
	rule__Float__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getEKeyword_3_0()); }
	'e'
	{ after(grammarAccess.getFloatAccess().getEKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group_3__1__Impl
	rule__Float__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getHyphenMinusKeyword_3_1()); }
	('-')?
	{ after(grammarAccess.getFloatAccess().getHyphenMinusKeyword_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Float__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Float__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatAccess().getINTTerminalRuleCall_3_2()); }
	RULE_INT
	{ after(grammarAccess.getFloatAccess().getINTTerminalRuleCall_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GalileoDft__RootAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoDftAccess().getRootGalileoFaultTreeNodeCrossReference_1_0()); }
		(
			{ before(grammarAccess.getGalileoDftAccess().getRootGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1()); }
			RULE_STRING
			{ after(grammarAccess.getGalileoDftAccess().getRootGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getGalileoDftAccess().getRootGalileoFaultTreeNodeCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__GatesAssignment_3_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoDftAccess().getGatesGalileoGateParserRuleCall_3_0_0_0()); }
		ruleGalileoGate
		{ after(grammarAccess.getGalileoDftAccess().getGatesGalileoGateParserRuleCall_3_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoDft__BasicEventsAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoDftAccess().getBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0()); }
		ruleGalileoBasicEvent
		{ after(grammarAccess.getGalileoDftAccess().getBasicEventsGalileoBasicEventParserRuleCall_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoGateAccess().getNameSTRINGTerminalRuleCall_0_0()); }
		RULE_STRING
		{ after(grammarAccess.getGalileoGateAccess().getNameSTRINGTerminalRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__TypeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoGateAccess().getTypeGalileoNodeTypeParserRuleCall_1_0()); }
		ruleGalileoNodeType
		{ after(grammarAccess.getGalileoGateAccess().getTypeGalileoNodeTypeParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoGate__ChildrenAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoGateAccess().getChildrenGalileoFaultTreeNodeCrossReference_2_0()); }
		(
			{ before(grammarAccess.getGalileoGateAccess().getChildrenGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1()); }
			RULE_STRING
			{ after(grammarAccess.getGalileoGateAccess().getChildrenGalileoFaultTreeNodeSTRINGTerminalRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getGalileoGateAccess().getChildrenGalileoFaultTreeNodeCrossReference_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoBasicEventAccess().getNameSTRINGTerminalRuleCall_0_0()); }
		RULE_STRING
		{ after(grammarAccess.getGalileoBasicEventAccess().getNameSTRINGTerminalRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__LambdaAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoBasicEventAccess().getLambdaFloatParserRuleCall_3_0()); }
		ruleFloat
		{ after(grammarAccess.getGalileoBasicEventAccess().getLambdaFloatParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__DormAssignment_4_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoBasicEventAccess().getDormFloatParserRuleCall_4_2_0()); }
		ruleFloat
		{ after(grammarAccess.getGalileoBasicEventAccess().getDormFloatParserRuleCall_4_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GalileoBasicEvent__RepairAssignment_5_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGalileoBasicEventAccess().getRepairFloatParserRuleCall_5_2_0()); }
		ruleFloat
		{ after(grammarAccess.getGalileoBasicEventAccess().getRepairFloatParserRuleCall_5_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NamedType__TypeNameAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNamedTypeAccess().getTypeNameAlternatives_0()); }
		(rule__NamedType__TypeNameAlternatives_0)
		{ after(grammarAccess.getNamedTypeAccess().getTypeNameAlternatives_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__ObservablesAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getObserverTypeAccess().getObservablesGalileoFaultTreeNodeCrossReference_1_0()); }
		(
			{ before(grammarAccess.getObserverTypeAccess().getObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1()); }
			RULE_STRING
			{ after(grammarAccess.getObserverTypeAccess().getObservablesGalileoFaultTreeNodeSTRINGTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getObserverTypeAccess().getObservablesGalileoFaultTreeNodeCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ObserverType__ObservationRateAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getObserverTypeAccess().getObservationRateFloatParserRuleCall_4_0()); }
		ruleFloat
		{ after(grammarAccess.getObserverTypeAccess().getObservationRateFloatParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__RDEPType__RateFactorAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getRDEPTypeAccess().getRateFactorFloatParserRuleCall_2_0()); }
		ruleFloat
		{ after(grammarAccess.getRDEPTypeAccess().getRateFactorFloatParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_XOFY : RULE_INT 'of' RULE_INT;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
