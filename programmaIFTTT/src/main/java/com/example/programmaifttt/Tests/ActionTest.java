package com.example.programmaifttt.Tests;

import com.example.programmaifttt.Actions.*;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class ActionTest {

    //MESSAGE BOX
    @Test
    public void execute_shouldReturnTrueForMessageBoxAction() {
        // Arrange
        Action action = new MessageBoxAction("Test", "Hello World!");

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //AUDIO ACTION
    @Test
    public void execute_shouldReturnFalseForInvalidAudioAction() {
        // Arrange
        Action action = new AudioAction("Test", new File("nonexistent.mp3"));

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidAudioAction() {
        // Arrange
        Action action = new AudioAction("Test", new File("alarm.mp3"));

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //EXTERNAL PROGRAM
    @Test
    public void execute_shouldReturnFalseForInvalidExternalProgramAction() {
        // Arrange
        Action action = new ExternalProgramAction("Test", new File("nonexistent.exe"), "arg1");

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidExternalProgramAction() {
        // Arrange
        Action action = new ExternalProgramAction("Test", new File("existing.exe"), "arg1");

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //APPEND STRING TO FILE
    @Test
    public void execute_shouldReturnFalseForInvalidAppendStringToFileAction() {
        // Arrange
        Action action = new AppendStringToFileAction("Test", "Hello, World!", new File("nonexistent.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidAppendStringToFileAction() {
        // Arrange
        Action action = new AppendStringToFileAction("Test", "Hello, World!", new File("existing.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //DELETE FILE
    @Test
    public void execute_shouldReturnFalseForInvalidDeleteFileAction() {
        // Arrange
        Action action = new DeleteFileAction("Test", new File("nonexistent.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidDeleteFileAction() {
        // Arrange
        Action action = new DeleteFileAction("Test", new File("existing.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //PASTE FILE
    @Test
    public void execute_shouldReturnFalseForInvalidPasteFileAction() {
        // Arrange
        Action action = new PasteFileAction("Test", new File("nonexistent.txt"), new File("destination.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidPasteFileAction() {
        // Arrange
        Action action = new PasteFileAction("Test", new File("existing.txt"), new File("destination.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }

    //MOVE FILE
    @Test
    public void execute_shouldReturnFalseForInvalidMoveFileAction() {
        // Arrange
        Action action = new MoveFileAction("Test", new File("nonexistent.txt"), new File("destination.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertFalse(result);
    }

    @Test
    public void execute_shouldReturnTrueForValidMoveFileAction() {
        // Arrange
        Action action = new MoveFileAction("Test", new File("existing.txt"), new File("destination.txt"));

        // Act
        boolean result = action.execute();

        // Assert
        assertTrue(result);
    }
}