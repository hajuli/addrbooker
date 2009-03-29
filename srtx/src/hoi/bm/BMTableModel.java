package hoi.bm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class BMTableModel extends AbstractTableModel {

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
    protected Vector<BMRecord> dataVector = new Vector<BMRecord>();

    public BMTableModel() {
        load();
    }

    public void reload() {
        dataVector.removeAllElements();
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
        if (column == HIDDEN_INDEX)
            return false;
        else
            return true;
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
        BMRecord record = dataVector.get(row);
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
        BMRecord record = dataVector.get(row);
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

    public void deleteRows(int[] rows) {
        Arrays.sort(rows);
        for (int i = rows.length - 1; i >= 0; i--)
            dataVector.remove(rows[i]);
        this.fireTableDataChanged();
        if (dataVector.size() == 0)
            addEmptyRow();
    }

    public void deleteSelectedRows() {
        Vector<Integer> v = new Vector<Integer>();
        int cnt = 0;
        for (BMRecord row : dataVector) {
            if (row.isSelected())
                v.add(cnt);
            cnt++;
        }

        int[] vv = new int[v.size()];
        cnt = 0;
        for (Integer k : v) {
            vv[cnt] = k;
            cnt++;
        }
        deleteRows(vv);
    }
}
