package com.example.programmaifttt.Actions;

import com.example.programmaifttt.Counter.CounterManager;

@SuppressWarnings("ClassEscapesDefinedScope")
public class SetCounterValueAction extends Action{
    public static final String type = "Set Counter Value";
    private CounterManager counterManager;
    private String counterName;
    private int counterValue;

    public SetCounterValueAction(String name, CounterManager counterManager, String counterName, int counterValue) {
        super(name, type, "Counter: " + counterName + "/value to set: " + counterValue);
        this.counterManager = counterManager;
        this.counterName = counterName;
        this.counterValue = counterValue;
    }

    public CounterManager getCounterManager() {
        return counterManager;
    }

    public String getCounterName() {
        return counterName;
    }

    public int getCounterValue() {
        return counterValue;
    }

    @Override
    public boolean execute() {
        try {
            counterManager.setCounterValue(counterName, counterValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
