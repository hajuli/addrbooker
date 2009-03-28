package hoi.bm;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class BMTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5559688802287696318L;
    public static final int NAME_INDEX = 0;
    public static final int TYPE_INDEX = 1;
    public static final int BIRTHDAY_INDEX = 2;
    public static final int AGE_INDEX = 3;
    public static final int TIMER_INDEX = 4;
    public static final int WEBSITE_INDEX = 5;
    public static final int NOTES_INDEX = 6;
    public static final int HIDDEN_INDEX = 7;

    protected String[] columnNames = {
            "姓名", "类型", "生日", "年龄", "时间", "博客", "备注", "" };
    protected Vector<BMRecord> dataVector = new Vector<BMRecord>();

    public BMTableModel() {
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        if (column == HIDDEN_INDEX)
            return false;
        else
            return true;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
        case NAME_INDEX:
        case TYPE_INDEX:
        case BIRTHDAY_INDEX:
        case AGE_INDEX:
        case TIMER_INDEX:
        case WEBSITE_INDEX:
        case NOTES_INDEX:
            return String.class;
        default:
            return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        BMRecord record = dataVector.get(row);
        switch (column) {
        case NAME_INDEX:
            return record.getName();
        case TYPE_INDEX:
            return record.getType();
        case BIRTHDAY_INDEX:
            return record.getBirthday();
        case AGE_INDEX:
            return record.getAge();
        case TIMER_INDEX:
            return record.getTimer();
        case WEBSITE_INDEX:
            return record.getWebsite();
        case NOTES_INDEX:
            return record.getNotes();
        default:
            return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
        BMRecord record = dataVector.get(row);
        switch (column) {
        case NAME_INDEX:
            record.setName((String) value);
            break;
        case TYPE_INDEX:
            record.setType((String) value);
            break;
        case BIRTHDAY_INDEX:
            record.setBirthday((String) value);
            break;
        case AGE_INDEX:
            record.setAge((String) value);
            break;
        case TIMER_INDEX:
            record.setTimer((String) value);
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

    public boolean hasEmptyRow() {
        if (dataVector.size() == 0)
            return false;
        BMRecord record = dataVector.get(dataVector.size() - 1);
        if (record.isEmptyRow()) {
            return true;
        } else
            return false;
    }

    public void addEmptyRow() {
        dataVector.add(new BMRecord());
        fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }
}
