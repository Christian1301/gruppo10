package com.example.programmaifttt.Actions;

import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;

public class CombinedAction extends Action{

    public static final String type = "Combined Action";
    private Action action1;
    private Action action2;

    private RuleController ruleController;

    public CombinedAction(String name, Action action1, Action action2) {
        super(name, type, action1.getName() + " + " + action2.getName());
        this.action1 = action1;
        this.action2 = action2;
        this.ruleController = RuleController.getInstance();
    }

    public CombinedAction(String name, String action1, String action2) {
        super(name, type, action1 + " + " + action2);
        this.ruleController = RuleController.getInstance();
        this.action1 = ruleController.getActionByName(action1);
        this.action2 = ruleController.getActionByName(action2);
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


    public boolean checkIfUsed(Action action) {
        boolean result = false;
        if (action.equals(action1) || action.equals(action2)) {
            result = true;
        }
        else {
            if(action1 instanceof CombinedAction) {
                CombinedAction combinedAction = (CombinedAction) action1;
                result = combinedAction.checkIfUsed(action);
            }
            if(!result) {
                if (action2 instanceof CombinedAction) {
                    CombinedAction combinedAction = (CombinedAction) action2;
                    result = combinedAction.checkIfUsed(action);
                }
            }
        }
        return result;
    }
}
