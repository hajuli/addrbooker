package hoi.bm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class BMTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5559688802287696318L;
    public static final int NAME_INDEX = 0;
    public static final int TIMER_INDEX = 1;
    public static final int NOTES_INDEX = 2;
    public static final int WEBSITE_INDEX = 3;
    public static final int BIRTHDAY_INDEX = 4;
    public static final int AGE_INDEX = 5;
    public static final int TIME_INDEX = 6;
    public static final int HIDDEN_INDEX = 7;

    protected String[] columnNames = {
            "姓名", "好久没联系了", "备注", "主页/博客", "[农/公]出生日期", "年龄", "最后修改时间", "" };
    protected Vector<BMRecord> dataVector = new Vector<BMRecord>();

    public BMTableModel() {
        load();
    }

    private void load() {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader("bm.data"));
            for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
                if (line != null && !line.trim().equals("")) {
                    dataVector.add(new BMRecord(line.trim()));
                    fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bReader != null)
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void save() {
        BufferedWriter bWriter = null;
        try {
            bWriter = new BufferedWriter(new FileWriter("bm.data")) {
                public void write(String str) throws IOException {
                    str += System.getProperty("line.separator");
                    this.write(str, 0, str.length());
                }
            };
            for (BMRecord record : dataVector)
                bWriter.write(record.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bWriter != null)
                try {
                    bWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        if (column == HIDDEN_INDEX || column == AGE_INDEX || column == TIMER_INDEX)
            return false;
        else
            return true;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
        case NAME_INDEX:
        case BIRTHDAY_INDEX:
        case AGE_INDEX:
        case TIME_INDEX:
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
        case BIRTHDAY_INDEX:
            return record.getBirthday();
        case AGE_INDEX:
            return record.getAge();
        case TIME_INDEX:
            return record.getTime();
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
        case BIRTHDAY_INDEX:
            record.setBirthday((String) value);
            break;
        case AGE_INDEX:
            record.setAge((String) value);
            break;
        case TIME_INDEX:
            record.setTime((String) value);
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
        save();
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
