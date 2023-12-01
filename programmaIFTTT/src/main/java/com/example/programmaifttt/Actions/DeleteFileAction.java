package com.example.programmaifttt.Actions;

import java.io.File;

public class DeleteFileAction extends  Action{

    public static final String type = "Delete File";
    private String path;


    public DeleteFileAction(String name, String path) {
        super(name, type, "Path:" + path);
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
