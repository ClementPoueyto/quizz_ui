package io.github.dsl.teamf.kernel.structural.samples;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
import io.github.dsl.teamf.kernel.generator.ToWiring;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.ui.Border;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Layout;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Zone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mock {

	public static void main(String[] args) {

		// Building the App
		App theSwitch = new App();
		theSwitch.setName("Switch!");
		Grid grid = new Grid();
		Size row1 = Size.small;
		Size row2 = Size.medium;

		Size col1 = Size.small;
		Size col2 = Size.large;
		
		
		grid.setLayouts(new ArrayList<>());

		ScreenCondition screenCondition = new ScreenCondition();
		screenCondition.setScreenSize(ScreenSize.PHONE);
		

		Zone header = new Zone();
		Border border = new Border();
		border.setSize(Size.small);
		header.setName("header");
		header.setColor("light-5");
		header.setRounding(Size.small);
		header.setBorder(border);
		grid.getZones().add(header);
		
		Zone middle = new Zone();
		middle.setColor("light-3");
		grid.getZones().add(middle);
		
		Zone left = new Zone();
		middle.setName("middle");
		left.setName("left");
		left.setColor("brand");
		grid.getZones().add(left);
		
		
		List<Size> rowsDefault = new ArrayList<>();
		rowsDefault.add(row1);
		rowsDefault.add(row2);

		List<Size> colsDefault = new ArrayList<>();
		colsDefault.add(col1);
		colsDefault.add(col2);

		Layout defaultLayout = new Layout();
		defaultLayout.setArrangement(new ArrayList());
		defaultLayout.getArrangement().add(Arrays.asList(header,header));
		defaultLayout.getArrangement().add(Arrays.asList(left,middle));
		defaultLayout.setColumns(colsDefault);
		defaultLayout.setRows(rowsDefault);
		grid.getLayouts().add(defaultLayout);


		List<Size> rowsPhone = new ArrayList<>();
		rowsPhone.add(row1);
		rowsPhone.add(row2);

		List<Size> colsPhone = new ArrayList<>();
		rowsPhone.add(col2);

		Layout phoneLayout = new Layout();
		phoneLayout.setArrangement(new ArrayList());
		phoneLayout.getArrangement().add(Arrays.asList(header));
		phoneLayout.getArrangement().add(Arrays.asList(middle));
		phoneLayout.setColumns(colsPhone);
		phoneLayout.setRows(rowsPhone);
		phoneLayout.setScreenCondition(screenCondition);
		grid.getLayouts().add(phoneLayout);
		//grid.setColumns(rows);
		//grid.setColumns(cols);
;
		QuizInfo quizInfo = new QuizInfo();
		TextComponent title = new TextComponent();
		title.setSize(Size.large);
		title.setTextAlign(TextAlign.center);
		quizInfo.setTitle(title);
		
		TextComponent theme = new TextComponent();
		theme.setSize(Size.medium);
		theme.setTextAlign(TextAlign.center);
		quizInfo.setTheme(theme);
		
		header.setQuizElement(quizInfo);
		
		
		//QUESTION
		Question question= new Question();
		MultipleAnswer answer = new MultipleAnswer();
		//PictureStatement statement = new PictureStatement();
		TextStatement statement = new TextStatement();

		TextComponent textStatement = new TextComponent();
		textStatement.setSize(Size.medium);
		statement.setStatement(textStatement);

	/*	PictureComponent picture=new PictureComponent();
		picture.setPath("./Hello");
		statement.setPicture(picture);*/

		ButtonComponent answerButton = new ButtonComponent();
		answerButton.setMargin(Size.xsmall);
		answerButton.setSize(Size.large);
		answerButton.setColor("dark-2");

		CheckBoxComponent checkBoxComponent = new CheckBoxComponent();

		answer.setAnswer(checkBoxComponent);


		question.setAnswer(answer);
		question.setStatement(statement);

		middle.setQuizElement(question);

		Timer timer = new Timer();
		ClockComponent clockComponent = new ClockComponent();
		clockComponent.setClockDirection(ClockDirection.backward);
		clockComponent.setSize(Size.large);
		clockComponent.setStartChrono("00:01:00");
		timer.setClockComponent(clockComponent);

		left.setQuizElement(timer);

		theSwitch.setGrid(grid);

		theSwitch.getQuizElementList().add(question);
		theSwitch.getQuizElementList().add(timer);

		// Generating Code
		Visitor codeGenerator = new ToWiring();
		theSwitch.accept(codeGenerator);

		// Printing the generated code on the console
		System.out.println(codeGenerator.getResult());
	}

}
