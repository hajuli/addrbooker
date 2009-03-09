package hoi.addrbook.ui;

import hoi.addrbook.data.ContactPropsEnum;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class InfoNotesArea extends JPanel implements AccessInterface {

	private static final long serialVersionUID = 3732388659066090439L;
	private JTextArea notes = new JTextArea();
	private ContactPropsEnum contactKey = null;

	public InfoNotesArea(ContactPropsEnum contactKey) {
		super(new BorderLayout(0, 0));
		this.contactKey = contactKey;
		notes.setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));
		add(new JScrollPane(notes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}

	public ContactPropsEnum getContactKey() {
		return contactKey;
	}

	public String getContent() {
		return notes.getText();
	}

	public void setContactKey(ContactPropsEnum contactKey) {
		// TODO Auto-generated method stub

	}

	public void setContent(String content) {
		this.notes.setText(content);
	}
}
