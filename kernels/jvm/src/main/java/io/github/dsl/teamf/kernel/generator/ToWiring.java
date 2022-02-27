package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.structural.GridLayout;


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
		w("import { Grommet, Grid } from 'grommet';\n");
		w("\nvar quiz = require('"+app.getQuizPath()+"');\n");
		w("export default class App extends Component {\n");
		w("\ncomponentDidMount() {\n");
		w("\tdocument.title = \""+app.getName()+"\";\n");
		w("}\n");
		w("\nrender() {\n");
		w("\tconsole.log(\""+app.getLayout().getName()+"\");\n");
		w("\tconsole.log(\""+app.getTheme()+"\");\n");
		w("\treturn (\n");
		if(app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		w("\t);}");
		w("}");
	}

	@Override
	public void visit(GridLayout grid) {
		w("\t\t<Grid\n");
		w("\t\trows={[");
		for(int i = 0; i < grid.getSizeByRowIndex().keySet().size(); i++)
			w("\""+grid.getSizeByRowIndex().get(i).toString().toLowerCase()+"\",");
		w("]}\n");
		w("\t\tcolumns={[");
		for(int i = 0; i < grid.getSizeByColumnIndex().keySet().size(); i++)
			w("\""+grid.getSizeByColumnIndex().toString().toLowerCase()+"\",");
		w("]}\n");
		w("\t\tgap=\""+grid.getGap().toString().toLowerCase()+"\"\n");
		w(">\n");
		w("\t\t</Grid>\n");
	}
}
