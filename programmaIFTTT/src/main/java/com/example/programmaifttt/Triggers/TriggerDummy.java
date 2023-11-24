package com.example.programmaifttt.Triggers;

public class TriggerDummy implements Trigger {
    private String name;

    // Constructor
    public TriggerDummy(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return "Test";
    }

    @Override
    public String getType() {
        return "Test";
    }

    @Override
    public boolean evaluate() {
        // Add your logic to evaluate the trigger here
        return true; // Replace with your actual evaluation logic
    }

    // Override toString to provide a meaningful representation
    @Override
    public String toString() {
        return name;
    }

}
