package View;

import Model.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Defines the GUI for the Linear Algebra Engine.
 */
public class MainFrame extends JFrame {

    //private constants
    private static final int CONTROL_PANEL_HEIGHT = 180;
    private static final int CONTROL_PANEL_WIDTH = 300;
    private static final int TOP_BANNER_HEIGHT = 100;
    private static final int TOP_BANNER_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 150;
    private static final int MATRIX_LABEL_HEIGHT = 50;
    private static final int MATRIX_LABEL_WIDTH = 400;
    private static final int MATRIX_PANEL_HEIGHT = 400;
    private static final int MATRIX_PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT_OFFSET = 175;
    private static final int TRICANT_ONE_WIDTH_OFFSET = 0;
    private static final int TRICANT_TWO_WIDTH_OFFSET = 200;
    private static final int TRICANT_THREE_WIDTH_OFFSET = 420;
    private static final int TRICANT_HEIGHT = 650;
    private static final int SCALE_LOWER_BOUND = 1;
    private static final int SCALE_UPPER_BOUND = 10;
    private static final int SCALE_DEFAULT = 1;
    private static final int SCALE_SLIDER_HEIGHT = 40;
    private static final int SCALE_SLIDER_WIDTH = 200;
    private static final int SLIDER_OFFSET = 10;

    //private colors
    private final Color LIGHT_PURPLE = new Color(216, 191, 216);
    private final Color GRAPE_JELLY = new Color(120, 100, 124);

    //program icon
    public ImageIcon icon;

    //the mainframe instance which stores all the elements in the view
    public JFrame mainFrame;

    //tricants, these store a single matrix panel as well as other elements
    public JPanel tricantOne;
    public JPanel tricantTwo;
    public JPanel tricantThree;
    public JPanel matrixOnePanel;
    public JPanel matrixTwoPanel;
    public JPanel matrixThreePanel;

    //matrix tables, these are used to render the matrices stored in our model
    public MatrixTable matrixViewOne;
    public MatrixTable matrixViewTwo;
    public MatrixTable matrixViewThree;
    public Border border;

    //buttons
    public JButton multiply;
    public JButton add;
    public JButton upsize;
    public JButton downsize;
    public JButton generateA;
    public JButton generateB;
    public JButton scaleA;
    public JButton scaleB;
    public JButton identityA;
    public JButton identityB;
    public JButton determinantA;
    public JButton determinantB;
    public JSlider scaleSliderA;
    public JSlider scaleSliderB;

    //determinant labels
    public JLabel detALabel;
    public JLabel detBLabel;
    public JLabel backdropLabel;

    //constructor method

    /**
     * Instantiates all GUI elements in the view. Takes screen resolution as input.
     * @param width width in pixels.
     * @param height height in pixels.
     */
    public MainFrame(int width, int height) {

        //backdrop setup
        //JLabel backdropLabel = new JLabel();
        //backdropLabel.setBounds(0, 0, width, height);

        //set icon image
        icon = new ImageIcon("src/images/engineIcon.png");
        this.setIconImage(icon.getImage());

        //border outline
        border = BorderFactory.createLineBorder(Color.WHITE, 3);

        //control panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds((width / 2) - 150, 140, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(border);

        //add button
        add = new JButton();
        add.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        add.setBackground(Color.WHITE);
        add.setText("Add Matrices");
        add.setFocusable(false);

        //multiply button
        multiply = new JButton();
        multiply.setBounds(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        multiply.setBackground(Color.WHITE);
        multiply.setText("Multiply Matrices");
        multiply.setFocusable(false);

        //downsize button
        downsize = new JButton();
        downsize.setBounds(0, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        downsize.setBackground(Color.WHITE);
        downsize.setText("Downsize Matrices");
        downsize.setFocusable(false);

        //upsize button
        upsize = new JButton();
        upsize.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        upsize.setBackground(Color.WHITE);
        upsize.setText("Upsize Matrices");
        upsize.setFocusable(false);

        //generate matrix a button
        generateA = new JButton();
        generateA.setBounds(0, BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        generateA.setBackground(Color.WHITE);
        generateA.setText("Generate Matrix A");
        generateA.setFocusable(false);

        //generate matrix b button
        generateB = new JButton();
        generateB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        generateB.setBackground(Color.WHITE);
        generateB.setText("Generate Matrix B");
        generateB.setFocusable(false);

        //scale matrix b button
        scaleA = new JButton();
        scaleA.setBounds(0, BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
        scaleA.setBackground(Color.WHITE);
        scaleA.setText("Scale Matrix A");
        scaleA.setFocusable(false);

        //scale matrix b button
        scaleB = new JButton();
        scaleB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
        scaleB.setBackground(Color.WHITE);
        scaleB.setText("Scale Matrix B");
        scaleB.setFocusable(false);

        //identity matrix a button
        identityA = new JButton();
        identityA.setBounds(0, BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
        identityA.setBackground(Color.WHITE);
        identityA.setText("Identity Matrix A");
        identityA.setFocusable(false);

        //identity matrix b button
        identityB = new JButton();
        identityB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
        identityB.setBackground(Color.WHITE);
        identityB.setText("Identity Matrix B");
        identityB.setFocusable(false);

        //return determinant A button
        determinantA = new JButton();
        determinantA.setBounds(0, BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT);
        determinantA.setBackground(Color.WHITE);
        determinantA.setText("Determinant A");
        determinantA.setFocusable(false);

        //return determinant B button
        determinantB = new JButton();
        determinantB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT);
        determinantB.setBackground(Color.WHITE);
        determinantB.setText("Determinant B");
        determinantB.setFocusable(false);

        //scale A slider
        scaleSliderA = new JSlider(SCALE_LOWER_BOUND, SCALE_UPPER_BOUND, SCALE_DEFAULT);
        scaleSliderA.setBounds(MATRIX_PANEL_WIDTH / 4, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SLIDER_OFFSET,
                SCALE_SLIDER_WIDTH, SCALE_SLIDER_HEIGHT);
        scaleSliderA.setPaintTicks(true);
        scaleSliderA.setPaintTrack(true);
        scaleSliderA.setPaintLabels(true);
        scaleSliderA.setMajorTickSpacing(1);

        //scale B slider
        scaleSliderB = new JSlider(SCALE_LOWER_BOUND, SCALE_UPPER_BOUND, SCALE_DEFAULT);
        scaleSliderB.setBounds(MATRIX_PANEL_WIDTH / 4, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SLIDER_OFFSET,
                SCALE_SLIDER_WIDTH, SCALE_SLIDER_HEIGHT);
        scaleSliderB.setPaintTicks(true);
        scaleSliderB.setPaintTrack(true);
        scaleSliderB.setPaintLabels(true);
        scaleSliderB.setMajorTickSpacing(1);

        //insert buttons into the control panel
        buttonPanel.add(add);
        buttonPanel.add(multiply);
        buttonPanel.add(downsize);
        buttonPanel.add(upsize);
        buttonPanel.add(generateA);
        buttonPanel.add(generateB);
        buttonPanel.add(scaleA);
        buttonPanel.add(scaleB);
        buttonPanel.add(identityA);
        buttonPanel.add(identityB);
        buttonPanel.add(determinantA);
        buttonPanel.add(determinantB);

        //create the top banner
        JLabel bannerLabel = new JLabel();
        bannerLabel.setText("Linear Algebra Engine");
        bannerLabel.setHorizontalTextPosition(JLabel.CENTER);
        bannerLabel.setVerticalTextPosition(JLabel.CENTER);
        bannerLabel.setForeground(Color.WHITE);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bannerLabel.setBackground(Color.DARK_GRAY);
        bannerLabel.setOpaque(true);
        bannerLabel.setBorder(border);
        bannerLabel.setVerticalAlignment(JLabel.CENTER);
        bannerLabel.setHorizontalAlignment(JLabel.CENTER);
        bannerLabel.setBounds((width / 2) - 150, 0, TOP_BANNER_WIDTH, TOP_BANNER_HEIGHT);

        //matrix A label
        JLabel matrixALabel = new JLabel();
        matrixALabel.setText("Matrix A");
        matrixALabel.setFont(new Font("Arial", Font.BOLD, 26));
        matrixALabel.setBounds(0, 0, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT);
        matrixALabel.setForeground(Color.WHITE);
        matrixALabel.setVerticalAlignment(JLabel.TOP);
        matrixALabel.setHorizontalAlignment(JLabel.CENTER);

        //matrix B label
        JLabel matrixBLabel = new JLabel();
        matrixBLabel.setText("Matrix B");
        matrixBLabel.setFont(new Font("Arial", Font.BOLD, 26));
        matrixBLabel.setBounds(0, 0, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT);
        matrixBLabel.setForeground(Color.WHITE);
        matrixBLabel.setVerticalAlignment(JLabel.TOP);
        matrixBLabel.setHorizontalAlignment(JLabel.CENTER);

        //determinant A label
        detALabel = new JLabel();
        detALabel.setText("Determinant: ");
        detALabel.setFont(new Font("Arial", Font.BOLD, 22));
        detALabel.setBounds(0, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SCALE_SLIDER_HEIGHT +
                2 * SLIDER_OFFSET, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT);
        detALabel.setForeground(Color.WHITE);
        detALabel.setVerticalAlignment(JLabel.TOP);
        detALabel.setHorizontalAlignment(JLabel.CENTER);

        //determinant B label
        detBLabel = new JLabel();
        detBLabel.setText("Determinant: ");
        detBLabel.setFont(new Font("Arial", Font.BOLD, 22));
        detBLabel.setBounds(0, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SCALE_SLIDER_HEIGHT +
                2 * SLIDER_OFFSET, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT);
        detBLabel.setForeground(Color.WHITE);
        detBLabel.setVerticalAlignment(JLabel.TOP);
        detBLabel.setHorizontalAlignment(JLabel.CENTER);

        //matrix A panel
        matrixOnePanel = new JPanel();
        matrixOnePanel.setBackground(Color.DARK_GRAY);
        matrixOnePanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT);

        //matrix B panel
        matrixTwoPanel = new JPanel();
        matrixTwoPanel.setBackground(Color.DARK_GRAY);
        matrixTwoPanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT);

        //matrix C panel
        matrixThreePanel = new JPanel();
        matrixThreePanel.setBackground(GRAPE_JELLY);
        matrixThreePanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT);
        matrixThreePanel.setBorder(border);

        //tricant panels
        tricantOne = new JPanel();
        tricantOne.setOpaque(true);
        tricantOne.setLayout(null);
        tricantOne.setBackground(Color.BLACK);
        tricantOne.setBounds(TRICANT_ONE_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
                MATRIX_PANEL_WIDTH, TRICANT_HEIGHT);
        tricantOne.add(matrixALabel);
        tricantOne.add(matrixOnePanel);
        tricantOne.add(scaleSliderA);
        tricantOne.add(detALabel);

        tricantTwo = new JPanel();
        tricantTwo.setOpaque(true);
        tricantTwo.setLayout(null);
        tricantTwo.setBackground(Color.BLACK);
        tricantTwo.setBounds((width / 2) - TRICANT_TWO_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
                MATRIX_PANEL_WIDTH, TRICANT_HEIGHT);
        tricantTwo.add(matrixBLabel);
        tricantTwo.add(matrixTwoPanel);
        tricantTwo.add(scaleSliderB);
        tricantTwo.add(detBLabel);

        tricantThree = new JPanel();
        tricantThree.setOpaque(true);
        tricantThree.setLayout(null);
        tricantThree.setBackground(Color.BLACK);
        tricantThree.setBounds(width - TRICANT_THREE_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
                MATRIX_PANEL_WIDTH, TRICANT_HEIGHT);
        tricantThree.add(matrixThreePanel);

        //mainframe initialization
        mainFrame = new JFrame();
        this.setTitle("Basic Linear Algebra Engine");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLayout(null); //no layout manager, dimensions to be set manually
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(bannerLabel);
        this.add(buttonPanel);
        this.add(tricantOne);
        this.add(tricantTwo);
        this.add(tricantThree);
    }

    /** Renders the first two matrices on start up. */
    public void initializeMatrices(Matrix matrixOne, Matrix matrixTwo) {
        renderMatrixOne(matrixOne);
        renderMatrixTwo(matrixTwo);
    }

    /**
     *  NOTE: The three render functions below could be rolled into one, but I have decided against it since that would
     *  require the panels and views to be parameterised and setup with getter and setter functions. For the sake of
     *  simplicity, I've decided to leave it like this.
     */

    /**
     * Renders the first matrix instance. Parameterized to allow a new matrix instance to be passed from the model.
     */
    public void renderMatrixOne(Matrix matrixOne) {
        matrixOnePanel.removeAll();
        matrixViewOne = new MatrixTable(matrixOne);
        matrixOnePanel.add(matrixViewOne.getMatrixTable());
        matrixOnePanel.repaint();
    }

    /**
     * Renders the second matrix instance. Parameterized to allow a new matrix instance to be passed from the model.
     */
    public void renderMatrixTwo(Matrix matrixTwo) {
        matrixTwoPanel.removeAll();
        matrixViewTwo = new MatrixTable(matrixTwo);
        matrixTwoPanel.add(matrixViewTwo.getMatrixTable());
        matrixTwoPanel.repaint();
    }

    /**
     * Renders the third matrix separately. If performing a calculation like addition or multiplication, the first two
     * matrices do not need to be rendered again.
     */
    public void renderProductMatrix(Matrix matrixThree) {
        matrixThreePanel.removeAll(); //clears panel contents before appending any new elements
        matrixViewThree = new MatrixTable(matrixThree);
        matrixThreePanel.add(matrixViewThree.getMatrixTable());
        matrixThreePanel.repaint();
    }

    public void clearProductMatrix() {
        matrixThreePanel.removeAll();
        matrixThreePanel.repaint();
    }
}
