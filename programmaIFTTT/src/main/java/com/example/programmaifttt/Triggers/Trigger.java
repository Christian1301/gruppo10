package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.Counter.CounterManager;
import com.example.programmaifttt.Enums.DayOfWeekEnum;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public abstract class Trigger {

    private String name;
    private String type;
    private String value;
    private String filePath;

    // Constructor
    public Trigger(String name, String type,String value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.filePath = null;
    }

    // Constructor
    public Trigger(String name, String type,String value, String filePath) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.filePath = filePath;
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
        switch (type) {
            case FileSizeTrigger.type, ExternalProgramTrigger.type, FileExistenceTrigger.type ->
                jsonObject.put("filePath", filePath);
        }
        return jsonObject;
    }

    public static Trigger fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String value = jsonObject.getString("value");
        switch (type) {
            case TimeOfDayTrigger.type -> {
                String[] time = value.split(": ");
                int hours = Integer.parseInt(time[0]);
                int minutes = Integer.parseInt(time[1]);
                return new TimeOfDayTrigger(name, hours, minutes);
            }
            case DayOfMonthTrigger.type -> {
                int day = Integer.parseInt(value.split(": ")[1]);
                return new DayOfMonthTrigger(name, day);
            }
            case DayOfWeekTrigger.type -> {
                return new DayOfWeekTrigger(name, DayOfWeekEnum.valueOf(value.split(": ")[1]));
            }
            case FileExistenceTrigger.type -> {
                File file = new File (jsonObject.getString("filePath"));
                return new FileExistenceTrigger(name, file);
            }
            case FileSizeTrigger.type -> {
                File file = new File (jsonObject.getString("filePath"));
                long sizeThreshold = Long.parseLong(value.split("/")[1].split(": ")[1]);
                return new FileSizeTrigger(name, file, sizeThreshold);
            }
            case ExternalProgramTrigger.type -> {
                File file = new File (jsonObject.getString("filePath"));
                String commandLineArguments = value.split("/")[1].split(": ")[1];
                int exitCode = Integer.parseInt(value.split("/")[2].split(": ")[1]);
                return new ExternalProgramTrigger(name, file, commandLineArguments, exitCode);
            }
            case CounterValueTrigger.type -> {
                CounterManager counterManager = new CounterManager();
                String counterName = value.split("/")[0].split(": ")[1];
                int currentValue = Integer.parseInt(value.split("/")[1]);
                String comparisonOperator = value.split("/")[2];
                int comparisonValue = Integer.parseInt(value.split("/")[3]);
                counterManager.createCounter(counterName, currentValue);
                return new CounterValueTrigger(name, counterManager, counterName, comparisonOperator, comparisonValue);
            }
            case CounterToCounterTrigger.type -> {
                CounterManager counterManager = new CounterManager();
                String sourceCounterName = value.split("/")[0].split(": ")[1];
                int sourceCounterValue = Integer.parseInt(value.split("/")[1]);
                String comparisonOperator = value.split("/")[2];
                String targetCounterName = value.split("/")[3].split(": ")[1];
                int targetCounterValue = Integer.parseInt(value.split("/")[4]);
                counterManager.createCounter(sourceCounterName, sourceCounterValue);
                counterManager.createCounter(targetCounterName, targetCounterValue);
                return new CounterToCounterTrigger(name, counterManager, sourceCounterName, comparisonOperator, targetCounterName);
            }
        }
        return null;
    }

    public abstract boolean isEvaluable() throws IOException;
}
