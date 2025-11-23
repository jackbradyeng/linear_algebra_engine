package View;

import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Defines the appearance of cells rendered in the Matrix Table class.
 */
public class MatrixCellRenderer extends DefaultTableCellRenderer {

    public MatrixCellRenderer() {}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(!isSelected) {
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }

        c.setForeground(Color.WHITE);
        c.setBackground(Color.DARK_GRAY);
        c.setFont(new Font("Arial", Font.BOLD, 16));
        return c;
    }
}
