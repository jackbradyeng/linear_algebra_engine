import Controller.Controller;
import Model.Model;
import View.MainFrame;

void main() {
        MainFrame view = new MainFrame(1920, 1080);
        Model model = new Model(4);
        Controller controller = new Controller(model, view);
}
