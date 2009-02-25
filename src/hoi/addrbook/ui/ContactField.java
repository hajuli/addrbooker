package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ContactField extends JPanel {

	private static final long serialVersionUID = -5178510604500684743L;

	private static final Border COMMON_BORDER = BorderFactory.createCompoundBorder( //
			BorderFactory.createEtchedBorder(), //
			new EmptyBorder(new Insets(1, 7, 1, 7)));

	private JTextField dateField = new JTextField("2009-02-10");
	private JButton now = new JButton("清零");

	public ContactField() {
		dateField.setBorder(COMMON_BORDER);
		//lunarField.setBorder(COMMON_BORDER);
		now.setBorder(COMMON_BORDER);

		setLayout(new BorderLayout(1, 1));
		add(dateField, BorderLayout.CENTER);
		add(now, BorderLayout.EAST);
		//setBorder(BorderFactory.createEtchedBorder());
	}
}
