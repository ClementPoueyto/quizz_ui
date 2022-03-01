package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.behavioral.PictureComponent;

public class PictureStatement extends Statement{

    private PictureComponent picture;

    public PictureStatement(){
        setPicture(new PictureComponent());

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public PictureComponent getPicture() {
        return picture;
    }

    public void setPicture(PictureComponent picture) {
        this.picture = picture;
    }
}
