package com.example.programmaifttt.Actions;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MessageBoxAction extends Action {
    public static final String type = "Message Box";
    private String message;

    public MessageBoxAction(String name, String message) {
        super(name, type, message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean execute() {
        try {
            // Sleep for 1 seconds
            Thread.sleep(1000);

            // Use Platform.runLater to safely interact with JavaFX components
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText(message);

                // Wait for the user to click OK
                alert.showAndWait();
            });

            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
