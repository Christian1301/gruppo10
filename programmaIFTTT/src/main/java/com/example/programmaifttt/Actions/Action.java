package com.example.programmaifttt.Actions;

import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.Counter.CounterManager;
import org.json.JSONObject;
import java.io.File;

public abstract class Action {
    private String name;
    private String type;
    private String value;
    private String filePath;

    public Action(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.filePath = null;
    }

    public Action(String name, String type, String value, String filePath) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.filePath = filePath;
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
    public String toString() {
        return name;
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
        switch (type) {
            case AudioAction.type , ExternalProgramAction.type, AppendStringToFileAction.type, DeleteFileAction.type,
                    MoveFileAction.type, PasteFileAction.type
                    -> jsonObject.put("filePath", filePath);
        }
        return jsonObject;
    }

    public static Action fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String value = jsonObject.getString("value");
        CounterManager CounterManager;
        return switch (type) {
            case MessageBoxAction.type ->
                    new MessageBoxAction(name, value);
            case AudioAction.type ->
                    new AudioAction(name, new File(jsonObject.getString("filePath")));
            case ExternalProgramAction.type ->
                    new ExternalProgramAction(name, new File(jsonObject.getString("filePath")), value.split("/")[1].split(": ")[1]);
            case AppendStringToFileAction.type ->
                    new AppendStringToFileAction(name, value.split("/")[1].split(": ")[1], new File(jsonObject.getString("filePath")));
            case DeleteFileAction.type ->
                    new DeleteFileAction(name, new File(jsonObject.getString("filePath")));
            case MoveFileAction.type ->
                    new MoveFileAction(name, new File(jsonObject.getString("filePath")), new File(value.split("/")[1].split(": ")[1]));
            case PasteFileAction.type ->
                    new PasteFileAction(name, new File(jsonObject.getString("filePath")), new File(value.split("/")[1].split(": ")[1]));
            case SetCounterValueAction.type -> {
                    CounterManager = new CounterManager();
                    String counterName = value.split("/")[0].split(": ")[1].split(": ")[0];
                    int counterValue = Integer.parseInt(value.split("/")[1].split(": ")[1]);
                    CounterManager.createCounter(counterName, counterValue);
                    yield new SetCounterValueAction(name, CounterManager, counterName, counterValue);
            }
            case AddToCounterValueAction.type -> {
                    CounterManager = new CounterManager();
                    String counterName = value.split("/")[0].split(": ")[1];
                    int addValue = Integer.parseInt(value.split("/")[1].split(": ")[1]);
                    int currentValue = Integer.parseInt(value.split("/")[2].split(": ")[1]);
                    CounterManager.createCounter(counterName, currentValue);
                    yield new AddToCounterValueAction(name, CounterManager, counterName, addValue);
            }
            case AddCounterValueToAnotherAction.type -> {
                    CounterManager = new CounterManager();
                    String sourceCounterName = value.split("/")[0].split(": ")[1].split(", ")[0];
                    int sourceCounterValue = Integer.parseInt(value.split("/")[0].split(": ")[1].split(", ")[1]);
                    String targetCounterName = value.split("/")[1].split(": ")[1].split(", ")[0];
                    int targetCounterValue = Integer.parseInt(value.split("/")[1].split(": ")[1].split(", ")[1]);
                    CounterManager.createCounter(sourceCounterName, sourceCounterValue);
                    CounterManager.createCounter(targetCounterName, targetCounterValue);
                    yield new AddCounterValueToAnotherAction(name, CounterManager, sourceCounterName, targetCounterName);
            }
            case CombinedAction.type -> {
                    String action1 = value.split(" \\+ ")[0];
                    String action2 = value.split(" \\+ ")[1];
                    yield new CombinedAction(name, action1, action2);
            }
            default ->
                    null;
        };
    }

    public boolean isUsedIn() {
        boolean result = false;
        for (Action action : RuleController.getInstance().getActions()){
            if(!action.equals(this)){
                if(action instanceof CombinedAction){
                    CombinedAction combinedAction = (CombinedAction) action;
                    result = combinedAction.checkIfUsed(this);
                }
            }
        }
        return result;
    }
}
