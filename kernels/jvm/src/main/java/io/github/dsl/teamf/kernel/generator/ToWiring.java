package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.BoxLayout;
import io.github.dsl.teamf.kernel.structural.GridLayout;
import io.github.dsl.teamf.kernel.structural.Layout;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {
		ONE
	}

	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s", s));
	}

	@Override
	public void visit(App app) {
		w("import React, { Component } from 'react';\n");
		w("import { Grommet, Grid, Box, Text } from 'grommet';\n");
		w("\nvar quiz = require('" + app.getQuizPath() + "');\n");
		w("export default class App extends Component {\n");
		w("\ncomponentDidMount() {\n");
		w("\tdocument.title = \"" + app.getName() + "\";\n");
		w("}\n");
		w("\nrender() {\n");
		w("\tconsole.log(\"" + app.getLayout().getName() + "\");\n");
		w("\tconsole.log(\"" + app.getTheme() + "\");\n");
		w("\treturn (\n");
		if (app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		w("\t);}");
		w("}");
	}

	@Override
	public void visit(GridLayout grid) {
		w("\t\t<Grid\n");
		w("\t\trows={[");
		for (int i = 0; i < grid.getSizeByRowIndex().keySet().size(); i++)
			w("\"" + grid.getSizeByRowIndex().get(i).toString().toLowerCase() + "\",");
		w("]}\n");
		w("\t\tcolumns={[");
		for (int i = 0; i < grid.getSizeByColumnIndex().keySet().size(); i++)
			w("\"" + grid.getSizeByColumnIndex().toString().toLowerCase() + "\",");
		w("]}\n");
		w("\t\tgap=\"" + grid.getGap().toString().toLowerCase() + "\"\n");
		w("areas={[\n");
		for (int i = 0; i < grid.getLayouts().length; i++)
			for (int j = 0; j < grid.getLayouts()[j].length; j++)
				w("{ name: \"" + grid.getName() + i + j + "\", start: [" + i + ", " + j + "], end: [" + i + ", " + j
						+ "] },");
		w("]}\n");
		w(">\n");
		for (int i = 0; i < grid.getLayouts().length; i++)
			for (int j = 0; j < grid.getLayouts()[j].length; j++) {
				Layout layout = grid.getLayouts()[i][j];
				layout.setGridArea(grid.getName() + i + j);
				layout.accept(this);
			}
		w("\t\t</Grid>\n");
	}

	@Override
	public void visit(BoxLayout box) {
		w("\t\t<Box ");
		if (box.getGridArea() != null)
			w("gridArea=\"" + box.getGridArea() + "\" ");
		w("background=\"" + box.getBackground() + "\"");
		w(">\n");
		box.getContent().accept(this);
		w("</Box>\n");
	}

	@Override
	public void visit(TextComponent text) {
		w("<Text>" + text.getValue() + "</Text>\n");
	}

	@Override
	public void visit(Layout layout) {
		((BoxLayout) layout).accept(this);
	}
}
