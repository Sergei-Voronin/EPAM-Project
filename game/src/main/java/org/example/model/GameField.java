package org.example.model;

import org.example.configuration.Config;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**The class contains the model of the playing field. */
public class GameField {
    /**Create a variable with application settings*/
    private static Config config = Config.getConfig();
    /**Matrix of rectangles*/
    private static Cell[][] cells = new Cell[config.getFieldHeight()][config.getFieldWidth()];
    /**Object groups a matrix of rectangles into a playing field*/
    private static Group field;

    /** Return matrix of rectangles.
     Implemented pattern "Singleton".
     The object exists in one instance */
    public static Cell[][] getCells() {
        if(cells == null){
            new GameField();
        }
        return cells;
    }

    /** This method return a grope of rectangles for JavaFX */
    public static Group getField() {
        if(field == null){
            new GameField();
        }
        return field;
    }

    /** The constructor calls a method that creates a matrix of rectangles */
    private GameField(){
        createCells();
    }

    /** This method creates a matrix of rectangles and a grope of rectangles for JavaFX
     @return the playing field filled with rectangles
     */
    public Group createCells(){
        field = new Group();
        for (int x = 0; x < cells.length; x++){
            for (int y = 0; y < cells[x].length; y++){
                Cell cell = new Cell();
                cell.setX(x * config.getCellSize());
                cell.setY(y * config.getCellSize());
                cell.setHeight(config.getCellSize());
                cell.setWidth(config.getCellSize());
                cell.setFill(Color.GRAY);
                cell.setStroke(Color.BLACK);
                cells[x][y] = cell;
                field.getChildren().add(cells[x][y]);
                cells[x][y].setOnMouseClicked(buildMouseEvent());
            }
        }
        return field;
    }
    /** Mouse listener */
    private EventHandler<MouseEvent> buildMouseEvent() {
        return event -> {
            Cell cell = (Cell) event.getTarget();
            if (event.getButton() == MouseButton.PRIMARY) {
                if (cell.getFill() == Color.BLACK){
                    cell.setFill(Color.GRAY);
                    cell.setLifeStatus((byte) 0);
                }

                else{
                    cell.setFill(Color.BLACK);
                    cell.setLifeStatus((byte) 1);
                }
            }
        };
    }
}
