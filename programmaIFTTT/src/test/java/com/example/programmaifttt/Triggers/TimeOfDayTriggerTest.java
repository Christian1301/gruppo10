package com.example.programmaifttt.Triggers;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeOfDayTriggerTest {

    @Test
    void testEvaluateTrue() {
        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute();
        TimeOfDayTrigger trigger = new TimeOfDayTrigger("test", hours, minutes);
        assertTrue(trigger.evaluate());
    }

    @Test
    void testEvaluateFalse() {
        // Provide specific hours and minutes that should result in false
        int hours = LocalTime.now().getHour() +1;
        int minutes = LocalTime.now().getMinute();

        TimeOfDayTrigger trigger = new TimeOfDayTrigger("test", hours, minutes);
        assertFalse(trigger.evaluate());
    }
}