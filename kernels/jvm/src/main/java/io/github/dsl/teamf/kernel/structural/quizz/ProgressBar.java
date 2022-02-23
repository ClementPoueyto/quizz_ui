package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.MeterComponent;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class ProgressBar extends QuizElement {
    private MeterComponent meter;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    public MeterComponent getMeter() {
        return meter;
    }

    public void setMeter(MeterComponent meter) {
        this.meter = meter;
    }
}
