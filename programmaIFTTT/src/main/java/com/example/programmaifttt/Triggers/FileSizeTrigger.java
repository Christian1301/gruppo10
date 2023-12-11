package com.example.programmaifttt.Triggers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSizeTrigger extends Trigger {
    public static final String type = "File Size";
    private final File file;
    private final long sizeThreshold;

    public FileSizeTrigger(String name, File file, long sizeThreshold) {
        super(name, type, "File: " + file.getName() + "/Size Threshold: " + sizeThreshold, file.getAbsolutePath());
        this.file = file;
        this.sizeThreshold = sizeThreshold;
    }

    public String getFilePath() {
        return file.getAbsolutePath();
    }

    public long getSizeThreshold() {
        return sizeThreshold;
    }

    @Override
    public boolean evaluate() {
        Path path = file.getAbsoluteFile().toPath();
        try {
            long fileSize = Files.size(path);
            return fileSize > sizeThreshold;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEvaluable() throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        return Files.exists(path) && Files.size(path) > sizeThreshold;
    }
}