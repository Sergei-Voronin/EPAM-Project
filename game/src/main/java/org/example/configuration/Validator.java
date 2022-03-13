package org.example.configuration;
/**
 This class is for checking parameters.
 */
public class Validator {
    private int fieldHeight;/** Playing field height.*/
    private int fieldWidth;/** Playing field width.*/
    private int animationSteps;/** Number of animation steps.*/

    /**
     The method checks if the passed parameters are numbers. If they are chills, check the valid range.
     @param args contains the size of the playing field and the number of generations
     */
    public void validationOfParametrs(String[] args) {
        try {
            fieldHeight = Integer.parseInt(args[0]);
            fieldWidth = Integer.parseInt(args[1]);
            animationSteps = Integer.parseInt(args[2]);
        } catch (Exception ite) {
            System.out.println("Error! Only numbers can be used as parameters");
            System.exit(0);
        }

        if (fieldHeight < 0 || fieldHeight > 200) {
            System.out.println("Error! First number (height) must be in the range 0 - 200. Enter the correct parameter.");
            System.exit(0);
        }

        if (fieldWidth < 0 || fieldWidth > 200) {
            System.out.println("Error! Second number (width) must be in the range 0 - 200. Enter the correct parameter.");
            System.exit(0);
        }
        if (animationSteps < 0 || animationSteps > 1000) {
            System.out.println("Error! Third number (animation steps) must be in the range 0 - 1000. Enter the correct parameter.");
            System.exit(0);
        }
    }
}
