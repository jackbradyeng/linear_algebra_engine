import Controller.Controller
import Model.Model
import View.MainFrame

fun main() {
    val view = MainFrame(1920, 1080)
    val model = Model(4)
    val controller = Controller(model, view)
}
