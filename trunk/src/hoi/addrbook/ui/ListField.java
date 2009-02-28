package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ListField extends JPanel {

	private static final long serialVersionUID = 6354225953429243350L;
	private JList note = new JList();

	public ListField() {

	}

	public ListField(String[] strings) {
		super();
		note = new JList(strings);
		note.setCellRenderer(new ComplexCellRenderer());
		//	note.setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));
		setLayout(new BorderLayout());
		add(new JScrollPane(note, //
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
}
