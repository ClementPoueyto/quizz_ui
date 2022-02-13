package io.github.dsl.teamf.kernel.structural.ui;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Grid implements Visitable {

    private List<Zone> zones = new ArrayList<>();
    private Size gap;
    private List<Size> rows;
    private List<Size> columns;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
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
}
