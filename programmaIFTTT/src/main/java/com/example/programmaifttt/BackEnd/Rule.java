package com.example.programmaifttt.BackEnd;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Triggers.Trigger;
import org.json.JSONObject;

public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;

    private boolean state ;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.state = state;
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
        return jsonRule;
    }

    public static Rule fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        Trigger trigger = Trigger.fromJson(jsonObject.getJSONObject("trigger").toString());
        Action action = Action.fromJson(jsonObject.getJSONObject("action").toString());
        return new Rule(name, trigger, action);
    }
}