package hoi.bm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
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
        };
        table.setModel(tableModel);
        //  table.getTableHeader().setReorderingAllowed(false); // 表头的顺序不可改变
        table.setRowHeight(24);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow();
        }

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
                if (evt.getButton() == MouseEvent.BUTTON3) {
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

                    } else if (table.getColumnName(column) == "姓名") {

                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem visitMenuItem = new JMenuItem("删除此行");
                        popupMenu.add(visitMenuItem);

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
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 500));
        TableColumn hidden = table.getColumnModel().getColumn(BMTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer(new InteractiveRenderer(BMTableModel.HIDDEN_INDEX));

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
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(newButton);
        toolbar.add(saveButton);
        toolbar.add(deleteButton);
        add(toolbar, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == deleteButton) {
            int[] rows = table.getSelectedRows();
            tableModel.deleteRows(rows);
        } else if (obj == newButton) {
            tableModel.addEmptyRow();
        } else if (obj == saveButton) {
            tableModel.save();
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
            initGlobalFontSetting(new Font("Dialog", Font.BOLD, 15));
            JFrame frame = new JFrame("草根生日提醒");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BMFrame());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
