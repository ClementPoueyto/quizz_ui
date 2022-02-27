package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.BoxLayout;
import io.github.dsl.teamf.kernel.structural.GridLayout;
import io.github.dsl.teamf.kernel.structural.Layout;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(GridLayout grid);

	public abstract void visit(BoxLayout box);

	public abstract void visit(TextComponent text);

	public abstract void visit(Layout layout);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String, Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}
