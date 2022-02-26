package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;


/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE}


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s",s));
	}

	@Override
	public void visit(App app) {
		w("import React, { Component } from 'react';\n");
		w("\nvar quiz = require('"+app.getQuizPath()+"');\n");
		w("export default class App extends Component {\n");
		w("\ncomponentDidMount() {\n");
		w("\tdocument.title = \""+app.getName()+"\";\n");
		w("}\n");
		w("\nrender() {\n");
		w("\tconsole.log(\""+app.getLayout().getName()+"\");\n");
		w("\tconsole.log(\""+app.getTheme().getName()+"\");\n");
		w("\treturn (<p>{quiz.title}</p>);\n}\n");
		w("}");
	}
}
