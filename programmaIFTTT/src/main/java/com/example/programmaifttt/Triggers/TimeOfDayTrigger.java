package com.example.programmaifttt.Triggers;
import java.time.LocalTime;
public class TimeOfDayTrigger implements Trigger{
    private String name;
    private int hours;
    private int minutes;


    // Constructor
    public TimeOfDayTrigger(String name,int hours,int minutes) {
        this.name = name;
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
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
    public String getValue(){
        String time = hours + ":" + minutes;
        return time;
    }

    @Override
    public String getType() {
        return "Time Of Day";
    }

    @Override
    public boolean evaluate() {
        int localHours = LocalTime.now().getHour(); // Use the current hour for a true case
        int localMinutes = LocalTime.now().getMinute();
        // Compare the two times
        return localHours == hours && localMinutes == minutes;

    }

    // Override toString to provide a meaningful representation
    @Override
    public String toString() {
        return name;
    }


}

