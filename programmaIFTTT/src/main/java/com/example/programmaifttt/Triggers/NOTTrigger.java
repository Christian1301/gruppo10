package com.example.programmaifttt.Triggers;

import java.io.IOException;

public class NOTTrigger extends Trigger{

    private Trigger trigger;

    public NOTTrigger(String name, Trigger trigger) {
        super(name, "NOT", "NOT " + trigger.getName());
        this.trigger = trigger;
    }

    @Override
    public boolean evaluate() {
        return !trigger.evaluate();
    }

    @Override
    public boolean isEvaluable() throws IOException {
        return trigger.isEvaluable();
    }

    //create a funcion that given a trigger checks if it is used in the NOTTrigger
    @Override
    public boolean isUsedIn(Trigger trigger){
        return this.trigger.equals(trigger) || this.trigger.isUsedIn(trigger);
    }
}
