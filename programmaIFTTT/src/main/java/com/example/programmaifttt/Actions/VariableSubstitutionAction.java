package com.example.programmaifttt.Actions;

import com.example.programmaifttt.Counter.CounterManager;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class VariableSubstitutionAction extends Action {
    public static final String type = "Variable Substitution";
    private String variableName;
    private String variableValue;
    private CounterManager counterManager;

    public VariableSubstitutionAction(String name, String variableName, String variableValue, CounterManager counterManager) {
        super(name, type, "Variable Name: " + variableName + ", Variable Value: " + variableValue);
        this.variableName = variableName;
        this.variableValue = variableValue;
        this.counterManager = counterManager;
    }

    @Override
    public boolean execute() {
        try {
            String counterValue = String.valueOf(counterManager.getCounter(variableName));
            String substitutedAction = variableValue.replace("$" + variableName, counterValue);
            // Sleep for 1 seconds
            Thread.sleep(1000);

            // Use Platform.runLater to safely interact with JavaFX components
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(type);
                alert.setHeaderText(null);
                alert.setContentText(substitutedAction);

                // Wait for the user to click OK
                alert.showAndWait();
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}