package com.example.programmaifttt.Actions;

import java.io.File;
import java.nio.file.Files;

public class DeleteFileAction extends  Action{
    public static final String type = "Delete File";
    private File fileToDelete;

    public DeleteFileAction(String name, File fileToDelete) {
        super(name, type, "File:" + fileToDelete.getName(), fileToDelete.getAbsolutePath());
        this.fileToDelete = fileToDelete;
    }

    public File getFileToDelete() {
        return fileToDelete;
    }

    @Override
    public boolean execute() {
        try {
            Files.delete(fileToDelete.toPath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
