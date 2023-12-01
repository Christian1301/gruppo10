package com.example.programmaifttt.Triggers;

import java.time.LocalDate;

public class DayOfMonthTrigger extends Trigger {
    public static final String type = "Day Of Month";
    private int day;

    // Constructor
    public DayOfMonthTrigger(String name, Integer day) {
        super(name, type, "Day:" + day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        // Get the current day of the month
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        // Check if the current day of the month matches the trigger day
        return  day == currentDayOfMonth;
    }

    @Override
    public boolean isEvaluable() {
        // Get the current day of the month
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        // Check if the current day of the month is greater than the trigger day
        return currentDayOfMonth > day;
    }
}
