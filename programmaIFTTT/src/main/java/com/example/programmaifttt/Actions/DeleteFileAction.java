package com.example.programmaifttt.Actions;

import java.io.File;

public class DeleteFileAction extends  Action{
    private String path;

    public DeleteFileAction(String name, String path) {
        super(name, "Delete File", "Path:" + path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean execute() {
        File file = new File(path);
        return file.delete();
    }
}
