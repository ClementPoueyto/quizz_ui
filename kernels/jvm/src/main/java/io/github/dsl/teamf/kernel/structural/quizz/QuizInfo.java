package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.QuizElement;

public class QuizInfo extends QuizElement {
    private TextComponent title;
    private TextComponent theme;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public TextComponent getTitle() {
        return title;
    }

    public void setTitle(TextComponent title) {
        this.title = title;
    }

    public TextComponent getTheme() {
        return theme;
    }

    public void setTheme(TextComponent theme) {
        this.theme = theme;
    }
}
