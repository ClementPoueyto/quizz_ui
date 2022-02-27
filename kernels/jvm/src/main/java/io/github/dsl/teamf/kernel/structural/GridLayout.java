package io.github.dsl.teamf.kernel.structural;

import java.util.HashMap;
import java.util.Map;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class GridLayout extends Layout implements Visitable {
    private Layout layouts[][] = new Layout[0][];
    private Size gap;
    private Map<Integer, Size> sizeByColumnIndex = new HashMap<>();
    private Map<Integer, Size> sizeByRowIndex = new HashMap<>();

    public GridLayout(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Layout[][] getLayouts() {
        return layouts;
    }

    public void setLayouts(Layout[][] layouts) {
        this.layouts = layouts;
    }

    public Size getGap() {
        return gap;
    }

    public void setGap(Size gap) {
        this.gap = gap;
    }

    public Map<Integer, Size> getSizeByColumnIndex() {
        return sizeByColumnIndex;
    }

    public void setSizeByColumnIndex(Map<Integer, Size> sizeByColumnIndex) {
        this.sizeByColumnIndex = sizeByColumnIndex;
    }

    public Map<Integer, Size> getSizeByRowIndex() {
        return sizeByRowIndex;
    }

    public void setSizeByRowIndex(Map<Integer, Size> sizeByRowIndex) {
        this.sizeByRowIndex = sizeByRowIndex;
    }

    
}
