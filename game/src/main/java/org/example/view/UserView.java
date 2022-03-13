package org.example.view;

import org.example.configuration.Config;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.logic.ControllerWorker;
import org.example.model.Cell;
import org.example.model.FieldMatrix;
import org.example.model.GameField;

/** The class is responsible for displaying the graphical interface of the game on JavaFX */

public class UserView {
    /** the object is responsible for the logic */
    private ControllerWorker controllerWorker;
    /** Matrix of rectangles */
    private Cell[][] cells = GameField.getCells();
    /** Matrix of bytes */
    private byte[][] originalMatrix = FieldMatrix.getByteMatrix();
    /** START button launches the game */
    private final Button START = new Button("Start");
    /** STOP button stops the game */
    private final Button STOP = new Button("Stop");
    /** CLEAR button clears the playing field*/
    private final Button CLEAR = new Button("Clear");
    /** Height of the program window */
    private final int SCREEN_HEIGHT = 1024;
    /** Weight of the program window */
    private final int SCREEN_WIDTH = 768;
    /**Config is stored in the "Config" class variable*/
    private Config config = Config.getConfig();

    public UserView() {
        this.controllerWorker = new ControllerWorker(cells, originalMatrix);
    }

    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setBottom(buttons());
        pane.setCenter(GameField.getField());

        Scene scene = new Scene(pane, SCREEN_HEIGHT, SCREEN_WIDTH);
        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();

        /** This code will shutdown the program if user has closed the window */
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    /** This method is responsible for the behavior of the buttons. */
    private HBox buttons() {
        HBox buttons = new HBox();

        buttons.setPrefHeight(50.0);
        buttons.setSpacing(30);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.getChildren().addAll(START, STOP, CLEAR);
        STOP.setDisable(true);

        START.setOnAction(event -> {
            if (controllerWorker.isEmptyMatrix(cells)) {
                controllerWorker.generateRandomMatrix(cells, originalMatrix, config.getNumberLiveCellsForGenerationField());
                controllerWorker.startThreads();
                STOP.setDisable(false);
                at.start();
            } else {
                controllerWorker.syncronizeMatrix(GameField.getCells(), FieldMatrix.getByteMatrix());
                controllerWorker.startThreads();
                STOP.setDisable(false);
                at.start();
            }
            START.setText("Running");
            START.setDisable(true);
            CLEAR.setDisable(true);

        });

        STOP.setOnAction(event -> {
            controllerWorker.stopThreads();
            START.setText("Start");
            START.setDisable(false);
            CLEAR.setDisable(false);
            STOP.setDisable(true);
        });
        CLEAR.setOnAction(event -> controllerWorker.clear(cells));
        return buttons;
    }

    /**AnimationTimer considers generational changes */
    private AnimationTimer at = new AnimationTimer() {

        @Override
        public void handle(long now) {
            if (!controllerWorker.checkSteps() || controllerWorker.isEmptyMatrix(cells)) {
                controllerWorker.stopThreads();
                START.setText("Start");
                START.setDisable(false);
                CLEAR.setDisable(false);
                STOP.setDisable(true);
            }
        }
    };
}
