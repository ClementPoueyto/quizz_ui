package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Size;

public class ButtonComponent extends UIComponent  {
    private String functionName;
    private String variableName;

    public ButtonComponent() {
        super();
        this.setSize(Size.MEDIUM);
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
