package com.example.programmaifttt.Actions;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class StringToFileAction {
 String message;
 File file;
    public StringToFileAction(String message, File file){
    this.message=message;
    this.file=file;
    }

public void   FileWriter(String message, File file){
    try {
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(message);
        myWriter.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}

}
