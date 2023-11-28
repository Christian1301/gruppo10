package com.example.programmaifttt;

import com.example.programmaifttt.BackEnd.RuleUpdateListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IFTTTApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Data data = Data.loadDatas();

        // Crea un'istanza di RuleUpdateListener
        RuleUpdateListener ruleUpdateListener = new IFTTTController();

        FXMLLoader fxmlLoader = new FXMLLoader(IFTTTApplication.class.getResource("IFTTTGui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("IFTTT gruppo 10");
        stage.setScene(scene);
        stage.show();

        // Ottieni il controller
        IFTTTController iftttController = fxmlLoader.getController();

        // Passa l'istanza dell'interfaccia al controller
        iftttController.setRuleUpdateListener(ruleUpdateListener);

        // Aggiungi le regole caricate al RuleController
        if (data != null) {
            iftttController.updateRules(data.getRules());
            iftttController.updateList();
        }
        stage.setOnCloseRequest((event) -> {
            Data dataToSave = new Data(iftttController.getRules());
            dataToSave.saveDatas("data.txt");
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

