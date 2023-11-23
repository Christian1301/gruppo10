package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Triggers.Trigger;

public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;


    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
    }


    void executeAction() {
            action.execute();
    }
    //-------------------------Getter & Setter--------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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