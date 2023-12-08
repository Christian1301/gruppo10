package com.example.programmaifttt.Actions;

public class CombinedAction extends Action{

    public static final String type = "Combined Action";
    private Action action1;
    private Action action2;

    public CombinedAction(String name, Action action1, Action action2) {
        super(name, type, action1.getName() + " + " + action2.getName());
        this.action1 = action1;
        this.action2 = action2;
    }
    @Override
    public boolean execute() {
        try {
            //execute action1
            action1.execute();
            //execute action2
            action2.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUsedIn(Action action) {
        return action.equals(action1) || action.equals(action2) || action1.isUsedIn(action) || action2.isUsedIn(action);
    }
}
