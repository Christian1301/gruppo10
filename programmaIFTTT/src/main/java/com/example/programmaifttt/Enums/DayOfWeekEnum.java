package com.example.programmaifttt.Enums;

public enum DayOfWeekEnum {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DayOfWeekEnum(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
