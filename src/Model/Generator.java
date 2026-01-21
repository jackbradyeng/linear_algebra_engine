package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Defines some pre-configured matrices for testing purposes. All values generated randomly.
 */
public class Generator {

    //defines an upper bound of the value of the integers generated
    private static final int RANDOM_BOUND = 20;

    /**
     * Generates an array of random value integers subject to some upper limit.
     */
    public ArrayList<ArrayList<Integer>> generateRandomMatrix(int rows, int columns)
            throws IllegalArgumentException {
        if(rows < 1 || columns < 1)
            throw new IllegalArgumentException("Row and column counts must be greater than or equal to one.");
        //random instance used for integer generation
        Random random = new Random();
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j = 0; j < columns; j++) {
                newRow.add(random.nextInt(RANDOM_BOUND));
            }
            newMatrix.add(newRow);
        }
        return newMatrix;
    }

    /**
     * Generates an array of sequentially ordered values.
     */
    public ArrayList<ArrayList<Integer>> generateSequentialMatrix(int rows, int columns)
            throws IllegalArgumentException {
        if(rows < 1 || columns < 1)
            throw new IllegalArgumentException("Row and column counts must be greater than or equal to one.");
        //counter instance used for sequential integer generation
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j = 0; j < columns; j++) {
                newRow.add(i * columns + j + 1);
            }
            newMatrix.add(newRow);
        }
        return newMatrix;
    }
}
