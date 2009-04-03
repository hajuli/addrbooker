package hoi.birthdaymgr;

import hoi.birthdaymgr.swing.TablePopupEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class BMgrPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 5084217279659068855L;

    protected JTable table;
    protected JScrollPane scroller;
    protected BMgrTableModel tableModel;

    protected JButton addNewRowButton;
    protected JButton saveButton;
    protected JButton deleteButton;
    protected JButton reloadButton;
    protected JButton exitButton;

    protected JButton undoButton, redoButton;

    public BMgrPanel() {
        initComponent();
    }

    public void initComponent() {
        tableModel = new BMgrTableModel();
        tableModel.addTableModelListener(new BMgrPanel.InteractiveTableModelListener());
        table = new BMgrTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false); // 表头的顺序不可改变
        table.setRowHeight(24);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyLastRow()) {
            tableModel.addEmptyRow();
        }

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 450));
        TableColumn hidden = table.getColumnModel().getColumn(BMgrTableModel.HIDDEN_INDEX);
        hidden.setCellRenderer(new InteractiveRenderer(BMgrTableModel.HIDDEN_INDEX));
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);

        int[][] k = new int[][] {
                {
                        BMgrTableModel.SELECTED_INDEX, 40, 45, 45 }, {
                        BMgrTableModel.NAME_INDEX, 100, 90, 90 }, {
                        BMgrTableModel.BIRTHDAY_INDEX, 200, 200, 200 }, {
                        BMgrTableModel.TIME_INDEX, 125, 125, 100 }, };
        for (int i = 0; i < k.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(k[i][0]);
            column.setMinWidth(k[i][1]);
            column.setPreferredWidth(k[i][1]);
            column.setMaxWidth(k[i][1]);
        }

        TableColumn time = table.getColumnModel().getColumn(BMgrTableModel.TIME_INDEX);
        time.setCellRenderer(new TimerTableCellRenderer());

        TableColumn birthday = table.getColumnModel().getColumn(BMgrTableModel.BIRTHDAY_INDEX);
        birthday.setCellRenderer(new BirthdayTableCellRenderer());

        TableColumn notes = table.getColumnModel().getColumn(BMgrTableModel.NOTES_INDEX);
        notes.setCellEditor(new TablePopupEditor());

        TableColumn website = table.getColumnModel().getColumn(BMgrTableModel.WEBSITE_INDEX);
        website.setCellEditor(new TablePopupEditor());

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

        deleteButton = new JButton("删除选中行");
        deleteButton.addActionListener(this);
        addNewRowButton = new JButton("新增空行");
        addNewRowButton.addActionListener(this);
        saveButton = new JButton("保存数据");
        saveButton.addActionListener(this);
        reloadButton = new JButton("重新读取");
        reloadButton.addActionListener(this);
        exitButton = new JButton("退出程序");
        exitButton.addActionListener(this);

        JvUndoManager undoManager = new JvUndoManager();
        tableModel.addUndoableEditListener(undoManager);
        redoButton = new JButton("重做");
        redoButton.addActionListener(undoManager.getRedoAction());
        undoButton = new JButton("撤销");
        undoButton.addActionListener(undoManager.getUndoAction());

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(addNewRowButton);
        // toolbar.add(saveButton);
        toolbar.add(deleteButton);
        toolbar.add(undoButton);
        toolbar.add(redoButton);
        // toolbar.add(reloadButton);
        toolbar.add(exitButton);
        add(toolbar, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == deleteButton) {
            tableModel.deleteSelectedRows();
        } else if (obj == addNewRowButton) {
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
                if ((BMgrPanel.this.tableModel.getRowCount() - 1) == row && !BMgrPanel.this.tableModel.hasEmptyLastRow()) {
                    BMgrPanel.this.tableModel.addEmptyRow();
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
                if (column != BMgrTableModel.SELECTED_INDEX) {
                    table.setColumnSelectionInterval(column + 1, column + 1);
                    table.setRowSelectionInterval(row, row);
                }
            }
        }
    }

}
