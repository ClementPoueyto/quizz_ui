package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.ButtonComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class SingleAnswer extends Answer{

    private ButtonComponent answer;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ButtonComponent getAnswer() {
        return answer;
    }

    public void setAnswer(ButtonComponent answer) {
        answer.setFunctionName("this.setState(onAnswerClick(this.state.quiz,item,index,i))");
        answer.setVariableName("this.state.quiz.questions[i].answers[index]");
        this.answer = answer;
    }
}
