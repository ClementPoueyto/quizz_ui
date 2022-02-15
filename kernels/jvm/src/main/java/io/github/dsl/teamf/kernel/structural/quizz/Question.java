package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class Question extends QuizElement {
    private Statement statement;

    private Answer answer;


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
