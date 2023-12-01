package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class Data {
    private RuleController ruleController;

    // Constructor
    public Data() {
        this.ruleController = new RuleController();
    }

    public Data(RuleController ruleController) {
        this.ruleController = ruleController;
    }

    // Getters
    public RuleController getRuleController() {
        return ruleController;
    }

    // Setters
    public void setRuleController(RuleController ruleController) {
        this.ruleController = ruleController;
    }


    //take the rulecontroller and put it in the data the rules, the triggers and the actions
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray rules = new JSONArray();
        JSONArray triggers = new JSONArray();
        JSONArray actions = new JSONArray();
        for (Rule rule : ruleController.getRules()) {
            rules.put(rule.toJson());
        }
        for (Trigger trigger : ruleController.getTriggers()) {
            triggers.put(trigger.toJson());
        }
        for (Action action : ruleController.getActions()) {
            actions.put(action.toJson());
        }
        jsonObject.put("rules", rules);
        jsonObject.put("triggers", triggers);
        jsonObject.put("actions", actions);
        return jsonObject;
    }

    public static Data fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        Data data = new Data();
        JSONArray rules = jsonObject.getJSONArray("rules");
        JSONArray triggers = jsonObject.getJSONArray("triggers");
        JSONArray actions = jsonObject.getJSONArray("actions");
        for (int i = 0; i < rules.length(); i++) {
            JSONObject rule = rules.getJSONObject(i);
            data.ruleController.addRule(Rule.fromJson(String.valueOf(rule)));
        }
        for (int i = 0; i < triggers.length(); i++) {
            JSONObject trigger = triggers.getJSONObject(i);
            data.ruleController.addTrigger(Trigger.fromJson(String.valueOf(trigger)));
        }
        for (int i = 0; i < actions.length(); i++) {
            JSONObject action = actions.getJSONObject(i);
            data.ruleController.addAction(Action.fromJson(String.valueOf(action)));
        }
        return data;
    }

    public void saveDatas(String fileName) {
        try {
            ClassLoader classLoader = IFTTTApplication.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            // Use PrintWriter to write to the file
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(this.toJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data loadDatas() {
        try {
            ClassLoader classLoader = IFTTTApplication.class.getClassLoader();
            File file = new File(classLoader.getResource("data.txt").getFile());
            String content = new String(Files.readAllBytes(file.toPath()));
            if (content.isEmpty()) {
                return new Data();
            }
            return Data.fromJson(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
