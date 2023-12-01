package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AppendStringToFileActionTest {

    @Test
    void execute() {
        File fileToAppend = new File("C:\\Users\\maxi\\Desktop\\test\\test 1\\prova.txt");
        AppendStringToFileAction action = new AppendStringToFileAction("test", "test", fileToAppend);
        assertTrue(action.execute());
    }
}