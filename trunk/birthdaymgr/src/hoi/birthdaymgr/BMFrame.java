package hoi.birthdaymgr;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BMFrame extends JPanel implements ActionListener {

    private static final long serialVersionUID = 5084217279659068855L;

    protected JTable table;
    protected JScrollPane scroller;
    protected BMTableModel tableModel;

    protected JButton upButton, downButton;
    protected JButton deleteButton;
    protected JButton newButton;
    protected JButton saveButton;
    protected JButton reloadButton, exitButton;

    public BMFrame() {
        initComponent();
    }

    public void initComponent() {
        tableModel = new BMTableModel();
        tableModel.addTableModelListener(new BMFrame.InteractiveTableModelListener());
        table = new JTable() {
            private static final long serialVersionUID = -4971187277500151465L;

            public TableCellEditor getCellEditor(int row, int column) {
                // Set custom cell editor only for first column.
                if (convertColumnIndexToModel(column) == BMTableModel.TIME_INDEX) {
                    // Based on row number passed decide which model to be used in drop-down.

                    String sValue = (String) tableModel.getValueAt(row, column);

                    JComboBox comboBox = new JComboBox();
                    comboBox.addItem(sValue.toString());
                    String sValue2 = new SimpleDateFormat("yyyy-M-d").format(new Date());
                    if (!sValue2.equals(sValue))
                        comboBox.addItem(sValue2);
                    return new DefaultCellEditor(comboBox);
                } else {
                    return super.getCellEditor(row, column);
                }
            }

            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == BMTableModel.NOTES_INDEX) {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } else if (realColumnIndex == BMTableModel.WEBSITE_INDEX) {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } else if (realColumnIndex == BMTableModel.BIRTHDAY_INDEX || realColumnIndex == BMTableModel.TIME_INDEX) {
                    tip = getValueAt(rowIndex, colIndex).toString();
                    //                    if (tip != null && !tip.trim().equals(""))
                    //                        tip = String.format("<html>%s<br>%s</html>", tip, new SimpleDateFormat("yyyy-M-d").format(new Date()));
                } else {
                    tip = super.getToolTipText(e);
                }
                if (tip == null || tip.trim().equals(""))
                    tip = null;
                return tip;
            }
        };
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false); // 表头的顺序不可改变
        //          table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(24);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow();
        }

        class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
            private static final long serialVersionUID = 3035808367556280155L;
            // This is the component that will handle the editing of the cell value
            JComponent component = new JTextArea();

            // This method is called when a cell value is edited by the user.
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
                // 'value' is value contained in the cell located at (rowIndex, vColIndex)

                if (isSelected) {
                    // cell (and perhaps other cells) are selected
                }

                // Configure the component with the specified value
                value = value.toString().replace("<br>", "\n").replace("<html>", "");
                ((JTextArea) component).setText((String) value);

                // Return the configured component
                return component;
            }

            // This method is called when editing is completed.
            // It must return the new value to be stored in the cell.
            public Object getCellEditorValue() {
                String text = ((JTextArea) component).getText();
                text = "<html>" + text.replace("\n", "<br>").replace("\r", "");
                return text;
            }

            public void addCellEditorListener(CellEditorListener l) {
                super.addCellEditorListener(l);
            }

            public void cancelCellEditing() {
                super.cancelCellEditing();
            }

            public boolean isCellEditable(EventObject anEvent) {
                return super.isCellEditable(anEvent);
            }

            public void removeCellEditorListener(CellEditorListener l) {
                super.removeCellEditorListener(l);
            }

            public boolean shouldSelectCell(EventObject anEvent) {
                return super.shouldSelectCell(anEvent);
            }

            public boolean stopCellEditing() {
                return super.stopCellEditing();
            }
        }
        table.getColumnModel().getColumn(BMTableModel.NOTES_INDEX).setCellEditor(new MyTableCellEditor());

        table.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int column = table.columnAtPoint(evt.getPoint());

                //                table.editCellAt(row, column);
                table.getSelectionModel().setSelectionInterval(row, row);
                table.setColumnSelectionInterval(column, column);
                table.setRowSelectionInterval(row, row);
                table.changeSelection(row, column, false, false);
                table.requestFocus();

                TableCellEditor edit = table.getCellEditor();
                if (edit != null)
                    edit.stopCellEditing();
            }

        });

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int column = table.columnAtPoint(evt.getPoint());
                    table.editCellAt(row, column);
                }
            }

            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int column = table.columnAtPoint(evt.getPoint());

                    if (table.getColumnName(column) == "主页/博客") {

                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem visitMenuItem = new JMenuItem("访问 全部");
                        JMenuItem aMenuItem = new JMenuItem("访问 http://www.google.com");
                        JMenuItem bMenuItem = new JMenuItem("访问 http://www.baidu.com");
                        popupMenu.add(visitMenuItem);
                        popupMenu.add(new javax.swing.JSeparator());
                        popupMenu.add(aMenuItem);
                        popupMenu.add(bMenuItem);

                        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                        //                        table.editCellAt(row, column);

                    }
                    //                    table.getCellRenderer(row, column);
                    table.getSelectionModel().setSelectionInterval(row, row);
                    table.setColumnSelectionInterval(column, column);
                    table.setRowSelectionInterval(row, row);
                    table.changeSelection(row, column, false, false);
                    table.requestFocus();
                }
            }
        });

        scroller = new javax.swing.JScrollPane(table);
        //        table.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 500));
        TableColumn hidden = table.getColumnModel().getColumn(BMTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer(new InteractiveRenderer(BMTableModel.HIDDEN_INDEX));

        int[][] k = new int[][] {
                {
                        BMTableModel.SELECTED_INDEX, 45, 45, 45 }, {
                        BMTableModel.NAME_INDEX, 100, 100, 100 }, {
                        BMTableModel.BIRTHDAY_INDEX, 175, 175, 175 }, {
                        BMTableModel.TIME_INDEX, 125, 125, 125 }, };
        for (int i = 0; i < k.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(k[i][0]);
            column.setMinWidth(k[i][1]);
            column.setPreferredWidth(k[i][2]);
            column.setMaxWidth(k[i][3]);
        }

        TableColumn time = table.getColumnModel().getColumn(BMTableModel.TIME_INDEX);
        time.setCellRenderer(new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 5154542079793970051L;

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String str = c.getText();

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
                    c.setText(String.format("%d个月%d天", kday / 30, kday % 30));
                }

                return c;
            }

        });

        TableColumn birthday = table.getColumnModel().getColumn(BMTableModel.BIRTHDAY_INDEX);
        birthday.setCellRenderer(new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 5154542079793970051L;

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String str = c.getText();

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
                    int kmonth = cyear * 12 + cmonth - byear * 12 - bmonth;
                    c.setText(String.format("%s%d月%d %d.%d岁", "公历", bmonth, bday, kmonth / 12, kmonth % 12));
                }

                return c;
            }

        });

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

        deleteButton = new JButton("删除选中行");
        deleteButton.addActionListener(this);
        newButton = new JButton("新增空行");
        newButton.addActionListener(this);
        saveButton = new JButton("保存数据");
        saveButton.addActionListener(this);
        reloadButton = new JButton("放弃编辑");
        reloadButton.addActionListener(this);
        exitButton = new JButton("退出");
        exitButton.addActionListener(this);
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(newButton);
        toolbar.add(saveButton);
        toolbar.add(deleteButton);
        toolbar.add(reloadButton);
        toolbar.add(exitButton);
        add(toolbar, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == deleteButton) {
            tableModel.deleteSelectedRows();
        } else if (obj == newButton) {
            tableModel.addEmptyRow();
        } else if (obj == saveButton) {
            tableModel.save();
        } else if (obj == reloadButton) {
            tableModel.reload();
        } else if (obj == exitButton) {
            System.exit(0);
        }
        table.repaint();
    }

    public void highlightLastRow(int row) {
        int lastrow = tableModel.getRowCount();
        if (row == lastrow - 1) {
            table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
        } else {
            table.setRowSelectionInterval(row + 1, row + 1);
        }

        table.setColumnSelectionInterval(0, 0);
    }

    class InteractiveRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = -985691893198596378L;
        protected int interactiveColumn;

        public InteractiveRenderer(int interactiveColumn) {
            this.interactiveColumn = interactiveColumn;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == interactiveColumn && hasFocus) {
                if ((BMFrame.this.tableModel.getRowCount() - 1) == row && !BMFrame.this.tableModel.hasEmptyRow()) {
                    BMFrame.this.tableModel.addEmptyRow();
                }

                highlightLastRow(row);
            }

            return c;
        }
    }

    public class InteractiveTableModelListener implements TableModelListener {
        public void tableChanged(TableModelEvent evt) {
            if (evt.getType() == TableModelEvent.UPDATE) {
                int column = evt.getColumn();
                int row = evt.getFirstRow();
                System.out.println("row: " + row + " column: " + column);
                if (column != -1) {
                    table.setColumnSelectionInterval(column + 1, column + 1);
                    table.setRowSelectionInterval(row, row);
                }
            }
        }
    }

    public static void initGlobalFontSetting(Font fnt) {
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration<?> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    public static void main(String[] args) {
        try {
            initGlobalFontSetting(new Font("Dialog", Font.BOLD, 17));
            JFrame frame = new JFrame("草根生日提醒器");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BMFrame());
            frame.setPreferredSize(new Dimension(800, 500));
            frame.setMinimumSize(new Dimension(800, 200));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}