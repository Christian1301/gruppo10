package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.Triggers.Trigger;

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

    // Getters for rules
    public List<Rule> getRules() {
        return rules;
    }

    // Setters for rules
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    // Getters for triggers
    public List<Trigger> getTriggers() {
        return triggers;
    }

    // Setters for triggers
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    // Getters for actions
    public List<Action> getActions() {
        return actions;
    }

    // Setters for actions
    public void setActions(List<Action> actions) {
        this.actions = actions;
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