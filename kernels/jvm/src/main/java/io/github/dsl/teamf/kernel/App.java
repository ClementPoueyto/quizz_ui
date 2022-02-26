package io.github.dsl.teamf.kernel;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.generator.Visitable;

public class App implements NamedElement, Visitable {
	private String name;
	private String quizPath;

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

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
