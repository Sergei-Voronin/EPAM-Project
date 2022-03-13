package org.example.logic;

import org.example.configuration.Config;
import javafx.scene.paint.Color;
import org.example.model.Cell;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 This class contains methods for working with game matrices.
 This class is also used for thread management.
 */

public class ControllerWorker implements Runnable{
    /** Matrix of rectangles - playing field. */
    private Cell[][] cells;
    /**Numerical representation of a matrix of rectangles. Used when counting neighbors.*/
    private byte[][] originalMatrix;
    /**Object for getting random numbers when generating a random field*/
    private Random random = new Random();
    /**Class object "LifeWorker" is responsible for creating new cells*/
    private LifeWorker life;
    /**Class object "DeathWorker" is responsible for the death of old cells*/
    private DeathWorker death;
    /**Thread thread1 creates a new cell using an object of the class "LifeWorker"*/
    private Thread thread1;
    /**Thread 2 kills old cells with a class object*/
    private Thread thread2;
    /**Thread 3 synchronizes the operation of thread 1 and thread 2*/
    private Thread thread3;
    /**Config is stored in the "Config" class variable*/
    private Config config = Config.getConfig();
    /**Объект класса "CyclicBarrier" синхронизирует потоки*/
    private CyclicBarrier barrier = new CyclicBarrier(2, this);

    /**Constructor assigns values to variables and creates objects
     @param cells - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public ControllerWorker(Cell[][] cells, byte[][] originalMatrix){
        this.cells = cells;
        this.originalMatrix = originalMatrix;
        life = new LifeWorker(cells, originalMatrix, barrier);
        death = new DeathWorker(cells, originalMatrix, barrier);
    }
    /**This constructor need  only for unit tests.*/
    public ControllerWorker(){
    }
    /**This  method creates and starts threads.*/
    public void startThreads(){
        thread1 = new Thread(life);
        thread1.start();
        thread2 = new Thread(death);
        thread2.start();
        thread3 = new Thread(this);
    }
    /**This method stops threads.*/
    public void stopThreads(){
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    }

    /**This method from interface Runnable.
     It syncs rectangles matrix and bytes matrix it won't stop or run out of animation steps
     Variable "i" will read the cycles - animation steps.
     */
    @Override
    public void run() {
        int i = 0;
        Thread current = Thread.currentThread();
            while (i < config.getAnimationSteps() && !current.isInterrupted()){
                syncronizeMatrixBack(cells, originalMatrix);
                i++;
            }
    }

    /**This method syncronize rectangles matrix and bytes matrix
     @param cells - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public void syncronizeMatrix(Cell[][] cells, byte[][] originalMatrix){
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].getLifeStatus() == 1)
                    originalMatrix[x][y] = 1;
                else
                    originalMatrix[x][y] = 0;
            }
        }
    }
    /**This method syncronize bytes matrix and rectangles matrix
     @param cells - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public void syncronizeMatrixBack(Cell[][] cells, byte[][] originalMatrix){
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (originalMatrix[x][y] == 1){
                    cells[x][y].setFill(Color.BLACK);
                    cells[x][y].setLifeStatus((byte) 1);
                }
                else{
                    cells[x][y].setFill(Color.GRAY);
                    cells[x][y].setLifeStatus((byte) 0);
                }
            }
        }
    }

    /**This method checks if the rectangles matrix is empty
     @param cells - matrix of rectangles
     @return true if there are no living cells in the matrix of rectangles
     */
    public boolean isEmptyMatrix(Cell[][] cells) {
        int count = 0;
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].getLifeStatus() == 1)
                    count++;
            }
        }
        if (count == 0)
            return true;
        else
            return false;
    }

    /**Clean the rectangles matrix
     @param cells - matrix of rectangles
     */
    public void clear(Cell[][] cells) {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y].setFill(Color.GRAY);
                cells[x][y].setLifeStatus((byte) 0);

            }
        }
        syncronizeMatrix(cells, originalMatrix);
    }
    /**The method randomly fills the digital matrix and synchronizes it with the matrix of rectangles.
     @param cell - matrix of rectangles
     @param originalMatrix - matrix of bytes
     */
    public void generateRandomMatrix(Cell[][] cell, byte[][] originalMatrix, int numberLiveCells) {
        for (int x = 0; x < numberLiveCells; x++){
            int randomHeight = random.nextInt(cell.length);
            int randomWidth = random.nextInt(cell.length) ;
            if (originalMatrix[randomHeight][randomWidth] == 0)
                originalMatrix[randomHeight][randomWidth] = 1;
            else
                x--;
        }
        syncronizeMatrixBack(cell, originalMatrix);
    }
    /**The method checks how many steps of the animation have been done.*/
    public boolean checkSteps(){
            if (life.getCount() < config.getAnimationSteps())
                return true;
            else{
                life.setCount(0);
                return false;
            }

    }
}
