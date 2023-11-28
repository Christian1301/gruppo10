package com.example.programmaifttt.Actions;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class StringToFileAction extends Action{
    public static final String type = "String to File";
    String message;
    File file;
    public StringToFileAction(String name, String message, File file){
        super(name,type,"File: " + file.getName() + "/message: " + message);
        this.message=message;
        this.file=file;
    }


    @Override
    public boolean execute() {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(message);
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}
