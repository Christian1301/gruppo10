package com.example.programmaifttt.Triggers;

import com.example.programmaifttt.Enums.DayOfWeekEnum;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekTrigger extends Trigger {
    public static final String type = "Day Of Week";
    String day;

    // Constructor
    public DayOfWeekTrigger(String name, DayOfWeekEnum dayOfWeek) {
        super(name, type, "Day " + dayOfWeek.getDay());
        this.day = dayOfWeek.getDay();
    }

    @Override
    public boolean evaluate() {
        // Get the current day of the week
        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();
        // Check if the current day of the week matches the trigger day of the week
        return currentDayOfWeek.toString().equalsIgnoreCase(getValue());
    }

    @Override
    public boolean isEvaluable() {
        // Day of the week triggers are always evaluable
        return true;
    }
}
