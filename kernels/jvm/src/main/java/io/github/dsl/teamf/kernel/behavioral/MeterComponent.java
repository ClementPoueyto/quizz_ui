package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.ui.Size;

import java.awt.*;

public class MeterComponent extends UiComponent{

    private Size size = Size.large;
    private String color;
    private String backgroundColor;
    private String functionName;
    private Size margin = Size.xsmall;
    private String maxValue = "100";
    private Size thickness = Size.medium;
    private MeterType type = MeterType.circle;

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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Size getMargin() {
        return margin;
    }

    public void setMargin(Size margin) {
        this.margin = margin;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public Size getThickness() {
        return thickness;
    }

    public void setThickness(Size thickness) {
        this.thickness = thickness;
    }

    public MeterType getType() {
        return type;
    }

    public void setType(MeterType type) {
        this.type = type;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
