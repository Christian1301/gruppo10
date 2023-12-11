package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.BackEnd.RuleController;

import java.io.IOException;

public class NOTTrigger extends Trigger{

    public static final String type = "NOT";
    private Trigger trigger;

    private RuleController ruleController;

    public NOTTrigger(String name, Trigger trigger) {
        super(name, "NOT", "NOT " + trigger.getName());
        this.trigger = trigger;
        this.ruleController = RuleController.getInstance();
    }

    public NOTTrigger(String name, String triggerName) {
        super(name, "NOT", "NOT " + triggerName);
        this.ruleController = RuleController.getInstance();
        this.trigger = ruleController.getTriggerByName(triggerName);
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

    public boolean checkIfUsed(Trigger trigger) {
        boolean result = false;
        if(trigger.equals(this.trigger)){
            result = true;
        }
        else {
            if(this.trigger instanceof ANDTrigger){
                ANDTrigger andTrigger = (ANDTrigger) this.trigger;
                result = andTrigger.checkIfUsed(trigger);
            }else if(this.trigger instanceof ORTrigger){
                ORTrigger orTrigger = (ORTrigger) this.trigger;
                result = orTrigger.checkIfUsed(trigger);
            }else if(this.trigger instanceof NOTTrigger){
                NOTTrigger notTrigger = (NOTTrigger) this.trigger;
                result = notTrigger.checkIfUsed(trigger);
            }
        }

        return result;
    }
}
