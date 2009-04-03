package hoi.birthdaymgr;

import hoi.birthdaymgr.utility.Browser;
import hoi.birthdaymgr.utility.EscapeChars;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("deprecation")
public class BMgrTable extends JTable implements MouseMotionListener, MouseListener {

    private static final long serialVersionUID = -1083220712281080152L;
    private static final FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(new JToolTip().getFont());

    public BMgrTable() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public TableCellEditor getCellEditor(int row, int column) {
        if (convertColumnIndexToModel(column) == BMgrTableModel.TIME_INDEX) {
            String sValue = getValueAt(row, column).toString().trim();
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(sValue);
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            if (!current.equals(sValue))
                comboBox.addItem(current);
            return new DefaultCellEditor(comboBox);
        } else {
            return super.getCellEditor(row, column);
        }
    }

    public String getToolTipText(MouseEvent e) {
        String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        int realColumnIndex = convertColumnIndexToModel(colIndex);

        if (realColumnIndex == BMgrTableModel.NOTES_INDEX) {
            String str = getValueAt(rowIndex, colIndex).toString().trim();
            if (!str.equals("")) {
                str = EscapeChars.forHTML(str);
                tip = "<html>";
                int len = 0;
                for (int i = 0; i < str.length(); i++) {
                    len += fm.stringWidth(str.substring(i, i + 1));
                    if (len > 280) {
                        tip += "<br>";
                        len = 0;
                    }
                    tip += str.substring(i, i + 1);
                }
            }
        } else if (realColumnIndex == BMgrTableModel.WEBSITE_INDEX) {
            String str = getValueAt(rowIndex, colIndex).toString().trim().toLowerCase();
            final Vector<String> urls = new Vector<String>();
            for (String item : str.split("[;； 　]+")) {
                item = item.trim();
                if (!item.equals("")) {
                    if (!item.matches("^[a-z]+:/.*$"))
                        item = "http://" + item;
                    urls.addElement(item.trim());
                }
            }
            if (urls.size() > 0) {
                tip = "";
                for (String url : urls)
                    if (tip.equals(""))
                        tip = "<html>" + EscapeChars.forHTML(url);
                    else
                        tip += "<br>" + EscapeChars.forHTML(url);
            } else {
                tip = getValueAt(rowIndex, colIndex).toString();
            }
        } else if (realColumnIndex == BMgrTableModel.BIRTHDAY_INDEX || realColumnIndex == BMgrTableModel.TIME_INDEX) {
            tip = getValueAt(rowIndex, colIndex).toString();
        } else {
            tip = super.getToolTipText(e);
        }
        if (tip == null || tip.trim().equals(""))
            tip = null;
        return tip;
    }

    public void mouseDragged(MouseEvent e) {
    }

    private void focusOnCurrentCell(MouseEvent e) {
        int row = rowAtPoint(e.getPoint());
        int column = columnAtPoint(e.getPoint());

        changeSelection(row, column, false, false);
        requestFocus();

        TableCellEditor editor = getCellEditor();
        if (editor != null)
            editor.stopCellEditing();
    }

    public void mouseMoved(MouseEvent e) {
        focusOnCurrentCell(e);
    }

    public void mouseClicked(MouseEvent evt) {
        int row = rowAtPoint(evt.getPoint());
        int column = columnAtPoint(evt.getPoint());
        if (column != BMgrTableModel.NOTES_INDEX && column != BMgrTableModel.WEBSITE_INDEX)
            if (evt.getClickCount() == 1)
                editCellAt(row, column);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent evt) {
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = rowAtPoint(evt.getPoint());
            int column = columnAtPoint(evt.getPoint());
            int realColumnIndex = convertColumnIndexToModel(column);
            if (realColumnIndex == BMgrTableModel.WEBSITE_INDEX) {
                String str = getValueAt(row, column).toString().trim().toLowerCase();
                final Vector<String> urls = new Vector<String>();
                for (String item : str.split("[;； 　]+")) {
                    item = item.trim();
                    if (!item.equals("")) {
                        if (!item.matches("^[a-z]+:/.*$"))
                            item = "http://" + item;
                        urls.addElement(item.trim());
                    }
                }

                if (urls.size() > 0) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem visitMenuItem = new JMenuItem("访问 全部");
                    popupMenu.add(visitMenuItem);
                    visitMenuItem.addActionListener(new ActionListener() {
                        @SuppressWarnings("deprecation")
                        public void actionPerformed(ActionEvent e) {
                            for (String url : urls)
                                try {
                                    Browser.openURL(url);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                        }
                    });
                    popupMenu.add(new javax.swing.JSeparator());
                    for (String _url : urls) {
                        final String url = _url;
                        JMenuItem menuItem = new JMenuItem("访问 " + url);
                        popupMenu.add(menuItem);
                        menuItem.addActionListener(new ActionListener() {
                            @SuppressWarnings("deprecation")
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Browser.openURL(url);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
            changeSelection(row, column, false, false);
            requestFocus();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }
}
