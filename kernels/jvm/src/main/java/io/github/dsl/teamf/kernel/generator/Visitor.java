package io.github.dsl.teamf.kernel.generator;

import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.ButtonComponent;
import io.github.dsl.teamf.kernel.behavioral.ClockComponent;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Theme;
import io.github.dsl.teamf.kernel.structural.ui.Zone;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Zone zone);
	public abstract void visit(Grid grid);
	public abstract void visit(Theme theme);
	public abstract void visit(QuizInfo quizInfo);
	public abstract void visit(Question question);
	public abstract void visit(Answer answer);
	public abstract void visit(Statement statement);
	public abstract void visit(Timer timer);

	public abstract void visit(TextComponent textComponent);
	public abstract void visit(ButtonComponent buttonComponent);
	public abstract void visit(ClockComponent clockComponent);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

