package org.example.logic;

import org.example.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifeWorkerTest {
    @Test
    public void testLifeWorker(){
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
                {1 ,1, 0, 1, 0},
                {1 ,1, 1, 0, 0},
                {0 ,0, 1, 0, 1},
                {1 ,1, 1, 0, 0},
                {0 ,0, 1, 1, 1}
        };
        LifeWorker lifeWorker = new LifeWorker();
        lifeWorker.life(cells, byteMatrix);
        assertArrayEquals(expectedResult, byteMatrix);
    }
    @Test
    public void testGetAndSetCount(){
        int countForTest = 12544;
        LifeWorker lifeWorker = new LifeWorker();
        lifeWorker.setCount(countForTest);
        assertEquals(countForTest, lifeWorker.getCount());
    }


}
