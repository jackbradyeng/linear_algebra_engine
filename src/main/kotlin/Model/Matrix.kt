package Model

import Exceptions.IllegalMatrixException
import Exceptions.MismatchedMatricesException
import Exceptions.NonSquareMatrixException
import kotlin.math.abs
import kotlin.math.roundToInt

/** a rectangular array of numbers or other mathematical objects. */
class Matrix(matrix: List<List<Double>>) {

    lateinit var matrix: List<List<Double>>
    lateinit var identity: List<List<Double>>
    var isSquare: Boolean = false
    var identityInUse: Boolean = false //set to false by default
    var rows: Int = 0
    var columns: Int = 0
    var determinant: Double = 0.0

    init {
        if (!validate(matrix)) throw IllegalMatrixException("Input matrix is not valid.")
    }

    /**
     * Each array in the matrix corresponds to a particular row and each row should have the same length. Validates the
     * integrity of the matrix and assigns parameters to the row, column, and isSquare dimensions. Note: There is no
     * upper limit on the number of rows allowed in a matrix. Method runs in O(N) time since we need to iterate over the
     * entire matrix.
     * @return true if the matrix is valid, false if not.
     */
    private fun validate(matrix: List<List<Double>>): Boolean {
        if (matrix.isEmpty())
            return false
        val rowLength = matrix.size
        var colLength = 0
        for (array in matrix) {
            colLength++
            if (array.size != rowLength)
                return false
        }
        columns = rowLength //number of columns should be equal to the length of the first row
        rows = colLength //number of rows should be equal to the length of the first column
        isSquare = colLength == rowLength
        this.matrix = matrix

        //if the matrix is square, then we compute the identity matrix
        if (isSquare) { identity = setIdentity() }
        return true
    }

    /** the identity matrix is a square matrix with all the elements along the main.kotlin.main diagonal being equal
     * to one. A matrix multiplied by its own identity returns itself. */
    fun setIdentity(): List<List<Double>> {
        require(isSquare) {"Matrix is not square. Cannot generate an identity matrix."}
        return List(rows) { i ->
            List(columns) { j ->
                if (i == j) 1.0 else 0.0
            }
        }
    }

    /** uses Gaussian Elimination to place the matrix in row echelon form before multiplying the diagonal elements by
     * the sign to compute the determinant. Runs in O(N^3) time. */
    fun setDeterminant(matrix: Matrix): Double {
        require(matrix.isSquare) {"Determinant undefined for non-square matrices."}

        val n = matrix.rows
        val doubleArray = Array(n) { i ->
            DoubleArray(n) {j -> matrix.matrix[i][j] }
        }

        var det = 1.0
        var sign = 1

        for (i in 0 until matrix.rows) {
            // Step 1: Pivot selection (Partial Pivoting)
            var pivotRow = i
            for (j in i + 1 until matrix.columns) {
                if (abs(doubleArray[j][i]) > abs(doubleArray[pivotRow][i])) {
                    pivotRow = j
                }
            }

            // Step 2: Swap the rows if necessary
            if (pivotRow != i) {
                val temp = doubleArray[i]
                doubleArray[i] = doubleArray[pivotRow]
                doubleArray[pivotRow] = temp
                sign *= -1 // Sign flips on every swap
            }

            // Step 3: Check for singular matrix
            if (abs(doubleArray[i][i]) < 1e-10) return 0.0

            // Step 4: Eliminate elements below pivot
            for (j in i + 1 until matrix.rows) {
                val factor = doubleArray[j][i] / doubleArray[i][i]
                for (k in i until matrix.columns) {
                    doubleArray[j][k] -= factor * doubleArray[i][k]
                }
            }

            // 5. Accumulate the diagonal product
            det *= doubleArray[i][i]
        }
        val result = (det * sign).roundToInt().toDouble()
        determinant = result
        return result
    }

    @Throws(IllegalMatrixException::class)
    fun setMatrix(matrix: ArrayList<ArrayList<Double>>) {
        if (!validate(matrix)) throw IllegalMatrixException("Input matrix not valid.")
    }

    /** for matrix addition, the two matrices must have an equal number of rows and columns in order to be added. */
    fun add(matrixOne: Matrix, matrixTwo: Matrix): Matrix {
        require(matrixOne.rows == matrixTwo.rows) {"Matrices have an unequal number of rows."}
        require(matrixOne.columns == matrixTwo.columns) {"Matrices have an unequal number of columns."}

        return Matrix(List(rows) {i ->
            List(columns) { j ->
                matrixOne.matrix[i][j] + matrixTwo.matrix[i][j]
            }
        })
    }

    /** scales the values in a matrix by some constant factor. */
    fun scale(matrix: Matrix, scalar: Int): Matrix {

        return Matrix(List(matrix.rows) {i ->
            List(matrix.columns) {j ->
                matrix.matrix[i][j] * scalar
            }
        })
    }

    /**
     * For matrix multiplication, the number of columns in matrix one must equal the number of rows in matrix two.
     * Note that matrix multiplication is associative but not commutative. This means that the order of the matrices in
     * the multiplication matters. (AB)C = A(BC) but AB does not equal BA. Method runs in (N^3) time complexity.
     */
    fun multiply(matrixOne: Matrix, matrixTwo: Matrix): Matrix {
        require(matrixOne.columns == matrixTwo.rows) {"Cannot compute the matrix product if the column count of " +
                "matrix one is not equal to the row count of matrix two."}

        return Matrix(List(matrixOne.rows) {i ->
            List(matrixOne.columns) {j ->
                (0 until matrixTwo.columns).sumOf {k ->
                    matrixOne.matrix[i][k] * matrixTwo.matrix[k][j]
                }
            }
        })
    }

    /** transposes the cells of the matrix. i.e. flips its values along the horizontal. */
    fun transpose(matrix: Matrix): Matrix {
        require(matrix.isSquare) { "Cannot transpose a non-square matrix."}

        return Matrix(List(matrix.columns) {i ->
            List(matrix.rows) {j ->
                matrix.matrix[j][i]
            }
        })
    }

    //print methods (for debugging purposes)
    fun printMatrix() {
        for (row in matrix) println(row.toString())
    }

    fun printIdentity() {
        for (row in identity) println(row.toString())
    }
}
