package io.github.dsl.teamf.kernel;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.structural.ui.Grid;


public class App implements NamedElement, Visitable {
	private Grid grid;
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
