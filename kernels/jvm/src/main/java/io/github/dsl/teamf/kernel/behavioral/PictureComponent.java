package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.ui.Size;

public class PictureComponent extends UiComponent{
    private String path="this.state.quiz.questions[i].statement.image";
    private Size height=Size.auto;
    private Size width=Size.auto;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Size getHeight() {
        return height;
    }

    public void setHeight(Size height) {
        this.height = height;
    }

    public Size getWidth() {
        return width;
    }

    public void setWidth(Size width) {
        this.width = width;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
