package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveFileAction extends Action{
    private String sourcePath;
    private String destinationPath;

    public MoveFileAction(String name, String sourcePath, String destinationPath) {
        super(name, "Move File", "SourcePath:" + sourcePath + "/DestinationPath:" + destinationPath);
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
            // Creazione degli oggetti per rappresentare i percorsi
            Path source = new File(sourcePath).toPath();
            Path destination = new File(destinationPath).toPath();

            // Spostamento del file
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

            // Restituzione true se il processo è riuscito
            return true;
        } catch (Exception e) {
            // Gestione delle eccezioni, ad esempio nel caso di un errore di spostamento del file
            e.printStackTrace();
            return false;
        }
    }
}
