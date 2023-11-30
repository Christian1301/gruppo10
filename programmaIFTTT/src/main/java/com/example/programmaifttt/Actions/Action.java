package com.example.programmaifttt.Actions;

import org.json.JSONObject;

import java.io.File;

public abstract class Action {
      private String name;
      private String type;
      private String value;

      // Constructor
      public Action(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getType() {
        return type;
      }

      public String getValue() {
          return value;
      }

      @Override
      public String toString() {return name;
      }

      //equals
      @Override
      public boolean equals(Object obj) {
        if (obj instanceof Action) {
          Action action = (Action) obj;
          return this.name.equals(action.name);
        }
        return false;
      }

      public abstract boolean execute();

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("type", type);
        jsonObject.put("value", value);
        return jsonObject;
    }

    public static Action fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String value = jsonObject.getString("value");
        return switch (type) {
            case "Audio" -> new AudioAction(name, new File(value));
            case "Message Box" -> new MessageBoxAction(name, value);
            case "ExternalProgram" -> new ExternalProgramAction(name, value.split("/")[0].split(": ")[1], value.split("/")[1].split(": ")[1].substring(1, value.split("/")[1].split(": ")[1].length() - 1).split(", "));
            case "String to File" -> new StringToFileAction(name, value.split("/")[1].split(": ")[1], new File(value.split("/")[0].split(": ")[1]));
            default -> null;
        };
    }
}
