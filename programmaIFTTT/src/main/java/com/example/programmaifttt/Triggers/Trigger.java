package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.Enums.DayOfWeekEnum;
import org.json.JSONObject;

public abstract class Trigger {

    private String name;
    private String type;
    private String value;

    // Constructor
    public Trigger(String name, String type,String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    // Get the name of the trigger
    public String getName() {
        return name;
    }

    // Set the name of the trigger
    public void setName(String name) {
        this.name = name;
    }

    // Get the type of the trigger
    public String getType() {
        return type;
    }

    //get the value of the trigger
    public String getValue() {
        return value;
    }

    //toString method
    @Override
    public String toString() {
        return name;
    }
    //equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Trigger) {
            Trigger trigger = (Trigger) obj;
            return this.name.equals(trigger.name);
        }
        return false;
    }

    // Abstract method to evaluate the trigger
    public abstract boolean evaluate();

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("type", type);
        jsonObject.put("value", value);
        return jsonObject;
    }

    public static Trigger fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String value = jsonObject.getString("value");
        switch (type) {
            case "Time Of Day" -> {
                String[] time = value.split(":");
                int hours = Integer.parseInt(time[0]);
                int minutes = Integer.parseInt(time[1]);
                return new TimeOfDayTrigger(name, hours, minutes);
            }
            case "Day Of Month" -> {
                int day = Integer.parseInt(value.split(":")[1]);
                return new DayOfMonthTrigger(name, day);
            }
            case "Day Of Week" -> {
                return new DayOfWeekTrigger(name, DayOfWeekEnum.valueOf(value.split(":")[1]));
            }
        }
        return null;
    }

    public abstract boolean isEvaluable();
}
