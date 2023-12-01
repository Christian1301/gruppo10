package com.example.programmaifttt.Actions;

import java.io.File;

public class DeleteFileAction extends  Action{
    public static final String type = "Delete File";
    private File fileToDelete;

    public DeleteFileAction(String name, File fileToDelete) {
        super(name, type, "File:" + fileToDelete.getName());
        this.fileToDelete = fileToDelete;
    }

    public File getFileToDelete() {
        return fileToDelete;
    }

    @Override
    public boolean execute() {
        return fileToDelete.delete();
    }
}
