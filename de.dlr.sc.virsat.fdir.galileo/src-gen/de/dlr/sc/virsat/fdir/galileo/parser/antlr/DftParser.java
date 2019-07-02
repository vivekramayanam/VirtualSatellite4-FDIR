/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.galileo.parser.antlr;

import com.google.inject.Inject;
import de.dlr.sc.virsat.fdir.galileo.parser.antlr.internal.InternalDftParser;
import de.dlr.sc.virsat.fdir.galileo.services.DftGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class DftParser extends AbstractAntlrParser {

	@Inject
	private DftGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalDftParser createParser(XtextTokenStream stream) {
		return new InternalDftParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "GalileoDft";
	}

	public DftGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(DftGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
