package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.BackEnd.RuleController;

import java.io.IOException;

public class ANDTrigger extends Trigger{

    public static final String type = "AND";

    private Trigger trigger1;

    private Trigger trigger2;

    private RuleController ruleController;

    public ANDTrigger(String name, Trigger trigger1, Trigger trigger2) {
        super(name, type, trigger1.getName() + " AND " + trigger2.getName());
        this.trigger1 = trigger1;
        this.trigger2 = trigger2;
        this.ruleController = RuleController.getInstance();
    }

    public ANDTrigger(String name, String trigger1Name, String trigger2Name) {
        super(name, type, trigger1Name + " AND " + trigger2Name);
        this.ruleController = RuleController.getInstance();
        this.trigger1 = ruleController.getTriggerByName(trigger1Name);
        this.trigger2 = ruleController.getTriggerByName(trigger2Name);

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


    public boolean checkIfUsed(Trigger trigger) {
        boolean result = false;
        if(trigger.equals(this.trigger1) || trigger.equals(this.trigger2)){
            result = true;
        }
        else {
            if(this.trigger1 instanceof ANDTrigger){
                ANDTrigger andTrigger = (ANDTrigger) this.trigger1;
                result = andTrigger.checkIfUsed(trigger);
            }else if(this.trigger1 instanceof ORTrigger){
                ORTrigger orTrigger = (ORTrigger) this.trigger1;
                result = orTrigger.checkIfUsed(trigger);
            }else if(this.trigger1 instanceof NOTTrigger){
                NOTTrigger notTrigger = (NOTTrigger) this.trigger1;
                result = notTrigger.checkIfUsed(trigger);
            }

            if(!result){
                if(this.trigger2 instanceof ANDTrigger){
                    ANDTrigger andTrigger = (ANDTrigger) this.trigger2;
                    result = andTrigger.checkIfUsed(trigger);
                }else if(this.trigger2 instanceof ORTrigger){
                    ORTrigger orTrigger = (ORTrigger) this.trigger2;
                    result = orTrigger.checkIfUsed(trigger);
                }else if(this.trigger2 instanceof NOTTrigger){
                    NOTTrigger notTrigger = (NOTTrigger) this.trigger2;
                    result = notTrigger.checkIfUsed(trigger);
                }
            }
        }
        return result;
    }
}
