import Model.Generator
import Model.Matrix
import Model.Model
import Controller.Controller
import View.MainFrame
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ControllerTesting : FunSpec( {

    // test instances
    val view = MainFrame(1920, 1080)
    val model = Model(4)
    val controller = Controller(model, view)

    context("Matrix Scaling") {
        test("A matrix scale by one should return itself.") {
            val original = model.matrixOne.matrix
            controller.scaleMatrixA()
        }
    }
})