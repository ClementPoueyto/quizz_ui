package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.PictureComponent;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Question extends QuizElement {
    private List<Statement> statements;

    private List<Answer> answers;

    public Question() {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
