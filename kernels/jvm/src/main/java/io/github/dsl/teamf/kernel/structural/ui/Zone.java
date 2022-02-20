package io.github.dsl.teamf.kernel.structural.ui;

import io.github.dsl.teamf.kernel.NamedElement;
import io.github.dsl.teamf.kernel.behavioral.ScreenCondition;
import io.github.dsl.teamf.kernel.behavioral.TextAlign;
import io.github.dsl.teamf.kernel.structural.quizz.QuizElement;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;


public class Zone implements NamedElement, Visitable {
    private String name;
    private String color;
    private TextAlign alignement = TextAlign.center;
    private QuizElement quizElement;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public QuizElement getQuizElement() {
        return quizElement;
    }

    public void setQuizElement(QuizElement quizElement) {
        this.quizElement = quizElement;
    }

    public TextAlign getAlignement() {
        return alignement;
    }

    public void setAlignement(TextAlign alignement) {
        this.alignement = alignement;
    }
}
