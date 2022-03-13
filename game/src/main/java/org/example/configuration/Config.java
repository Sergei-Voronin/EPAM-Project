package org.example.configuration;

import org.example.model.Cell;

/**
 An object of this class exists in one instance.
 This object stores the configuration of the application - width and height of the playing field, number of animation steps, cell size, animation speed.
 Before saving the configuration, the values are checked in an object of the "Validator" class.
 */
public class Config {
    private int fieldHeight; /** Playing field height.*/
    private int fieldWidth; /** Playing field width.*/
    private int animationSteps; /** Number of animation steps.*/
    private int animationSpeedInMs = 150; /** Animation speed in milliseconds.*/
    private int cellSize = 14; /** Rectangle cell size.*/
    private Validator validator = new Validator(); /** A reference to an object of the "Validator" class. It checks the correctness of the entered parameters.*/
    private static Config config; /** A reference to an object of the "Config".The object exists in one instance. Implemented pattern "Singleton".*/
    /**the number of living cells as a percentage of the playing field.
     The variable is needed to generate a random playing field in the method
     @see logic.ControllerWorker#generateRandomMatrix(Cell[][], byte[][]) */
    private int numberLiveCellsForGenerationField;

    /**
     The constructor passes parameters to the validator for verification.
     If the parameters meet the requirements, they are entered into the corresponding variables.
     @param args contains the size of the playing field and the number of generations
     */
    private Config(String[] args) {
        validator.validationOfParametrs(args);
        setValues(args);
    }
    /**
     The method returns an object of the "Config" class, which exists only in one instance.
     If the object has not yet been created, this method will create it.
     @param args contains the size of the playing field and the number of generations
     */
    public static Config getConfig(String[] args){
        if(config == null){
            config = new Config(args);
        }
        return config;
    }
    /**
     The method returns an object of the "Config" class, which exists only in one instance.
     @return config - Config class object, сontains application settings
     */
    public static Config getConfig(){
        return config;
    }
    /**
     Method "setValues" sets values to fields.
     @param args contains the size of the playing field and the number of generations.
     In the last line, we count the number of living cells for random generation. Default value - 20%.
     */
    public void setValues(String[] args){
            fieldHeight = Integer.parseInt(args[0]);
            fieldWidth = Integer.parseInt(args[1]);
            animationSteps = Integer.parseInt(args[2]);
            numberLiveCellsForGenerationField = ((fieldHeight * fieldWidth)/100) * 20;
        }

    /**
     Getter of the field "fieldHeight".
     @return fieldHeight - playing field height
     * */
    public int getFieldHeight() {
        return fieldHeight;
    }

    /**
     Getter of the field "fieldWidth".
     @return fieldWidth - playing field width
     * */
    public int getFieldWidth() {
        return fieldWidth;
    }

    /**
     Getter of the field "animationSteps".
     @return animationSteps - number of generations
     * */
    public int getAnimationSteps() {
        return animationSteps;
    }

    /**
     Getter of the field "animationSpeedInMs".
     @return animationSpeedInMs - animation speed in milliseconds
     * */
    public int getAnimationSpeedInMs() {
        return animationSpeedInMs;
    }

    /**
     Getter of the field "sellSize".
     @return cellSize - cell size
     * */
    public int getCellSize() {
        return cellSize;
    }

    public int getNumberLiveCellsForGenerationField() {
        return numberLiveCellsForGenerationField;
    }

}