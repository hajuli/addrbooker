package hoi.birthdaymgr;

import hoi.birthdaymgr.utility.BaseCalendar;
import hoi.birthdaymgr.utility.LunarCalendar;
import hoi.birthdaymgr.utility.SolarCalendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
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

        BaseCalendar birthday, today;
        while (true) {
            if (str.trim().equals(""))
                comp.setBackground(Color.WHITE);
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

            int len = getWaitDays(birthday, today);
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
                    if (getWaitDays(birthday.next(), today) == 0) {
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
            else if (len > 0)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 %d天", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12, len));
            else if (len == 0)
                comp.setText(String.format("%s%d月%d号 %d.%d岁 生日", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            else
                comp.setText(String.format("%s%d月%d号 %d.%d岁 出错", type, //
                        birthday.getMonth(), birthday.getDay(), //
                        kmonth / 12, kmonth % 12));
            break;
        }
        return comp;
    }

    /**
     * 特殊处理：公历2月29日生日，以及农历12月30日生日 等
     * 
     * @param birthday_
     * @param today_
     * @return
     */
    private static int getWaitDays(final BaseCalendar birthday_, final BaseCalendar today_) {
        BaseCalendar birthday = birthday_.copy();
        BaseCalendar today = today_.copy();

        birthday.setYear(today.getYear());
        int k = birthday.toString2().compareTo(today.toString2());
        if (k < 0)
            birthday.setYear(today.getYear() + 1);
        else if (k == 0)
            return 0;
        else if (k > 0)
            ;

        try {
            String b = birthday.toString2();
            for (int cnt = 0; cnt < 400; cnt++) {
                BaseCalendar next = today.next();
                String a = today.toString2();
                String c = next.toString2();
                if (a.compareTo(b) <= 0 && c.compareTo(b) > 0)
                    return cnt;
                today = next;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
