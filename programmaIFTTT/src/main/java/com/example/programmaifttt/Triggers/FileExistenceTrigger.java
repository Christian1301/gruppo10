package com.example.programmaifttt.Triggers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileExistenceTrigger extends Trigger {
    public static final String type = "File Existence";
    private final File file;

    public FileExistenceTrigger(String name, File file) {
        super(name, type, "File: " + file.getName() + "/Directory: " + file.getAbsolutePath(), file.getAbsolutePath());
        this.file = file;
    }

    public String getFileName() {
        return file.getName();
    }

    public String getDirectory() {
        return file.getAbsolutePath();
    }

    @Override
    public boolean evaluate() {
        return isEvaluable();
    }

    @Override
    public boolean isEvaluable() {
        return Files.exists(Paths.get(file.getAbsolutePath()));
    }
}