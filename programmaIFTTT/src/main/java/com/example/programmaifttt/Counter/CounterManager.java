package com.example.programmaifttt.Counter;

import java.util.HashMap;
import java.util.Map;

public class CounterManager {
    private Map<String, Counter> counters;

    public CounterManager() {
        counters = new HashMap<>();
    }

    public Counter createCounter(String name, int value) {
        Counter counter = new Counter(name, value);
        counters.put(name, counter);
        return counter;
    }

    public Counter getCounter(String name) {
        return counters.get(name);
    }

    public void setCounterValue(String name, int value) {
        Counter counter = counters.get(name);
        if (counter != null) {
            counter.setValue(value);
        }
    }

    public Map<String, Integer> getAllCounters() {
        Map<String, Integer> counterValues = new HashMap<>();
        for (Map.Entry<String, Counter> entry : counters.entrySet()) {
            counterValues.put(entry.getKey(), entry.getValue().getValue());
        }
        return counterValues;
    }

    public void addToCounterValue(String counterName, int addValue) throws Exception {
        Counter counter = getCounter(counterName);
        if (counter != null) {
            counter.setValue(counter.getValue() + addValue);
        } else {
            throw new Exception("Counter " + counterName + " does not exist.");
        }
    }

    public void addCounterValueToAnother(String sourceCounterName, String targetCounterName) throws Exception {
        Counter sourceCounter = getCounter(sourceCounterName);
        Counter targetCounter = getCounter(targetCounterName);
        if (sourceCounter != null && targetCounter != null) {
            targetCounter.setValue(targetCounter.getValue() + sourceCounter.getValue());
        } else {
            throw new Exception("One or both counters do not exist.");
        }
    }

    public boolean compareCounterValue(String counterName, String comparisonOperator, int comparisonValue) throws Exception {
        Counter counter = getCounter(counterName);
        if (counter != null) {
            return switch (comparisonOperator) {
                case "equal to" -> counter.getValue() == comparisonValue;
                case "less than" -> counter.getValue() < comparisonValue;
                case "greater than" -> counter.getValue() > comparisonValue;
                default -> throw new Exception("Invalid comparison operator.");
            };
        } else {
            throw new Exception("Counter " + counterName + " does not exist.");
        }
    }

    public boolean compareCounterValues(String sourceCounterName, String comparisonOperator, String targetCounterName) throws Exception {
        Counter sourceCounter = getCounter(sourceCounterName);
        Counter targetCounter = getCounter(targetCounterName);
        if (sourceCounter != null && targetCounter != null) {
            return switch (comparisonOperator) {
                case "equal to" -> sourceCounter.getValue() == targetCounter.getValue();
                case "less than" -> sourceCounter.getValue() < targetCounter.getValue();
                case "greater than" -> sourceCounter.getValue() > targetCounter.getValue();
                default -> throw new Exception("Invalid comparison operator.");
            };
        } else {
            throw new Exception("One or both counters do not exist.");
        }
    }
}