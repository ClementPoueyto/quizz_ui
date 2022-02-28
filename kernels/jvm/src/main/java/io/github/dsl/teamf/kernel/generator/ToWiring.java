package io.github.dsl.teamf.kernel.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
import io.github.dsl.teamf.kernel.structural.BoxLayout;
import io.github.dsl.teamf.kernel.structural.GridLayout;
import io.github.dsl.teamf.kernel.structural.Layout;
import io.github.dsl.teamf.kernel.structural.Size;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	List<String> functions = new ArrayList<>();
	List<String> states = new ArrayList<>();
	List<String> variables = new ArrayList<>();

	enum PASS {
		STATE,FUNCTION,VARIABLE,JSX,FUNCTION_WITH_JSX
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
		box.setMargin(Size.MEDIUM);
		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();
		text.setMargin(Size.XSMALL);

		RadioButtonComponent radio = new RadioButtonComponent();
		radio.setBindedToAnswers(true);

		box.setContents(
				Arrays.asList(text, radio));
		w("\t\t");
		box.accept(this);
		w(");}\n");
	}

	private void produceMultipleAnswerHelperFunction() {
		w("renderMultipleAnswerQuestion(question) {\n");
		w("return (\n");
		BoxLayout box = new BoxLayout("");
		box.setDirection(Direction.COLUMN);
		box.setMargin(Size.MEDIUM);

		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();
		text.setMargin(Size.XSMALL);

		CheckBoxComponent checkBox = new CheckBoxComponent();
		checkBox.setVariableName("question.answers");
		box.setContents(Arrays.asList(text, checkBox));
		w("\t\t");
		box.accept(this);
		w(");}\n");
	}

	private void produceOpenAnswerHelperFunction() {
		w("renderOpenAnswerQuestion(question) {\n");
		w("return (\n");

		BoxLayout box = new BoxLayout("");
		box.setDirection(Direction.COLUMN);
		box.setMargin(Size.MEDIUM);

		TextComponent text = new TextComponent();
		text.bindToQuestionStatement();
		text.setMargin(Size.XSMALL);

		TextInputComponent input = new TextInputComponent();

		box.setContents(Arrays.asList(text, input));
		w("\t\t");
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
		w("\tconstructor() {\n" +
				"\t\tsuper();\n" +
				"\t\tthis.state = { \n\t\t\tquiz : quiz,\n");
		context.put("pass", PASS.STATE);
		if (app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		w("\t\t};\n\t}\n\n");
		w("\ncomponentDidMount() {\n");
		w("\tdocument.title = \"" + app.getName() + "\";\n");
		w("}\n");
		context.put("pass",PASS.FUNCTION_WITH_JSX);
			produceHelperFunctions();
		context.put("pass", PASS.FUNCTION);
		if (app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		context.put("pass", PASS.VARIABLE);
		if (app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		w("\nrender() {\n");
		w("\tconsole.log(\"" + app.getLayout().getName() + "\");\n");
		w("\tconsole.log(\"" + app.getTheme() + "\");\n");
		w("\treturn (\n");
		context.put("pass", PASS.JSX);
		if (app.getLayout() instanceof GridLayout)
			((GridLayout) app.getLayout()).accept(this);
		w("\t);}");
		w("}");

	}

	@Override
	public void visit(GridLayout grid) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w("\t\t<Grid \n");
			w(" margin=\"" + grid.getMargin().value() + "\" ");
			w("\t\trows={[");
			for (int i = 0; i < grid.getSizeByRowIndex().keySet().size(); i++)
				w("\"" + grid.getSizeByRowIndex().get(i).toString().toLowerCase() + "\",");
			w("]}\n");
			w("\t\tcolumns={[");
			for (int i = 0; i < grid.getSizeByColumnIndex().keySet().size(); i++)
				w("\"" + grid.getSizeByColumnIndex().get(i).toString().toLowerCase() + "\",");
			w("]}\n");
			w("\t\tgap=\"" + grid.getGap().toString().toLowerCase() + "\"\n");
			w("\t\tareas={[\n");
			for (int i = 0; i < grid.getLayouts().length; i++)
				for (int j = 0; j < grid.getLayouts()[i].length; j++)
					w("\t\t{ name: \"" + grid.getName() + i + j + "\", start: [" + j + ", " + i + "], end: [" + j + ", " + i
							+ "] },");
			w("]}\n");
			w("\t\t>\n");
			for (int i = 0; i < grid.getLayouts().length; i++)
				for (int j = 0; j < grid.getLayouts()[i].length; j++) {
					Layout layout = grid.getLayouts()[i][j];
					layout.setGridArea(grid.getName() + i + j);
					w("\t\t\t");
					layout.accept(this);
				}
			w("\t\t</Grid>\n");
		}
		if(context.get("pass") == PASS.FUNCTION) {
			for (int i = 0; i < grid.getLayouts().length; i++)
				for (int j = 0; j < grid.getLayouts()[i].length; j++) {
					Layout layout = grid.getLayouts()[i][j];
					layout.setGridArea(grid.getName() + i + j);
					w("\t\t\t");
					layout.accept(this);
				}
		}
		if(context.get("pass") == PASS.STATE) {
			for (int i = 0; i < grid.getLayouts().length; i++)
				for (int j = 0; j < grid.getLayouts()[i].length; j++) {
					Layout layout = grid.getLayouts()[i][j];
					layout.setGridArea(grid.getName() + i + j);
					w("\t\t\t");
					layout.accept(this);
				}
		}
	}

	@Override
	public void visit(BoxLayout box) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {

			w("\t\t<Box ");
			if (box.getGridArea() != null)
				w(" gridArea=\"" + box.getGridArea() + "\" ");
			w(" background=\"" + box.getBackground() + "\"");
			w(" flex={" + box.isFlex() + "}");
			w(" direction=\"" + box.getDirection().value() + "\"");
			w(" basis=\"" + box.getBasis() + "\" ");
			w(" margin=\"" + box.getMargin().value() + "\" ");
			w(">\n");
			if (box.isBindedToQuestions()) {
				if (box.getNavigation() != null) {
					w("\t\t\t{this.renderQuestion(this.state.quiz.questions[this.state.index])\n}");
					box.getNavigation().accept(this);
				} else {
					w("\t\t\t{ this.state.quiz.questions.map((question) =>\n");
					w("\t\t\t\tthis.renderQuestion(question)\n");
					w("\t\t\t)}\n");
				}
			} else {
				box.getContents().forEach(uiComponent -> {
					w("\t\t\t");
					uiComponent.accept(this);
				});
			}
			w("\t\t</Box>\n");
		}
		if(context.get("pass") == PASS.FUNCTION) {
			box.getContents().forEach(uiComponent -> {
				uiComponent.accept(this);
			});
			if (box.getNavigation() != null)
				box.getNavigation().accept(this);
		}
		if(context.get("pass") == PASS.STATE) {
			box.getContents().forEach(uiComponent -> {
				uiComponent.accept(this);
			});
			if (box.getNavigation() != null)
				box.getNavigation().accept(this);
		}
	}

	@Override
	public void visit(TextComponent text) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w("<Text" + text.getGeneralStyle() + text.getTextStyle() + ">" + text.getValue() + "</Text>\n");
		}
	}

	@Override
	public void visit(Layout layout) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w("\t\t");
			((BoxLayout) layout).accept(this);
		}
	}

	@Override
	public void visit(UIComponent uiComponent) {

	}

	@Override
	public void visit(ButtonComponent buttonComponent) {
		if(context.get("pass") == PASS.FUNCTION)
			w(String.format("%s(){}", buttonComponent.getFunctionName()));
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w(String.format("<Button %s onClick={()=>this.%s()}" +
							" label='%s' />\n", buttonComponent.getGeneralStyle(), buttonComponent.getFunctionName(),
					buttonComponent.getVariableName()));
		}
	}

	@Override
	public void visit(CheckBoxComponent checkBoxComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {

			w(String.format("<CheckBoxGroup %s options={%s}  gap=\'%s\' />\n",
					checkBoxComponent.getGeneralStyle(), checkBoxComponent.getVariableName(), checkBoxComponent.getGap()));
			if (checkBoxComponent.getFunctionName() != null && !checkBoxComponent.getFunctionName().equals(""))
				w(String.format("onChange={ () =>{%s({ value, option })}}", checkBoxComponent.getFunctionName()));
		}
		if(context.get("pass") == PASS.FUNCTION) {
			w(String.format("%s({value, option}){}", checkBoxComponent.getFunctionName()));
		}
	}

	@Override
	public void visit(TextInputComponent textInputComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w("<TextInput");
			if (textInputComponent.getValue() != null)
				w(String.format(" placeholder=\"%s\" ", textInputComponent.getValue()));
			w(textInputComponent.getGeneralStyle() + textInputComponent.getTextStyle());
			w(String.format("/>\n"));
		}
	}

	@Override
	public void visit(RadioButtonComponent radioButtonComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTION_WITH_JSX) {
			w("<RadioButtonGroup ");
			w(radioButtonComponent.getGeneralStyle());
			if (radioButtonComponent.isBindedToAnswers())
				w("options={question.answers}");
			else
				w("option={" + radioButtonComponent.getOptions() + "}");
			w("/>\n");
		}
	}

	@Override
	public void visit(Navigation navigation) {
		if(context.get("pass") == PASS.STATE) {
			w("index: 0,\n");
		}
		if(context.get("pass") == PASS.FUNCTION) {
			w("nextQuestion(){\n");
			w("\tif(this.state.index !== this.state.quiz.questions.length-1){\n");
			w("\t\tthis.setState(prevState => {\n");
			w("\t\t\treturn {index: prevState.index + 1}\n");
			w("\t\t})\n");
			w("\t}\n");
			w("}\n");
			if(!navigation.isOnlyNext()){
				w("previousQuestion(){\n");
				w("\tif(this.state.index !== 0){ \n");
				w("\t\tthis.setState(prevState => {\n");
				w("\t\t\treturn {index: prevState.index + 1}\n");
				w("\t\t})\n");
				w("\t}\n");
				w("}\n");
			}
		}

		if(context.get("pass") == PASS.JSX) {
			BoxLayout boxLayout = new BoxLayout("Nav");
			boxLayout.setFlex(true);
			boxLayout.setDirection(Direction.ROW);
			List<UIComponent> components = new ArrayList<>();

			ButtonComponent buttonNext = new ButtonComponent();
			components.add(buttonNext);
			buttonNext.setFunctionName("nextQuestion");
			buttonNext.setVariableName(navigation.getNextLabel());
			buttonNext.setAligment(Alignment.END);
			if(!navigation.isOnlyNext()) {
				ButtonComponent buttonPrevious = new ButtonComponent();
				components.add(buttonPrevious);
				buttonPrevious.setFunctionName("previousQuestion");
				buttonPrevious.setVariableName(navigation.getPreviousLabel());
				buttonPrevious.setAligment(Alignment.START);
			}
			boxLayout.setContents(components);
			boxLayout.accept(this);
		}


	}

}
