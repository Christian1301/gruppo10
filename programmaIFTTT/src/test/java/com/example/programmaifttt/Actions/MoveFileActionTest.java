package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

class MoveFileActionTest {

    @Test
    void execute_shouldMoveFileSuccessfully() {
        // Arrange
        File sourceFile = new File("C:\\Users\\maxi\\Desktop\\test\\test 1\\test.txt");
        File destinationPath = new File("C:\\Users\\maxi\\Desktop\\test\\test 2\\");
        createTestFile(sourceFile);

        MoveFileAction moveFileAction = new MoveFileAction("MoveTest", sourceFile, destinationPath);

        // Act
        boolean result = moveFileAction.execute();

        // Assert
        assertTrue(result);
        assertFalse(sourceFile.exists()); // File should not exist in the original path
        assertTrue(new File(destinationPath, sourceFile.getName()).exists()); // File should exist in the destination path
    }

    @Test
    void execute_shouldReturnFalseOnError() {
        // Arrange
        File sourceFile = new File("test.txt");
        File destinationPath = new File("destination");

        MoveFileAction moveFileAction = new MoveFileAction("MoveTest", sourceFile, destinationPath);

        // Act
        boolean result = moveFileAction.execute();

        // Assert
        assertFalse(result);
        assertFalse(destinationPath.exists()); // Destination path should not be created on failure
    }

    private void createTestFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
