package com.example.programmaifttt;

public class Rule {
    private Trigger trigger;
    private Action action;


    public Rule(Trigger trigger, Action action) {
        this.trigger = trigger;
        this.action = action;
    }


    void executeAction() {
            action.execute();
    }
    //-------------------------Getter & Setter--------------------------------------------------------------------
    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
    //------------------------------------------------------------------------------------------------------------
}