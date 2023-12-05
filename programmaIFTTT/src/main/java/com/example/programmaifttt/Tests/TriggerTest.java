package com.example.programmaifttt.Tests;

import com.example.programmaifttt.Enums.DayOfWeekEnum;
import com.example.programmaifttt.Triggers.DayOfMonthTrigger;
import com.example.programmaifttt.Triggers.DayOfWeekTrigger;
import com.example.programmaifttt.Triggers.TimeOfDayTrigger;
import com.example.programmaifttt.Triggers.Trigger;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TriggerTest {

    @Test
    public void evaluate_shouldReturnTrueForValidTimeOfDayTrigger() {
        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute();
        Trigger trigger = new TimeOfDayTrigger("Test", hours, minutes);
        assertTrue(trigger.evaluate());
    }

    @Test
    public void evaluate_shouldReturnFalseForInvalidTimeOfDayTrigger() {
        int hours = LocalTime.now().getHour() + 1;
        int minutes = LocalTime.now().getMinute();
        Trigger trigger = new TimeOfDayTrigger("Test", hours, minutes);
        assertFalse(trigger.evaluate());
    }

    @Test
    public void evaluate_shouldReturnTrueForValidDayOfMonthTrigger() {
        int day = LocalDate.now().getDayOfMonth();
        Trigger trigger = new DayOfMonthTrigger("Test", day);
        assertTrue(trigger.evaluate());
    }

    @Test
    public void evaluate_shouldReturnFalseForInvalidDayOfMonthTrigger() {
        int day = LocalDate.now().getDayOfMonth() + 1;
        Trigger trigger = new DayOfMonthTrigger("Test", day);
        assertFalse(trigger.evaluate());
    }

    @Test
    public void evaluate_shouldReturnTrueForValidDayOfWeekTrigger() {
        DayOfWeekEnum dayOfWeek = DayOfWeekEnum.valueOf(LocalDate.now().getDayOfWeek().toString().toUpperCase());
        Trigger trigger = new DayOfWeekTrigger("Test", dayOfWeek);
        assertTrue(trigger.evaluate());
    }

    @Test
    public void evaluate_shouldReturnFalseForInvalidDayOfWeekTrigger() {
        DayOfWeekEnum dayOfWeek = DayOfWeekEnum.valueOf(LocalDate.now().getDayOfWeek().plus(1).toString().toUpperCase());
        Trigger trigger = new DayOfWeekTrigger("Test", dayOfWeek);
        assertFalse(trigger.evaluate());
    }
}