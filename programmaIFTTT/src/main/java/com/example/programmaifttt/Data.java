package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Rules.Rules;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Data {
    private Trigger[] triggers;
    private Action[] actions;
    private Rules[] rules;

    // Constructor
    public Data(Trigger[] triggers, Action[] actions, Rules[] rules) {
        this.triggers = triggers;
        this.actions = actions;
        this.rules = rules;
    }

    public Trigger[] getTriggers() {
        return triggers;
    }

    public void setTriggers(Trigger[] triggers) {
        this.triggers = triggers;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public Rules[] getRules() {
        return rules;
    }

    public void setRules(Rules[] rules) {
        this.rules = rules;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trigger", this.triggers);
        jsonObject.put("action", this.actions);
        jsonObject.put("rules", this.rules);
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
                (Rules[]) jsonObject.get("rules"));
    }
}
