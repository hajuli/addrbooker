package hoi.birthdaymgr;

import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class BMgrTable extends JTable {

    private static final long serialVersionUID = -1083220712281080152L;

    public TableCellEditor getCellEditor(int row, int column) {
        if (convertColumnIndexToModel(column) == BMgrTableModel.TIME_INDEX) {
            String sValue = getValueAt(row, column).toString().trim();
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(sValue);
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            if (!current.equals(sValue))
                comboBox.addItem(current);
            return new DefaultCellEditor(comboBox);
        } else {
            return super.getCellEditor(row, column);
        }
    }

    public String getToolTipText(MouseEvent e) {
        String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        int realColumnIndex = convertColumnIndexToModel(colIndex);

        if (realColumnIndex == BMgrTableModel.NOTES_INDEX) {
            tip = getValueAt(rowIndex, colIndex).toString();
        } else if (realColumnIndex == BMgrTableModel.WEBSITE_INDEX) {
            tip = getValueAt(rowIndex, colIndex).toString();
        } else if (realColumnIndex == BMgrTableModel.BIRTHDAY_INDEX || realColumnIndex == BMgrTableModel.TIME_INDEX) {
            tip = getValueAt(rowIndex, colIndex).toString();
        } else {
            tip = super.getToolTipText(e);
        }
        if (tip == null || tip.trim().equals(""))
            tip = null;
        return tip;
    }
}
