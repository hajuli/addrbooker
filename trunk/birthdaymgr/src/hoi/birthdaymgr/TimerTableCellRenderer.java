package hoi.birthdaymgr;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TimerTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 2914673474705829821L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String str = comp.getText().trim();

        Pattern pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int tyear = Integer.parseInt(matcher.group(1));
            int tmonth = Integer.parseInt(matcher.group(2));
            int tday = Integer.parseInt(matcher.group(3));
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            matcher = pattern.matcher(current);
            matcher.find();
            int cyear = Integer.parseInt(matcher.group(1));
            int cmonth = Integer.parseInt(matcher.group(2));
            int cday = Integer.parseInt(matcher.group(3));
            int kday = cyear * 365 + cmonth * 30 + cday - tyear * 365 - tmonth * 30 - tday; // 大概的算一下，不用那么精确
            if (kday == 0)
                comp.setText(String.format("今天"));
            else if (kday == 1)
                comp.setText(String.format("昨天"));
            else if (kday / 30 == 0)
                comp.setText(String.format("%d天前", kday % 30));
            else if (kday % 30 == 0)
                comp.setText(String.format("%d个月前", kday / 30));
            else
                comp.setText(String.format("%d个月%d天前", kday / 30, kday % 30));
        }

        return comp;
    }
}
