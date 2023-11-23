package com.example.programmaifttt;

import com.example.programmaifttt.Actions.Action;
import com.example.programmaifttt.Actions.ActionDummy;
import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;
import com.example.programmaifttt.Triggers.Trigger;
import com.example.programmaifttt.Triggers.TriggerDummy;
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
    private AnchorPane ruleMenuPage;

    @FXML
    private TextField ruleName;

    @FXML
    private TableView<Rule> ruleTable;

    @FXML
    private TableColumn<Rule, Action> ruleTableAction;

    @FXML
    private TableColumn<Rule, String> ruleTableName;

    @FXML
    private AnchorPane ruleTablePage;

    @FXML
    private TableColumn<Rule, Trigger> ruleTableTrigger;

    @FXML
    private ChoiceBox<Trigger> triggerSelect;

    private RuleController ruleController;

    private BooleanProperty createButtonDisabled = new SimpleBooleanProperty(true);

    private ObservableList<Rule> ruleData = FXCollections.observableArrayList();



    public void initialize() {
        this.ruleController = new RuleController();

        this.ruleController.addAction(new ActionDummy("action 1"));
        this.ruleController.addTrigger(new TriggerDummy("trigger 1"));
        this.ruleController.addAction(new ActionDummy("action 2"));
        this.ruleController.addTrigger(new TriggerDummy("trigger 2"));

        // Disable "Create" button by default
        ruleCreateBtn.disableProperty().bind(createButtonDisabled);

        // Bind the createButtonDisabled property based on whether all three values are inserted
        createButtonDisabled.bind(
                Bindings.isEmpty(ruleName.textProperty())
                        .or(Bindings.isNull(triggerSelect.valueProperty()))
                        .or(Bindings.isNull(actionSelect.valueProperty()))
        );

        // Set up the table columns
        ruleTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ruleTableTrigger.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        ruleTableAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Connect the ruleData to the table
        ruleTable.setItems(ruleData);

        // Load initial data into the table (if any)
        ruleData.addAll(ruleController.getRules());

        // Set items for the ChoiceBoxes
        actionSelect.setItems(FXCollections.observableArrayList(ruleController.getActions()));
        triggerSelect.setItems(FXCollections.observableArrayList(ruleController.getTriggers()));
    }


    @FXML
    public void showRulesPage(ActionEvent actionEvent) {
    }

    @FXML
    public void createRule(ActionEvent actionEvent) {
        String name = ruleName.getText();
        Trigger trigger = triggerSelect.getValue();
        Action action = actionSelect.getValue();

        if (name != null && !name.isEmpty() && trigger != null && action != null) {
            Rule newRule = new Rule(name, trigger, action);
            ruleController.addRule(newRule);
            // Refresh the table after adding a new rule
            ruleData.setAll(ruleController.getRules());

        }
    }
}
