package Model;

/**
 * Defines the state of the Linear Algebra Engine with a number of instance variables to modulate behaviour.
 */
public class Model {

    //public instance variables
    public Matrix matrixOne;
    public Matrix matrixTwo;
    public Matrix matrixThree;
    public Matrix identityMatrix;
    public Generator generator;
    public int scaleFactorOne = 1; //set to one by default
    public int scaleFactorTwo = 1;
    public int matrixSize; //only working with square matrices for now

    //constructor method
    /**
     * Creates a generator instance as well as matrix one, matrix two, and identity matrix instances. The model is only
     * designed to handle square matrices, therefore the two matrix instances can share a single identity.
     * @param size the size of the matrix instances ie. size N generates two NxN matrices.
     * @throws IllegalArgumentException size must be greater than one but less than nine. Lower bound is self-explanatory
     * but upper bound is mainly linked to the very slow O(N!) determinant method.
     */
    public Model(int size) throws IllegalArgumentException {
        if(size < 1 || size > 9) {
            throw new IllegalArgumentException("Matrix size must be between 0 and 9.");
        }

        matrixSize = size;
        generator = new Generator();
        matrixOne = new Matrix(generator.generateRandomMatrix(matrixSize, matrixSize));
        matrixTwo = new Matrix(generator.generateRandomMatrix(matrixSize, matrixSize));
        identityMatrix = new Matrix(matrixOne.getIdentity());
    }

    //public access methods
    public Matrix getMatrixOne() {
        return matrixOne;
    }
    public Matrix getMatrixTwo() { return matrixTwo; }
    public Matrix getMatrixThree() {
        return matrixThree;
    }
    public Matrix getIdentityMatrix() {
        return identityMatrix;
    }
    public int getMatrixSize() {
        return matrixSize;
    }
    public int getScaleFactorOne() {return scaleFactorOne; }
    public int getScaleFactorTwo() {return scaleFactorTwo; }

    //public update methods
    public void setMatrixOne(Matrix matrixOne) {
        this.matrixOne = matrixOne;
    }
    public void setMatrixTwo(Matrix matrixTwo) {
        this.matrixTwo = matrixTwo;
    }
    public void setMatrixThree(Matrix matrixThree) {
        this.matrixThree = matrixThree;
    }
    public void setIdentityMatrix(Matrix identityMatrix) {
        this.identityMatrix = identityMatrix;
    }
    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }
    public void setScaleFactorOne(int scaleFactorOne) {this.scaleFactorOne = scaleFactorOne; }
    public void setScaleFactorTwo(int scaleFactorTwo) {this.scaleFactorTwo = scaleFactorTwo; }
}
