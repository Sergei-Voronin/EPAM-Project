package org.example.model;

import javafx.scene.shape.Rectangle;

/** The class is inherited from the "Rectangle" class.
 He adds a variable that tells whether there is life in the cell.
 */

public class Cell extends Rectangle {
    /**The variable shows whether there is "life" in the cell. 0 - no life. 1 - there is life. */
    private byte lifeStatus = 0;
    /** This constructor is only needed for unit tests.
     It allows you to create a cell and immediately indicate if there is life in it.
     @param lifeStatus - sets a cell alive or dead
     */
    public Cell(byte lifeStatus){
        this.lifeStatus = lifeStatus;
    }
    /** Этот конструктор создаёт клетку. По умолчанию "жизни" в ней нет. */
    public Cell(){}
    /** Returns the status of the cell - whether it is alive or not.
     @return a cell alive or dead
     */
    public byte getLifeStatus() {
        return lifeStatus;
    }
    /** Sets the status of the cell - alive or not.
     @param lifeStatus - sets a cell alive or dead
     */
    public void setLifeStatus(byte lifeStatus) {
        this.lifeStatus = lifeStatus;
    }
}
