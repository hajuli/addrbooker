package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

public class ContactList extends JPanel {

	private static final long serialVersionUID = 6354225953429243350L;
	private JList contacts = new JList();

	public ContactList() {
		super(new BorderLayout(0, 0));
		contacts.setCellRenderer(new ComplexCellRenderer());
		add(new JScrollPane(contacts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
}

class ComplexCellRenderer implements ListCellRenderer {
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		renderer.setText(String.format(" %s", value));
		return renderer;
	}
}
