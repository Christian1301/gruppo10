package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.Counter.CounterManager;

public class CounterValueTrigger extends Trigger {
    public static final String type = "Counter Value";
    private CounterManager counterManager;
    private String counterName;
    private String comparisonOperator;
    private int comparisonValue;

    public CounterValueTrigger(String name, CounterManager counterManager, String counterName, String comparisonOperator, int comparisonValue) {
        super(name, type, "Counter: " + counterName + "/" + counterManager.getCounter(counterName).getValue() + "/" + comparisonOperator + "/" + comparisonValue);
        this.counterManager = counterManager;
        this.counterName = counterName;
        this.comparisonOperator = comparisonOperator;
        this.comparisonValue = comparisonValue;
    }

    @Override
    public boolean evaluate() {
        try {
            return counterManager.compareCounterValue(counterName, comparisonOperator, comparisonValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEvaluable() {
        return counterManager.getCounter(counterName) != null;
    }
}