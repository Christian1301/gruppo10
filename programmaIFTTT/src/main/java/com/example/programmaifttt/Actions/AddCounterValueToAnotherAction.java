package com.example.programmaifttt.Actions;

import com.example.programmaifttt.Counter.CounterManager;

public class AddCounterValueToAnotherAction extends Action {
    public static final String type = "Add Counter Value To Another";
    private CounterManager counterManager;
    private String sourceCounterName;
    private String targetCounterName;

    public AddCounterValueToAnotherAction(String name, CounterManager counterManager, String sourceCounterName, String targetCounterName) {
        super(name, type, "Source Counter: " + sourceCounterName + ", " + counterManager.getCounter(sourceCounterName) + "/Target Counter: " + targetCounterName + ", " + counterManager.getCounter(targetCounterName));
        this.counterManager = counterManager;
        this.sourceCounterName = sourceCounterName;
        this.targetCounterName = targetCounterName;
    }

    @Override
    public boolean execute() {
        try {
            counterManager.addCounterValueToAnother(sourceCounterName, targetCounterName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}