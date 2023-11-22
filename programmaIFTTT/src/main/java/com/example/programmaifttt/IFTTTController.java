package com.example.programmaifttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class IFTTTController {

    @FXML
    private ChoiceBox<?> ActionSelect;

    @FXML
    private Button EditRulesBtn;

    @FXML
    private Button RuleCreateBtn;

    @FXML
    private AnchorPane RuleMenuPage;

    @FXML
    private TextField RuleName;

    @FXML
    private TableView<?> RuleTable;

    @FXML
    private TableColumn<?, ?> RuleTableAction;

    @FXML
    private TableColumn<?, ?> RuleTableName;

    @FXML
    private AnchorPane RuleTablePage;

    @FXML
    private TableColumn<?, ?> RuleTableTrigger;

    @FXML
    private ChoiceBox<?> TriggerSelect;

    @FXML
    void CreateRule(ActionEvent event) {

    }

    @FXML
    void ShowRulesPage(ActionEvent event) {

    }

}
