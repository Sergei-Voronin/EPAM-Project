package org.example.logic;

import org.example.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DeathWorkerTest {


    @Test
    public void testDeathWorker(){
        Cell[][] cells = {
                {new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0)},
                {new Cell((byte) 1), new Cell((byte) 1), new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 1)},
                {new Cell((byte) 1), new Cell((byte) 1), new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 0)},
                {new Cell((byte) 0), new Cell((byte) 0), new Cell((byte) 1), new Cell((byte) 0), new Cell((byte) 1)}
        };
        byte[][] byteMatrix = {
                {0 ,1, 0, 1, 0},
                {1 ,1, 1, 0, 0},
                {0 ,0, 1, 0, 1},
                {1 ,1, 1, 0, 0},
                {0 ,0, 1, 0, 1}
        };
        byte[][] expectedResult = {
                {0 ,1, 0, 0, 0},
                {1 ,0, 0, 0, 0},
                {0 ,0, 0, 0, 0},
                {0 ,0, 1, 0, 0},
                {0 ,0, 1, 0, 0}
        };
        DeathWorker deathWorker = new DeathWorker();
        deathWorker.death(cells, byteMatrix);
        assertArrayEquals(expectedResult, byteMatrix);
    }

}
