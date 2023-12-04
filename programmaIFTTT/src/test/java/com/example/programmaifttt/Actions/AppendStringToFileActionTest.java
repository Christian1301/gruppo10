package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class AppendStringToFileActionTest {

    @Test
    void execute_shouldAppendStringToFileSuccessfully() {
        // Arrange
        File testFile = createTestFile("test.txt", "Initial content.");

        AppendStringToFileAction appendStringAction = new AppendStringToFileAction("AppendTest", "Appended message.", testFile);

        // Act
        boolean result = appendStringAction.execute();

        // Assert
        assertTrue(result);
        String fileContent = readFromFile(testFile);
        assertTrue(fileContent.contains("Initial content."));
        assertTrue(fileContent.contains("Appended message."));
    }

    @Test
    void execute_shouldReturnFalseOnError() {
        // Arrange
        // Creating a file in a non-writable directory to force an error
        File nonWritableFile = new File("/non-writable-directory/test.txt");
        nonWritableFile.getParentFile().mkdirs(); // Creating parent directories
        nonWritableFile.setReadOnly(); // Making the file non-writable

        AppendStringToFileAction appendStringAction = new AppendStringToFileAction("AppendTest", "Appended message.", nonWritableFile);

        // Act
        boolean result = appendStringAction.execute();

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

    private String readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
