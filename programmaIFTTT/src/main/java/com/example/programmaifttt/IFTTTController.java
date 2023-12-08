package com.example.programmaifttt;

import com.example.programmaifttt.Actions.*;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.BackEnd.Scheduler;
import com.example.programmaifttt.Enums.DayOfWeekEnum;
import com.example.programmaifttt.Triggers.*;
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

    private File textFile;
    private File deleteFile;
    private File externalProgram;
    private File moveFile;
    private File moveFileDirectory;
    private File pasteFile;
    private File pasteFileDirectory;

    private File externalProgramTrig;
    private File fileExistTrig;
    private File fileSizeTrig;

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
    @FXML
    private AnchorPane appendStringToFileValSel;
    @FXML
    private TextField messageToAppend;
    @FXML
    private Button selectTextFileBtn;
    @FXML
    private Label textFileSelectedLabel;
    @FXML
    private AnchorPane deleteFileValSel;
    @FXML
    private Button selectDeleteFileBtn;
    @FXML
    private Label deleteFileSelectedLabel;
    @FXML
    private AnchorPane externalProgramValSel;
    @FXML
    private TextField commandLineArgumentsVal;
    @FXML
    private Button selectExternalProgramBtn;
    @FXML
    private Label externalProgramSelected;
    @FXML
    private AnchorPane moveFileValSel;
    @FXML
    private Button selectMoveFileBtn;
    @FXML
    private Button selectMoveFileDirectoryBtn;
    @FXML
    private Label selectedMoveFile;
    @FXML
    private Label selectedMoveFileDirectory;
    @FXML
    private Button selectPasteFileBtn;
    @FXML
    private Button selectPasteFileDirectoryBtn;
    @FXML
    private Label selectedPasteFile;
    @FXML
    private Label selectedPasteFileDirectory;
    @FXML
    private AnchorPane pasteFileValSel;
    @FXML
    private AnchorPane dayOfWeekTriggerValSel;
    @FXML
    private ChoiceBox dayTriggerSelected;
    @FXML
    private AnchorPane dayOfMonthTriggerValSel;
    @FXML
    private DatePicker dayOfMonthSelected;
    @FXML
    private AnchorPane externalProgramTriggerValSel;
    @FXML
    private TextField commandLineArgumentsTriggerSel;
    @FXML
    private Button selectExternalProgramTriggerBtn;
    @FXML
    private TextField externalProgramTriggerExistStatusSel;
    @FXML
    private Label externalProgramSelected1;
    @FXML
    private AnchorPane fileExistenceTriggerValSel;
    @FXML
    private Button selectFileExistTriggerBtn;
    @FXML
    private Label fileExistTriggerLabel;
    @FXML
    private AnchorPane fileSizeTriggerValSel;
    @FXML
    private Button selectFileSizeTriggerBtn;
    @FXML
    private TextField fileSizeTriggerSizeSel;
    @FXML
    private Label fileSizeTriggerLabel;

    //get rule controller
    public RuleController getRuleController() {
        return ruleController;
    }


    /*

    Methods for the main page

     */

    @FXML
    public void initialize() {
        Data data = Data.loadDatas();
        this.ruleController = data.getRuleController();

        this.audioFile = null;
        this.textFile = null;
        this.deleteFile = null;
        this.externalProgram = null;
        this.moveFile = null;
        this.moveFileDirectory = null;
        this.pasteFile = null;
        this.pasteFileDirectory = null;
        this.externalProgramTrig = null;
        this.fileExistTrig = null;
        this.fileSizeTrig = null;



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

    @FXML
    public void stopScheduler(ActionEvent actionEvent) {
        scheduler.stop();
        stopSchedulerBtn.setDisable(true);
        startSchedulerBtn.setDisable(false);
    }


    /*
     *
     *  Methods for the rule page
     *
     */

    private void initRulePage(){

        // Set up the rule table columns
        ruleTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleTableTrigger.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        ruleTableAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        ruleTableMultiUse.setCellValueFactory(new PropertyValueFactory<>("multiUse"));
        ruleTableSleepTime.setCellValueFactory(new PropertyValueFactory<>("sleepTime"));
        ruleTableState.setCellValueFactory(new PropertyValueFactory<>("state"));


        //set the sleep time text field to 60 by default
        sleepTimeSelBox.setText("60");


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
    public void deleteSelectedRule(ActionEvent actionEvent) {
        //get the selected rule and delete it
        Rule selectedRule = ruleTable.getSelectionModel().getSelectedItem();
        ruleController.deleteRule(selectedRule.getName());
        //refresh the table after deleting a rule
        ruleData.setAll(ruleController.getRules());
    }


    /*
     *
     *  Methods for the trigger page
     *
     */

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

        //bind the delete button disabled property based on whether a trigger is selected
        deleteSelectedTriggerBtn.disableProperty().bind(Bindings.isNull(triggerTable.getSelectionModel().selectedItemProperty()));

        // Populate the ChoiceBox with all the known types
        triggerTypeSelect.setItems(FXCollections.observableArrayList(TimeOfDayTrigger.type, DayOfWeekTrigger.type, DayOfMonthTrigger.type, ExternalProgramTrigger.type, FileExistenceTrigger.type, FileSizeTrigger.type));

        initTriggerTypes();

    }
    @FXML
    public void createTrigger(ActionEvent actionEvent) {
        String name = triggerName.getText();
        String type = triggerTypeSelect.getValue();
        //check if value is selected based on type
        if (checkValueSelectedTrigger(type)) {
            showErrorAlert("Error", "Value not selected!");
            return;
        }

        //create the trigger based on type
        Trigger newTrigger = createNewTrigger(name, type);

        ruleController.addTrigger(newTrigger);
        //refresh the table after adding a new trigger
        triggerData.setAll(ruleController.getTriggers());
        //refresh the trigger list in rule page
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));


    }

    private boolean checkValueSelectedTrigger(String type) {
        switch (type) {
            case TimeOfDayTrigger.type -> {
                return false;
            }
            case DayOfWeekTrigger.type -> {
                return dayTriggerSelected.getValue() == null;
            }
            case DayOfMonthTrigger.type -> {
                return dayOfMonthSelected.getValue() == null;
            }
            case ExternalProgramTrigger.type -> {
                return externalProgramTrig == null || commandLineArgumentsTriggerSel.getText().isEmpty() || externalProgramTriggerExistStatusSel.getText().isEmpty();
            }
            case FileExistenceTrigger.type -> {
                return fileExistTrig == null;
            }
            case FileSizeTrigger.type -> {
                return fileSizeTrig == null || fileSizeTriggerSizeSel.getText().isEmpty();
            }
        }
        return false;
    }

    @FXML
    public void changeTriggerValueBox(ActionEvent event) {
        updateTriggerValueBox();
    }


    //trigger type init methods
    private void initTriggerTypes(){

        //Time Of Day init
        initTriggerTypeTOD();
        initTriggerTypeTOW();
        initTriggerTypeTOM();
        initTriggerTypeEP();
        initTriggerTypeFE();
        initTriggerTypeFS();
        disableTriggerValueBox();
    }

    private void initTriggerTypeTOW() {
        // Populate the ChoiceBox with all the days of the week from DayOfWeekEnum
        dayTriggerSelected.setItems(FXCollections.observableArrayList(DayOfWeekEnum.values()));

    }

    private void initTriggerTypeTOM() {
        //set the default date to today
        dayOfMonthSelected.setValue(dayOfMonthSelected.getValue());


    }

    private void initTriggerTypeEP() {
        //set the label to no file selected
        externalProgramSelected1.setText("No file selected");
    }

    private void initTriggerTypeFE() {
        //set the label to no file selected
        fileExistTriggerLabel.setText("No file selected");
    }

    private void initTriggerTypeFS() {
        //set the label to no file selected
        fileSizeTriggerLabel.setText("No file selected");
    }

    private void disableTriggerValueBox() {
        //disable all the trigger value boxes

        //Time Of Day
        timeOfDayTriggerValSel.setDisable(true);
        timeOfDayTriggerValSel.setVisible(false);
        //Day Of Week
        dayOfWeekTriggerValSel.setDisable(true);
        dayOfWeekTriggerValSel.setVisible(false);
        //Day Of Month
        dayOfMonthTriggerValSel.setDisable(true);
        dayOfMonthTriggerValSel.setVisible(false);
        //External Program
        externalProgramTriggerValSel.setDisable(true);
        externalProgramTriggerValSel.setVisible(false);
        //File Existence
        fileExistenceTriggerValSel.setDisable(true);
        fileExistenceTriggerValSel.setVisible(false);
        //File Size
        fileSizeTriggerValSel.setDisable(true);
        fileSizeTriggerValSel.setVisible(false);


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
            case DayOfWeekTrigger.type -> {
                dayOfWeekTriggerValSel.setDisable(false);
                dayOfWeekTriggerValSel.setVisible(true);
            }
            case DayOfMonthTrigger.type -> {
                dayOfMonthTriggerValSel.setDisable(false);
                dayOfMonthTriggerValSel.setVisible(true);
            }
            case ExternalProgramTrigger.type -> {
                externalProgramTriggerValSel.setDisable(false);
                externalProgramTriggerValSel.setVisible(true);
            }
            case FileExistenceTrigger.type -> {
                fileExistenceTriggerValSel.setDisable(false);
                fileExistenceTriggerValSel.setVisible(true);
            }
            case FileSizeTrigger.type -> {
                fileSizeTriggerValSel.setDisable(false);
                fileSizeTriggerValSel.setVisible(true);
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
            case DayOfWeekTrigger.type -> {
                DayOfWeekEnum day = (DayOfWeekEnum) dayTriggerSelected.getValue();
                return new DayOfWeekTrigger(name,day);
            }
            case DayOfMonthTrigger.type -> {
                int day = dayOfMonthSelected.getValue().getDayOfMonth();
                return new DayOfMonthTrigger(name,day);
            }
            case ExternalProgramTrigger.type -> {
                ExternalProgramTrigger externalProgramTrigger = new ExternalProgramTrigger(name, externalProgramTrig, commandLineArgumentsTriggerSel.getText(), Integer.parseInt( externalProgramTriggerExistStatusSel.getText()));

                return externalProgramTrigger;
            }
            case FileExistenceTrigger.type -> {
                FileExistenceTrigger fileExistenceTrigger = new FileExistenceTrigger(name, fileExistTrig);

                return fileExistenceTrigger;
            }
            case FileSizeTrigger.type -> {
                int size = Integer.parseInt(fileSizeTriggerSizeSel.getText());
                FileSizeTrigger fileSizeTrigger = new FileSizeTrigger(name, fileSizeTrig, size);
                //fileSizeTrigger.execute();
                return fileSizeTrigger;
            }
        }
        return null;
    }

    @FXML
    public void selectExternalProgramTrigger(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            externalProgramTrig = selectedFile;
            externalProgramSelected1.setText(selectedFile.getName());
        }

    }

    @FXML
    public void selectFileExistTrigger(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            fileExistTrig = selectedFile;
            fileExistTriggerLabel.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectFileSizeTrigger(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            fileSizeTrig = selectedFile;
            fileSizeTriggerLabel.setText(selectedFile.getName());

        }
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


    /*
     *
     *  Methods for the action page
     *
     */

    @FXML
    public void createAction(ActionEvent actionEvent) {
        String name = actionName.getText();
        String type = actionTypeSelect.getValue();
        //check if value is selected based on type
        if (checkValueSelected(type)) {
            showErrorAlert("Error", "Value not selected!");
            return;
        }

        Action newAction = createNewAction(name, type);

        ruleController.addAction(newAction);
        //refresh the table after adding a new action
        actionData.setAll(ruleController.getActions());
        //refresh the action list in rule page
        actionSelect.setItems(FXCollections.observableArrayList(ruleController.getActions()));
    }

    private boolean checkValueSelected(String type) {
        switch (type) {
            case AudioAction.type -> {
                return audioFile == null;
            }
            case MessageBoxAction.type -> {
                return messageBoxVal.getText().isEmpty();
            }
            case AppendStringToFileAction.type -> {
                return textFile == null || messageToAppend.getText().isEmpty();
            }
            case DeleteFileAction.type -> {
                return deleteFile == null;
            }
            case ExternalProgramAction.type -> {
                return externalProgram == null || commandLineArgumentsVal.getText().isEmpty();
            }
            case MoveFileAction.type -> {
                return moveFile == null || moveFileDirectory == null;
            }
            case PasteFileAction.type -> {
                return pasteFile == null || pasteFileDirectory == null;
            }
        }
        return false;

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
            case AppendStringToFileAction.type -> {
                AppendStringToFileAction appendStringToFileAction = new AppendStringToFileAction(name, messageToAppend.getText(), textFile );
                //appendStringToFileAction.execute();
                return appendStringToFileAction;
            }
            case DeleteFileAction.type -> {
                DeleteFileAction deleteFileAction = new DeleteFileAction(name, deleteFile);
                //deleteFileAction.execute();
                return deleteFileAction;
            }
            case ExternalProgramAction.type -> {
                ExternalProgramAction externalProgramAction = new ExternalProgramAction(name, externalProgram, commandLineArgumentsVal.getText());
                //externalProgramAction.execute();
                return externalProgramAction;
            }
            case MoveFileAction.type -> {
                MoveFileAction moveFileAction = new MoveFileAction(name, moveFile, moveFileDirectory);
                //moveFileAction.execute();
                return moveFileAction;
            }
            case PasteFileAction.type -> {
                PasteFileAction pasteFileAction = new PasteFileAction(name, pasteFile, pasteFileDirectory);
                //pasteFileAction.execute();
                return pasteFileAction;
            }
        }
        return null;
    }

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
        actionTypeSelect.setItems(FXCollections.observableArrayList(AudioAction.type, MessageBoxAction.type, AppendStringToFileAction.type, DeleteFileAction.type, ExternalProgramAction.type, MoveFileAction.type, PasteFileAction.type));

        initActionTypes();
    }

    private void initActionTypes() {
        //init all action types
        initActionTypeAudioText();
        initActionTypeMessageBox();
        initActionTypeAppendStringToFile();
        initActionTypeDeleteFile();
        initActionTypeExternalProgram();
        initActionTypeMoveFile();
        initActionTypePasteFile();

        disbleActionValueBox();

    }

    //init all action types
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

    private void initActionTypeAppendStringToFile() {
        // Set the default value for the message
        messageToAppend.setText("the rule was triggered!");
        //set the label to no file selected
        textFileSelectedLabel.setText("No file selected");
    }

    private void initActionTypeDeleteFile() {
        // Set the default value for the message
        deleteFileSelectedLabel.setText("No file selected");
    }

    private void initActionTypeExternalProgram() {
        // Set the default value for the message
        externalProgramSelected.setText("No file selected");
    }

    private void initActionTypeMoveFile() {
        // Set the default value for the message
        selectedMoveFile.setText("no file selected");
        selectedMoveFileDirectory.setText("no directory selected");
    }

    private void initActionTypePasteFile() {
        // Set the default value for the message
        selectedPasteFile.setText("no file selected");
        selectedPasteFileDirectory.setText("no directory selected");
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
            case MessageBoxAction.type -> {
                messageBoxValSel.setDisable(false);
                messageBoxValSel.setVisible(true);
            }
            case AppendStringToFileAction.type -> {
                appendStringToFileValSel.setDisable(false);
                appendStringToFileValSel.setVisible(true);
            }
            case DeleteFileAction.type -> {
                deleteFileValSel.setDisable(false);
                deleteFileValSel.setVisible(true);
            }
            case ExternalProgramAction.type -> {
                externalProgramValSel.setDisable(false);
                externalProgramValSel.setVisible(true);
            }
            case MoveFileAction.type -> {
                moveFileValSel.setDisable(false);
                moveFileValSel.setVisible(true);
            }
            case PasteFileAction.type -> {
                pasteFileValSel.setDisable(false);
                pasteFileValSel.setVisible(true);
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
        //Append String To File
        appendStringToFileValSel.setDisable(true);
        appendStringToFileValSel.setVisible(false);
        //Delete File
        deleteFileValSel.setDisable(true);
        deleteFileValSel.setVisible(false);
        //External Program
        externalProgramValSel.setDisable(true);
        externalProgramValSel.setVisible(false);
        //Move File
        moveFileValSel.setDisable(true);
        moveFileValSel.setVisible(false);
        //Paste File
        pasteFileValSel.setDisable(true);
        pasteFileValSel.setVisible(false);


    }


    @FXML
    public void selectAudioFile(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            audioFile = selectedFile;
            audioFileSelectedLabel.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectTextFile(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            textFile = selectedFile;
            textFileSelectedLabel.setText(selectedFile.getName());
        }

    }

    @FXML
    public void selectDeleteFile(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            deleteFile = selectedFile;
            deleteFileSelectedLabel.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectExternalProgram(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            externalProgram = selectedFile;
            externalProgramSelected.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectMoveFile(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            moveFile = selectedFile;
            selectedMoveFile.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectMoveFileDirectory(ActionEvent actionEvent) {
        File selectedDirectory = selectDirectory();
        if (selectedDirectory != null) {
            moveFileDirectory = selectedDirectory;
            selectedMoveFileDirectory.setText(selectedDirectory.getName());
        }
    }

    @FXML
    public void selectPasteFile(ActionEvent actionEvent) {
        File selectedFile = selectFile();
        if (selectedFile != null) {
            pasteFile = selectedFile;
            selectedPasteFile.setText(selectedFile.getName());
        }
    }

    @FXML
    public void selectPasteFileDirectory(ActionEvent actionEvent) {
        File selectedDirectory = selectDirectory();
        if (selectedDirectory != null) {
            pasteFileDirectory = selectedDirectory;
            selectedPasteFileDirectory.setText(selectedDirectory.getName());
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


    /*
     *
     *  Other methods
     *
     */

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Wait for the user to click OK
        alert.showAndWait();
    }

    public void updateList() {
        Platform.runLater(() -> {
            ruleData.setAll(ruleController.getRules());
            triggerData.setAll(ruleController.getTriggers());
            actionData.setAll(ruleController.getActions());
        });
    }

    private File selectFile() {
        //open a file chooser to select a file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private File selectDirectory() {
        //open a file chooser to select a directory
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}