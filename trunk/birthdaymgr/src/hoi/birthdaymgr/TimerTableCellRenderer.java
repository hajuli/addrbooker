package hoi.birthdaymgr;

import hoi.birthdaymgr.utility.BaseCalendar;
import hoi.birthdaymgr.utility.SolarCalendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class TimerTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 2914673474705829821L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String str = comp.getText().trim();

        // -----------------------------------------------------------------------
        Color bg = null;

        JTable.DropLocation dropLocation = table.getDropLocation();
        if (dropLocation != null && !dropLocation.isInsertRow() && !dropLocation.isInsertColumn() && //
                dropLocation.getRow() == row && dropLocation.getColumn() == column) {
            bg = UIManager.getColor("Table.dropCellBackground");
            isSelected = true;
        }

        if (isSelected) {
            super.setBackground(bg == null ? table.getSelectionBackground() : bg);
        } else {
            super.setBackground(table.getBackground());
        }

        if (hasFocus) {
            if (!isSelected && table.isCellEditable(row, column)) {
                Color col;
                col = UIManager.getColor("Table.focusCellBackground");
                if (col != null) {
                    super.setBackground(col);
                }
            }
        } else {
        }
        // -----------------------------------------------------------------------

        while (true) {
            if (str.trim().equals(""))
                break;//comp.setBackground(Color.WHITE);
            else
                comp.setBackground(Color.GRAY);

            int kday = 0;
            try {
                SolarCalendar history = new SolarCalendar(str);
                SolarCalendar today = SolarCalendar.today();
                kday = BaseCalendar.getWaitDays2(history, today);
            } catch (Exception ignore) {
                break;
            }

            if (kday < 0)
                comp.setBackground(Color.GRAY);
            else if (kday >= 30 * 3 * 0.8)
                comp.setBackground(Color.RED);
            else if (kday >= 30 * 0.8) //《职业生涯管理》张再生, 经济管理出版社 第1版 -- 183页
                comp.setBackground(Color.YELLOW);
            else
                comp.setBackground(Color.CYAN);

            if (kday == 0)
                comp.setText(String.format("今天"));
            else if (kday == 1)
                comp.setText(String.format("昨天"));
            else if (kday / 30 == 0)
                comp.setText(String.format("%d天前", kday % 30));
            else if (kday % 30 / 7 == 0)
                comp.setText(String.format("%d个月前", kday / 30));
            else
                comp.setText(String.format("%d个月%d周前", kday / 30, kday % 30 / 7));
            break;
        }

        return comp;
    }
}
