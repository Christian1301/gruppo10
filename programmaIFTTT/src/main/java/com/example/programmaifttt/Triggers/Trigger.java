package com.example.programmaifttt.Triggers;

public abstract class Trigger {

    private String name;
    private String type;
    private String value;



    // Constructor
    public Trigger(String name, String type,String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    // Get the name of the trigger
    public String getName() {
        return name;
    }

    // Set the name of the trigger
    public void setName(String name) {
        this.name = name;
    }

    // Get the type of the trigger
    public String getType() {
        return type;
    }

    //get the value of the trigger
    public String getValue() {
        return value;
    }


    //toString method
    @Override
    public String toString() {
        return name;
    }
    //equals method
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Trigger) {
            Trigger trigger = (Trigger) obj;
            return this.name.equals(trigger.name);
        }
        return false;
    }


    // Abstract method to evaluate the trigger
    public abstract boolean evaluate();
}
