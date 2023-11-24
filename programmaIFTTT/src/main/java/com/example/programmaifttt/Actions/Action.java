package com.example.programmaifttt.Actions;

public interface Action {
  // Get the name of the action
  String getName();

  // Set the name of the action
  void setName(String name);

  // Execute the action
  void execute();
}
