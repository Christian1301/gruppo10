package com.example.programmaifttt.Triggers;

import java.io.IOException;

public class ANDTrigger extends Trigger{

    public static final String type = "AND";

    private Trigger trigger1;

    private Trigger trigger2;

    public ANDTrigger(String name, Trigger trigger1, Trigger trigger2) {
        super(name, type, trigger1.getName() + " AND " + trigger2.getName());
        this.trigger1 = trigger1;
        this.trigger2 = trigger2;
    }

    @Override
    public boolean evaluate() {
        return trigger1.evaluate() && trigger2.evaluate();
    }

    @Override
    public boolean isEvaluable() throws IOException {
        return trigger1.isEvaluable() && trigger2.isEvaluable();
    }

    //create a funcion that given a trigger checks if it is used in the ANDTrigger
    @Override
    public boolean isUsedIn(Trigger trigger){
        return trigger1.equals(trigger) || trigger2.equals(trigger) || trigger1.isUsedIn(trigger) || trigger2.isUsedIn(trigger);
    }
}
