package com.example.programmaifttt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class IFTTTApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Data data = Data.loadDatas();

        FXMLLoader fxmlLoader = new FXMLLoader(IFTTTApplication.class.getResource("IFTTTGui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("IFTTT gruppo 10");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((event) -> {
            data.saveDatas();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}