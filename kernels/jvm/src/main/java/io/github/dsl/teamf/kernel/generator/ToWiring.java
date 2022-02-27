package io.github.dsl.teamf.kernel.generator;

import java.util.Arrays;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
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

	private void produceSingleAnswerHelperFunction() {
		w("renderSingleAnswerQuestion(question) {\n");
		w("return (\n");

		BoxLayout box = new BoxLayout("");
		box.setDirection(Direction.COLUMN);

		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();

		RadioButtonComponent radio = new RadioButtonComponent();
		radio.setBindedToAnswers(true);

		box.setContents(
				Arrays.asList(text, radio));

		box.accept(this);
		w(");}\n");
	}

	private void produceMultipleAnswerHelperFunction() {
		w("renderMultipleAnswerQuestion(question) {\n");
		w("return (\n");

		BoxLayout box = new BoxLayout("");
		box.setDirection(Direction.COLUMN);

		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();

		CheckBoxComponent checkBox = new CheckBoxComponent();
		checkBox.setVariableName("question.answers");

		box.setContents(Arrays.asList(text, checkBox));
		box.accept(this);
		w(");}\n");
	}

	private void produceOpenAnswerHelperFunction() {
		w("renderOpenAnswerQuestion(question) {\n");
		w("return (\n");

		BoxLayout box = new BoxLayout("");
		box.setDirection(Direction.COLUMN);

		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();

		TextInputComponent input = new TextInputComponent();

		box.setContents(Arrays.asList(text, input));
		box.accept(this);
		w(");}\n");
	}

	private void produceHelperFunctions() {
		produceSingleAnswerHelperFunction();
		w("\n");
		produceMultipleAnswerHelperFunction();
		w("\n");
		produceOpenAnswerHelperFunction();
		w("\nrenderQuestion(question) {\n");
		w("var type = question.type;\n\n");
		w("switch (type) {\n");
		w("case \"single\":\n");
		w("return this.renderSingleAnswerQuestion(question);\n");
		w("case \"multiple\":\n");
		w("return this.renderMultipleAnswerQuestion(question);\n");
		w("case \"open\":\n");
		w("return this.renderOpenAnswerQuestion(question);\n");
		w("}\n}\n");
	}

	@Override
	public void visit(App app) {
		w("import React, { Component } from 'react';\n");
		w("import { Grommet, Grid, Box, Text, TextInput, Button, CheckBoxGroup, RadioButtonGroup  } from 'grommet';\n");
		w("\nvar quiz = require('" + app.getQuizPath() + "');\n");
		w("export default class App extends Component {\n");
		w("\ncomponentDidMount() {\n");
		w("\tdocument.title = \"" + app.getName() + "\";\n");
		w("}\n");
		produceHelperFunctions();
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
			w("\"" + grid.getSizeByColumnIndex().get(i).toString().toLowerCase() + "\",");
		w("]}\n");
		w("\t\tgap=\"" + grid.getGap().toString().toLowerCase() + "\"\n");
		w("areas={[\n");
		for (int i = 0; i < grid.getLayouts().length; i++)
			for (int j = 0; j < grid.getLayouts()[i].length; j++)
				w("{ name: \"" + grid.getName() + i + j + "\", start: [" + j + ", " + i + "], end: [" + j + ", " + i
						+ "] },");
		w("]}\n");
		w(">\n");
		for (int i = 0; i < grid.getLayouts().length; i++)
			for (int j = 0; j < grid.getLayouts()[i].length; j++) {
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
			w(" gridArea=\"" + box.getGridArea() + "\" ");
		w(" background=\"" + box.getBackground() + "\"");
		w(" flex={" + box.isFlex() + "}");
		w(" direction=\"" + box.getDirection().value() + "\"");
		w(">\n");
		if (box.isBindedToQuestions()) {
			w("{ quiz.questions.map((question) =>\n");
			w("this.renderQuestion(question)\n");
			w(")}\n");
		} else {
			box.getContents().forEach(uiComponent -> {
				uiComponent.accept(this);
			});
		}
		w("</Box>\n");
	}

	@Override
	public void visit(TextComponent text) {
		w("<Text" + text.getGeneralStyle() + text.getTextStyle() + ">" + text.getValue() + "</Text>\n");
	}

	@Override
	public void visit(Layout layout) {
		((BoxLayout) layout).accept(this);
	}

	@Override
	public void visit(UIComponent uiComponent) {

	}

	@Override
	public void visit(ButtonComponent buttonComponent) {
		w(String.format("<Button %s onClick={%s}" +
				" label={%s} />\n", buttonComponent.getGeneralStyle(), buttonComponent.getFunctionName(),
				buttonComponent.getVariableName()));
	}

	@Override
	public void visit(CheckBoxComponent checkBoxComponent) {
		w(String.format("<CheckBoxGroup %s options={%s}  gap=\'%s\' />\n",
				checkBoxComponent.getGeneralStyle(), checkBoxComponent.getVariableName(), checkBoxComponent.getGap()));
		if (checkBoxComponent.getFunctionName() != null && !checkBoxComponent.getFunctionName().equals(""))
			w(String.format("onChange={ () =>{%s({ value, option })}}", checkBoxComponent.getFunctionName()));
	}

	@Override
	public void visit(TextInputComponent textInputComponent) {
		w("<TextInput");
		if (textInputComponent.getValue() != null)
			w(String.format(" placeholder=\"%s\" ", textInputComponent.getValue()));
		w(textInputComponent.getGeneralStyle() + textInputComponent.getTextStyle());
		w(String.format("/>\n"));
	}

	@Override
	public void visit(RadioButtonComponent radioButtonComponent) {
		w("<RadioButtonGroup ");
		if (radioButtonComponent.isBindedToAnswers())
			w("options={question.answers}");
		else
			w("option={" + radioButtonComponent.getOptions() + "}");
		w("/>\n");
	}

}
