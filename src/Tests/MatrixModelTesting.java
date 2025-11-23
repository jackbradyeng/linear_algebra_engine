package Tests;

import Exceptions.MismatchedMatricesException;
import Exceptions.NonSquareMatrixException;
import Model.Generator;
import Model.Matrix;
import java.util.ArrayList;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Special-purpose class for testing the functionality of various backend functions in the Matrix model.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MatrixModelTesting {

    //testing relies on the generator class to initialize data
    Generator generator = new Generator();

    //sequential arraylist instances
    ArrayList<ArrayList<Integer>> sequential1x1 = generator.generateSequentialMatrix(1, 1);
    ArrayList<ArrayList<Integer>> sequential2x2 = generator.generateSequentialMatrix(2, 2);
    ArrayList<ArrayList<Integer>> sequential3x3 = generator.generateSequentialMatrix(3, 3);
    ArrayList<ArrayList<Integer>> sequential4x4 = generator.generateSequentialMatrix(4, 4);
    ArrayList<ArrayList<Integer>> sequential9x9 = generator.generateSequentialMatrix(9, 9);
    ArrayList<ArrayList<Integer>> sequential2x3 = generator.generateSequentialMatrix(2, 3);

    //matrix instances
    Matrix m1;
    Matrix m2;
    Matrix m3;
    Matrix m4;
    Matrix m9;
    Matrix nonSquare;

    //constructor
    public MatrixModelTesting() {
        m1 = new Matrix(sequential1x1);
        m2 = new Matrix(sequential2x2);
        m3 = new Matrix(sequential3x3);
        m4 = new Matrix(sequential4x4);
        m9 = new Matrix(sequential9x9);
        nonSquare = new Matrix(sequential2x3);
    };

    @Test
    @Order(1)
    /* Summing two vectors with of size 1 should simply yield the sum of their contents. */
    public void testAddition1x1() {
        assertEquals(2, m1.add(m1, m1).getMatrix().getFirst().getFirst());
    }

    @Test
    @Order(2)
    /* Summing two 2x2 matrices should yield the sum of their contents. */
    public void testAddition2x2Result() {
        ArrayList<ArrayList<Integer>> sumMatrix = m2.add(m2, m2).getMatrix();
        assertTrue(sumMatrix.get(0).get(0).equals(2) &&
                sumMatrix.get(0).get(1).equals(4) &&
                sumMatrix.get(1).get(0).equals(6) &&
                sumMatrix.get(1).get(1).equals(8));
    }

    @Test
    @Order(3)
    /* Summing two 2x2 matrices should conserve the dimensions of the original matrices. */
    public void testAddition2x2Dimensions() {
        Matrix sumMatrix = new Matrix(m2.add(m2, m2).getMatrix());
        assertTrue(sumMatrix.getRows() == 2 & sumMatrix.getColumns() == 2);
    }

    @Test
    @Order(4)
    /* Matrices with mismatched columns or rows should throw an IllegalArgumentException. */
    public void testAdditionMismatchedRows() {
        assertThrows(MismatchedMatricesException.class, () -> m1.add(m1, m2));
    }

    @Test
    @Order(5)
    /* Multiplying two vectors of size 1 should simply yield the product of their contents. */
    public void testMultiplication1x1() {
        assertEquals(1, m1.multiply(m1, m1).getMatrix().getFirst().getFirst());
    }

    @Test
    @Order(6)
    /* Multiplying two 2x2 matrices should yield the product of their contents. */
    public void testMultiplication2x2Result() {
        ArrayList<ArrayList<Integer>> productMatrix = m2.multiply(m2, m2).getMatrix();
        assertTrue(productMatrix.get(0).get(0).equals(7) &&
                productMatrix.get(0).get(1).equals(10) &&
                productMatrix.get(1).get(0).equals(15) &&
                productMatrix.get(1).get(1).equals(22));
    }

    @Test
    @Order(7)
    /* Multiplying two 2x2 matrices should conserve the dimensions of the original matrices. */
    public void testMultiplication2x2Dimensions() {
        Matrix productMatrix = new Matrix(m2.multiply(m2, m2).getMatrix());
        assertTrue(productMatrix.getRows() == 2 && productMatrix.getColumns() == 2);
    }

    @Test
    @Order(8)
    /* Multiplying two matrices with mismatched sizes should throw an IllegalArgumentException. */
    public void testMultiplicationMismatchedDimensions() {
        assertThrows(MismatchedMatricesException.class, () -> m2.multiply(m2, m3));
    }

    @Test
    @Order(9)
    /* Non-square matrices should not have identities. */
    public void testIdentityException() {
        assertThrows(NonSquareMatrixException.class, () -> nonSquare.setIdentity());
    }

    @Test
    @Order(10)
    /* The identity of a 1x1 should simply be 1. */
    public void testIdentity1x1() {
        assertEquals(1, m1.getIdentity().getFirst().getFirst());
    }

    @Test
    @Order(11)
    /* The identity of a 3x3 should return all 1s along the diagonal and all zeros elsewhere. */
    public void testIdentity3x3() {
        ArrayList<ArrayList<Integer>> identity = m3.getIdentity();
        assertTrue(identity.get(0).get(0) == 1 &&
                identity.get(0).get(1) == 0 &&
                identity.get(0).get(2) == 0 &&
                identity.get(1).get(0) == 0 &&
                identity.get(1).get(1) == 1 &&
                identity.get(1).get(2) == 0 &&
                identity.get(2).get(0) == 0 &&
                identity.get(2).get(1) == 0 &&
                identity.get(2).get(2) == 1);
    }

    @Test
    @Order(12)
    /* Non-square matrices should not have determinants. */
    public void testDeterminantException() {
        assertThrows(NonSquareMatrixException.class, () -> nonSquare.setDeterminant(nonSquare));
    }

    @Test
    @Order(13)
    /* The determinant of a 1x1 matrix should just be itself */
    public void testDeterminant1x1() {
        assertEquals(1, m1.setDeterminant(m1));
    }

    @Test
    @Order(14)
    /* The determinant of a 2x2 matrix should be equal to ad - bc. */
    public void testDeterminant2x2() {
        assertEquals(-2, m2.setDeterminant(m2));
    }

    @Test
    @Order(15)
    /* The determinant of a 3x3 matrix should be equal to a * | ei - fh| - b * | di - fg| + c * |dh - eg|. */
    public void testDeterminant3x3() {
        assertEquals(0, m3.setDeterminant(m3));
    }
}
