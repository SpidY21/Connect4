package com.yashkumarroy.connect4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane insertedDiscsPane;
    @FXML
    public Label PlayerOne;
    @FXML
    public Label Turn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}