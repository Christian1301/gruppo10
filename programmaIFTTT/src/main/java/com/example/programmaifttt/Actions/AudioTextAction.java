package com.example.programmaifttt.Actions;

import javax.swing.*;
import java.io.File;

public class AudioTextAction extends Action {
    private String audioFilePath;
    private String messageText;

    public AudioTextAction(String name, String type, String audioFilePath, String messageText) {
        super(name, type, "Path" + audioFilePath + "Message" + messageText);
        this.audioFilePath = audioFilePath;
        this.messageText = messageText;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String getValue() {
        return audioFilePath == null ? messageText : audioFilePath;
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
            JOptionPane.showMessageDialog(null, messageText, "Messaggio", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Eseguendo l'azione AudioTextAction: " + getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}