/**
import Exceptions.MismatchedMatricesException
import Exceptions.NonSquareMatrixException
import Model.Generator
import Model.Matrix
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MatrixModelTesting : FunSpec({

    // Setup helper
    val generator = Generator()

    // Lazily initialized test data
    val m1 = Matrix(generator.generateSequentialMatrix(1, 1))
    val m2 = Matrix(generator.generateSequentialMatrix(2, 2))
    val m3 = Matrix(generator.generateSequentialMatrix(3, 3))
    val nonSquare = Matrix(generator.generateSequentialMatrix(2, 3))

    context("Matrix Addition") {
        test("Summing 1x1 matrices yields the sum of their contents") {
            m1.add(m1, m1).matrix[0][0] shouldBe 2.0
        }

        test("Summing 2x2 matrices yields the correct sum and conserves dimensions") {
            val result = m2.add(m2, m2)
            val sumMatrix = result.matrix

            sumMatrix[0][0] shouldBe 2.0
            sumMatrix[0][1] shouldBe 4.0
            sumMatrix[1][0] shouldBe 6.0
            sumMatrix[1][1] shouldBe 8.0

            result.rows shouldBe 2
            result.columns shouldBe 2
        }

        test("Adding mismatched matrices should throw MismatchedMatricesException") {
            shouldThrow<MismatchedMatricesException> {
                m1.add(m1, m2)
            }
        }
    }

    context("Matrix Multiplication") {
        test("Multiplying 1x1 matrices yields the product of their contents") {
            m1.multiply(m1, m1).matrix[0][0] shouldBe 1.0
        }

        test("Multiplying 2x2 matrices yields the correct product and conserves dimensions") {
            val result = m2.multiply(m2, m2)
            val productMatrix = result.matrix

            productMatrix[0][0] shouldBe 7.0
            productMatrix[0][1] shouldBe 10.0
            productMatrix[1][0] shouldBe 15.0
            productMatrix[1][1] shouldBe 22.0

            result.rows shouldBe 2
            result.columns shouldBe 2
        }

        test("Multiplying mismatched matrices should throw MismatchedMatricesException") {
            shouldThrow<MismatchedMatricesException> {
                m2.multiply(m2, m3)
            }
        }
    }

    context("Identity Matrix") {
        test("Non-square matrices should throw NonSquareMatrixException for identity") {
            shouldThrow<NonSquareMatrixException> {
                nonSquare.setIdentity()
            }
        }

        test("The identity of a 1x1 matrix should be 1.0") {
            m1.identity[0][0] shouldBe 1.0
        }

        test("The identity of a 3x3 matrix should be correctly formatted") {
            val identity = m3.identity
            val expected = listOf(
                listOf(1.0, 0.0, 0.0),
                listOf(0.0, 1.0, 0.0),
                listOf(0.0, 0.0, 1.0)
            )
            identity shouldBe expected
        }
    }

    context("Determinants") {
        test("Non-square matrices should throw NonSquareMatrixException for determinant") {
            shouldThrow<NonSquareMatrixException> {
                nonSquare.setDeterminant(nonSquare)
            }
        }

        test("Determinant of 1x1 matrix is the value itself") {
            m1.setDeterminant(m1) shouldBe 1.0
        }

        test("Determinant of 2x2 matrix should follow (ad - bc)") {
            m2.setDeterminant(m2) shouldBe -2.0
        }

        test("Determinant of 3x3 matrix should be calculated correctly") {
            m3.setDeterminant(m3) shouldBe 0.0
        }
    }
})
**/