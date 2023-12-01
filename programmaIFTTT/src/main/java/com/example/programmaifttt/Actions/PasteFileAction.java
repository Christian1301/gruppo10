package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PasteFileAction extends Action {
    public static final String type = "Paste File";
    private String sourcePath;
    private String destinationPath;

    public PasteFileAction(String name, String sourcePath, String destinationPath) {
        super(name, type, "SourcePath:" + sourcePath + "/DestinationPath:" + destinationPath);
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    @Override
    public boolean execute() {
        try {
            Path source = new File(sourcePath).toPath();
            Path destination = new File(destinationPath).toPath();
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
