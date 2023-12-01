package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileActionTest {

    @Test
    void execute() {
        File fileToDelete = new File("C:\\Users\\maxi\\Desktop\\test\\test 1\\test.txt");
        DeleteFileAction action = new DeleteFileAction("test", fileToDelete);
        action.execute();
        assertFalse(fileToDelete.exists());
    }
}