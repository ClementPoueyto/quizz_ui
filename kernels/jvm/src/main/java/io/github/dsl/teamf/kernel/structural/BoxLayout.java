package io.github.dsl.teamf.kernel.structural;

import io.github.dsl.teamf.kernel.behavioral.Direction;
import io.github.dsl.teamf.kernel.behavioral.UIComponent;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

import java.util.List;

public class BoxLayout extends Layout implements Visitable {
    private List<UIComponent> contents;
    private boolean isFlex = true;
    private Direction direction = Direction.COLUMN;
    private String background = "unset";
    private boolean bindedToQuestions = false;

    public BoxLayout(String name) {
        super(name);
    }

    public boolean isBindedToQuestions() {
        return bindedToQuestions;
    }

    public void setBindedToQuestions(boolean bindedToQuestions) {
        this.bindedToQuestions = bindedToQuestions;
    }

    public List<UIComponent> getContents() {
        return contents;
    }

    public void setContents(List<UIComponent> contents) {
        this.contents = contents;
    }

    public boolean isFlex() {
        return isFlex;
    }

    public void setFlex(boolean flex) {
        isFlex = flex;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
