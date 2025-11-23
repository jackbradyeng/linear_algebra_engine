package Model;

import Exceptions.IllegalMatrixException;
import Exceptions.MismatchedMatricesException;
import Exceptions.NonSquareMatrixException;

import java.util.ArrayList;

/**
 * A rectangular array of numbers or other mathematical objects.
 */
public class Matrix {

    //public instance variables
    public ArrayList<ArrayList<Integer>> matrix;
    public ArrayList<ArrayList<Integer>> identity;
    public boolean isSquare;
    public boolean identityInUse = false; //set to false by default
    public int rows;
    public int columns;
    public int determinant;

    //private instance variables
    private int counter; //for debugging purposes, shows recursive invocations

    //constructor method
    public Matrix(ArrayList<ArrayList<Integer>> matrix) throws IllegalMatrixException {
        if(!validate(matrix))
            throw new IllegalMatrixException("Input matrix is not valid.");
    }

    //private update methods

    /**
     * Each array in the matrix corresponds to a particular row and each row should have the same length. Validates the
     * integrity of the matrix and assigns parameters to the row, column, and isSquare dimensions. Note: There is no
     * upper limit on the number of rows allowed in a matrix. Method runs in O(N) time since we need to iterate over the
     * entire matrix.
     * @return true if the matrix is valid, false if not.
     */
    private boolean validate(ArrayList<ArrayList<Integer>> matrix) {
        if(matrix.isEmpty())
            return false;
        int rowLength = matrix.getFirst().size();
        int colLength = 0;
        for(ArrayList<Integer> array : matrix) {
            colLength++;
            if (array.size() != rowLength)
                return false;
        }

        columns = rowLength; //number of columns should be equal to the length of the first row
        rows = colLength; //number of rows should be equal to the length of the first column
        isSquare = colLength == rowLength;
        this.matrix = matrix;

        //if the matrix is square, then we compute the identity matrix
        if(isSquare) {
            setIdentity();
        }

        return true;
    }

    /**
     * By definition, the identity matrix is a square matrix with all the elements along the main diagonal being equal
     * to one. A matrix multiplied by its own identity returns itself.
     */
    public ArrayList<ArrayList<Integer>> setIdentity() throws NonSquareMatrixException {
        if(!isSquare)
            throw new NonSquareMatrixException("Matrix is not square. Cannot generate an identity matrix.");
        ArrayList<ArrayList<Integer>> newIdentity = new ArrayList<>();
        for(int i = 0; i < columns; i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                //along the diagonal, set the values to one
                if (i == j) {
                    newRow.add(1);
                } else {
                    newRow.add(0);
                }
            }
            newIdentity.add(newRow);
        }
        identity = newIdentity;
        return newIdentity;
    }

    //public update methods

    /**
     * Recursive technique utilizing Laplace Expansion to find the sub-determinants of a matrix before computing their
     * sum. Time complexity very slow O(N!) for Laplace Expansion. Gaussian Elimination should be used for larger
     * matrices since it runs in O(N^3).
     */
    public int setDeterminant(Matrix matrix) throws NonSquareMatrixException {
        if(!matrix.isSquare())
            throw new NonSquareMatrixException("Determinant undefined for non-square matrices.");

        int size = matrix.getMatrix().size();

        //elementary case, the determinant of a 1x1 matrix is just itself
        if(size == 1) {
            this.determinant = matrix.getMatrix().getFirst().getFirst();
            return determinant;
        }

        //base case, a 2x2 matrix
        if(size == 2) {
            this.determinant = findSubDeterminant(matrix.getMatrix());
            return determinant;
        }

        int determinant = 0;

        System.out.println( "\n" + "Submatrix: ");
        matrix.printMatrix();
        System.out.println("Outer rows: " + matrix.getColumns() + " | Outer columns: " + matrix.getRows());
        counter++;
        System.out.println("Recursive invocation no. " + counter);

        //as per the Laplace Expansion method, we begin by iterating over the first row in the matrix
        for(int i = 0; i < size; i++) {

            //computes the submatrix relative to a particular column index
            ArrayList<ArrayList<Integer>> subMatrix = findSubMatrix(matrix.getMatrix(), 0, i);

            //if the column index is even, then the sub-determinant returned by the Laplace Expansion should be negative
            int sign = (i % 2 == 0) ? 1 : -1;

            //convert ArrayList to Matrix object
            Matrix newSubMatrix = new Matrix(subMatrix);

            //recursive invocation
            determinant += sign * matrix.getMatrix().getFirst().get(i) * setDeterminant(newSubMatrix);
        }
        this.determinant = determinant;
        return determinant;
    }

    //private helper methods
    private int findSubDeterminant (ArrayList<ArrayList<Integer>> subMatrix) {
        return subMatrix.get(0).get(0) * subMatrix.get(1).get(1)
                - subMatrix.get(0).get(1) * subMatrix.get(1).get(0);
    }

    private ArrayList<ArrayList<Integer>> findSubMatrix(ArrayList<ArrayList<Integer>> matrix,
                                                        int excludeRow, int excludeCol) {
        ArrayList<ArrayList<Integer>> subMatrix = new ArrayList<>();
        int size = matrix.size();

        for (int i = 0; i < size; i++) {
            if (i == excludeRow) {
                continue; //skip the excluded row
            }

            ArrayList<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (j == excludeCol) {
                    continue; //skip the excluded column
                }
                newRow.add(matrix.get(i).get(j));
            }
            subMatrix.add(newRow);
        }
        return subMatrix;
    }

    //public update methods
    public void setMatrix(ArrayList<ArrayList<Integer>> matrix) throws IllegalMatrixException {
        if(!validate(matrix))
            throw new IllegalMatrixException("Input matrix not valid.");
    }

    /**
     * For matrix addition, the two matrices must have an equal number of rows and columns in order to be added.
     */
    public Matrix add(Matrix matrixOne, Matrix matrixTwo) throws MismatchedMatricesException {

        //check to see that matrices have an equal number of rows and columns
        if(matrixOne.getRows() != matrixTwo.getRows())
            throw new MismatchedMatricesException("Matrices have an unequal number of rows");
        if(matrixOne.getColumns() != matrixTwo.getColumns())
            throw new MismatchedMatricesException("Matrices have an unequal number of columns.");

        //instantiate return matrix and an array to be appended to it after each while loop iteration
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();

        for(int i = 0; i < matrixOne.getRows(); i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j = 0; j < matrixOne.getColumns(); j++) {
                int value1 = matrixOne.getMatrix().get(i).get(j);
                int value2 = matrixTwo.getMatrix().get(i).get(j);
                newRow.add(value1 + value2);
            }
            newMatrix.add(newRow);
        }
        return new Matrix(newMatrix);
    }

    /**
     * Scales the values in a matrix by some constant factor.
     */
    public Matrix scale(Matrix matrix, int scalar) {

        ArrayList<ArrayList<Integer>> scaledMatrix = new ArrayList<>();

        for(int i = 0; i < matrix.getRows(); i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for(int j = 0; j < matrix.getColumns(); j++) {
                int scaledValue = matrix.getMatrix().get(i).get(j) * scalar;
                newRow.add(scaledValue);
            }
            scaledMatrix.add(newRow);
        }
        return new Matrix(scaledMatrix);
    }

    /**
     * For matrix multiplication, the number of columns in matrix one must equal the number of rows in matrix two.
     * NOTE: Matrix multiplication is associative but not commutative. This means that the order of the matrices in
     * the multiplication matters. (AB)C = A(BC) but AB does not equal BA. Method runs in (N^3) time complexity.
     */
    public Matrix multiply(Matrix matrixOne, Matrix matrixTwo) throws MismatchedMatricesException {
        if(matrixOne.getColumns() != matrixTwo.getRows())
            throw new MismatchedMatricesException("Cannot compute the matrix product if the column count of matrix one is" +
                    " not equal to the row count of matrix two.");

        //instantiate return matrix
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();

        //iterating over each column in matrix one for each row in matrix two
        for(int i = 0; i < matrixOne.getColumns(); i++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < matrixOne.getColumns(); j++) {
                int newValue = 0;
                for (int k = 0; k < matrixTwo.getRows(); k++) {
                    /* we iterate over the row in matrix one and the column in matrix two, multiplying both to generate
                    the new value in the product matrix. */
                    int mat1 = matrixOne.getMatrix().get(i).get(k);
                    int mat2 = matrixTwo.getMatrix().get(k).get(j);
                    newValue += mat1 * mat2;
                }
                newRow.add(newValue);
            }
            newMatrix.add(newRow);
        }
        return new Matrix(newMatrix);
    }

    public void setIdentityInUse(boolean identityInUse) {
        this.identityInUse = identityInUse;
    }

    //public access methods
    public ArrayList<ArrayList<Integer>> getMatrix() {
        if(identityInUse) {
            return identity;
        } else {
            return matrix;
        }
    }
    public ArrayList<ArrayList<Integer>> getIdentity() {
        if(identityInUse) {
            return matrix;
        } else {
            return identity;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isSquare() {
        return isSquare;
    }

    public int getDeterminant() {
        return determinant;
    }

    /**
     * If we want to display the identity matrix, but still keep the original in memory, then we can simply swap the
     * two using a flag. The main benefit of this technique is that we do not have to waste time copying matrices
     * back and forth in order to process them.
     */
    public boolean isIdentityInUse() {
        return identityInUse;
    }

    //print methods (for debugging purposes)
    public void printMatrix() {
        for(ArrayList<Integer> row : matrix)
            System.out.println(row.toString());
    }

    public void printIdentity() {
        for(ArrayList<Integer> row : identity)
            System.out.println(row.toString());
    }
}
