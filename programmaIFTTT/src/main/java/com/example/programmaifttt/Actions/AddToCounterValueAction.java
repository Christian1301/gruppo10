package com.example.programmaifttt.Actions;

import com.example.programmaifttt.Counter.CounterManager;

public class AddToCounterValueAction extends Action {
    public static final String type = "Add To Counter Value";
    private CounterManager counterManager;
    private String counterName;
    private int addValue;

    public AddToCounterValueAction(String name, CounterManager counterManager, String counterName, int addValue) {
        super(name, type, "Counter: " + counterName + "/value to add: " + addValue + "/current value: " + counterManager.getCounter(counterName).getValue());
        this.counterManager = counterManager;
        this.counterName = counterName;
        this.addValue = addValue;
    }

    @Override
    public boolean execute() {
        try {
            counterManager.addToCounterValue(counterName, addValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}