package hoi.bm;

//import hoi.swing.DateComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class BMForm extends JPanel {

    private static final long serialVersionUID = 5084217279659068855L;
    protected JTable table;
    protected JScrollPane scroller;
    protected BMTableModel tableModel;

    public BMForm() {
        initComponent();
    }

    public void initComponent() {
        tableModel = new BMTableModel();
        tableModel.addTableModelListener(new BMForm.InteractiveTableModelListener());
        table = new JTable();
        table.setModel(tableModel);
        table.setSurrendersFocusOnKeystroke(true);
        if (!tableModel.hasEmptyRow()) {
            tableModel.addEmptyRow();
        }

        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 600));
        TableColumn hidden = table.getColumnModel().getColumn(BMTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer(new InteractiveRenderer(BMTableModel.HIDDEN_INDEX));

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
        setCellEditor();
    }

    private void setCellEditor() {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("公历"); // 默认为 公历
        comboBox.addItem("农历");
        table.getColumnModel().getColumn(BMTableModel.TYPE_INDEX).setCellEditor(new DefaultCellEditor(comboBox));
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
                if ((BMForm.this.tableModel.getRowCount() - 1) == row && !BMForm.this.tableModel.hasEmptyRow()) {
                    BMForm.this.tableModel.addEmptyRow();
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
                table.setColumnSelectionInterval(column + 1, column + 1);
                table.setRowSelectionInterval(row, row);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Birthday Manager");
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    System.exit(0);
                }
            });
            frame.getContentPane().add(new BMForm());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
