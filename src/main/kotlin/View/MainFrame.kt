package View

import main.kotlin.Model.Matrix
import java.awt.Color
import java.awt.Font
import javax.swing.*
import javax.swing.border.Border

/**
 * Defines the GUI for the Linear Algebra Engine.
 */
class MainFrame(width: Int, height: Int) : JFrame() {
    //private colors
    private val LIGHT_PURPLE = Color(216, 191, 216)
    private val GRAPE_JELLY = Color(120, 100, 124)

    //set icon image
    var icon: ImageIcon? = ImageIcon("src/images/engineIcon.png")

    //the mainframe instance which stores all the elements in the view
    var mainFrame: JFrame?

    //tricants, these store a single matrix panel as well as other elements
    var tricantOne: JPanel
    var tricantTwo: JPanel
    var tricantThree: JPanel
    var matrixOnePanel: JPanel
    var matrixTwoPanel: JPanel
    var matrixThreePanel: JPanel

    //matrix tables, these are used to render the matrices stored in our model
    var matrixViewOne: MatrixTable? = null
    var matrixViewTwo: MatrixTable? = null
    var matrixViewThree: MatrixTable? = null
    var border: Border?

    //buttons
    var multiply: JButton
    var add: JButton
    var upsize: JButton
    var downsize: JButton
    var generateA: JButton
    var generateB: JButton
    var scaleA: JButton
    var scaleB: JButton
    var identityA: JButton
    var identityB: JButton
    var determinantA: JButton
    var determinantB: JButton
    var scaleSliderA: JSlider
    var scaleSliderB: JSlider
    var transposeA: JButton
    var transposeB: JButton

    //determinant labels
    var detALabel: JLabel
    var detBLabel: JLabel
    var backdropLabel: JLabel? = null

    /**
     * Instantiates all GUI elements in the view. Takes screen resolution as input.
     * @param width width in pixels.
     * @param height height in pixels.
     */
    init {
        this.iconImage = icon!!.getImage()

        //border outline
        border = BorderFactory.createLineBorder(Color.WHITE, 3)

        //control panel
        val buttonPanel = JPanel()
        buttonPanel.setLayout(null)
        buttonPanel.setBounds((width / 2) - 150, 140, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT)
        buttonPanel.setBackground(Color.BLACK)
        buttonPanel.setBorder(border)

        //add button
        add = JButton()
        add.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT)
        add.setBackground(Color.WHITE)
        add.setText("Add Matrices")
        add.setFocusable(false)

        //multiply button
        multiply = JButton()
        multiply.setBounds(BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT)
        multiply.setBackground(Color.WHITE)
        multiply.setText("Multiply Matrices")
        multiply.setFocusable(false)

        //downsize button
        downsize = JButton()
        downsize.setBounds(0, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT)
        downsize.setBackground(Color.WHITE)
        downsize.setText("Downsize Matrices")
        downsize.setFocusable(false)

        //upsize button
        upsize = JButton()
        upsize.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT)
        upsize.setBackground(Color.WHITE)
        upsize.setText("Upsize Matrices")
        upsize.setFocusable(false)

        //generate matrix a button
        generateA = JButton()
        generateA.setBounds(0, BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
        generateA.setBackground(Color.WHITE)
        generateA.setText("Generate Matrix A")
        generateA.setFocusable(false)

        //generate matrix b button
        generateB = JButton()
        generateB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
        generateB.setBackground(Color.WHITE)
        generateB.setText("Generate Matrix B")
        generateB.setFocusable(false)

        //scale matrix b button
        scaleA = JButton()
        scaleA.setBounds(0, BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
        scaleA.setBackground(Color.WHITE)
        scaleA.setText("Scale Matrix A")
        scaleA.setFocusable(false)

        //scale matrix b button
        scaleB = JButton()
        scaleB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
        scaleB.setBackground(Color.WHITE)
        scaleB.setText("Scale Matrix B")
        scaleB.setFocusable(false)

        //identity matrix a button
        identityA = JButton()
        identityA.setBounds(0, BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT)
        identityA.setBackground(Color.WHITE)
        identityA.setText("Identity Matrix A")
        identityA.setFocusable(false)

        //identity matrix b button
        identityB = JButton()
        identityB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT)
        identityB.setBackground(Color.WHITE)
        identityB.setText("Identity Matrix B")
        identityB.setFocusable(false)

        //return determinant A button
        determinantA = JButton()
        determinantA.setBounds(0, BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT)
        determinantA.setBackground(Color.WHITE)
        determinantA.setText("Determinant A")
        determinantA.setFocusable(false)

        //return determinant B button
        determinantB = JButton()
        determinantB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT)
        determinantB.setBackground(Color.WHITE)
        determinantB.setText("Determinant B")
        determinantB.setFocusable(false)

        //transpose A button
        transposeA = JButton()
        transposeA.setBounds(0, BUTTON_HEIGHT * 6, BUTTON_WIDTH, BUTTON_HEIGHT)
        transposeA.setBackground(Color.WHITE)
        transposeA.setText("Transpose A")
        transposeA.setFocusable(false)

        //transpose B button
        transposeB = JButton()
        transposeB.setBounds(BUTTON_WIDTH, BUTTON_HEIGHT * 6, BUTTON_WIDTH, BUTTON_HEIGHT)
        transposeB.setBackground(Color.WHITE)
        transposeB.setText("Transpose B")
        transposeB.setFocusable(false)

        //scale A slider
        scaleSliderA = JSlider(SCALE_LOWER_BOUND, SCALE_UPPER_BOUND, SCALE_DEFAULT)
        scaleSliderA.setBounds(
            MATRIX_PANEL_WIDTH / 4, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SLIDER_OFFSET,
            SCALE_SLIDER_WIDTH, SCALE_SLIDER_HEIGHT
        )
        scaleSliderA.setPaintTicks(true)
        scaleSliderA.setPaintTrack(true)
        scaleSliderA.setPaintLabels(true)
        scaleSliderA.setMajorTickSpacing(1)

        //scale B slider
        scaleSliderB = JSlider(SCALE_LOWER_BOUND, SCALE_UPPER_BOUND, SCALE_DEFAULT)
        scaleSliderB.setBounds(
            MATRIX_PANEL_WIDTH / 4, MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SLIDER_OFFSET,
            SCALE_SLIDER_WIDTH, SCALE_SLIDER_HEIGHT
        )
        scaleSliderB.setPaintTicks(true)
        scaleSliderB.setPaintTrack(true)
        scaleSliderB.setPaintLabels(true)
        scaleSliderB.setMajorTickSpacing(1)

        //insert buttons into the control panel
        buttonPanel.add(add)
        buttonPanel.add(multiply)
        buttonPanel.add(downsize)
        buttonPanel.add(upsize)
        buttonPanel.add(generateA)
        buttonPanel.add(generateB)
        buttonPanel.add(scaleA)
        buttonPanel.add(scaleB)
        buttonPanel.add(identityA)
        buttonPanel.add(identityB)
        buttonPanel.add(determinantA)
        buttonPanel.add(determinantB)
        buttonPanel.add(transposeA)
        buttonPanel.add(transposeB)

        //create the top banner
        val bannerLabel = JLabel()
        bannerLabel.setText("Linear Algebra Engine")
        bannerLabel.setHorizontalTextPosition(JLabel.CENTER)
        bannerLabel.setVerticalTextPosition(JLabel.CENTER)
        bannerLabel.setForeground(Color.WHITE)
        bannerLabel.setFont(Font("Arial", Font.BOLD, 20))
        bannerLabel.setBackground(Color.DARK_GRAY)
        bannerLabel.setOpaque(true)
        bannerLabel.setBorder(border)
        bannerLabel.setVerticalAlignment(JLabel.CENTER)
        bannerLabel.setHorizontalAlignment(JLabel.CENTER)
        bannerLabel.setBounds((width / 2) - 150, 0, TOP_BANNER_WIDTH, TOP_BANNER_HEIGHT)

        //matrix A label
        val matrixALabel = JLabel()
        matrixALabel.setText("Matrix A")
        matrixALabel.setFont(Font("Arial", Font.BOLD, 26))
        matrixALabel.setBounds(0, 0, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT)
        matrixALabel.setForeground(Color.WHITE)
        matrixALabel.setVerticalAlignment(JLabel.TOP)
        matrixALabel.setHorizontalAlignment(JLabel.CENTER)

        //matrix B label
        val matrixBLabel = JLabel()
        matrixBLabel.setText("Matrix B")
        matrixBLabel.setFont(Font("Arial", Font.BOLD, 26))
        matrixBLabel.setBounds(0, 0, MATRIX_LABEL_WIDTH, MATRIX_LABEL_HEIGHT)
        matrixBLabel.setForeground(Color.WHITE)
        matrixBLabel.setVerticalAlignment(JLabel.TOP)
        matrixBLabel.setHorizontalAlignment(JLabel.CENTER)

        //determinant A label
        detALabel = JLabel()
        detALabel.setText("Determinant: ")
        detALabel.setFont(Font("Arial", Font.BOLD, 22))
        detALabel.setBounds(
            0,
            MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SCALE_SLIDER_HEIGHT + 2 * SLIDER_OFFSET,
            MATRIX_LABEL_WIDTH,
            MATRIX_LABEL_HEIGHT
        )
        detALabel.setForeground(Color.WHITE)
        detALabel.setVerticalAlignment(JLabel.TOP)
        detALabel.setHorizontalAlignment(JLabel.CENTER)

        //determinant B label
        detBLabel = JLabel()
        detBLabel.setText("Determinant: ")
        detBLabel.setFont(Font("Arial", Font.BOLD, 22))
        detBLabel.setBounds(
            0,
            MATRIX_LABEL_HEIGHT + MATRIX_PANEL_HEIGHT + SCALE_SLIDER_HEIGHT + 2 * SLIDER_OFFSET,
            MATRIX_LABEL_WIDTH,
            MATRIX_LABEL_HEIGHT
        )
        detBLabel.setForeground(Color.WHITE)
        detBLabel.setVerticalAlignment(JLabel.TOP)
        detBLabel.setHorizontalAlignment(JLabel.CENTER)

        //matrix A panel
        matrixOnePanel = JPanel()
        matrixOnePanel.setBackground(Color.DARK_GRAY)
        matrixOnePanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT)

        //matrix B panel
        matrixTwoPanel = JPanel()
        matrixTwoPanel.setBackground(Color.DARK_GRAY)
        matrixTwoPanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT)

        //matrix C panel
        matrixThreePanel = JPanel()
        matrixThreePanel.setBackground(GRAPE_JELLY)
        matrixThreePanel.setBounds(0, MATRIX_LABEL_HEIGHT, MATRIX_PANEL_WIDTH, MATRIX_PANEL_HEIGHT)
        matrixThreePanel.setBorder(border)

        //tricant panels
        tricantOne = JPanel()
        tricantOne.setOpaque(true)
        tricantOne.setLayout(null)
        tricantOne.setBackground(Color.BLACK)
        tricantOne.setBounds(
            TRICANT_ONE_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
            MATRIX_PANEL_WIDTH, TRICANT_HEIGHT
        )
        tricantOne.add(matrixALabel)
        tricantOne.add(matrixOnePanel)
        tricantOne.add(scaleSliderA)
        tricantOne.add(detALabel)

        tricantTwo = JPanel()
        tricantTwo.setOpaque(true)
        tricantTwo.setLayout(null)
        tricantTwo.setBackground(Color.BLACK)
        tricantTwo.setBounds(
            (width / 2) - TRICANT_TWO_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
            MATRIX_PANEL_WIDTH, TRICANT_HEIGHT
        )
        tricantTwo.add(matrixBLabel)
        tricantTwo.add(matrixTwoPanel)
        tricantTwo.add(scaleSliderB)
        tricantTwo.add(detBLabel)

        tricantThree = JPanel()
        tricantThree.setOpaque(true)
        tricantThree.setLayout(null)
        tricantThree.setBackground(Color.BLACK)
        tricantThree.setBounds(
            width - TRICANT_THREE_WIDTH_OFFSET, (height / 2) - PANEL_HEIGHT_OFFSET,
            MATRIX_PANEL_WIDTH, TRICANT_HEIGHT
        )
        tricantThree.add(matrixThreePanel)

        //mainframe initialization
        mainFrame = JFrame()
        this.setTitle("Basic Linear Algebra Engine")
        this.setDefaultCloseOperation(EXIT_ON_CLOSE)
        this.setResizable(false)
        this.setSize(width, height)
        this.layout = null //no layout manager, dimensions to be set manually
        this.isVisible = true
        this.contentPane.setBackground(Color.BLACK)
        this.add(bannerLabel)
        this.add(buttonPanel)
        this.add(tricantOne)
        this.add(tricantTwo)
        this.add(tricantThree)
    }

    /** Renders the first two matrices on start up.  */
    fun initializeMatrices(matrixOne: Matrix?, matrixTwo: Matrix?) {
        renderMatrixOne(matrixOne)
        renderMatrixTwo(matrixTwo)
    }

    /**
     * Renders the first matrix instance. Parameterized to allow a new matrix instance to be passed from the model.
     */
    fun renderMatrixOne(matrixOne: Matrix?) {
        matrixOnePanel.removeAll()
        matrixViewOne = MatrixTable(matrixOne)
        matrixOnePanel.add(matrixViewOne!!.matrixTable)
        matrixOnePanel.repaint()
    }

    /**
     * Renders the second matrix instance. Parameterized to allow a new matrix instance to be passed from the model.
     */
    fun renderMatrixTwo(matrixTwo: Matrix?) {
        matrixTwoPanel.removeAll()
        matrixViewTwo = MatrixTable(matrixTwo)
        matrixTwoPanel.add(matrixViewTwo!!.matrixTable)
        matrixTwoPanel.repaint()
    }

    /**
     * Renders the third matrix separately. If performing a calculation like addition or multiplication, the first two
     * matrices do not need to be rendered again.
     */
    fun renderProductMatrix(matrixThree: Matrix?) {
        matrixThreePanel.removeAll() //clears panel contents before appending any new elements
        matrixViewThree = MatrixTable(matrixThree)
        matrixThreePanel.add(matrixViewThree!!.matrixTable)
        matrixThreePanel.repaint()
    }

    fun clearProductMatrix() {
        matrixThreePanel.removeAll()
        matrixThreePanel.repaint()
    }

    companion object {
        //private constants
        private const val CONTROL_PANEL_HEIGHT = 210
        private const val CONTROL_PANEL_WIDTH = 300
        private const val TOP_BANNER_HEIGHT = 100
        private const val TOP_BANNER_WIDTH = 300
        private const val BUTTON_HEIGHT = 30
        private const val BUTTON_WIDTH = 150
        private const val MATRIX_LABEL_HEIGHT = 50
        private const val MATRIX_LABEL_WIDTH = 400
        private const val MATRIX_PANEL_HEIGHT = 400
        private const val MATRIX_PANEL_WIDTH = 400
        private const val PANEL_HEIGHT_OFFSET = 175
        private const val TRICANT_ONE_WIDTH_OFFSET = 0
        private const val TRICANT_TWO_WIDTH_OFFSET = 200
        private const val TRICANT_THREE_WIDTH_OFFSET = 420
        private const val TRICANT_HEIGHT = 650
        private const val SCALE_LOWER_BOUND = 1
        private const val SCALE_UPPER_BOUND = 10
        private const val SCALE_DEFAULT = 1
        private const val SCALE_SLIDER_HEIGHT = 40
        private const val SCALE_SLIDER_WIDTH = 200
        private const val SLIDER_OFFSET = 10
    }
}
