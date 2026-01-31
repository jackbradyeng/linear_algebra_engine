import Model.Model
import Controller.Controller
import View.MainFrame
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class ControllerTesting : FunSpec( {

    // test instances
    val view = MainFrame(1920, 1080)
    val model = Model(4)
    val controller = Controller(model, view)
    val tolerance = 0.0001 // doubles can sometimes have rounding issues so we allow for some tolerance

    fun resetIdentityInUse() {
        model.matrixOne.identityInUse = false
        model.matrixTwo.identityInUse = false
    }

    context("Matrix Scaling") {
        test("A matrix scaled by two should return each of its constituent values multiplied by two.") {
            val original = model.matrixOne.matrix
            model.scaleFactorOne = 2
            controller.scaleMatrixA()
            val result = model.matrixOne.matrix

            // test that the dimensions are maintained after scaling
            original.size shouldBe result.size

            for (i in result.indices) {
                for (j in result[i].indices) {
                    result[i][j] shouldBe ((original[i][j] * model.scaleFactorOne) plusOrMinus tolerance)
                }
            }
        }
    }

    context("Identity Operations") {
        test("If the identity for Matrix A is in use then calling it should return the identity instance.") {
            val identity = model.matrixOne.identity
            controller.renderIdentityA()
            val matrix = model.matrixOne.matrix

            for (i in matrix.indices) {
                for (j in matrix.indices) {
                    matrix[i][j] shouldBe identity[i][j] plusOrMinus tolerance
                }
            }
        }

        test("If the identity for Matrix B is in use then calling it should return the identity instance.") {
            val identity = model.matrixTwo.identity
            controller.renderIdentityB()
            val matrix = model.matrixTwo.matrix

            for (i in matrix.indices) {
                for (j in matrix.indices) {
                    matrix[i][j] shouldBe identity[i][j] plusOrMinus tolerance
                }
            }
        }

        test("Adding two identity Matrices should return the sum of the two.") {
            resetIdentityInUse()
            controller.renderIdentityA()
            controller.renderIdentityB()
            controller.addMatrices()
            val matrix = model.matrixThree?.matrix

            for (i in matrix?.indices!!) {
                for (j in matrix.indices) {
                    if (i == j)
                        matrix[i][j] shouldBe 2.0 plusOrMinus tolerance
                    else
                        matrix[i][j] shouldBe 0.0 plusOrMinus tolerance
                }
            }
        }

        test("Adding an identity Matrix and a non-identity Matrix should return the sum of the two.") {
            resetIdentityInUse()
            controller.renderIdentityB()
            controller.addMatrices()
            val matrix = model.matrixThree?.matrix

            for (i in matrix?.indices!!) {
                for (j in matrix.indices) {
                    matrix[i][j] shouldBe (model.matrixOne.matrix[i][j] + model.matrixTwo.matrix[i][j]) plusOrMinus
                            tolerance
                }
            }
        }

        test("The determinant of any identity Matrix should be one.") {
            resetIdentityInUse()
            controller.renderIdentityA()
            controller.determinantMatrixA()
            model.matrixOne.determinant shouldBe 1.0 plusOrMinus tolerance
        }
    }
})