package Controller;

import Model.*;
import View.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines the functionality of the Linear Algebra Engine by mediating between the model and the view. Implements the
 * ActionListener interface to process user input.
 */
public class Controller implements ActionListener, ChangeListener {

    private static final int MATRIX_GRID_UPPER_LIMIT = 10;
    private static final int MATRIX_GRID_LOWER_LIMIT = 1;

    //public instance variables
    public Model model;
    public MainFrame view;

    //constructor method
    public Controller(Model model, MainFrame view) {
        this.model = model;
        this.view = view;
        view.initializeMatrices(model.getMatrixOne(), model.getMatrixTwo());
        view.add.addActionListener(this);
        view.multiply.addActionListener(this);
        view.downsize.addActionListener(this);
        view.upsize.addActionListener(this);
        view.generateA.addActionListener(this);
        view.generateB.addActionListener(this);
        view.scaleA.addActionListener(this);
        view.scaleB.addActionListener(this);
        view.identityA.addActionListener(this);
        view.identityB.addActionListener(this);
        view.determinantA.addActionListener(this);
        view.determinantB.addActionListener(this);
        view.scaleSliderA.addChangeListener(this);
        view.scaleSliderB.addChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.add) {
            model.setMatrixThree(model.getMatrixOne().add(model.getMatrixOne(), model.getMatrixTwo()));
            view.renderProductMatrix(model.getMatrixThree());
            model.getMatrixThree().printMatrix();
        }
        else if (e.getSource() == view.multiply) {
            model.setMatrixThree(model.getMatrixOne().multiply(model.getMatrixOne(), model.getMatrixTwo()));
            view.renderProductMatrix(model.getMatrixThree());
            model.getMatrixThree().printMatrix();
        }
        else if (e.getSource() == view.downsize) {
            if(model.getSize() <= MATRIX_GRID_LOWER_LIMIT) {
                System.out.println("Matrices cannot be decremented any further.");
            } else {
                model.setSize(model.getSize() - 1);
                resetMatrixOne();
                resetMatrixTwo();
                resetIdentityMatrix();
                resetDetLabels();
                view.clearProductMatrix(); //if we change the size of our matrices then any pre-existing data in the
                //product matrix should be cleared
                System.out.println("Matrices decremented.");
            }
        }
        else if (e.getSource() == view.upsize) {
            if(model.getSize() >= MATRIX_GRID_UPPER_LIMIT) {
                System.out.println("Matrices cannot be incremented any further.");
            } else {
                model.setSize(model.getSize() + 1);
                resetMatrixOne();
                resetMatrixTwo();
                resetIdentityMatrix();
                resetDetLabels();
                view.clearProductMatrix();
                System.out.println("Matrices incremented.");
            }
        }
        else if(e.getSource() == view.generateA) {
            //call the generator's relevant method to return a new matrix
            model.setMatrixOne(new Matrix(model.getGenerator().generateRandomMatrix(model.getSize(),
                    model.getSize())));
            //update the identity matrix in our model
            model.setIdentityMatrix(new Matrix(model.getMatrixOne().getIdentity()));
            //clear any pre-existing data in the product matrix and set the instance to null
            model.setMatrixThree(null);
            view.clearProductMatrix();
            view.renderMatrixOne(model.getMatrixOne());
            view.detALabel.setText("Determinant: ");
            model.getMatrixOne().printMatrix();
        }
        else if(e.getSource() == view.generateB) {
            model.setMatrixTwo(new Matrix(model.getGenerator().generateRandomMatrix(model.getSize(),
                    model.getSize())));
            model.setIdentityMatrix(new Matrix(model.getMatrixTwo().getIdentity()));
            model.setMatrixThree(null);
            view.clearProductMatrix();
            view.renderMatrixTwo(model.getMatrixTwo());
            view.detBLabel.setText("Determinant: ");
            model.getMatrixTwo().printMatrix();
        }
        else if(e.getSource() == view.identityA) {
            if(!model.getMatrixOne().isIdentityInUse()) {
                view.renderMatrixOne(model.getIdentityMatrix());
                model.getMatrixOne().setIdentityInUse(true);
                //after this, if the Matrix A getter method is called, the identity matrix will be returned
            } else {
                model.getMatrixOne().setIdentityInUse(false);
                view.renderMatrixOne(model.getMatrixOne());
                //after this, the Matrix A getter method will go back to returning the original matrix
            }
        }
        else if(e.getSource() == view.identityB) {
            if(!model.getMatrixTwo().isIdentityInUse()) {
                view.renderMatrixTwo(model.getIdentityMatrix());
                model.getMatrixTwo().setIdentityInUse(true);
            } else {
                model.getMatrixTwo().setIdentityInUse(false);
                view.renderMatrixTwo(model.getMatrixTwo());
            }
        }
        else if (e.getSource() == view.scaleA) {
            model.setMatrixOne(model.getMatrixOne().scale(model.getMatrixOne(), model.getScaleFactorOne()));
            view.renderMatrixOne(model.getMatrixOne());
            model.setMatrixThree(null);
            view.clearProductMatrix();
            view.detALabel.setText("Determinant: ");
            System.out.println("Matrix A scaled by a factor of: " + model.getScaleFactorOne());
        }
        else if (e.getSource() == view.scaleB) {
            model.setMatrixTwo(model.getMatrixTwo().scale(model.getMatrixTwo(), model.getScaleFactorTwo()));
            view.renderMatrixTwo(model.getMatrixTwo());
            model.setMatrixThree(null);
            view.clearProductMatrix();
            view.detBLabel.setText("Determinant: ");
            System.out.println("Matrix B scaled by a factor of: " + model.getScaleFactorTwo());
        }
        else if (e.getSource() == view.determinantA) {
            model.getMatrixOne().setDeterminant(model.getMatrixOne());
            view.detALabel.setText("Determinant: " + model.getMatrixOne().getDeterminant());
            System.out.println("Matrix determinant is: " + model.getMatrixOne().getDeterminant());
        }
        else if (e.getSource() == view.determinantB) {
            model.getMatrixTwo().setDeterminant(model.getMatrixTwo());
            view.detBLabel.setText("Determinant: " + model.getMatrixTwo().getDeterminant());
            System.out.println("Matrix determinant is: " + model.getMatrixTwo().getDeterminant());
        }
    }

    /**
     * Regulates scale slider interactions.
     * @param e  a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == view.scaleSliderA) {
            model.setScaleFactorOne(view.scaleSliderA.getValue());
        }
        else if (e.getSource() == view.scaleSliderB) {
            model.setScaleFactorTwo(view.scaleSliderB.getValue());
        }
    }

    public void resetMatrixOne() {
        model.setMatrixOne(new Matrix(model.getGenerator().generateRandomMatrix(model.getSize(),
                model.getSize())));
        view.renderMatrixOne(model.getMatrixOne());
    }

    public void resetMatrixTwo() {
        model.setMatrixTwo(new Matrix(model.getGenerator().generateRandomMatrix(model.getSize(),
                model.getSize())));
        view.renderMatrixTwo(model.getMatrixTwo());
    }

    public void resetIdentityMatrix() {
        model.setIdentityMatrix(new Matrix(model.getMatrixOne().getIdentity()));
    }

    public void resetDetLabels() {
        view.detALabel.setText("Determinant: ");
        view.detBLabel.setText("Determinant: ");
    }
}
