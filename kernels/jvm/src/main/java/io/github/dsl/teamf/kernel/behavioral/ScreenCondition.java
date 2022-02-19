package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.generator.Visitable;

public class ScreenCondition implements Visitable{
    
    ScreenSize screenSize;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);      
    }

    public ScreenSize getScreenSize(){
        return this.screenSize;
    }

    public void setScreenSize(ScreenSize screenSize){
        this.screenSize = screenSize;
    }

    
    public String getScreenConditionName(){
        return screenSize.getGrommetName(); 
    }
}
