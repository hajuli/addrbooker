package hoi.birthdaymgr;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class BMgrTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5559688802287696318L;
    public static final int SELECTED_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int BIRTHDAY_INDEX = 2;
    public static final int WEBSITE_INDEX = 3;
    public static final int NOTES_INDEX = 4;
    public static final int TIME_INDEX = 5;
    public static final int HIDDEN_INDEX = 6;

    protected String[] columnNames = {
            "", "姓名", "[农/公]出生日期", "主页/博客", "备注", "好久没联系了", "" };
    protected Vector<BMgrRecord> dataVector = new Vector<BMgrRecord>();

    public BMgrTableModel() {
        load();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        return column != HIDDEN_INDEX;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
        case SELECTED_INDEX:
            return Boolean.class;
        case NAME_INDEX:
        case BIRTHDAY_INDEX:
        case TIME_INDEX:
        case WEBSITE_INDEX:
        case NOTES_INDEX:
            return String.class;
        default:
            return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        BMgrRecord record = dataVector.get(row);
        switch (column) {
        case SELECTED_INDEX:
            return record.isSelected();
        case NAME_INDEX:
            return record.getName();
        case BIRTHDAY_INDEX:
            return record.getBirthday();
        case TIME_INDEX:
            return record.getTime();
        case WEBSITE_INDEX:
            return record.getWebsite();
        case NOTES_INDEX:
            return record.getNotes();
        default:
            return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
        BMgrRecord record = dataVector.get(row);
        switch (column) {
        case SELECTED_INDEX:
            record.setSelected((Boolean) value);
            break;
        case NAME_INDEX:
            record.setName((String) value);
            break;
        case BIRTHDAY_INDEX:
            record.setBirthday((String) value);
            break;
        case TIME_INDEX:
            record.setTime((String) value);
            break;
        case WEBSITE_INDEX:
            record.setWebsite((String) value);
            break;
        case NOTES_INDEX:
            record.setNotes((String) value);
            break;

        default:
            System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public boolean hasEmptyLastRow() {
        if (dataVector.size() == 0)
            return false;
        BMgrRecord record = dataVector.get(dataVector.size() - 1);
        return record.isEmptyRecord();
    }

    public void addEmptyRow() {
        dataVector.add(new BMgrRecord());
        fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }

    public void deleteRows(int[] rows) {
        Arrays.sort(rows);
        for (int i = rows.length - 1; i >= 0; i--)
            dataVector.remove(rows[i]);
        fireTableDataChanged();
        if (dataVector.size() == 0)
            addEmptyRow();
    }

    public void deleteSelectedRows() {
        Vector<Integer> vector = new Vector<Integer>();
        int cnt = 0;
        for (BMgrRecord record : dataVector) {
            if (record.isSelected())
                vector.add(cnt);
            cnt++;
        }

        int[] array = new int[vector.size()];
        cnt = 0;
        for (Integer integer : vector) {
            array[cnt] = integer;
            cnt++;
        }
        deleteRows(array);
    }

    public void reload() {
        dataVector.removeAllElements();
        load();
    }

    public void load() {
        int firstRow = dataVector.size();
        BMgrIO.load(dataVector);
        int lastRow = dataVector.size() - 1;
        if (lastRow >= firstRow)
            fireTableRowsInserted(firstRow, lastRow);
    }

    public void save() {
        BMgrIO.save(dataVector);
    }
}
