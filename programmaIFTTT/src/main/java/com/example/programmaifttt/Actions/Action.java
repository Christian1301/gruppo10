package com.example.programmaifttt.Actions;

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
}
