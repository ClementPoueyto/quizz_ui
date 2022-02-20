package io.github.dsl.teamf.kernel.structural.ui;

import java.util.List;

import io.github.dsl.teamf.kernel.behavioral.ScreenCondition;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Layout implements Visitable{
    private ScreenCondition screenCondition;
    private List<List<Zone>> arrangement;
    private Size gap;
    private List<Size> rows;
    private List<Size> columns;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        
    }

    public ScreenCondition getScreenCondition() {
        return screenCondition;
    }

    public void setScreenCondition(ScreenCondition screenCondition) {
        this.screenCondition = screenCondition;
    }
    public Size getGap() {
        return gap;
    }
    public void setGap(Size gap) {
        this.gap = gap;
    }
    public List<Size> getRows() {
        return rows;
    }
    public void setRows(List<Size> rows) {
        this.rows = rows;
    }
    public List<Size> getColumns() {
        return columns;
    }
    public void setColumns(List<Size> columns) {
        this.columns = columns;
    }

    public List<List<Zone>> getArrangement() {
        return arrangement;
    }

    public void setArrangement(List<List<Zone>> arrangement) {
        this.arrangement = arrangement;
    }

}
