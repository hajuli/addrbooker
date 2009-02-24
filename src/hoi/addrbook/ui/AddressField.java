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

public class AddressField extends JPanel {

	private static final long serialVersionUID = -2283281574591552446L;
	public static final Border COMMON_BORDER = BorderFactory.createCompoundBorder( //
			BorderFactory.createEtchedBorder(), //
			new EmptyBorder(new Insets(1, 1, 1, 1)));

	private JTextField solarField = new JTextField("1986-11-26");
	private JTextField lunarField = new JTextField("");

	public AddressField(String info) {
		solarField.setBorder(COMMON_BORDER);
		lunarField.setBorder(COMMON_BORDER);
		lunarField.setPreferredSize(new Dimension(80, 1));

		setLayout(new BorderLayout(1, 1));
		add(solarField, BorderLayout.CENTER);
		add(lunarField, BorderLayout.EAST);
		//setBorder(BorderFactory.createEtchedBorder());
	}
}
