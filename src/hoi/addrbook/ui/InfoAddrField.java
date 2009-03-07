package hoi.addrbook.ui;

import hoi.addrbook.data.ContactPropsEnum;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class InfoAddrField extends JPanel implements AccessInterface {

	private static final long serialVersionUID = 9087341622805152794L;
	private InfoTextField address = new InfoTextField();
	private InfoTextField postcode = new InfoTextField(null, "邮政编码");
	private ContactPropsEnum contactKey = null;

	public InfoAddrField(ContactPropsEnum contactKey, String compName) {
		super(new BorderLayout(1, 1));
		this.contactKey = contactKey;
		setName(compName);
		postcode.setPreferredSize(new Dimension(85, 1));

		add(address, BorderLayout.CENTER);
		add(postcode, BorderLayout.EAST);
	}

	public ContactPropsEnum getContactKey() {
		return contactKey;
	}

	public String getContent() {
		return "";
	}

	public void setContactKey(ContactPropsEnum contactKey) {
		// TODO Auto-generated method stub

	}

	public void setContent(String content) {
		// TODO Auto-generated method stub

	}
}
