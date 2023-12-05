package com.example.programmaifttt.Enums;

public enum DayOfWeekEnum {
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");

    private final String day;

    DayOfWeekEnum(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
