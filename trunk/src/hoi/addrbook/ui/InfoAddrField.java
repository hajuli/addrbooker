package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel implements AccessInterface {

	private static final long serialVersionUID = 9087341622805152794L;
	private InfoTextField address = new InfoTextField();
	private InfoTextField postcode = new InfoTextField(null, "邮政编码");
	private String contactKey = null;

	public InfoAddrField(String contactKey, String compName) {
		super(new BorderLayout(1, 1));
		this.contactKey = contactKey;
		setName(compName);
		postcode.setPreferredSize(new Dimension(85, 1));

		add(address, BorderLayout.CENTER);
		add(postcode, BorderLayout.EAST);
	}

	public String getContactKey() {
		return contactKey;
	}

	public String getContent() {
		return "";
	}

	public void setContactKey(String contactKey) {
		// TODO Auto-generated method stub

	}

	public void setContent(String content) {
		// TODO Auto-generated method stub

	}
}
