package View;

import Model.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Utilizes a JTable in conjunction with other data structures, such as a Cell Renderer and a Table Model, to render
 * Matrices from the Model to the screen.
 */
public class MatrixTable {

    public JTable matrixTable;
    private MatrixCellRenderer renderer;
    private TableModel matrixModel;
    private Border border;

    public MatrixTable(Matrix matrix) {

        matrixModel = new MatrixTableModel(matrix);
        matrixTable = new JTable(matrixModel);
        renderer = new MatrixCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        border = BorderFactory.createLineBorder(Color.WHITE, 3);
        matrixTable.setBounds(0, 0, 400, 400);
        matrixTable.setPreferredSize(new Dimension(400, 400));
        matrixTable.setDefaultRenderer(Object.class, renderer);
        matrixTable.doLayout(); //automatically scales the table columns to fit the designated space
        matrixTable.setRowHeight(400 / matrix.getRows());
        matrixTable.setGridColor(Color.WHITE);
        matrixTable.setBorder(border);
        matrixTable.setOpaque(true);
        matrixTable.setVisible(true);
    }

}
