package io.github.dsl.teamf.kernel.structural.ui;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Grid implements Visitable {

    private List<Zone> zones = new ArrayList<>();
    private List<Layout> layouts;
    //private Layout defaultLayout;

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

    public List<Layout> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    public boolean isResponsiveGrid(){
        for(Layout layout: layouts){
            if(layout.getScreenCondition()!=null){
                return true;
            }
        }
        return false;
    }
    
}
