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

public class InfoAddrField extends JPanel {

	private static final long serialVersionUID = 9087341622805152794L;
	private InfoTextField solarField = new InfoTextField();
	private InfoTextField lunarField = new InfoTextField("邮政编码");

	public InfoAddrField() {
		lunarField.setPreferredSize(new Dimension(85, 1));

		setLayout(new BorderLayout(1, 1));
		add(solarField, BorderLayout.CENTER);
		add(lunarField, BorderLayout.EAST);
		//setBorder(BorderFactory.createEtchedBorder());
	}
}
