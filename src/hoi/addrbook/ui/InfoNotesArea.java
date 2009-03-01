package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class InfoNotesArea extends JPanel {

	private static final long serialVersionUID = 3732388659066090439L;
	private JTextArea notes = new JTextArea();

	public InfoNotesArea() {
		super(new BorderLayout(0, 0));
		notes.setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));
		add(new JScrollPane(notes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
}
