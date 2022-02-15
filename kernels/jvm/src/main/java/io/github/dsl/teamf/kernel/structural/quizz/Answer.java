package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class Answer extends QuestionElement{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
