package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.ui.Size;

public class CheckBoxComponent extends UiComponent{

    private String functionName;
    private String variableName;
    private Size gap = Size.small;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Size getGap() {
        return gap;
    }

    public void setGap(Size gap) {
        this.gap = gap;
    }
}
