package com.example.programmaifttt.Triggers;


public interface Trigger {

        // Get the name of the trigger
        String getName();

        // Set the name of the trigger
        void setName(String name);

        // Get the value of the trigger
        String getValue();

        String getType();

        // Evaluate the trigger
        boolean evaluate();
}
