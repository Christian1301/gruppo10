package com.example.programmaifttt.Actions;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExternalProgramActionTest {

    @Test
    void execute() {
        File externalProgram = new File("C:\\Users\\maxi\\AppData\\Local\\Programs\\Opera GX\\launcher.exe");
        String commandLineArguments = "https://www.youtube.com/watch?v=6n3pFFPSlW4";
        ExternalProgramAction externalProgramAction = new ExternalProgramAction("External Program", externalProgram, commandLineArguments);
        assertTrue(externalProgramAction.execute());
    }
}