package com.yashkumarroy.connect4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Controller controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane= loader.load();
        controller=loader.getController();
        Scene scene= new Scene(rootGridPane);
        stage.setScene(scene);
        stage.setTitle("Connect4");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}