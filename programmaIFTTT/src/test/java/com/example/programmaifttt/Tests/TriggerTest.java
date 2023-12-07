package com.example.programmaifttt.Tests;

import com.example.programmaifttt.Enums.DayOfWeekEnum;
import com.example.programmaifttt.Triggers.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TriggerTest {

    //TRIGGER TIME OF DAY TESTS
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

    //TRIGGER DAY OF MONTH TESTS
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

    //TRIGGER DAY OF WEEK TESTS
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

    //EXTERNAL PROGRAM TRIGGER TESTS
    @Test
    public void externalProgramTriggerShouldEvaluateTrueWhenExitStatusMatches() {
        File externalProgram = new File("/path/to/program");
        ExternalProgramTrigger trigger = new ExternalProgramTrigger("test", externalProgram, "-version", 0);
        assertTrue(trigger.evaluate()); // Assuming the program returns an exit status of 0
    }

    @Test
    public void externalProgramTriggerShouldEvaluateFalseWhenExitStatusDoesNotMatch() {
        File externalProgram = new File("/path/to/program");
        ExternalProgramTrigger trigger = new ExternalProgramTrigger("test", externalProgram, "-version", 1);
        assertFalse(trigger.evaluate()); // Assuming the program returns an exit status of 0
    }

    @Test
    public void externalProgramTriggerShouldEvaluateFalseWhenProgramDoesNotExist() {
        File externalProgram = new File("/path/to/nonexistent/program");
        ExternalProgramTrigger trigger = new ExternalProgramTrigger("test", externalProgram, "-version", 0);
        assertFalse(trigger.evaluate()); // Assuming the program does not exist
    }

    @Test
    public void externalProgramTriggerShouldNotBeEvaluableWhenProgramDoesNotExist() {
        File externalProgram = new File("/path/to/nonexistent/program");
        ExternalProgramTrigger trigger = new ExternalProgramTrigger("test", externalProgram, "-version", 0);
        assertFalse(trigger.isEvaluable()); // Assuming the program does not exist
    }

    @Test
    public void externalProgramTriggerShouldBeEvaluableWhenProgramExists() {
        File externalProgram = new File("/path/to/program");
        ExternalProgramTrigger trigger = new ExternalProgramTrigger("test", externalProgram, "-version", 0);
        assertTrue(trigger.isEvaluable()); // Assuming the program exists
    }

    //FILE EXISTENCE TRIGGER TESTS
    @Test
    public void fileExistenceTriggerShouldEvaluateTrueWhenFileExists() {
        File file = new File("/path/to/existing/file");
        FileExistenceTrigger trigger = new FileExistenceTrigger("test", file);
        assertTrue(trigger.evaluate()); // Assuming the file exists
    }

    @Test
    public void fileExistenceTriggerShouldEvaluateFalseWhenFileDoesNotExist() {
        File file = new File("/path/to/nonexistent/file");
        FileExistenceTrigger trigger = new FileExistenceTrigger("test", file);
        assertFalse(trigger.evaluate()); // Assuming the file does not exist
    }

    @Test
    public void fileExistenceTriggerShouldNotBeEvaluableWhenFileDoesNotExist() {
        File file = new File("/path/to/nonexistent/file");
        FileExistenceTrigger trigger = new FileExistenceTrigger("test", file);
        assertFalse(trigger.isEvaluable()); // Assuming the file does not exist
    }

    @Test
    public void fileExistenceTriggerShouldBeEvaluableWhenFileExists() {
        File file = new File("/path/to/existing/file");
        FileExistenceTrigger trigger = new FileExistenceTrigger("test", file);
        assertTrue(trigger.isEvaluable()); // Assuming the file exists
    }

    //FILE SIZE TRIGGER TESTS
    @Test
    public void fileSizeTriggerShouldEvaluateTrueWhenFileSizeExceedsThreshold() {
        File file = new File("/path/to/existing/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000);
        assertTrue(trigger.evaluate()); // Assuming the file exists and its size exceeds 1000 bytes
    }

    @Test
    public void fileSizeTriggerShouldEvaluateFalseWhenFileSizeDoesNotExceedThreshold() {
        File file = new File("/path/to/existing/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000000);
        assertFalse(trigger.evaluate()); // Assuming the file exists and its size does not exceed 1000000 bytes
    }

    @Test
    public void fileSizeTriggerShouldEvaluateFalseWhenFileDoesNotExist() {
        File file = new File("/path/to/nonexistent/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000);
        assertFalse(trigger.evaluate()); // Assuming the file does not exist
    }

    @Test
    public void fileSizeTriggerShouldNotBeEvaluableWhenFileDoesNotExist() throws IOException {
        File file = new File("/path/to/nonexistent/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000);
        assertFalse(trigger.isEvaluable()); // Assuming the file does not exist
    }

    @Test
    public void fileSizeTriggerShouldBeEvaluableWhenFileExistsAndSizeExceedsThreshold() throws IOException {
        File file = new File("/path/to/existing/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000);
        assertTrue(trigger.isEvaluable()); // Assuming the file exists and its size exceeds 1000 bytes
    }

    @Test
    public void fileSizeTriggerShouldNotBeEvaluableWhenFileExistsAndSizeDoesNotExceedThreshold() throws IOException {
        File file = new File("/path/to/existing/file");
        FileSizeTrigger trigger = new FileSizeTrigger("test", file, 1000000);
        assertFalse(trigger.isEvaluable()); // Assuming the file exists and its size does not exceed 1000000 bytes
    }
}