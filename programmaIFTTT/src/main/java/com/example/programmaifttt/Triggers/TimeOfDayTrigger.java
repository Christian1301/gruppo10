package com.example.programmaifttt.Triggers;
import java.time.LocalTime;
public class TimeOfDayTrigger extends Trigger{
    public static final String type = "Time Of Day";
    private int hours;
    private int minutes;


    // Constructor
    public TimeOfDayTrigger(String name,int hours,int minutes) {
        super(name,type, hours + ":" + minutes);
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
    public boolean evaluate() {
        int localHours = LocalTime.now().getHour(); // Use the current hour for a true case
        int localMinutes = LocalTime.now().getMinute();
        // Compare the two times
        return localHours == hours && localMinutes == minutes;

    }


}

