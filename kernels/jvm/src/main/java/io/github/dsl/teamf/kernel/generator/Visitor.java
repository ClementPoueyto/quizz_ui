package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.QuizInfo;
import io.github.dsl.teamf.kernel.structural.Grid;
import io.github.dsl.teamf.kernel.structural.Theme;
import io.github.dsl.teamf.kernel.structural.Zone;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Zone zone);
	public abstract void visit(Grid grid);
	public abstract void visit(Theme theme);
	public abstract void visit(QuizInfo quizInfo);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

