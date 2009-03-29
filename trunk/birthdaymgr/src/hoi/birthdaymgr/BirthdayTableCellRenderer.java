package hoi.birthdaymgr;

import hoi.birthdaymgr.utility.LunisolarCalendar;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BirthdayTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 2914673474705829821L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String str = c.getText().trim();

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

        Pattern pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int byear = Integer.parseInt(matcher.group(1));
            int bmonth = Integer.parseInt(matcher.group(2));
            int bday = Integer.parseInt(matcher.group(3));
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            matcher = pattern.matcher(current);
            matcher.find();
            int cyear = Integer.parseInt(matcher.group(1));
            int cmonth = Integer.parseInt(matcher.group(2));
            int cday = Integer.parseInt(matcher.group(3));
            if (type.equals("农历")) {
                try {
                    LunisolarCalendar lunar = LunisolarCalendar.solar2lunar(new LunisolarCalendar(cyear, cmonth, cday));
                    cyear = lunar.getYear();
                    cmonth = lunar.getMonth();
                    cday = lunar.getDay();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int kmonth = cyear * 12 + cmonth - byear * 12 - bmonth;
            c.setText(String.format("%s%d月%d号  %d.%d岁", type, bmonth, bday, kmonth / 12, kmonth % 12));
        }

        return c;
    }
}
