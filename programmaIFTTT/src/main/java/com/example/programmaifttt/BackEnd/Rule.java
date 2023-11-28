package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONObject;

import java.util.Date;

public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean multiUse;
    private Date lastUse;
    private int sleepTime;
    private boolean state;

    public Rule(String name, Trigger trigger, Action action, boolean state, boolean multiUse, int sleepTime) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.state = state;
        this.multiUse = multiUse;
        this.lastUse = null;
        this.sleepTime = sleepTime;
    }

    public Rule(String name, Trigger trigger, Action action, boolean state, boolean multiUse, Date lastUse, int sleepTime) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.state = state;
        this.multiUse = multiUse;
        this.lastUse = lastUse;
        this.sleepTime = sleepTime;
    }

    //-------------------------Getter & Setter--------------------------------------------------------------------

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

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

    public Date getLastUse() {
        return lastUse;
    }

    public void setLastUse() {
        this.lastUse = new Date();
    }

    public int getSleepTime() {
        return sleepTime;
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
        jsonRule.put("state", this.state);
        jsonRule.put("multiUse", this.multiUse);
        jsonRule.put("sleepTime", this.sleepTime);
        return jsonRule;
    }

    public static Rule fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        Trigger trigger = Trigger.fromJson(jsonObject.getJSONObject("trigger").toString());
        Action action = Action.fromJson(jsonObject.getJSONObject("action").toString());
        boolean state = jsonObject.getBoolean("state");
        boolean multiUse = jsonObject.getBoolean("multiUse");
        int sleepTime = jsonObject.getInt("sleepTime");
        return new Rule(name, trigger, action, state, multiUse, sleepTime);
    }
}