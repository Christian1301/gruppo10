package com.example.programmaifttt.Actions;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MessageBoxAction extends Action{
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
            // Sleep for 5 seconds
            Thread.sleep(5000);

            // Use Platform.runLater to safely interact with JavaFX components
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText(message);

                // Wait for the user to click OK
                alert.showAndWait();

                // Resume the background thread after the user closes the dialog
                // You may want to handle this differently based on your requirements
                // For example, you could use a callback or CompletableFuture
                // to notify the calling code that the user has closed the dialog.
            });

            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
