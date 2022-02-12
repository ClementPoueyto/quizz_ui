package io.github.dsl.teamf.kernel.structural.samples;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.generator.ToWiring;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Color;
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
		Size row = Size.large;
		Size col = Size.medium;
		List<Size> rows = new ArrayList<>();
		rows.add(row);
		List<Size> cols = new ArrayList<>();
		cols.add(col);
		grid.setColumns(cols);
		grid.setColumns(rows);
		grid.setRows(rows);
		grid.setColumns(cols);
		Zone header = new Zone();
		header.setName("test");
		header.setStart(new int[]{0, 1});
		header.setEnd(new int[]{0, 1});
		header.setColor(Color.blue);
		grid.getZones().add(header);
		theSwitch.setGrid(grid);
		// Generating Code
		Visitor codeGenerator = new ToWiring();
		theSwitch.accept(codeGenerator);

		// Printing the generated code on the console
		System.out.println(codeGenerator.getResult());
	}

}
