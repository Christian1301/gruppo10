package com.example.programmaifttt.Triggers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExternalProgramTrigger extends Trigger {
    public static final String type = "External Program";
    private final File externalProgram;
    private final String commandLineArguments;
    private final int exitStatus;

    public ExternalProgramTrigger(String name, File externalProgram, String commandLineArguments, int exitStatus) {
        super(name, type, "Program: " + externalProgram.getName() + "/Arguments: " + commandLineArguments + "/Exit Status: " + exitStatus, externalProgram.getAbsolutePath());
        this.externalProgram = externalProgram;
        this.commandLineArguments = commandLineArguments;
        this.exitStatus = exitStatus;
    }

    public File getExternalProgram() {
        return externalProgram;
    }

    public String getCommandLineArguments() {
        return commandLineArguments;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    @Override
    public boolean evaluate() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(externalProgram.getAbsolutePath());
        processBuilder.command().addAll(java.util.Arrays.asList(commandLineArguments.split(" ")));
        try {
            Process process = processBuilder.start();
            return process.waitFor() == exitStatus;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEvaluable() {
        return Files.exists(Paths.get(externalProgram.getAbsolutePath()));
    }
}