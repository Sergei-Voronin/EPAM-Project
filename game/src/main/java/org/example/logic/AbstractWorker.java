package org.example.logic;

import org.example.model.Cell;

import java.util.concurrent.CyclicBarrier;
/**
 This abstract class contains the logic for counting the neighbors of the cell.
 Counts the number of cells - neighbors in which there is "life".
 */
public abstract class AbstractWorker {
    public Cell[][] cell;/** Matrix of rectangles - playing field. */
    public byte[][] originalMatrix;/**Numerical representation of a matrix of rectangles. Used when counting neighbors.*/
    public CyclicBarrier barrier; /** The class object "CyclicBarrier" is needed to synchronize threads */

    /** Parameterized constructor.
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     @param barrier - flow control
     */
    public AbstractWorker(Cell[][] cell, byte[][] originalMatrix, CyclicBarrier barrier) {
        this.cell = cell;
        this.originalMatrix = originalMatrix;
        this.barrier = barrier;
    }
    /** Parameterless constructor */
    public AbstractWorker(){
    }

    /**
     The method checks where the cell is on the playing field.
     You need to know the position of the cell in order to use the appropriate calculation technique.
     If the position of the cell is incorrectly determined, then an error may occur during the counting.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     */
    public int countingNeighbors(Cell[][] cell, int x, int y) {
                if (isNearNoBorder(cell, x, y))
                    return countNearNoBorder(cell, x,y);
                else if (isNearLeftBorder(cell, x, y))
                    return countNearLeftBorder(cell, x, y);
                else if (isNearTopBorder(cell, x, y))
                    return countNearTopBorder(cell, x, y);
                else if (isNearRightBorder(cell, x, y))
                    return countNearRightBorder(cell, x, y);
                else if (isNearBottomBorder(cell, x, y))
                    return countNearBottomBorder(cell, x, y);
                else if (isLeftTopCorner(cell, x, y))
                    return countLeftTopCorner(cell, x, y);
                else if (isRightTopCorner(cell, x, y))
                    return countRightTopCorner(cell, x, y);
                else if (isRightBottomCorner(cell, x, y))
                    return countRightBottomCorner(cell, x, y);
                else if (isLeftBottomCorner(cell, x, y))
                    return countLeftBottomCorner(cell, x, y);
                else return 0;
        }

    /** The method checks if the cell is on the borders of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is not adjacent to the borders of the playing field
     */
    private boolean isNearNoBorder(Cell[][] cell, int x, int y) {
        return x > 0 && x < cell.length - 1 && y > 0 && y < cell.length - 1;
    }

    /** Counting method for a cell that is not at the border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
      */
    private int countNearNoBorder(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x - 1][y + 1].getLifeStatus() + cell[x][y + 1].getLifeStatus() +
                cell[x + 1][y + 1].getLifeStatus() + cell[x + 1][y].getLifeStatus() + cell[x + 1][y - 1].getLifeStatus() +
                cell[x][y - 1].getLifeStatus() + cell[x - 1][y - 1].getLifeStatus();
    }

    /** The method checks if the cell is at the left border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is near the left border of the playing field
     */
    private boolean isNearLeftBorder(Cell[][] cell, int x, int y) {
        return x == 0 && y > 0 && y < cell.length - 1;
    }

    /** Counting method for the cell that is at the left border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countNearLeftBorder(Cell[][] cell, int x, int y) {
        return cell[x][y + 1].getLifeStatus() + cell[x + 1][y + 1].getLifeStatus() + cell[x + 1][y].getLifeStatus() +
                cell[x + 1][y - 1].getLifeStatus() + cell[x][y - 1].getLifeStatus();
    }

    /** The method checks if the cell is at the top border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return returns true if the cell is near the top border of the playing field
     */
    private boolean isNearTopBorder(Cell[][] cell, int x, int y) {
        return x > 0 && x < cell.length - 1 && y == 0;
    }

    /** Counting method for the cell that is at the top border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countNearTopBorder(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x - 1][y + 1].getLifeStatus() + cell[x][y + 1].getLifeStatus() +
                cell[x + 1][y + 1].getLifeStatus() + cell[x + 1][y].getLifeStatus();

    }

    /** The method checks if the cell is at the right border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is near the right border of the playing field
     */
    private boolean isNearRightBorder(Cell[][] cell, int x, int y) {
        return x > 0 && x < cell.length - 1 && y == cell.length - 1;
    }
    /** Counting method for the cell that is at the right border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countNearRightBorder(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x + 1][y].getLifeStatus() + cell[x + 1][y - 1].getLifeStatus() +
                cell[x][y - 1].getLifeStatus() + cell[x - 1][y - 1].getLifeStatus();
    }
    /** The method checks if the cell is at the bottom border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is near the bottom border of the playing field
     */

    private boolean isNearBottomBorder(Cell[][] cell, int x, int y) {
        return x == cell.length - 1 && y > 0 && y < cell.length - 1;
    }
    /** Counting method for the cell that is at the bottom border of the field.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countNearBottomBorder(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x - 1][y + 1].getLifeStatus() + cell[x][y + 1].getLifeStatus() +
                cell[x][y - 1].getLifeStatus() + cell[x - 1][y - 1].getLifeStatus();
    }

    /**The method checks if the cell is in the upper left corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is in left top corner of the playing field
     */
    private boolean isLeftTopCorner(Cell[][] cell, int x, int y) {
        return x == 0 && y == 0;
    }

    /** Counting method for the cell that is the upper left corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countLeftTopCorner(Cell[][] cell, int x, int y) {
        return cell[x][y + 1].getLifeStatus() + cell[x + 1][y + 1].getLifeStatus() + cell[x + 1][y].getLifeStatus();
    }

    /**The method checks if the cell is in the upper right corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is in right top corner of the playing field
     */
    private boolean isRightTopCorner(Cell[][] cell, int x, int y) {
        return x == 0 && y == cell.length - 1;
    }

    /** Counting method for the cell that is the upper right corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countRightTopCorner(Cell[][] cell, int x, int y) {
        return cell[x + 1][y].getLifeStatus() + cell[x + 1][y - 1].getLifeStatus() + cell[x][y - 1].getLifeStatus();
    }

    /**The method checks if the cell is in the lower right corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is in right bottom corner of the playing field
     */
    private boolean isRightBottomCorner(Cell[][] cell, int x, int y) {
        return x == cell.length - 1 && y == 0;
    }

    /** Counting method for the cell that is the lower right corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countRightBottomCorner(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x - 1][y + 1].getLifeStatus() + cell[x][y + 1].getLifeStatus();
    }

    /** Counting method for the cell that is the lower left corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns true if the cell is in left bottom corner of the playing field
     */
    private boolean isLeftBottomCorner(Cell[][] cell, int x, int y) {
        return x == cell.length - 1 && y == cell.length - 1;
    }

    /** Counting method for the cell that is the lower left corner.
     @param cell - matrix of rectangles
     @param x - cell coordinate in the matrix
     @param y - cell coordinate in the matrix
     @return - returns the number of living neighbors of a cell
     */
    private int countLeftBottomCorner(Cell[][] cell, int x, int y) {
        return cell[x - 1][y].getLifeStatus() + cell[x][y - 1].getLifeStatus() + cell[x - 1][y - 1].getLifeStatus();
    }
}