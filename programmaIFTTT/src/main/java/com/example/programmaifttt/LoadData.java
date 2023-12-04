package com.example.programmaifttt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadData {
    public static Data loadDatas() {
        String percorsoFile = "src/main/resources/data.json";
        try {
            String content = new String(Files.readAllBytes(Paths  .get(percorsoFile)));
            return Data.fromJson(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
