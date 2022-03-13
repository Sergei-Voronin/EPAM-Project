package org.example.logic;

import org.example.configuration.Config;
import org.example.model.Cell;
import java.util.concurrent.CyclicBarrier;

/** An object of the LifeWorker class is responsible for the "life" of cells. */
public class LifeWorker extends AbstractWorker implements Runnable{
    /** This variable counts generational changes. From it we understand when to stop the execution of the program. */
    private int count = 0;
    /**Create a variable with application settings*/
    private Config config = Config.getConfig();

    /**Calling the parent class constructor and passing parameters
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     @param barrier - thread sync object
     */
    public LifeWorker(Cell[][] cell, byte[][] originalMatrix, CyclicBarrier barrier){
        super(cell, originalMatrix, barrier);
    }

    /**This constructor is only needed for unit tests.*/
    public LifeWorker(){
    }
    /**Returns the value of a variable "count".
     @return count - generation counter */
    public int getCount() {
        return count;
    }

    /**Sets the value of a variable "count".
    @param - set generation counter
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**The method is executed in a separate thread.
     It first executes the "life" method.
     Then it "falls asleep" by the amount specified in the parameters.
     Then it increases the value of the "count" variable, and waits for a command from the "CyclicBarrier" class object to continue working.
     */
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        try {
            while (!current.isInterrupted()){
                life(cell, originalMatrix);
                Thread.sleep(config.getAnimationSpeedInMs());
                count++;
                barrier.await();
            }
        }
        catch (Exception exp){
            current.interrupt();
        }
    }

    /**This method counts the number of neighbors around the cell.
     * If a cell has three "living" neighbors, but there is no "life" in it, then "life" appears in it.
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public void life(Cell[][] cell, byte[][] originalMatrix){
        int neighbors = 0;
        for (int x = 0; x < cell.length; x++) {
            for (int y = 0; y < cell[x].length; y++) {
                neighbors = countingNeighbors(cell, x, y);
                if (neighbors == 3 && cell[x][y].getLifeStatus() == 0){
                    originalMatrix[x][y] = 1;
                }
            }
        }

    }
}