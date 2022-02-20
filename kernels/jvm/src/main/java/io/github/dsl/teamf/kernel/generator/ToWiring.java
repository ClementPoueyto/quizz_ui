package io.github.dsl.teamf.kernel.generator;

import java.io.Console;
import java.util.List;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Layout;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Theme;
import io.github.dsl.teamf.kernel.structural.ui.Zone;


/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN}


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s",s));
	}

	@Override
	public void visit(App app) {
		context.put("pass", PASS.ONE); //import components
		w("import React, { Component } from 'react'\n");
		w("import { ");
		for(QuizElement quizElement : app.getQuizElementList()){
			quizElement.accept(this);
		}
		w("} from './functions'\n");
		w("import { Grommet, ");
		app.getGrid().accept(this);
		w(" } from 'grommet'\n");
		w("import { deepMerge } from \"grommet/utils\";");
		w("import { grommet } from \"grommet/themes\";");
		w("var data = require('./quiz.json');\n\n");
		w("export default class App extends Component {\n\n");
		w("\tconstructor() {\n" +
				"\t\tsuper();\n" +
				"\t\tthis.state = { quiz : data }\n" +
				"\t}\n\n");
		context.put("pass", PASS.TWO); //describe components
		w("\trender() {\n");



			w("\t\tvar customBreakpoints = deepMerge(grommet, {\n");
			w("\t\t\tglobal: {\n");
		if(app.getGrid().isResponsiveGrid()){
			w("\t\t\t\tbreakpoints: {\n");
			w("\t\t\t\t\tsmall: {\n");
			w("\t\t\t\t\t\t value: 600\n");
			w("\t\t\t\t\t},\n");
			w("\t\t\t\t\tmedium: {\n");
			w("\t\t\t\t\t\tvalue: 950\n");
			w("\t\t\t\t\t},\n");
			w("\t\t\t\t\tlarge: 3000\n");
			w("\t\t\t\t},\n");
		}
		app.getTheme().accept(this);
			w("\t\t\t}\n");
			w("\t\t});\n");


		app.getGrid().accept(this);
		w("\t\treturn (\n");
		context.put("pass", PASS.FIVE); //describe components
		app.getGrid().accept(this);


		w("\t\t</Grommet>\n");
		w("\t\t);\n\t}\n}\n");

	}

	@Override
	public void visit(Zone zone) {
		if(context.get("pass") == PASS.TWO) {
			w(String.format("\"%s\",",zone.getName()));
		}
		
		if(context.get("pass") == PASS.FIVE){

			w(String.format("\t\t\t\t\t<Box gridArea=\'%s\' align=\'%s\' background=\'%s\' >\n", zone.getName(),zone.getAlignement(), zone.getColor()));
			if(zone.getQuizElement()!=null){
				zone.getQuizElement().accept(this);
			}
			w("\t\t\t\t\t</Box>\n");
		}


	}
	@Override
	public void visit(ScreenCondition displayCondition) {
	}

	@Override
	public void visit(Grid grid) {
		if (context.get("pass") == PASS.ONE) {
			w("Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext");
		}
		if(context.get("pass") == PASS.TWO){

			w("\t\tconst areas = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("\t\t}\n");
			context.put("pass", PASS.THREE);
			w("\t\tconst rows = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("\t\t}\n");
	
			context.put("pass", PASS.FOUR);
			w("\t\tconst columns = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("}\n");
		}
		if (context.get("pass") == PASS.FIVE) {
			if(grid.isResponsiveGrid()){
				w("\t\t\t<Grommet theme={customBreakpoints}>\n");
			}else{
				w("\t\t\t<Grommet>\n");
			}
			w("\t\t\t\t<ResponsiveContext.Consumer>\n");
			w("\t\t\t\t\t{size =>\n");
			w("\t\t\t\t\t<Grid\n");
			w("\t\t\t\t\t\trows={rows[size] ? rows[size] : rows[\"default\"]}\n");
			w("\t\t\t\t\t\tcolumns={columns[size] ? columns[size] : columns[\"default\"]}\n");
			w(String.format("\t\t\t\t\tgap=\'%s\'\n", "null"));
			w("\t\t\t\t\t\tareas={areas[size] ? areas[size] : areas[\"default\"]}\n");
			w("\t\t\t\t>\n");
			for (Zone zone : grid.getZones()) {
				zone.accept(this);
			}
			w("\t\t\t\t</Grid>\n");
			w("\t\t\t}\n");
			w("\t\t\t</ResponsiveContext.Consumer>\n");
		}
	}

	
	@Override
	public void visit(Layout layout) {
		if (context.get("pass") == PASS.TWO) {
			if(layout.getScreenCondition() !=null){
				w(String.format("\t\t\t%s: [\n",layout.getScreenCondition().getScreenConditionName()));
			}else{
				w("\t\t\tdefault: [\n");
			}
			for(List<Zone> row : layout.getArrangement()){
				w("\t\t\t\t[");
				for(Zone zone : row){
					zone.accept(this);
				}
				w("],\n");
			}
			w("\t\t\t],\n");
		}else{
			if(layout.getScreenCondition() !=null){
				w(String.format("\t\t\t%s:[",layout.getScreenCondition().getScreenConditionName()));
			}else{
				w("\t\t\tdefault:[");
			}
			if (context.get("pass") == PASS.THREE) {
				for(Size rowSize :layout.getRows()){
					w(String.format("\'%s\',",rowSize));
				}
			}
			if (context.get("pass") == PASS.FOUR) {
				for(Size colSize :layout.getColumns()){
					w(String.format("\'%s\',",colSize));
				}
			}
			w("],\n");
		}
	}
	@Override
	public void visit(Theme theme) {

		w("\t\t\t\tfont: {\n");
		w("\t\t\t\t\tfamily: '" + theme.getFontStyle().getFamily() +"'\n");
		if(theme.getFontStyle().getSize()!=0)
		w("\t\t\t\t\t,size: '"+ theme.getFontStyle().getSize()+"px'\n");
		w("\t\t\t\t}\n");

	}

	@Override
	public void visit(QuizInfo quizInfo) {
		if(quizInfo.getTitle()!=null){
			quizInfo.getTitle().accept(this);
		}
		if(quizInfo.getTheme()!=null){
			quizInfo.getTheme().accept(this);
		}


	}

	@Override
	public void visit(Question question) {
		question.getStatement().accept(this);
		question.getAnswer().accept(this);
	}


	@Override
	public void visit(SingleAnswer singleAnswer) {
		if(context.get("pass") == PASS.ONE) {
			w(String.format(" onAnswerClick, "));
		}
		if(context.get("pass") == PASS.FIVE) {
			w("\t\t\t\t\t\t{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{\n\t\t\t\t\t\t\treturn ");
			singleAnswer.getAnswer().accept(this);
			w("\t\t\t\t\t\t})}\n");
		}
	}

	@Override
	public void visit(MultipleAnswer multipleAnswer) {
		if(context.get("pass") == PASS.ONE) {
			w(String.format(" onMultipleAnswerChange, "));
		}
		if(context.get("pass") == PASS.FIVE) {
			multipleAnswer.getAnswer().accept(this);
		}
	}

	@Override
	public void visit(Statement statement) {

		statement.getStatement().accept(this);
	}

	@Override
	public void visit(Timer timer) {
		if(context.get("pass") == PASS.ONE) {
			w(String.format(" onTimerChange, "));
		}
		if(context.get("pass") == PASS.FIVE) {
			timer.getClockComponent().accept(this);
		}

	}

	@Override
	public void visit(TextComponent textComponent) {
		if(context.get("pass") == PASS.FIVE) {
			w("\t\t\t\t\t\t<Text");
			if (textComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", textComponent.getSize()));
			}
			if (textComponent.getTextAlign() != null) {
				w(String.format(" textAlign=\'%s\' ", textComponent.getTextAlign()));
			}
			if (textComponent.getColor() != null) {
				w(String.format(" color=\'%s\' ", textComponent.getColor()));
			}
			w(String.format(" >{%s}</Text>\n", textComponent.getVariableName()));
		}

	}

	@Override
	public void visit(ButtonComponent buttonComponent) {
		if(context.get("pass") == PASS.FIVE) {
			w("<Button");
			w(String.format(" primary={%s} ", buttonComponent.getPrimary()));

			if (buttonComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", buttonComponent.getSize()));
			}
			if (buttonComponent.getMargin() != null) {
				w(String.format(" margin=\'%s\' ", buttonComponent.getMargin()));
			}
			if (buttonComponent.getColor() != null) {
				w(String.format(" color=\'%s\' ", buttonComponent.getColor()));
			}
			w(String.format(" onClick={()=>{ this.setState({ quiz : %s})}} ", buttonComponent.getFunctionName()));

			w(String.format(" label={%s} ", buttonComponent.getVariableName()));

			w(String.format(" />\n"));
		}
	}

	@Override
	public void visit(CheckBoxComponent checkBoxComponent) {
		if(context.get("pass") == PASS.FIVE) {
			w("<CheckBoxGroup");
			w(String.format(" options = { %s }", checkBoxComponent.getVariableName()));
			w(String.format(" onChange={ ({ value, option }) => { this.setState ({ quiz : %s}) } }", checkBoxComponent.getFunctionName()));
			w(String.format(" gap = \'%s\' ", checkBoxComponent.getGap()));


			w(String.format(" />\n"));
		}
	}

	@Override
	public void visit(ClockComponent clockComponent) {

		if(context.get("pass") == PASS.FIVE) {
			w("\t\t\t\t\t\t<Clock");
			w(String.format(" run=\'%s\' ", clockComponent.getClockDirection()));
			w(String.format(" type=\'%s\' ", clockComponent.getType()));
			if (clockComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", clockComponent.getSize()));
			}
			w(String.format(" time=\'T%s\' ", clockComponent.getStartChrono()));
			w(String.format(" alignSelf=\'%s\' ", clockComponent.getSelfAlign()));
			w(String.format(" precision=\'%s\' ", clockComponent.getPrecision()));
			w(String.format(" onChange={%s} ", clockComponent.getFunctionName()));

			w(String.format(" />\n"));
		}
	}

}
