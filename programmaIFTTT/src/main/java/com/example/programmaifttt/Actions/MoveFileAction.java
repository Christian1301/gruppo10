package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveFileAction extends Action {
    public static final String type = "Move File";
    private File fileToMove;
    private String destinationPath;

    public MoveFileAction(String name, File fileToMove, String destinationPath) {
        super(name, type, "File:" + fileToMove.getName() + "/DestinationPath:" + destinationPath);
        this.fileToMove = fileToMove;
        this.destinationPath = destinationPath;
    }

    public File getFileToMove() {
        return fileToMove;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    @Override
    public boolean execute() {
        try {
            // Creazione degli oggetti per rappresentare i percorsi
            Path source = fileToMove.toPath();
            Path destination = new File(destinationPath).toPath();

            // Spostamento del file
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
