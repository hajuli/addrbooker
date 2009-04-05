package hoi.birthdaymgr;

import hoi.birthdaymgr.utility.BaseCalendar;
import hoi.birthdaymgr.utility.LunarCalendar;
import hoi.birthdaymgr.utility.SolarCalendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class BirthdayTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 2914673474705829821L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String str = comp.getText().trim();

        String type = null;
        for (String header : new String[] {
                "公历", "公", "阳历", "阳" })
            if (str.startsWith(header)) {
                str = str.substring(header.length()).trim();
                type = "公历";
                break;
            }
        if (type == null)
            for (String header : new String[] {
                    "农历", "农", "阴历", "阴" })
                if (str.startsWith(header)) {
                    str = str.substring(header.length()).trim();
                    type = "农历";
                    break;
                }
        if (type == null)
            type = "公历";

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

        BaseCalendar birthday, today;
        while (true) {
            if (str.trim().equals(""))
                break;//comp.setBackground(Color.WHITE);
            else
                comp.setBackground(Color.GRAY);
            if (type.equals("农历")) {
                try {
                    birthday = new LunarCalendar(str);
                } catch (Exception ignore) {
                    break;
                }
                today = LunarCalendar.today();
            } else {
                try {
                    birthday = new SolarCalendar(str);
                } catch (Exception ignore) {
                    break;
                }
                today = SolarCalendar.today();
            }
            int kmonth = today.getYear() * 12 + today.getMonth() - //
                    birthday.getYear() * 12 - birthday.getMonth();

            int len = BaseCalendar.getWaitDays(birthday, today);
            boolean yesterday = false;
            if (len < 0)
                comp.setBackground(Color.GRAY);
            else if (len == 0)
                comp.setBackground(Color.RED);
            else if (len <= 2)
                comp.setBackground(Color.YELLOW);
            else if (len <= 5)
                comp.setBackground(Color.GREEN);
            else {
                try {
                    if (BaseCalendar.getWaitDays(birthday.next(), today) == 0) {
                        comp.setBackground(Color.PINK);
                        yesterday = true;
                    } else
                        comp.setBackground(Color.CYAN);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (yesterday)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 昨天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            else if (len == 0)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 今天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            else if (len == 1)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 明天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            else if (len == 2)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 后天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            else if (len > 0)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 %d天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12, len));
            else
                comp.setText(String.format("%s%d月%d号 %d.%d岁 出错", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            break;
        }
        return comp;
    }
}
