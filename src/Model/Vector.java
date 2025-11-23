package Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Mathematically defined as a quantity with direction as well as magnitude.
 */
public class Vector {

    //instance variables
    List<Integer> vector;
    int dimensions;

    //constructor method
    public Vector(ArrayList<Integer> vector) {
        validate(vector);
        this.vector = vector;
        setDimensions();
    }

    //private update methods

    /**
     * Validation check to ensure than the given vector is not empty.
     */
    private boolean validate(ArrayList<Integer> vector) {
        if(vector.isEmpty())
            return false;
        return true;
    }

    /**
     * Returns the number of dimensions in a given vector (zero if null).
     */
    private void setDimensions() {
        int count = 0;
        for(int i = 0; i < vector.size(); i++) {
            count++;
        }
        dimensions = count;
    }

    //public update methods
    public void setVector(ArrayList<Integer> newVector) {
        validate(newVector);
        vector = newVector;
        setDimensions();
    }

    //public access methods
    public int getDimensions() {
        return dimensions;
    }

    public List<Integer> getVector() {
        return vector;
    }
}
