package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PasteFileActionTest {

    @Test
    void execute() {
        File sourceFile = new File("C:\\Users\\maxi\\Desktop\\test\\test 1\\test.txt");

        File destinationPath = new File("C:\\Users\\maxi\\Desktop\\test\\test 2\\");

        PasteFileAction pasteFileAction = new PasteFileAction("PasteTest", sourceFile, destinationPath);

        boolean result = pasteFileAction.execute();

        assertTrue(result);

    }
}