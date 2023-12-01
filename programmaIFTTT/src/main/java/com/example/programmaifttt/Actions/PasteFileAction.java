package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PasteFileAction extends Action {
    public static final String type = "Paste File";
    private File fileToPaste;
    private String destinationPath;

    public PasteFileAction(String name, File fileToPaste, String destinationPath) {
        super(name, type, "File:" + fileToPaste + "/DestinationPath:" + destinationPath);
        this.fileToPaste = fileToPaste;
        this.destinationPath = destinationPath;
    }

    public File getFileToPaste() {
        return fileToPaste;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    @Override
    public boolean execute() {
        try {
            Path source = fileToPaste.toPath();
            Path destination = new File(destinationPath).toPath();
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
