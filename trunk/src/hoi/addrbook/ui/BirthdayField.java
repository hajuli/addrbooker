package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BirthdayField extends JPanel {

	private static final long serialVersionUID = -2283281574591552446L;
	public static final Border COMMON_BORDER = BorderFactory.createCompoundBorder( //
			BorderFactory.createEtchedBorder(), //
			new EmptyBorder(new Insets(1, 1, 1, 1)));

	private JTextField solarField = new JTextField("1986-11-26");
	private JTextField lunarField = new JTextField("1986-10-25");

	public BirthdayField() {
		solarField.setBorder(COMMON_BORDER);
		lunarField.setBorder(COMMON_BORDER);

		setLayout(new GridLayout(1, 2, 0, 0));
		add(solarField);
		add(lunarField);
		//setBorder(BorderFactory.createEtchedBorder());
	}
}
