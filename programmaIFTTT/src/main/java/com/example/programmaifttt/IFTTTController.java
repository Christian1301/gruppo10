package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.Triggers.TimeOfDayTrigger;
import com.example.programmaifttt.Triggers.Trigger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IFTTTController {

    @FXML
    private ChoiceBox<Action> actionSelect;

    @FXML
    private Button editRulesBtn;

    @FXML
    private Button ruleCreateBtn;

    @FXML
    private TextField ruleName;

    @FXML
    private TableView<Rule> ruleTable;

    @FXML
    private TableColumn<Rule, Action> ruleTableAction;

    @FXML
    private TableColumn<Rule, String> ruleTableName;

    @FXML
    private TableColumn<Rule, Trigger> ruleTableTrigger;

    @FXML
    private ChoiceBox<Trigger> triggerSelect;
    @FXML
    private Button editTriggersBtn;
    @FXML
    private Button editActionsBtn;
    @FXML
    private SplitPane rulePage;
    @FXML
    private TableView<Trigger> triggerTable;
    @FXML
    private TableColumn<Trigger, String> triggerTableName;
    @FXML
    private TableColumn<Trigger, String> triggerTableType;
    @FXML
    private TableColumn<Trigger, String> triggerTableValue;
    @FXML
    private TextField triggerName;
    @FXML
    private ChoiceBox<Integer> timeTriggerMinutes;
    @FXML
    private Button triggerCreateBtn;
    @FXML
    private AnchorPane timeOfDayTriggerValSel;
    @FXML
    private ChoiceBox<Integer> timeTriggerHours;
    @FXML
    private ChoiceBox<String> triggerTypeSelect;
    @FXML
    private SplitPane triggerPage;

    private RuleController ruleController;
    private BooleanProperty createRuleButtonDisabled = new SimpleBooleanProperty(true);
    private BooleanProperty createTriggerButtonDisabled = new SimpleBooleanProperty(true);

    private ObservableList<Rule> ruleData = FXCollections.observableArrayList();
    private ObservableList<Trigger> triggerData = FXCollections.observableArrayList();
    private ObservableList<Action> actionData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        this.ruleController = new RuleController();
        //init the pages
        disablePages();
        initRulePage();
        initTriggerPage();

    }

    private void disablePages() {
        //disable rule page
        rulePage.setDisable(true);
        rulePage.setVisible(false);

        //disable trigger page
        triggerPage.setDisable(true);
        triggerPage.setVisible(false);

        //disable action page


    }

    @FXML
    public void showRulesPage(ActionEvent actionEvent) {
        disablePages();
        rulePage.setDisable(false);
        rulePage.setVisible(true);
    }

    @FXML
    public void showTriggersPage(ActionEvent actionEvent) {
        disablePages();
        triggerPage.setDisable(false);
        triggerPage.setVisible(true);
    }

    @FXML
    public void showActionsPage(ActionEvent actionEvent) {
        disablePages();
    }

    @FXML
    public void createRule(ActionEvent actionEvent) {
        String name = ruleName.getText();
        Trigger trigger = triggerSelect.getValue();
        Action action = actionSelect.getValue();

        Rule newRule = new Rule(name, trigger, action);
        ruleController.addRule(newRule);
        Data data = new Data();
        data.addRule(newRule);
        // Refresh the table after adding a new rule
        ruleData.setAll(ruleController.getRules());
    }

    @FXML
    public void createTrigger(ActionEvent actionEvent) {
        String name = triggerName.getText();
        String type = triggerTypeSelect.getValue();
        //create the trigger based on type
        Trigger newTrigger = createNewTrigger(name, type);

        ruleController.addTrigger(newTrigger);
        Data data = new Data();
        data.addTrigger(newTrigger);
        //refresh the table after adding a new trigger
        triggerData.setAll(ruleController.getTriggers());
        //refresh the trigger list in rule page
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));


    }

    private Trigger createNewTrigger(String name, String type) {

        switch (triggerTypeSelect.getValue()) {
            case "Time Of Day" -> {
                int hours = timeTriggerHours.getValue();
                int minutes = timeTriggerMinutes.getValue();
                return  new TimeOfDayTrigger(name,hours,minutes);
            }
        }
        return null;
    }

    @FXML
    public void changeTriggerValueBox(ActionEvent event) {
        updateTriggerValueBox();
    }


    private void updateTriggerValueBox() {
        disableTriggerValueBox();
        String selectedType = triggerTypeSelect.getValue();
        switch (selectedType) {
            case "Time Of Day" -> {
                timeOfDayTriggerValSel.setDisable(false);
                timeOfDayTriggerValSel.setVisible(true);
            }
        }

    }

    private void initRulePage(){

        // Set up the rule table columns
        ruleTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleTableTrigger.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        ruleTableAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Connect the ruleData to the table
        ruleTable.setItems(ruleData);

        // Load initial data into the table (if any)
        ruleData.addAll(ruleController.getRules());

        // Disable "Create" button by default
        ruleCreateBtn.disableProperty().bind(createRuleButtonDisabled);

        // Bind the createButtonDisabled property based on whether all three values are inserted
        createRuleButtonDisabled.bind(
                Bindings.isEmpty(ruleName.textProperty())
                        .or(Bindings.isNull(triggerSelect.valueProperty()))
                        .or(Bindings.isNull(actionSelect.valueProperty()))
        );

        // Set items for the ChoiceBoxes
        actionSelect.setItems(FXCollections.observableArrayList(ruleController.getActions()));
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));

    }

    private void initTriggerPage(){
        // Set up the trigger table columns
        triggerTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        triggerTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        triggerTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Connect the triggerData to the table
        triggerTable.setItems(triggerData);

        // Load initial data into the table (if any)
        triggerData.addAll(ruleController.getTriggers());


        // Disable "Create" button by default
        triggerCreateBtn.disableProperty().bind(createTriggerButtonDisabled);

        // Bind the createButtonDisabled property based on whether all three values are inserted
        createTriggerButtonDisabled.bind(
                Bindings.isEmpty(triggerName.textProperty())
                        .or(Bindings.isNull(triggerTypeSelect.valueProperty()))
        );

        // Populate the ChoiceBox with all the known types
        triggerTypeSelect.setItems(FXCollections.observableArrayList("Time Of Day", "Test"));

        initTriggerTypes();

    }

    private void initTriggerTypes(){

        //Time Of Day init
        initTriggerTypeTOD();
        disableTriggerValueBox();
    }

    private void disableTriggerValueBox() {
        //disable all the trigger value boxes
        timeOfDayTriggerValSel.setDisable(true);
        timeOfDayTriggerValSel.setVisible(false);
    }

    private void initTriggerTypeTOD(){
        // Populate the ChoiceBox with numbers from 0 to 23
        timeTriggerHours.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

        // Set 0 as the default value
        timeTriggerHours.setValue(0);

        // Populate the ChoiceBox with numbers from 0 to 59 for minutes
        timeTriggerMinutes.setItems(FXCollections.observableArrayList(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59
        ));

        // Set 0 as the default value for minutes
        timeTriggerMinutes.setValue(0);
    }



}
