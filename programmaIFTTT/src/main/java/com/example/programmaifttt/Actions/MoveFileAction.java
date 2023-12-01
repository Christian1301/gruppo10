package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveFileAction extends Action {
    public static final String type = "Move File";
    private File fileToMove;
    private File destinationPath;

    public MoveFileAction(String name, File fileToMove, File destinationPath) {
        super(name, type, "File:" + fileToMove.getName() + "/DestinationPath:" + destinationPath.getAbsolutePath(), fileToMove.getAbsolutePath());
        this.fileToMove = fileToMove;
        this.destinationPath = destinationPath;
    }

    public File getFileToMove() {
        return fileToMove;
    }

    public File getDestinationPath() {
        return destinationPath;
    }

    @Override
    public boolean execute() {
        try {
            // Get the absolute path of the file to move and of the destination
            Path source = fileToMove.toPath();
            Path destination = destinationPath.toPath();
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            // Delete the file from the original path
            Files.deleteIfExists(source);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
