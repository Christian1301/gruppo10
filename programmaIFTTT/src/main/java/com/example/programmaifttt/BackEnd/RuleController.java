package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Triggers.Trigger;

import java.util.ArrayList;
import java.util.List;
public class RuleController{
    private List<Rule> rules;
    private List<Trigger> triggers;
    private List<Action> actions;

    private List<RuleControllerObserver> observers;

    public RuleController() {
        this.rules = new ArrayList<>();
        this.triggers = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(RuleControllerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RuleControllerObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (RuleControllerObserver observer : observers) {
            observer.update(this);
        }
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
        if (!rules.contains(rule)) {
            rules.add(rule);
            notifyObservers();
        }
    }

    public void deleteRule(String ruleName) {
        rules.removeIf(rule -> rule.getName().equals(ruleName));
        notifyObservers();
    }

    public void addTrigger(Trigger trigger) {
        //check if the list contains the trigger with the same name,type and value and if it doesn't add it
        if (!triggers.contains(trigger)) {
            triggers.add(trigger);
        }
    }

    public void deleteTrigger(String triggerName) {
        triggers.removeIf(trigger -> trigger.getName().equals(triggerName));
    }

    public void addAction(Action action) {
        if (!actions.contains(action)) {
            actions.add(action);
        }
    }

    public void deleteAction(String actionName) {
        actions.removeIf(action -> action.getName().equals(actionName));
    }

    public boolean isTriggerUsed(Trigger selectedTrigger) {
        //check if the trigger is used in a rule
        for (Rule rule : rules) {
            if (rule.getTrigger().equals(selectedTrigger)) {
                return true;
            }
        }
        return false;
    }

    public boolean isActionUsed(Action selectedAction) {
        //check if the action is used in a rule
        for (Rule rule : rules) {
            if (rule.getAction().equals(selectedAction)) {
                return true;
            }
        }
        return false;
    }
}