package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Navigation implements Visitable {
    private boolean onlyNext = false;
    private String previousLabel = "Précédent";
    private String nextLabel = "Suivant";

    public boolean isOnlyNext() {
        return onlyNext;
    }

    public String getPreviousLabel() {
        return previousLabel;
    }

    public void setPreviousLabel(String previousLabel) {
        this.previousLabel = previousLabel;
    }

    public String getNextLabel() {
        return nextLabel;
    }

    public void setNextLabel(String nextLabel) {
        this.nextLabel = nextLabel;
    }

    public void setOnlyNext(boolean onlyNext) {
        this.onlyNext = onlyNext;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
