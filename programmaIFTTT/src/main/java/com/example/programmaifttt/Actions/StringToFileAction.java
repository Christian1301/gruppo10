package com.example.programmaifttt.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringToFileAction extends Action {
    public static final String type = "String to File";
    String message;
    File file;

    public StringToFileAction(String name, String message, File file) {
        super(name, type, "File: " + file.getName() + "/message: " + message);
        this.message = message;
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean execute() {
        try {
            //add the message to the file as a new line
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(message + "\n");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}
