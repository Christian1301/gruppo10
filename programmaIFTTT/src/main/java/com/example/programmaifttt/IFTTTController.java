package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;

import com.example.programmaifttt.Actions.AudioAction;
import com.example.programmaifttt.Actions.MessageBoxAction;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.BackEnd.Scheduler;
import com.example.programmaifttt.Triggers.TimeOfDayTrigger;
import com.example.programmaifttt.Triggers.Trigger;
import javafx.application.Platform;
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

import javax.swing.*;
import java.io.File;
import java.util.List;

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

    @FXML
    private SplitPane actionPage;
    @FXML
    private TableView<Action> actionTable;
    @FXML
    private TableColumn<Action, String> actionTableName;
    @FXML
    private TableColumn<Action, String> actionTableType;
    @FXML
    private TableColumn<Action, String> actionTableValue;
    @FXML
    private TextField actionName;
    @FXML
    private ChoiceBox<String> actionTypeSelect;
    @FXML
    private Button actionCreateBtn;

    //private variables not from FXML
    private RuleController ruleController;
    private Scheduler scheduler;
    private File audioFile;
    private BooleanProperty createRuleButtonDisabled = new SimpleBooleanProperty(true);
    private BooleanProperty createTriggerButtonDisabled = new SimpleBooleanProperty(true);
    private BooleanProperty createActionButtonDisabled = new SimpleBooleanProperty(true);

    private BooleanProperty actionValueSelected = new SimpleBooleanProperty(true);

    private ObservableList<Rule> ruleData = FXCollections.observableArrayList();
    private ObservableList<Trigger> triggerData = FXCollections.observableArrayList();
    private ObservableList<Action> actionData = FXCollections.observableArrayList();
    @FXML
    private AnchorPane audioTextValSel;
    @FXML
    private Button selectAudioFileBtn;
    @FXML
    private Label audioFileSelectedLabel;
    @FXML
    private AnchorPane messageBoxValSel;
    @FXML
    private TextField messageBoxVal;
    @FXML
    private Button startSchedulerBtn;
    @FXML
    private Button stopSchedulerBtn;
    @FXML
    private TextField intervalSchedulerSel;
    @FXML
    private Button deleteSelectedRuleBtn;
    @FXML
    private Button deleteSelectedTriggerBtn;
    @FXML
    private Button deleteSelectedActionBtn;
    @FXML
    private TableColumn ruleTableMultiUse;
    @FXML
    private TableColumn ruleTableSleepTime;
    @FXML
    private TableColumn ruleTableState;
    @FXML
    private CheckBox multiUseCheckBox;
    @FXML
    private TextField sleepTimeSelBox;
    @FXML
    private CheckBox toggleActiveRuleCheckBox;

    //get rule controller
    public RuleController getRuleController() {
        return ruleController;
    }


    //init methods
    @FXML
    public void initialize() {
        Data data = Data.loadDatas();
        this.ruleController = data.getRuleController();

        this.audioFile = null;

        intervalSchedulerSel.setText("10");
        scheduler = new Scheduler(Integer.parseInt(intervalSchedulerSel.getText()), ruleController, this);
        stopSchedulerBtn.setDisable(true);
        //init the pages
        disablePages();
        initRulePage();
        initTriggerPage();
        initActionPage();

    }

    private void disablePages() {
        //disable rule page
        rulePage.setDisable(true);
        rulePage.setVisible(false);

        //disable trigger page
        triggerPage.setDisable(true);
        triggerPage.setVisible(false);

        //disable action page
        actionPage.setDisable(true);
        actionPage.setVisible(false);

    }

    //main page methods

    @FXML
    public void showRulesPage(ActionEvent actionEvent) {
        disablePages();
        rulePage.setDisable(false);
        rulePage.setVisible(true);
        editRulesBtn.setDisable(true);
        editTriggersBtn.setDisable(false);
        editActionsBtn.setDisable(false);

    }

    @FXML
    public void showTriggersPage(ActionEvent actionEvent) {
        disablePages();
        triggerPage.setDisable(false);
        triggerPage.setVisible(true);
        editRulesBtn.setDisable(false);
        editTriggersBtn.setDisable(true);
        editActionsBtn.setDisable(false);
    }

    @FXML
    public void showActionsPage(ActionEvent actionEvent) {
        disablePages();
        actionPage.setDisable(false);
        actionPage.setVisible(true);
        editRulesBtn.setDisable(false);
        editTriggersBtn.setDisable(false);
        editActionsBtn.setDisable(true);

    }

    @FXML
    public void createRule(ActionEvent actionEvent) {
        String name = ruleName.getText();
        Trigger trigger = triggerSelect.getValue();
        Action action = actionSelect.getValue();
        Boolean multiUse = multiUseCheckBox.isSelected();
        //get the sleep time from the text field and if its not a number call the error alert
        int sleepTime;
        //if the multi use checkbox is not selected the sleep time is null
        if (multiUse) {
            try {
                sleepTime = Integer.parseInt(sleepTimeSelBox.getText());
            } catch (NumberFormatException e) {
                showErrorAlert("Error", "Sleep time must be a number!");
                return;
            }
        } else {
            sleepTime = 0;
        }
        //create the rule


        Rule newRule = new Rule(name, trigger, action, true, multiUse, sleepTime);
        ruleController.addRule(newRule);
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
        //refresh the table after adding a new trigger
        triggerData.setAll(ruleController.getTriggers());
        //refresh the trigger list in rule page
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));


    }

    @FXML
    public void createAction(ActionEvent actionEvent) {
        String name = actionName.getText();
        String type = actionTypeSelect.getValue();
        Action newAction = createNewAction(name, type);

        ruleController.addAction(newAction);
        //refresh the table after adding a new action
        actionData.setAll(ruleController.getActions());
        //refresh the action list in rule page
        actionSelect.setItems(FXCollections.observableArrayList(ruleController.getActions()));
    }

    private Action createNewAction(String name, String type) {
        switch (type) {
            case AudioAction.type -> {
                AudioAction audioAction = new AudioAction(name, audioFile);
                //audioAction.execute();
                return audioAction;
            }
            case MessageBoxAction.type -> {
                MessageBoxAction messageBoxAction = new MessageBoxAction(name, messageBoxVal.getText());
                //messageBoxAction.execute();
                return messageBoxAction;
            }
        }
        return null;
    }


    @FXML
    public void changeTriggerValueBox(ActionEvent event) {
        updateTriggerValueBox();
    }



    //rule page methods
    private void initRulePage(){

        // Set up the rule table columns
        ruleTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleTableTrigger.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        ruleTableAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        ruleTableMultiUse.setCellValueFactory(new PropertyValueFactory<>("multiUse"));
        ruleTableSleepTime.setCellValueFactory(new PropertyValueFactory<>("sleepTime"));
        ruleTableState.setCellValueFactory(new PropertyValueFactory<>("state"));


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

        //bind the delete button disabled property based on whether a rule is selected
        deleteSelectedRuleBtn.disableProperty().bind(Bindings.isNull(ruleTable.getSelectionModel().selectedItemProperty()));

        //bind the sleep time text field disabled property based on whether the multi use checkbox is selected
        sleepTimeSelBox.disableProperty().bind(multiUseCheckBox.selectedProperty().not());

        //bind the toggle active rule checkbox disabled property based on whether a rule is selected
        toggleActiveRuleCheckBox.disableProperty().bind(Bindings.isNull(ruleTable.getSelectionModel().selectedItemProperty()));


        //set the value of the active rule checkbox based on the state of the selected rule
        toggleActiveRuleCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Rule selectedRule = ruleTable.getSelectionModel().getSelectedItem();
            selectedRule.setState(newValue);
            //refresh the table after changing the state of a rule
            ruleData.setAll(ruleController.getRules());
        });

        //set the active rule checkbox to the state of the selected rule
        ruleTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                toggleActiveRuleCheckBox.setSelected(newValue.getState());
            }
        });



        // Set items for the ChoiceBoxes
        actionSelect.setItems(FXCollections.observableArrayList(ruleController.getActions()));
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));

    }


    //trigger page init methods
    private void initTriggerPage(){
        // Set up the trigger table columns
        triggerTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        triggerTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        triggerTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Connect the triggerData to the table
        triggerTable.setItems(triggerData);

        // Load initial data into the table (if any)
        triggerData.addAll(ruleController.getTriggers());

        //set the sleep time text field to 60 by default
        sleepTimeSelBox.setText("60");

        // Disable "Create" button by default
        triggerCreateBtn.disableProperty().bind(createTriggerButtonDisabled);

        // Bind the createButtonDisabled property based on whether all three values are inserted
        createTriggerButtonDisabled.bind(
                Bindings.isEmpty(triggerName.textProperty())
                        .or(Bindings.isNull(triggerTypeSelect.valueProperty()))
        );

        //bind the delete button disabled property based on whether a trigger is selected
        deleteSelectedTriggerBtn.disableProperty().bind(Bindings.isNull(triggerTable.getSelectionModel().selectedItemProperty()));

        // Populate the ChoiceBox with all the known types
        triggerTypeSelect.setItems(FXCollections.observableArrayList(TimeOfDayTrigger.type));

        initTriggerTypes();

    }

    private void initTriggerTypes(){

        //Time Of Day init
        initTriggerTypeTOD();
        disableTriggerValueBox();
    }

    private void disableTriggerValueBox() {
        //disable all the trigger value boxes

        //Time Of Day
        timeOfDayTriggerValSel.setDisable(true);
        timeOfDayTriggerValSel.setVisible(false);
        //new trigger types here

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

    private void updateTriggerValueBox() {
        disableTriggerValueBox();
        String selectedType = triggerTypeSelect.getValue();
        switch (selectedType) {
            case TimeOfDayTrigger.type -> {
                timeOfDayTriggerValSel.setDisable(false);
                timeOfDayTriggerValSel.setVisible(true);
            }
        }

    }

    private Trigger createNewTrigger(String name, String type) {

        switch (triggerTypeSelect.getValue()) {
            case TimeOfDayTrigger.type -> {
                int hours = timeTriggerHours.getValue();
                int minutes = timeTriggerMinutes.getValue();
                return  new TimeOfDayTrigger(name,hours,minutes);
            }
        }
        return null;
    }


    //action page init methods
    private void initActionPage() {
        // Set up the action table columns
        actionTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        actionTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        actionTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Connect the actionData to the table
        actionTable.setItems(actionData);

        // Load initial data into the table (if any)
        actionData.addAll(ruleController.getActions());

        // Disable "Create" button by default
        actionCreateBtn.disableProperty().bind(createActionButtonDisabled);


        // Bind the createButtonDisabled property based on whether all three values are inserted
        createActionButtonDisabled.bind(
                Bindings.isEmpty(actionName.textProperty())
                        .or(Bindings.isNull(actionTypeSelect.valueProperty()))
        );

        //bind the delete button disabled property based on whether an action is selected
        deleteSelectedActionBtn.disableProperty().bind(Bindings.isNull(actionTable.getSelectionModel().selectedItemProperty()));


        // Populate the ChoiceBox with all the known types
        actionTypeSelect.setItems(FXCollections.observableArrayList(AudioAction.type, MessageBoxAction.type));

        initActionTypes();
    }

    private void initActionTypes() {
        //Audio Text init
        initActionTypeAudioText();
        initActionTypeMessageBox();

        disbleActionValueBox();

    }

    private void initActionTypeMessageBox() {
        // Set the default value for the message
        messageBoxVal.setText("Rule triggered!");
    }

    private void initActionTypeAudioText() {
        // Set the default value for the audio file path
        //get the default path name of the project and add the default audio file name
        String defaultPath = System.getProperty("user.dir")+ "\\programmaIFTTT\\Default resources";
        String defaultAudioFileName = "alarm.mp3";
        String defaultAudioFilePath = defaultPath + "\\" + defaultAudioFileName;
        System.out.println(defaultAudioFilePath);
        audioFileSelectedLabel.setText(defaultAudioFileName);
        audioFile = new File(defaultAudioFilePath);


    }

    @FXML
    public void changeActionValueBox(ActionEvent actionEvent) {
        updateActionValueBox();
    }

    private void updateActionValueBox() {
        //disable all the action value boxes
        disbleActionValueBox();
        String selectedType = actionTypeSelect.getValue();
        switch (selectedType) {
            case AudioAction.type -> {
                audioTextValSel.setDisable(false);
                audioTextValSel.setVisible(true);
            }
            case "Message Box" -> {
                messageBoxValSel.setDisable(false);
                messageBoxValSel.setVisible(true);
            }
        }
    }

    private void disbleActionValueBox() {
        //disable all the action value boxes

        //Audio Text
        audioTextValSel.setDisable(true);
        audioTextValSel.setVisible(false);
        //Message Box
        messageBoxValSel.setDisable(true);
        messageBoxValSel.setVisible(false);
        //new action types here


    }

    @FXML
    public void selectAudioFile(ActionEvent actionEvent) {
        //open a file chooser to select an audio file and set the label to the selected file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            audioFileSelectedLabel.setText(selectedFile.getName());
            audioFile = selectedFile;
        }



    }


    @FXML
    public void startScheduler(ActionEvent actionEvent) {
        List<Rule> rules = ruleController.getRules();

        if (rules.isEmpty()) {
            showErrorAlert("Error", "No rules to schedule!");
            return;
        }

        try {
            int interval = Integer.parseInt(intervalSchedulerSel.getText());
            scheduler = new Scheduler(interval, ruleController, this);
            scheduler.start();
            stopSchedulerBtn.setDisable(false);
            startSchedulerBtn.setDisable(true);
        } catch (NumberFormatException e) {
            showErrorAlert("Error", "Interval must be a number!");
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Wait for the user to click OK
        alert.showAndWait();
    }

    @FXML
    public void stopScheduler(ActionEvent actionEvent) {
        scheduler.stop();
        stopSchedulerBtn.setDisable(true);
        startSchedulerBtn.setDisable(false);
    }

    public void updateList() {
        Platform.runLater(() -> {
            ruleData.setAll(ruleController.getRules());
            triggerData.setAll(ruleController.getTriggers());
            actionData.setAll(ruleController.getActions());
        });
    }

    @FXML
    public void deleteSelectedRule(ActionEvent actionEvent) {
        //get the selected rule and delete it
        Rule selectedRule = ruleTable.getSelectionModel().getSelectedItem();
        ruleController.deleteRule(selectedRule.getName());
        //refresh the table after deleting a rule
        ruleData.setAll(ruleController.getRules());
    }

    @FXML
    public void deleteSelectedTrigger(ActionEvent actionEvent) {
        //get the selected trigger and delete it if it is not used in any rule
        Trigger selectedTrigger = triggerTable.getSelectionModel().getSelectedItem();
        if (ruleController.isTriggerUsed(selectedTrigger)) {
            showErrorAlert("Error", "Trigger is used in a rule!");
        } else {
            ruleController.deleteTrigger(selectedTrigger.getName());
            //refresh the table after deleting a trigger
            triggerData.setAll(ruleController.getTriggers());
        }
    }

    @FXML
    public void deleteSelectedAction(ActionEvent actionEvent) {
//get the selected action and delete it if it is not used in any rule
        Action selectedAction = actionTable.getSelectionModel().getSelectedItem();
        if (ruleController.isActionUsed(selectedAction)) {
            showErrorAlert("Error", "Action is used in a rule!");
        } else {
            ruleController.deleteAction(selectedAction.getName());
            //refresh the table after deleting an action
            actionData.setAll(ruleController.getActions());
        }
    }
}
