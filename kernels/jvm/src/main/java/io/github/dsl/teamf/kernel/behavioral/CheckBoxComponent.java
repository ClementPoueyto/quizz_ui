package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Size;

public class CheckBoxComponent extends ButtonComponent {
    private Size gap = Size.SMALL;
    public Size getGap() {
        return gap;
    }

    public void setGap(Size gap) {
        this.gap = gap;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
