package com.example.programmaifttt.Actions;

public interface Action {
  // Get the name of the action
  String getName();

  // Set the name of the action
  void setName(String name);

  // Get the value of the action
  String getValue();

  // Get the type of the action
  String getType();


  // Execute the action
  void execute();
}
