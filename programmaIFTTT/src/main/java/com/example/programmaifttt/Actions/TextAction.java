package com.example.programmaifttt.Actions;

import javax.swing.*;

public class TextAction extends Action {
    private String messageText;

    public TextAction(String name, String type, String messageText) {
        super(name, type, "Message: " + messageText);
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String getValue() {
        return messageText;
    }

    @Override
    public boolean execute() {
        try {
            JOptionPane.showMessageDialog(null, messageText, "Messaggio", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Eseguendo l'azione AudioTextAction: " + getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
