package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONObject;

public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean multiUse;
    private int sleepTime;


    public Rule(String name, Trigger trigger, Action action, boolean multiUse, int sleepTime) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.multiUse = multiUse;
        this.sleepTime = sleepTime;
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

    public boolean isMultiUse() {
        return multiUse;
    }
    //------------------------------------------------------------------------------------------------------------
    @Override
    //equals method
    public boolean equals(Object obj) {
        if (obj instanceof Rule) {
            Rule rule = (Rule) obj;
            return this.name.equals(rule.name);
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject jsonRule = new JSONObject();
        jsonRule.put("name", this.name);
        jsonRule.put("action", this.action.toJson());
        jsonRule.put("trigger", this.trigger.toJson());
        jsonRule.put("multiUse", this.multiUse);
        jsonRule.put("sleepTime", this.sleepTime);
        return jsonRule;
    }

    public static Rule fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        Trigger trigger = Trigger.fromJson(jsonObject.getJSONObject("trigger").toString());
        Action action = Action.fromJson(jsonObject.getJSONObject("action").toString());
        boolean multiUse = jsonObject.getBoolean("multiUse");
        int sleepTime = jsonObject.getInt("sleepTime");
        return new Rule(name, trigger, action, multiUse, sleepTime);
    }
}