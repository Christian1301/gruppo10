package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Data {
    private Trigger[] triggers;
    private Action[] actions;
    private Rule[] rules;

    // Constructor
    public Data(Trigger[] triggers, Action[] actions, Rule[] rules) {
        this.triggers = triggers;
        this.actions = actions;
        this.rules = rules;
    }

    public Data() {
        this.triggers = new Trigger[0];
        this.actions = new Action[0];
        this.rules = new Rule[0];
    }

    public Trigger[] getTriggers() {
        return triggers;
    }

    public void addTrigger(Trigger trigger) {
        Trigger[] newTriggers = new Trigger[triggers.length + 1];
        for (int i = 0; i < triggers.length; i++) {
            newTriggers[i] = triggers[i];
        }
        newTriggers[triggers.length] = trigger;
        triggers = newTriggers;
    }

    public Action[] getActions() {
        return actions;
    }

    public void addActions(Action action) {
        Action[] newActions = new Action[actions.length + 1];
        for (int i = 0; i < actions.length; i++) {
            newActions[i] = actions[i];
        }
        newActions[actions.length] = action;
        actions = newActions;
    }

    public Rule[] getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        Rule[] newRules = new Rule[rules.length + 1];
        for (int i = 0; i < rules.length; i++) {
            newRules[i] = rules[i];
        }
        newRules[rules.length] = rule;
        rules = newRules;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trigger", triggers);
        jsonObject.put("action", actions);
        jsonObject.put("rules", rules);
        return jsonObject.toString();
    }

    public void saveDatas() {
        String percorsoFile = "src/main/resources/data.json";

        try (FileWriter fileWriter = new FileWriter(percorsoFile)) {
            fileWriter.write(this.toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data fromJson(String json) {
       JSONObject jsonObject = new JSONObject(json);
        return new Data(
                (Trigger[]) jsonObject.get("trigger"),
                (Action[]) jsonObject.get("action"),
                (Rule[]) jsonObject.get("rules"));
    }
}
