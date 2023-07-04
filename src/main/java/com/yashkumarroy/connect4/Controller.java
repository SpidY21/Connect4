package com.yashkumarroy.connect4;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

public class Controller implements Initializable {

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int CIRCLE_DIAMETER = 80;
    private static final String discColor1 = "#24303E";
    private static final String discColor2 = "#4CAA88";
    private static String PLAYER_ONE = "Player One";
    private static String PLAYER_TWO = "Player Two";

    private boolean isPlayerOneTurn = true;

    private Disc[][] insertedDiscsArray = new Disc[ROWS][COLUMNS];

    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane insertedDiscsPane;
    @FXML
    public Label playerNameLabel;
    @FXML
    public Label Turn;

    public void createPlayground() {

        rootGridPane.add(createGameStructuralGrid(), 0, 1);
        List<Rectangle> rectangleList = createClickableColumns();
        for (Rectangle rectangle : rectangleList) {
            rootGridPane.add(rectangle, 0, 1);
        }
    }

    private Shape createGameStructuralGrid() {
        Shape rectangleWIthHole = new Rectangle((COLUMNS + 1) * CIRCLE_DIAMETER, (ROWS + 1) * CIRCLE_DIAMETER);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle circle = new Circle();
                circle.setRadius(CIRCLE_DIAMETER / 2);
                circle.setCenterX(CIRCLE_DIAMETER / 2);
                circle.setCenterY(CIRCLE_DIAMETER / 2);

                circle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
                circle.setTranslateY(row * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);

                rectangleWIthHole = Shape.subtract(rectangleWIthHole, circle);
            }
        }


        rectangleWIthHole.setFill(Color.WHITE);
        return rectangleWIthHole;
    }

    private List<Rectangle> createClickableColumns() {
        List<Rectangle> rectangleList = new ArrayList<>();
        for (int col = 0; col < COLUMNS; col++) {
            Rectangle rectangle = new Rectangle(CIRCLE_DIAMETER, (ROWS + 1) * CIRCLE_DIAMETER);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setTranslateX(col * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
            rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.valueOf("#eeeeee76")));
            rectangle.setOnMouseExited(event -> rectangle.setFill(Color.TRANSPARENT));
            final int column = col;
            rectangle.setOnMouseClicked(event -> {
                insertDisc(new Disc(isPlayerOneTurn), column);
            });

            rectangleList.add(rectangle);
        }
        return rectangleList;
    }

    private void insertDisc(Disc disc, int column) {

        int row = ROWS - 1;
        while (row >= 0) {
            if (getDiscIfPresent(row,column) == null) {
                break;
            } else {
                row--;
            }
        }
        if (row < 0) return;

        insertedDiscsArray[row][column] = disc;
        insertedDiscsPane.getChildren().add(disc);
        disc.setTranslateX(column * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
        int currentRow = row;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), disc);
        translateTransition.setToY(row * (CIRCLE_DIAMETER + 5) + CIRCLE_DIAMETER / 4);
        translateTransition.setOnFinished(event -> {
            if (gameEnded(currentRow, column)) {
                gameOver();
            }
            isPlayerOneTurn = !isPlayerOneTurn;
            playerNameLabel.setText(isPlayerOneTurn ? PLAYER_ONE : PLAYER_TWO);
        });
        translateTransition.play();
    }

    private boolean gameEnded(int row, int column) {

        //vertical point
        List<Point2D> verticalPoints = IntStream.rangeClosed(row - 3, row + 3)
                .mapToObj(r -> new Point2D(r, column))
                .collect(Collectors.toList());

        //horizontal point
        List<Point2D> horizontalPoints = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(c -> new Point2D(row, c))
                .collect(Collectors.toList());
        boolean isEnded= checkCombinations(verticalPoints);

        return isEnded;
    }

    private boolean checkCombinations(List<Point2D>points){
        int chain=0;
        for(Point2D point:points){
            int rowIndexArray=(int)point.getX();
            int columnIndexArray=(int)point.getY();

            Disc disc=getDiscIfPresent(rowIndexArray,columnIndexArray);
            if(disc!=null && disc.isPlayerMove==isPlayerOneTurn){
                chain++;
                if(chain==4){
                    return true;
                }
            }
            else{
                chain=0;
            }
        }
        return false;
    }

    private Disc getDiscIfPresent(int r,int c){
        if(r>ROWS || r<0 || c>COLUMNS || c<0){
            return null;
        }
        return insertedDiscsArray[r][c];
    }
    private void gameOver() {
        String winner=isPlayerOneTurn? PLAYER_ONE:PLAYER_TWO;
        System.out.println("Winner is: "+winner);
    }

    private static class Disc extends Circle {
        private final boolean isPlayerMove;

        public Disc(boolean isPlayerMove) {
            this.isPlayerMove = isPlayerMove;
            setRadius(CIRCLE_DIAMETER / 2);
            setFill(isPlayerMove ? Color.valueOf(discColor1) : Color.valueOf(discColor2));
            setCenterX(CIRCLE_DIAMETER / 2);
            setCenterY(CIRCLE_DIAMETER / 2);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}