package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class TextInputComponent extends TextComponent {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
