package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel {

	private static final long serialVersionUID = 9087341622805152794L;
	private InfoTextField address = new InfoTextField();
	private InfoTextField postcode = new InfoTextField(null, "邮政编码");

	public InfoAddrField(String name) {
		super(new BorderLayout(1, 1));
		setName(name);
		postcode.setPreferredSize(new Dimension(85, 1));

		add(address, BorderLayout.CENTER);
		add(postcode, BorderLayout.EAST);
	}
}
