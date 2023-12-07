package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.Counter.CounterManager;

public class CounterToCounterTrigger extends Trigger {
    public static final String type = "Counter to Counter";
    private CounterManager counterManager;
    private String sourceCounterName;
    private String comparisonOperator;
    private String targetCounterName;

    public CounterToCounterTrigger(String name, CounterManager counterManager, String sourceCounterName, String comparisonOperator, String targetCounterName) {
        super(name, type, "Counter: " + sourceCounterName + "/" + counterManager.getCounter(sourceCounterName).getValue() + "/" + comparisonOperator + "/Counter to confront: " + targetCounterName + "/" + counterManager.getCounter(targetCounterName).getValue());
        this.counterManager = counterManager;
        this.sourceCounterName = sourceCounterName;
        this.comparisonOperator = comparisonOperator;
        this.targetCounterName = targetCounterName;
    }

    @Override
    public boolean evaluate() {
        try {
            return counterManager.compareCounterValues(sourceCounterName, comparisonOperator, targetCounterName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEvaluable() {
        return counterManager.getCounter(sourceCounterName) != null && counterManager.getCounter(targetCounterName) != null;
    }
}