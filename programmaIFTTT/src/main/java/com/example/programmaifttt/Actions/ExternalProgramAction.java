package com.example.programmaifttt.Actions;

import java.io.IOException;
import java.util.Arrays;

public class ExternalProgramAction extends Action {
    private String externalProgram; // Path to the esecutable file
    private String[] commandLineArguments; // Array of command line arguments

    public ExternalProgramAction(String name, String externalProgram, String[] commandLineArguments) {
        super(name, "ExternalProgram", "ProgramPath:" + externalProgram + "/CommandLineArguments" + Arrays.toString(commandLineArguments));
        this.externalProgram = externalProgram;
        this.commandLineArguments = commandLineArguments;
    }

    public String getExternalProgram() {
        return externalProgram;
    }

    public String[] getCommandLineArguments() {
        return commandLineArguments;
    }

    @Override
    public boolean execute() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command().add(externalProgram);
            processBuilder.command().addAll(Arrays.asList(commandLineArguments));
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("Il programma esterno è stato eseguito con codice di uscita: " + exitCode);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}