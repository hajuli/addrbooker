package hoi.addrbook.ui;

import hoi.addrbook.data.ContactPropsEnum;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfoTimerField extends JPanel implements AccessInterface {
	private static final long serialVersionUID = 3640198100387397959L;

	private InfoTextField delta = new InfoTextField();
	private JButton clear = new JButton("清零");
	private ContactPropsEnum contactKey = null;

	public InfoTimerField(ContactPropsEnum contactKey, String compName) {
		super(new BorderLayout(1, 1));
		this.contactKey = contactKey;
		setName(compName);
		clear.setBorder(BorderFactory.createCompoundBorder( //
				BorderFactory.createEtchedBorder(), new EmptyBorder(new Insets(1, 7, 1, 7))));

		add(delta, BorderLayout.CENTER);
		add(clear, BorderLayout.EAST);
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
