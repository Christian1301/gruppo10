package com.example.programmaifttt.Triggers;


public interface Trigger {
        // Get the name of the trigger
        String getName();

        // Set the name of the trigger
        void setName(String name);

        // Evaluate the trigger
        boolean evaluate();
}
