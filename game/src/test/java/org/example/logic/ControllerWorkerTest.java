package org.example.logic;

import org.example.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerWorkerTest {

    @Test
    public void testControllerWorkerSyncronizeMatrix() {
        Cell[][] cells = {
                {new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 1)},
                {new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0)},
                {new Cell((byte) 1), new Cell((byte) 1), new Cell((byte) 0)}
        };
        byte[][] byteCopyCells = new byte[3][3];

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].getLifeStatus() == 1)
                    byteCopyCells[x][y] = 1;
                else
                    byteCopyCells[x][y] = 0;
            }
        }
        byte[][] byteForSyncronize = new byte[3][3];
        ControllerWorker controllerWorker = new ControllerWorker(cells, byteCopyCells);
        controllerWorker.syncronizeMatrix(cells, byteForSyncronize);
        assertArrayEquals(byteCopyCells, byteForSyncronize);

    }

    @Test
    public void testControllerWorkersyncronizeMatrixBack() {
        byte[][] byteMatrix = {
                {1 , 0, 1}, {0 , 0, 1}, {0 , 1, 0}
        };

        Cell[][] cells = {
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)}
        };

        ControllerWorker controllerWorker = new ControllerWorker(cells, byteMatrix);
        controllerWorker.syncronizeMatrixBack(cells, byteMatrix);

        byte[][] byteCopyCell = new byte[cells.length][cells.length];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].getLifeStatus() == 1)
                    byteCopyCell[x][y] = 1;
                else
                    byteCopyCell[x][y] = 0;
            }
        }
        assertArrayEquals(byteMatrix, byteCopyCell);
    }

    @Test
    public void testControllerWorkerIsEmptyMatrixNotEmpty() {
        Cell[][] cells = {
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 1)},
                {new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0)},
                {new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 0)}
        };
        ControllerWorker controllerWorker = new ControllerWorker();
        assertFalse(controllerWorker.isEmptyMatrix(cells));
    }

    @Test
    public void testControllerWorkerIsEmptyMatrixEmpty() {
        Cell[][] cells = {
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)}
        };
        ControllerWorker controllerWorker = new ControllerWorker();
        assertTrue(controllerWorker.isEmptyMatrix(cells));
    }

    @Test
    public void testControllerWorkerGenerateRandomMatrix(){
        int count = 0;
        byte[][] byteMatrix = {
                {0 ,0, 0},
                {0 ,0, 0},
                {0 ,0, 0}
        };
        Cell[][] cells = {
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 0)}
        };

        ControllerWorker controllerWorker = new ControllerWorker();
        controllerWorker.generateRandomMatrix(cells, byteMatrix, 5);

        for (int x = 0; x < byteMatrix.length; x++) {
            for (int y = 0; y < byteMatrix[x].length; y++){
                if (byteMatrix[x][y] == 1)
                    count++;
            }
        }
        assertTrue(count > 0);
    }
  }