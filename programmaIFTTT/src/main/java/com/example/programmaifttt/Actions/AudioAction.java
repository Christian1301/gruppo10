package com.example.programmaifttt.Actions;

import javax.swing.*;
import java.io.File;

public class AudioAction extends Action {
    private String audioFilePath;

    public AudioAction(String name, String type, String audioFilePath, String messageText) {
        super(name, type, "Path" + audioFilePath + "Message" + messageText);
        this.audioFilePath = audioFilePath;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public String getValue() {
        return audioFilePath;
    }

    @Override
    public boolean execute() {
        try {
            if (audioFilePath != null && !audioFilePath.isEmpty()) {
                File audioFile = new File(audioFilePath);
                if (audioFile.exists()) {
                    Runtime.getRuntime().exec("cmd /c start " + audioFilePath);
                } else {
                    JOptionPane.showMessageDialog(null, "Il file audio non esiste", "Errore", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}