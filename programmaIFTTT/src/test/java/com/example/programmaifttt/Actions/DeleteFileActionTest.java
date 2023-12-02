package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileActionTest {

    @Test
    void execute_shouldDeleteFileSuccessfully() {
        // Arrange
        File testFile = createTestFile("test.txt", "Test content.");

        DeleteFileAction deleteFileAction = new DeleteFileAction("DeleteTest", testFile);

        // Act
        boolean result = deleteFileAction.execute();

        // Assert
        assertTrue(result);
        assertFalse(testFile.exists()); // File should not exist after deletion
    }

    @Test
    void execute_shouldReturnFalseOnError() {
        // Arrange
        File testFile = new File("test.txt"); // File does not exist

        DeleteFileAction deleteFileAction = new DeleteFileAction("DeleteTest", testFile);

        // Act

        boolean result = deleteFileAction.execute();

        // Assert

        assertFalse(result);



    }

    private File createTestFile(String fileName, String content) {
        File file = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
