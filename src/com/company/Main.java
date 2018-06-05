package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //https://stackoverflow.com/questions/13209538/looking-for-good-algorithm-for-equal-distribution
    //quasi random sequence -> matlab library for java
    //Dla rozmieszczenia równomiernego

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("fxml/menu.fxml"));
        Pane root = fxmlloader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("( ͡° ͜ʖ ͡°)");
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/Game_of_life.png")));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(2);
        });
    }
}
