package com.example.programmaifttt.Actions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ExternalProgramAction extends Action {
    public static final String type = "External Program";
    private File externalProgram; // Path to the esecutable file
    private String[] commandLineArguments; // Array of command line arguments

    public ExternalProgramAction(String name, File externalProgram, String[] commandLineArguments) {
        super(name, type, "File:" + externalProgram.getName() + "/CommandLineArguments:" + Arrays.toString(commandLineArguments), externalProgram.getAbsolutePath());
        this.externalProgram = externalProgram;
        this.commandLineArguments = commandLineArguments;
    }

    public File getExternalProgram() {
        return externalProgram;
    }

    public String[] getCommandLineArguments() {
        return commandLineArguments;
    }

    @Override
    public boolean execute() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command().add(externalProgram.getPath());
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