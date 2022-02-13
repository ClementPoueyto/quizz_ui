package io.github.dsl.teamf.kernel.structural.samples;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.TextAlign;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.ToWiring;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.QuizInfo;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Zone;

import java.util.ArrayList;
import java.util.List;

public class Switch {

	public static void main(String[] args) {

		// Building the App
		App theSwitch = new App();
		theSwitch.setName("Switch!");
		Grid grid = new Grid();
		Size row1 = Size.small;
		Size row2 = Size.medium;

		Size col1 = Size.small;
		Size col2 = Size.large;
		List<Size> rows = new ArrayList<>();
		rows.add(row1);
		rows.add(row2);

		List<Size> cols = new ArrayList<>();
		cols.add(col1);
		cols.add(col2);

		grid.setColumns(cols);
		grid.setColumns(rows);
		grid.setRows(rows);
		grid.setColumns(cols);

		Zone header = new Zone();

		header.setName("header");
		header.setStart(new int[]{0, 0});
		header.setEnd(new int[]{1, 0});
		header.setColor("light-5");
		grid.getZones().add(header);

		Zone middle = new Zone();
		middle.setName("middle");
		middle.setStart(new int[]{1,1});
		middle.setEnd(new int[]{1,1});
		middle.setColor("light-3");
		grid.getZones().add(middle);

		Zone left = new Zone();
		left.setName("left");
		left.setStart(new int[]{0, 0});
		left.setEnd(new int[]{0, 1});
		left.setColor("brand");
		grid.getZones().add(left);

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

		theSwitch.setGrid(grid);

		// Generating Code
		Visitor codeGenerator = new ToWiring();
		theSwitch.accept(codeGenerator);

		// Printing the generated code on the console
		System.out.println(codeGenerator.getResult());
	}

}
