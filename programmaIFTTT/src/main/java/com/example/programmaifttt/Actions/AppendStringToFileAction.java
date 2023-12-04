package com.example.programmaifttt.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendStringToFileAction extends Action{
    public static final String type = "Append String to File";
    String message;
    File file;

    public AppendStringToFileAction(String name, String message, File file){
        super(name,type,"File: " + file.getName() + "/message to append: " + message, file.getAbsolutePath());
        this.message = message;
        this.file = file;
    }

    public String getMessage() {
        return message;
    }
    public  File  getFile()   {  return file;}

    @Override
    public boolean execute() {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(message+"\n");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

    }

}