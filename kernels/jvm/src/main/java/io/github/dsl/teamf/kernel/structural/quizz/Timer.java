package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.ClockComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Timer extends QuizElement{

    private ClockComponent clockComponent;


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ClockComponent getClockComponent() {
        return clockComponent;
    }

    public void setClockComponent(ClockComponent clockComponent) {
        clockComponent.setFunctionName("onTimerChange(this.state.quiz,time)");
        this.clockComponent = clockComponent;
    }
}
