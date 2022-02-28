package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.Size;

public class UIComponent implements Visitable {
    private Alignment aligment = Alignment.CENTER;
    private Size size = Size.AUTO;
    private String color = "unset";
    private Size margin = Size.AUTO;
    private Boolean primary = true;

    public Alignment getAligment() {
        return aligment;
    }

    public void setAligment(Alignment aligment) {
        this.aligment = aligment;
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

    public Size getMargin() {
        return margin;
    }

    public void setMargin(Size margin) {
        this.margin = margin;
    }

    public String getGeneralStyle() {
        return String.format(" primary={%s} size=\'%s\'  margin=\'%s\'  " +
                "color=\'%s\' alignSelf=\'%s\' ", this.primary, this.size.value(), this.margin.value(), this.color,
                this.aligment.value());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
