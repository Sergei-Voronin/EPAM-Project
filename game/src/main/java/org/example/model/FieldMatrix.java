package org.example.model;

import org.example.configuration.Config;

/** This class creates and stores a matrix of bytes.
 It is a digital representation of a matrix of rectangles.
 Needed to count the neighbors of cells.
 */

public class FieldMatrix {
    /** Matrix of bytes */
    private static byte byteMatrix[][];
    /**Create a variable with application settings*/
    Config config = Config.getConfig();

    /** Matrix of bytes */
    private FieldMatrix(){
        byteMatrix = new byte[config.getFieldHeight()][config.getFieldWidth()];
    }

    /** Return matrix of bytes.
     Implemented pattern "Singleton".
     The object exists in one instance
     @return byte matrix, еxisting in one copy
     */
    public static byte[][] getByteMatrix() {
        if(byteMatrix == null){
            new FieldMatrix();
        }
        return byteMatrix;
    }
    }

