import Controller.Controller;
import Model.Model;
import View.MainFrame;

/**
 * A Java program that performs a series of operations on n-dimensional arrays of integers.
 */
public class Main {

    public static void main(String[] args) {
        MainFrame view = new MainFrame(1920, 1080);
        Model model = new Model(4);
        Controller controller = new Controller(model, view);
    }
}
