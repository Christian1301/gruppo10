package com.example.programmaifttt.Actions;

import java.io.File;
import java.io.IOException;

public class ExternalProgramAction extends Action {
    public static final String type = "External Program";
    private File externalProgram; // Path to the esecutable file
    private String commandLineArguments; // Array of command line arguments

    public ExternalProgramAction(String name, File externalProgram, String commandLineArguments) {
        super(name, type, "File: " + externalProgram.getName() + "/CommandLineArguments: " + commandLineArguments, externalProgram.getAbsolutePath());
        this.externalProgram = externalProgram;
        this.commandLineArguments = commandLineArguments;
    }

    public File getExternalProgram() {
        return externalProgram;
    }

    public String getCommandLineArguments() {
        return commandLineArguments;
    }

    @Override
    public boolean execute() {
        try {
            // Create a process builder and start the external program
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command().add(externalProgram.getAbsolutePath());
            processBuilder.command().addAll(java.util.Arrays.asList(commandLineArguments.split(" ")));
            processBuilder.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}