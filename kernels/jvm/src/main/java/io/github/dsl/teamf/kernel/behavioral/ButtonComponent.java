package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.ui.Size;

public class ButtonComponent extends UiComponent{

    private Size size;
    private String color;
    private String variableName;
    private Size margin;
    private Boolean primary=true;
    private String functionNameOnClick;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getFunctionNameOnClick() {
        return functionNameOnClick;
    }

    public void setFunctionNameOnClick(String functionNameOnClick) {
        this.functionNameOnClick = functionNameOnClick;
    }

    public Size getMargin() {
        return margin;
    }

    public void setMargin(Size margin) {
        this.margin = margin;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}
