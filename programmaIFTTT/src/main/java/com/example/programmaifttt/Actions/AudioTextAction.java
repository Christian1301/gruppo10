package com.example.programmaifttt.Actions;

import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.io.File;

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
        try {
            String mediaSource = audioFile.toURI().toString();
            Media media = new Media(mediaSource);
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setOnEndOfMedia(() -> {
                // This is called when the audio finishes playing
                mediaPlayer.stop();
            });

            mediaPlayer.play();

            // Sleep while the audio is playing
            Thread.sleep((long) media.getDuration().toMillis());

            // Use Platform.runLater to safely interact with JavaFX components
            Platform.runLater(() -> {
                // Update JavaFX components or perform UI-related tasks here if needed
            });

            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }
}