package com.example.programmaifttt;

import java.util.ArrayList;
import java.util.List;
public class RuleController{
    private List<Rule> rules;
    private List<Trigger> triggers;
    private List<Action> actions;

    public RuleController() {
        this.rules = new ArrayList<>();
        this.triggers = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void deleteRule(Rule rule) {
        rules.remove(rule);
    }

    public void addTrigger(Trigger trigger) {
        triggers.add(trigger);
    }

    public void deleteTrigger(Trigger trigger) {
        triggers.remove(trigger);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void deleteAction(Action action) {
        actions.remove(action);
    }
}