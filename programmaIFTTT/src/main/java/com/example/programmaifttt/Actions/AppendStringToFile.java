package com.example.programmaifttt.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendStringToFile  extends Action{
    public static final String type = "String to File";
    String message;
    File file;

    public AppendStringToFile(String name, String message, File file){
        super(name,type,"File: " + file.getName() + "/message to append: " + message);
        this.message=message;
        this.file=file;
    }

    public String getMessage() {
        return message;
    }
    public  File  getFile()   {  return file;}

    @Override
    public boolean execute() {
       try {
           FileWriter fileWriter = new FileWriter(file, true);
           fileWriter.write(message);
           fileWriter.close();
           return true;
       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
           return false;
       }

   }

}
