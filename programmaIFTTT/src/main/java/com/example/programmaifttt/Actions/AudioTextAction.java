package com.example.programmaifttt.Actions;

import javafx.application.Platform;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioTextAction extends Action {
    public static final String type = "Audio Text";
    private File audioFile;

    public AudioTextAction(String name, File audioFile) {
        super(name, type, "File:" + audioFile.getName());
        this.audioFile = audioFile;

    }

    public File getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(File audioFile) {
        this.audioFile = audioFile;
    }

    @Override
    public boolean execute() {
        //play the audio file and return true if the audio file is played correctly
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            // Sleep while the audio is playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the clip
            clip.close();

            // Use Platform.runLater to safely interact with JavaFX components
            Platform.runLater(() -> {
                // Update JavaFX components or perform UI-related tasks here if needed
            });

            return true;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }



    }
}