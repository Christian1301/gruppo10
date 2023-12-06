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
}