package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ClassField extends JPanel {

	private static final long serialVersionUID = 7531465834540410473L;
	private JComboBox note = new JComboBox(new String[] {
			"", "a", "b" }) {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	};

	public ClassField() {
		super();
		setLayout(new BorderLayout());
		note.setBorder(BorderFactory.createEtchedBorder());
		//	note.setUI(new com.jgoodies.looks.windows.WindowsComboBoxUI());
		note.setUI(new com.jgoodies.looks.plastic.PlasticComboBoxUI());
		//	note.setUI(new MyComboBoxUI());
		note.setEditable(true);
		add(note, BorderLayout.CENTER);
	}
}
