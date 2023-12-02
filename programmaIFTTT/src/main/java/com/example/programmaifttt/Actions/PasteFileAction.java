package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PasteFileAction extends Action {
    public static final String type = "Paste File";
    private File fileToPaste;
    private File destinationPath;

    public PasteFileAction(String name, File fileToPaste, File destinationPath) {
        super(name, type, "File:" + fileToPaste + "/DestinationPath:" + destinationPath.getAbsolutePath(), fileToPaste.getAbsolutePath());
        this.fileToPaste = fileToPaste;
        this.destinationPath = destinationPath;
    }

    public File getFileToPaste() {
        return fileToPaste;
    }

    public File getDestinationPath() {
        return destinationPath;
    }

    @Override
    public boolean execute() {
        try {
            // Get the absolute path of the file to paste and of the destination
            Path source = fileToPaste.toPath();
            Path destination = destinationPath.toPath();
            Files.copy(source, destination.resolve(fileToPaste.getName()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
