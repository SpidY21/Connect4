package com.yashkumarroy.connect4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load();
        controller = loader.getController();
        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);
        Scene scene = new Scene(rootGridPane);
        stage.setScene(scene);
        stage.setTitle("Connect4");
        stage.setResizable(false);
        stage.show();
    }

    private MenuBar createMenu() {
        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(actionEvent -> resetGame());
        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(actionEvent -> resetGame());
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });


        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        // Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutConnect4 = new MenuItem("About Connect 4");
        aboutConnect4.setOnAction(ActionEvent -> aboutConnect4());
        MenuItem aboutMe = new MenuItem("About Developer");
        aboutMe.setOnAction(Actionevent -> aboutMe());
        helpMenu.getItems().addAll(aboutConnect4, separatorMenuItem, aboutMe);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void aboutMe() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the Developer");
        alert.setHeaderText("Yash Kumar Roy");
        alert.setContentText("I Have developed this game by the help of JavaFX");
        alert.show();
    }

    private void aboutConnect4() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("About Connect Four");
        dialog.setHeaderText("How to Play");
        dialog.setContentText("Connect Four is a two-player connection game in\nwhich the players first choose a color and then take\nturns dropping colored discs from the top into a\nseven-column, six-row vertically suspended grid.\nThe pieces fall straight down, occupying the next available\nspace within the column. The objective of the game is\nto be the first to form a horizontal, vertical, or diagonal\nline of four of one's own discs.\nConnect Four is a solved game.\nThe first player can always win by playing the right moves.");
        dialog.show();
    }

    private void resetGame() {
        //TODO
    }

    public static void main(String[] args) {
        launch();
    }
}