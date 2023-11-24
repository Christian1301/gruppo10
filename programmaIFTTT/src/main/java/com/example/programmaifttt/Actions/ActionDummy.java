package com.example.programmaifttt.Actions;

public class ActionDummy implements Action {
    private String name;

    // Constructor
    public ActionDummy(String name) {
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
    public void execute() {
        // Add your logic to execute the action here
    }

    // Override toString to provide a meaningful representation
    @Override
    public String toString() {
        return name;
    }

}
