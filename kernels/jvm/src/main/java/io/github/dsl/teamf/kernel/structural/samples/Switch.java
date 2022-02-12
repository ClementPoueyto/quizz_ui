package io.github.dsl.teamf.kernel.structural.samples;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.generator.ToWiring;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Grid;
import io.github.dsl.teamf.kernel.structural.Size;
import io.github.dsl.teamf.kernel.structural.Zone;

import java.util.ArrayList;
import java.util.List;

public class Switch {

	public static void main(String[] args) {

		// Building the App
		App theSwitch = new App();
		theSwitch.setName("Switch!");
		Grid grid = new Grid();
		Size row1 = Size.medium;
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
<<<<<<< Updated upstream
		header.setName("test");
		header.setStart(new int[]{0, 1});
		header.setEnd(new int[]{0, 1});
		header.setColor(Color.BLUE);
=======
		header.setName("header");
		header.setStart(new int[]{0, 0});
		header.setEnd(new int[]{1, 0});
		header.setColor("blue");
>>>>>>> Stashed changes
		grid.getZones().add(header);

		Zone left = new Zone();
		left.setName("left");
		left.setStart(new int[]{0, 0});
		left.setEnd(new int[]{0, 1});
		left.setColor("red");
		grid.getZones().add(left);

		theSwitch.setGrid(grid);

		// Generating Code
		Visitor codeGenerator = new ToWiring();
		theSwitch.accept(codeGenerator);

		// Printing the generated code on the console
		System.out.println(codeGenerator.getResult());
	}

}
