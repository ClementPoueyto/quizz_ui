package io.github.dsl.teamf.kernel;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Layout;
import io.github.dsl.teamf.kernel.behavioral.Theme;
import io.github.dsl.teamf.kernel.generator.Visitable;

public class App implements NamedElement, Visitable {
	private String name;
	private String quizPath;
	private Layout layout;
	private Theme theme;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getQuizPath() {
		return quizPath;
	}

	public void setQuizPath(String quizPath) {
		this.quizPath = quizPath;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
