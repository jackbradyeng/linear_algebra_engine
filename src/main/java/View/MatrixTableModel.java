package View;

import main.kotlin.Model.Matrix;
import javax.swing.table.AbstractTableModel;

public class MatrixTableModel extends AbstractTableModel {

    private Matrix matrix;

    //constructor method
    public MatrixTableModel(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public int getRowCount() {
        return matrix.getRows();
    }

    @Override
    public int getColumnCount() {
        return matrix.getColumns();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrix.getMatrix().get(rowIndex).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //allow all cells in the matrix table to be edited
        return true;
    }

    @Override
    public void setValueAt(Object userInput, int rowIndex, int colIndex) {
        try {
            //parse the user's input to an integer
            Double newDouble = Double.parseDouble(userInput.toString());

            //update the underlying model
            matrix.getMatrix().get(rowIndex).set(colIndex, newDouble);

            //tells the JTable that the cell has changed so that it can be repainted
            fireTableCellUpdated(rowIndex, colIndex);

        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter an integer.");
        }
    }
}
