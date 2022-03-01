package io.github.dsl.teamf.kernel.generator;

import java.io.Console;
import java.util.List;
import java.util.Locale;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.ui.Border;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Layout;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Theme;
import io.github.dsl.teamf.kernel.structural.ui.Zone;


/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {IMPORT, FUNCTION,FUNCTIONJSX, GLOBAL, VARIABLE, ROW, COLUMN, GAP, JSX }


	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s",s));
	}

	@Override
	public void visit(App app) {
		context.put("pass", PASS.IMPORT); //import components
		w("import React, { Component } from 'react'\n");
		w("import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange, onQuizEnd");
		/*for(QuizElement quizElement : app.getQuizElementList()){
			quizElement.accept(this);
		}*/
		w("} from './functions'\n");
		w("import { Grommet, ");
		app.getGrid().accept(this);
		w(" } from 'grommet'\n");
		w("import { deepMerge } from \"grommet/utils\";\n");
		w("import { grommet } from \"grommet/themes\";\n");
		w("var data = require('./quiz.json');\n\n");
		w("export default class App extends Component {\n\n");
		w("\tconstructor() {\n" +
				"\t\tsuper();\n" +
				"\t\tthis.state = { quiz : data,\n" +
				"\t\tindexQuestion : 0}\n" +
				"\t}\n\n");
		context.put("pass", PASS.FUNCTION); // Function
		app.getGrid().accept(this);
		context.put("pass", PASS.FUNCTIONJSX); // Function
		app.getGrid().accept(this);
		w("\trender() {\n");



			w("\t\tvar customBreakpoints = deepMerge(grommet, {\n");
			w("\t\t\tglobal: {\n");
		context.put("pass", PASS.GLOBAL); //describe components
		app.getGrid().accept(this);
		context.put("pass",PASS.VARIABLE);
		if(app.getTheme()!=null){
			app.getTheme().accept(this);
		}
		w("\t\t\t}\n");
		w("\t\t});\n");



		app.getGrid().accept(this);
		w("\t\treturn (\n");
		context.put("pass", PASS.JSX); //describe components
		app.getGrid().accept(this);


		w("\t\t</Grommet>\n");
		w("\t\t);\n\t}\n}\n");

	}

	@Override
	public void visit(Zone zone) {
		if(context.get("pass") == PASS.VARIABLE) {
			w(String.format("\"%s\",",zone.getName()));
		}
		if(context.get("pass") == PASS.FUNCTION){
			if(zone.getQuizElement()!=null){
				zone.getQuizElement().accept(this);
			}
		}
		if(context.get("pass") == PASS.FUNCTIONJSX){
			if(zone.getQuizElement()!=null){
				zone.getQuizElement().accept(this);
			}
		}
		if(context.get("pass") == PASS.JSX){
			w("\t\t\t\t\t{\n");
			w("\t\t\t\t\t\tc_areas =  areas[size] ? areas[size] : areas[\"default\"],\n");
			w(String.format("\t\t\t\t\t\tc_areas.find((row) => row.indexOf(\"%s\") >=0) ?\n",zone.getName()));
			
	/*		if(zone.getQuizElement() instanceof Question)
			{
				if(((Question) zone.getQuizElement()).getStatement() instanceof PictureStatement)
					context.put("pass",PASS.JSX);
			}*/

			w(String.format("\t\t\t\t\t\t<Box overflow='auto' gridArea=\'%s\' align=\'%s\'  ", zone.getName(),zone.getAlignement()));
			if(zone.getColor()!=null){
				w(String.format(" background=\'%s\' ", zone.getColor().toLowerCase()));
			}
			if(zone.getRounding() != null){
				w(String.format("round=\'%s\' ",zone.getRounding()));
			}
			if(zone.getBorder() != null){
				zone.getBorder().accept(this);
			}
			w(">\n");
			
		/*	w(String.format("\t\t\t\t\t\t<Box "));
			if(context.get("pass")==PASS.JSX)
				zone.getQuizElement().accept(this);
			context.put("pass",PASS.JSX);
			w(String.format("gridArea=\'%s\' align=\'%s\' background=\'%s\'>\n", zone.getName(),zone.getAlignement(), zone.getColor()));
		*/
			if(zone.getQuizElement()!=null){
				zone.getQuizElement().accept(this);
			}
			w("\t\t\t\t\t\t</Box>\n");
			w("\t\t\t\t\t\t:\n");
			w("\t\t\t\t\t\t<Box/>\n");
			w("\t\t\t\t\t}\n");
		}


	}
	@Override
	public void visit(ScreenCondition displayCondition) {
	}

	@Override
	public void visit(Grid grid) {
		if (context.get("pass") == PASS.IMPORT) {
			w("Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter");
		}
		if(context.get("pass") == PASS.FUNCTIONJSX){
			for (Zone zone : grid.getZones()) {
				zone.accept(this);
			}
		}
		if(context.get("pass") == PASS.FUNCTION){
			for (Zone zone : grid.getZones()) {
				zone.accept(this);
			}
		}
		if(context.get("pass") == PASS.GLOBAL) {

			if (grid.isResponsiveGrid()) {
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
		}
		if(context.get("pass")==PASS.VARIABLE){
			
			w("\t\tvar c_areas= []\n");
			w("\t\tconst areas = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("\t\t}\n");
			context.put("pass", PASS.ROW);
			w("\t\tconst rows = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("\t\t}\n");
	
			context.put("pass", PASS.COLUMN);
			w("\t\tconst columns = {\n");
			
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("}\n");

			context.put("pass", PASS.GAP);
			w("\t\tconst gaps = {\n");
			for(Layout layout: grid.getLayouts()){
				layout.accept(this);
			}
			w("}\n");
		}
		if (context.get("pass") == PASS.JSX) {
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
			w("\t\t\t\t\t\tgap={gaps[size] ? gaps[size] : gaps[\"default\"]}\n");
			w("\t\t\t\t\t\tareas={areas[size] ? areas[size] : areas[\"default\"]}>\n");
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
		if (context.get("pass") == PASS.VARIABLE) {
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
		}
		else if (context.get("pass") == PASS.GAP) {
			if(layout.getScreenCondition() !=null){
				w(String.format("\t\t\t%s:",layout.getScreenCondition().getScreenConditionName()));
			}else{
				w("\t\t\tdefault:");
			}
			
			w(String.format("\'%s\',\n",layout.getGap()));	
		}
		else{
			if(layout.getScreenCondition() !=null){
				w(String.format("\t\t\t%s:[",layout.getScreenCondition().getScreenConditionName()));
			}else{
				w("\t\t\tdefault:[");
			}
			if (context.get("pass") == PASS.ROW) {
				for(Size rowSize :layout.getRows()){
					w(String.format("\'%s\',",rowSize));
				}
			}
			if (context.get("pass") == PASS.COLUMN) {
				for(Size colSize :layout.getColumns()){
					w(String.format("\'%s\',",colSize));
				}
			}
			w("],\n");
		}
	}
	@Override
	public void visit(Theme theme) {
		if(theme.getFontStyle()!=null){
			w("\t\t\t\tfont: {\n");
			w("\t\t\t\t\tfamily: '" + theme.getFontStyle().getFamily() +"'\n");
			if(theme.getFontStyle().getSize()!=0)
				w("\t\t\t\t\t,size: '"+ theme.getFontStyle().getSize()+"px'\n");
			w("\t\t\t\t}\n");
		}


	}

	@Override
	public void visit(QuizInfo quizInfo) {
		if(context.get("pass") == PASS.JSX) {
			if (quizInfo.getTitle() != null) {
				w("\t\t\t\t\t\t");
				quizInfo.getTitle().accept(this);
			}
			if (quizInfo.getTheme() != null) {
				w("\t\t\t\t\t\t");
				quizInfo.getTheme().accept(this);
			}
		}


	}
	@Override
	public void visit(Page page) {
		if(context.get("pass") == PASS.IMPORT) {
			page.getQuestion().accept(this);
			page.getQuestion().accept(this);
		}
		if (context.get("pass") == PASS.FUNCTIONJSX) {
			w("\trenderQuestion(){\n");
			if(page.getNavigation() == null){
				w("\t\treturn this.state.quiz.questions.map((question,i)=>{\n");
				w("\t\treturn(<>\n");
				page.getQuestion().accept(this);
				w("\t\t\t\t\t</>)})\n");
			}
			else{
				w("\t\tlet i = this.state.indexQuestion;\n\t\t");
				w("\t\treturn(<>\n");
				page.getQuestion().accept(this);
				w("\t\t\t\t\t</>)\n");			}
			w("\t}\n");
		}
		if (context.get("pass") == PASS.JSX) {
			w("\t\t\t\t\t\t\t{this.renderQuestion()}\n");
		}
	}
	@Override
	public void visit(Question question) {
		if(context.get("pass") == PASS.FUNCTIONJSX) {
			w("\t\t\t\t\t");
			question.getStatement().accept(this);
			w("\t\t\t\t\t");
			question.getAnswer().accept(this);
		}
	}


	@Override
	public void visit(SingleAnswer singleAnswer) {
		if(context.get("pass") == PASS.IMPORT) {
			w(String.format(" onAnswerClick, "));
		}
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("{this.state.quiz.questions[i].answers.map((item,index)=>{\n\t\t\t\t\t\t\treturn ");
			singleAnswer.getAnswer().accept(this);
			w("\t\t\t\t\t})}\n");
		}
	}

	@Override
	public void visit(Navigation navigation) {
		if(context.get("pass") == PASS.FUNCTION) {
			w("nextQuestion(){\n");
			w("\tif(this.state.indexQuestion !== this.state.quiz.questions.length-1){\n");
			w("\t\tthis.setState(prevState => {\n");
			w("\t\t\treturn {indexQuestion: prevState.indexQuestion + 1}\n");
			w("\t\t})\n");
			w("\t}\n");
			w("}\n");
			if(navigation.getPrecedent() != null){
				w("previousQuestion(){\n");
				w("\tif(this.state.indexQuestion !== 0){ \n");
				w("\t\tthis.setState(prevState => {\n");
				w("\t\t\treturn {indexQuestion: prevState.indexQuestion - 1}\n");
				w("\t\t})\n");
				w("\t}\n");
				w("}\n");
			}
		}
		if(context.get("pass") == PASS.JSX){
			w("\t\t\t\t\t\t<Box  background=\"unset\" flex={true} fill={true} direction=\"row\" basis=\"auto\" >\n");
			if(navigation.getPrecedent() != null){
				w("\t\t\t\t\t\t{this.state.indexQuestion!==0 && \n");
				navigation.getPrecedent().setFunctionName("this.previousQuestion()");
				w("\t\t\t\t\t\t\t");
				navigation.getPrecedent().accept(this);
				w("\t\t\t\t\t\t}\n");
			}
			w("\t\t\t\t\t\t{this.state.indexQuestion!==this.state.quiz.questions.length-1?\n");

			navigation.getSuivant().setFunctionName("this.nextQuestion()");
			w("\t\t\t\t\t\t\t\t");
			navigation.getSuivant().accept(this);
			w("\t\t\t\t\t\t\t:\n");
			w("\t\t\t\t\t\t\t\t");
			navigation.getSuivant().setFunctionName("onQuizEnd(this.state)");
			navigation.getSuivant().accept(this);
			w("\t\t\t\t\t\t\t}\n");

			w("\t\t\t\t\t\t</Box>\n");
		}
	}



	@Override
	public void visit(MultipleAnswer multipleAnswer) {
		if(context.get("pass") == PASS.IMPORT) {
			w(String.format(" onMultipleAnswerChange, "));
		}
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			multipleAnswer.getAnswer().accept(this);
		}
	}

	public void visit(TextStatement textStatement) {
		if(context.get("pass")==PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX)
		textStatement.getStatement().accept(this);
	}

	@Override
	public void visit(PictureStatement pictureStatement){
		/*if(context.get("pass")==PASS.IMPORT){
			w(String.format("Image, "));
		}*/

			pictureStatement.getPicture().accept(this);

	}

	@Override
	public void visit(Timer timer) {
		if(context.get("pass") == PASS.IMPORT) {
			w(String.format(" onTimerChange, "));
		}
		if(context.get("pass") == PASS.JSX) {
			w("\t\t\t\t\t\t");
			timer.getClockComponent().accept(this);
		}

	}

	@Override
	public void visit(ProgressBar progressBar) {

		if(context.get("pass") == PASS.JSX) {
			w("\t\t\t\t\t\t");
			progressBar.getMeter().accept(this);
		}
	}

	@Override
	public void visit(MeterComponent meterComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<Meter");
			if (meterComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", meterComponent.getSize()));
			}
			if (meterComponent.getType() != null) {
				w(String.format(" type=\'%s\' ", meterComponent.getType()));
			}
			if (meterComponent.getColor() != null) {
				w(String.format(" color=\'%s\' ", meterComponent.getColor().toLowerCase()));
			}
			if (meterComponent.getBackgroundColor() != null) {
				w(String.format(" background=\'%s\' ", meterComponent.getBackgroundColor().toLowerCase()));
			}
			if (meterComponent.getThickness() != null) {
				w(String.format(" thickness=\'%s\' ", meterComponent.getThickness()));
			}
			w(String.format( "value = {this.state.indexQuestion*100/this.state.quiz.questions.length} "));
			w(String.format(" />\n"));
		}
	}

	@Override
	public void visit(TextComponent textComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<Text");
			if (textComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", textComponent.getSize()));
			}
			if (textComponent.getTextAlign() != null) {
				w(String.format(" textAlign=\'%s\' ", textComponent.getTextAlign()));
			}
			if (textComponent.getColor() != null) {
				w(String.format(" color=\'%s\' ", textComponent.getColor().toLowerCase()));
			}
			w(String.format(" >{%s}</Text>\n", textComponent.getVariableName()));
		}

	}
	@Override
	public void visit(PictureComponent pictureComponent)
	{
		if(context.get("pass")==PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX){
			w(String.format("<Box height=\"%s\" width=\"%s\">\n",pictureComponent.getHeight(),pictureComponent.getWidth()));

			w("<Image ");
			w(String.format("src= {%s} ", pictureComponent.getPath()));
			w(String.format("/>"));
			w("</Box>");
		}
/*
		if(context.get("pass")==PASS.JSX)
		{
			w(String.format("height=\"%s\" width=\"%s\"",pictureComponent.getHeight(),pictureComponent.getWidth()));

		}*/
	}

	@Override
	public void visit(ButtonComponent buttonComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<Button");
			w(String.format(" primary={%s} ", buttonComponent.getPrimary()));

			if (buttonComponent.getSize() != null) {
				w(String.format(" size=\'%s\' ", buttonComponent.getSize()));
			}
			if (buttonComponent.getMargin() != null) {
				w(String.format(" margin=\'%s\' ", buttonComponent.getMargin()));
			}
			if (buttonComponent.getColor() != null) {
				w(String.format(" color=\'%s\' ", buttonComponent.getColor().toLowerCase()));
			}
			if (buttonComponent.getAlign() != null) {
				w(String.format(" alignSelf=\'%s\' ", buttonComponent.getAlign()));
			}
			w(String.format(" onClick={()=>{%s}} ", buttonComponent.getFunctionName()));

			w(String.format(" label={%s} ", buttonComponent.getVariableName()));

			w(String.format(" />\n"));
		}
	}

	@Override
	public void visit(CheckBoxComponent checkBoxComponent) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<CheckBoxGroup");
			w(String.format(" options = { %s }", checkBoxComponent.getVariableName()));
			w(String.format(" onChange={ ({ value, option }) => { %s}}", checkBoxComponent.getFunctionName()));
			w(String.format(" gap = \'%s\' ", checkBoxComponent.getGap()));


			w(String.format(" />\n"));
		}
	}

	@Override
	public void visit(ClockComponent clockComponent) {

		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<Clock");
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

	@Override
	public void visit(TextInputComponent textInputComponent) {
		if (context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX) {
			w("<TextInput");
			if (textInputComponent.getPlaceholder() != null)
				w(String.format(" placeholder=\"%s\"", textInputComponent.getPlaceholder()));
			if (textInputComponent.getSize() != null)
				w(String.format(" size=\"%s\"", textInputComponent.getSize()));
			if (textInputComponent.getTextAlign() != null)
				w(String.format(" textAlign=\"%s\"", textInputComponent.getTextAlign()));

			w(String.format("/>\n"));
		}
	}

	@Override
	public void visit(OpenAnswer openAnswer) {
		if (context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX)
			openAnswer.getAnswer().accept(this);
	}

	@Override
	public void visit(Border border) {
		if(context.get("pass") == PASS.JSX || context.get("pass") == PASS.FUNCTIONJSX){
			w("border={{");
			if(border.getColor()!=null){
				w(String.format("color: \"%s\",", border.getColor().toLowerCase()));
			}
			if(border.getSize()!=null){
				w(String.format("size: \"%s\",", border.getSize()));
			}
			if(border.getStyle()!=null){
				w(String.format("style: \"%s\",", border.getStyle()));
			}
			w("}}");
			
		}
		
	}

	@Override
	public void visit(Send send) {
		if(context.get("pass") == PASS.JSX){
			w("\t\t\t\t\t\t");
			if(send.getSend() != null)
				send.getSend().accept(this);
			w("\n");

		}
	}
}
