package Controller

import Model.Matrix
import Model.Model
import View.MainFrame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener

/**
 * Defines the functionality of the Linear Algebra Engine by mediating between the model and the view. Implements the
 * ActionListener interface to process user input.
 */
class Controller(private val model: Model, private val view: MainFrame) : ActionListener, ChangeListener {

    //constructor method
    init {
        view.initializeMatrices(model.matrixOne, model.matrixTwo)
        view.add.addActionListener(this)
        view.multiply.addActionListener(this)
        view.downsize.addActionListener(this)
        view.upsize.addActionListener(this)
        view.generateA.addActionListener(this)
        view.generateB.addActionListener(this)
        view.scaleA.addActionListener(this)
        view.scaleB.addActionListener(this)
        view.identityA.addActionListener(this)
        view.identityB.addActionListener(this)
        view.determinantA.addActionListener(this)
        view.determinantB.addActionListener(this)
        view.scaleSliderA.addChangeListener(this)
        view.scaleSliderB.addChangeListener(this)
        view.transposeA.addActionListener(this)
        view.transposeB.addActionListener(this)
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.getSource() == view.add) {
            addMatrices()
        } else if (e.getSource() == view.multiply) {
            multiplyMatrices()
        } else if (e.getSource() == view.downsize) {
            decrementMatrices()
        } else if (e.getSource() == view.upsize) {
            incrementMatrices()
        } else if (e.getSource() == view.generateA) {
            generateMatrixA()
        } else if (e.getSource() == view.generateB) {
            generateMatrixB()
        } else if (e.getSource() == view.identityA) {
            renderIdentityA()
        } else if (e.getSource() == view.identityB) {
            renderIdentityB()
        } else if (e.getSource() == view.scaleA) {
            scaleMatrixA()
        } else if (e.getSource() == view.scaleB) {
            scaleMatrixB()
        } else if (e.getSource() == view.determinantA) {
            determinantMatrixA()
        } else if (e.getSource() == view.determinantB) {
            determinantMatrixB()
        } else if (e.getSource() == view.transposeA) {
            transposeMatrixA()
        } else if (e.getSource() == view.transposeB) {
            transposeMatrixB()
        }
    }

    /** regulates scale slider interactions.  */
    override fun stateChanged(e: ChangeEvent) {
        if (e.getSource() === view.scaleSliderA) {
            model.scaleFactorOne = view.scaleSliderA.getValue()
        } else if (e.getSource() === view.scaleSliderB) {
            model.scaleFactorTwo = view.scaleSliderB.getValue()
        }
    }

    fun resetMatrixOne() {
        model.matrixOne = Matrix(
            model.generator.generateRandomMatrix(
                model.size,
                model.size
            )
        )
        view.renderMatrixOne(model.matrixOne)
    }

    fun resetMatrixTwo() {
        model.matrixTwo = Matrix(
            model.generator.generateRandomMatrix(
                model.size,
                model.size
            )
        )
        view.renderMatrixTwo(model.matrixTwo)
    }

    fun addMatrices() {
        model.matrixThree = model.matrixOne.add(model.matrixOne, model.matrixTwo)
        view.renderProductMatrix(model.matrixThree)
        model.matrixThree!!.printMatrix()
    }

    fun multiplyMatrices() {
        model.matrixThree = model.matrixOne.multiply(model.matrixOne, model.matrixTwo)
        view.renderProductMatrix(model.matrixThree)
        model.matrixThree!!.printMatrix()
    }

    fun decrementMatrices() {
        if (model.size <= MATRIX_GRID_LOWER_LIMIT) {
            println("Matrices cannot be decremented any further.")
        } else {
            model.size -= 1
            resetMatrixOne()
            resetMatrixTwo()
            resetIdentityMatrix()
            resetDetLabels()
            view.clearProductMatrix() //if we change the size of our matrices then any pre-existing data in the
            //product matrix should be cleared
            println("Matrices decremented.")
        }
    }

    fun incrementMatrices() {
        if (model.size >= MATRIX_GRID_UPPER_LIMIT) {
            println("Matrices cannot be incremented any further.")
        } else {
            model.size += 1
            resetMatrixOne()
            resetMatrixTwo()
            resetIdentityMatrix()
            resetDetLabels()
            view.clearProductMatrix()
            println("Matrices incremented.")
        }
    }

    fun generateMatrixA() {
        //call the generator's relevant method to return a new matrix
        model.matrixOne = Matrix(
            model.generator.generateRandomMatrix(
                model.size,
                model.size
            )
        )
        //update the identity matrix in our model
        model.identityMatrix = Matrix(model.matrixOne.identity)
        //clear any pre-existing data in the product matrix and set the instance to null
        model.matrixThree = null
        view.clearProductMatrix()
        view.renderMatrixOne(model.matrixOne)
        view.detALabel.setText("Determinant: ")
        model.matrixOne.printMatrix()
    }

    fun generateMatrixB() {
        model.matrixTwo = Matrix(
            model.generator.generateRandomMatrix(
                model.size,
                model.size
            )
        )
        model.identityMatrix = Matrix(model.matrixTwo.identity)
        model.matrixThree = null
        view.clearProductMatrix()
        view.renderMatrixTwo(model.matrixTwo)
        view.detBLabel.setText("Determinant: ")
        model.matrixTwo.printMatrix()
    }

    fun renderIdentityA() {
        if (!model.matrixOne.identityInUse) {
            view.renderMatrixOne(model.identityMatrix)
            model.matrixOne.identityInUse = true
        } else {
            model.matrixOne.identityInUse = false
            view.renderMatrixOne(model.matrixOne)
        }
    }

    fun renderIdentityB() {
        if (!model.matrixTwo.identityInUse) {
            view.renderMatrixTwo(model.identityMatrix)
            model.matrixTwo.identityInUse = true
        } else {
            model.matrixTwo.identityInUse = false
            view.renderMatrixTwo(model.matrixTwo)
        }
    }

    fun scaleMatrixA() {
        model.matrixOne = model.matrixOne.scale(model.matrixOne, model.scaleFactorOne)
        view.renderMatrixOne(model.matrixOne)
        model.matrixThree = null
        view.clearProductMatrix()
        view.detALabel.setText("Determinant: ")
        println("Matrix A scaled by a factor of: " + model.scaleFactorOne)
    }

    fun scaleMatrixB() {
        model.matrixTwo = model.matrixTwo.scale(model.matrixTwo, model.scaleFactorTwo)
        view.renderMatrixTwo(model.matrixTwo)
        model.matrixThree = null
        view.clearProductMatrix()
        view.detBLabel.setText("Determinant: ")
        println("Matrix B scaled by a factor of: " + model.scaleFactorTwo)
    }

    fun determinantMatrixA() {
        model.matrixOne.setDeterminant(model.matrixOne)
        view.detALabel.setText("Determinant: " + model.matrixOne.determinant)
        println("Matrix determinant is: " + model.matrixOne.determinant)
    }

    fun determinantMatrixB() {
        model.matrixTwo.setDeterminant(model.matrixTwo)
        view.detBLabel.setText("Determinant: " + model.matrixTwo.determinant)
        println("Matrix determinant is: " + model.matrixTwo.determinant)
    }

    fun transposeMatrixA() {
        model.matrixOne = model.matrixOne.transpose(model.matrixOne)
        view.renderMatrixOne(model.matrixOne)
        model.matrixThree = null
        view.clearProductMatrix()
        println("Matrix A transposed.")
    }

    fun transposeMatrixB() {
        model.matrixTwo = model.matrixTwo.transpose(model.matrixTwo)
        view.renderMatrixTwo(model.matrixTwo)
        model.matrixThree = null
        view.clearProductMatrix()
        println("Matrix B transposed.")
    }

    fun resetIdentityMatrix() {
        model.identityMatrix = Matrix(model.matrixOne.identity)
    }

    fun resetDetLabels() {
        view.detALabel.setText("Determinant: ")
        view.detBLabel.setText("Determinant: ")
    }

    companion object {
        private const val MATRIX_GRID_UPPER_LIMIT = 10
        private const val MATRIX_GRID_LOWER_LIMIT = 2
    }
}
