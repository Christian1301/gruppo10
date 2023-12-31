package com.example.programmaifttt.BackEnd;


import com.example.programmaifttt.IFTTTController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Scheduler implements RuleControllerObserver {

    private int interval;
    private RuleController ruleController;
    private Thread schedulerThread;

    private IFTTTController iftttController;

    public Scheduler(int interval, IFTTTController IFTTTController) {
        this.interval = interval;
        this.ruleController = RuleController.getInstance();
        this.iftttController = IFTTTController;
    }

    public void start() {
        ruleController.addObserver(this);
        schedulerThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(interval * 1000);
                    checkRules();
                    updateUI();
                } catch (InterruptedException e) {
                    // Thread interrupted, exit the loop
                    break;
                }
            }
        });
        schedulerThread.start();
    }

    public void stop() {
        ruleController.removeObserver(this);
        if (schedulerThread != null && schedulerThread.isAlive()) {
            schedulerThread.interrupt();
            try {
                schedulerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(RuleController ruleController) {
        // RuleController has been updated from outside
        // You might want to add additional logic here if needed
        updateUI();
    }

    public void checkRules() {
        synchronized (ruleController) {
            List<Rule> rulesToDelete = new ArrayList<>();
            for (Rule rule : ruleController.getRules()) {
                if (rule.getState()) {
                    if (rule.getLastUse() == null || (rule.getLastUse() != null && rule.getLastUse().getTime() + rule.getSleepTime() * 1000 > System.currentTimeMillis())) {
                        if (rule.getTrigger().evaluate()) {
                            if (rule.getAction().execute()) {
                                rule.setLastUse();
                                if (!rule.isMultiUse()) {
                                    rulesToDelete.add(rule);
                                }
                            } else {
                                // Create popup window with error
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Error with action " + rule.getAction().getName() +
                                            " of rule " + rule.getName());

                                    // Wait for the user to click OK
                                    alert.showAndWait();
                                });
                            }
                        }
                    }
                }
            }
            // Remove the rules outside the loop after finishing the iteration
            Platform.runLater(() -> {
                for (Rule rule : rulesToDelete) {
                    ruleController.deleteRule(rule.getName());

                }
            });
        }
    }

    private void updateUI() {
        if (iftttController != null) {
            iftttController.updateList();
        }
    }
}