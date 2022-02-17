package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.ui.Size;

public class ClockComponent extends UiComponent{

    private Size size;

    private TextAlign selfAlign = TextAlign.center;

    private ClockDirection clockDirection = ClockDirection.forward;

    private String startChrono = "00:00:00";

    private ClockType type = ClockType.digital;

    private String precision = "seconds";

    private String functionName;

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

    public TextAlign getSelfAlign() {
        return selfAlign;
    }

    public void setSelfAlign(TextAlign selfAlign) {
        this.selfAlign = selfAlign;
    }

    public ClockDirection getClockDirection() {
        return clockDirection;
    }

    public void setClockDirection(ClockDirection clockDirection) {
        this.clockDirection = clockDirection;
    }

    public String getStartChrono() {
        return startChrono;
    }

    public void setStartChrono(String startChrono) {
        this.startChrono = startChrono;
    }

    public ClockType getType() {
        return type;
    }

    public void setType(ClockType type) {
        this.type = type;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
