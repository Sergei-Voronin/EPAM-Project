package org.example.logic;

import org.example.configuration.Config;
import org.example.model.Cell;
import java.util.concurrent.CyclicBarrier;

/**An object of the "DeathWorker" class is responsible for the "death" of cells */

public class DeathWorker extends AbstractWorker implements Runnable {
    /**Create a variable with application settings*/
    Config config = Config.getConfig();

    /**Calling the parent class constructor and passing parameters
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     @param barrier - thread sync object
     */
    public DeathWorker(Cell[][] cell, byte[][] originalMatrix, CyclicBarrier barrier) {
        super(cell, originalMatrix, barrier);
    }

    /**This constructor is only needed for unit tests.*/
    public DeathWorker() {
    }
    /**The method is executed in a separate thread.
     It first executes the "death" method.
     Then it "falls asleep" by the amount specified in the parameters.
     Then it increases the value of the "count" variable, and waits for a command from the "CyclicBarrier" class object to continue working.
     */
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        try {
            while (!current.isInterrupted()) {
                death(cell, originalMatrix);
                Thread.sleep(config.getAnimationSpeedInMs());
                barrier.await();
            }
        } catch (Exception exp) {
            current.interrupt();
        }
    }
    /**This method counts the number of neighbors around the cell.
     If there is life in the cell, and it has less than two "living" neighbors, it dies from loneliness.
     If there is life in a cell, and it has more than three "living" neighbors, it dies from overpopulation.
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public void death(Cell[][] cell, byte[][] originalMatrix) {
        int neighbors = 0;
        for (int x = 0; x < cell.length; x++) {
            for (int y = 0; y < cell[x].length; y++) {
                neighbors = countingNeighbors(cell, x, y);
                if ((neighbors < 2 || neighbors > 3) && cell[x][y].getLifeStatus() == 1) {
                    originalMatrix[x][y] = 0;
                }
            }
        }
    }
}