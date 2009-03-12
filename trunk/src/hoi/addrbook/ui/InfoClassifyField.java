package hoi.addrbook.ui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class InfoClassifyField extends JPanel implements AccessInterface {

	private static final long serialVersionUID = 7531465834540410473L;
	private JComboBox classify = new JComboBox();
	private String contactKey = null;

	public InfoClassifyField(String contactKey, String compName) {
		super(new BorderLayout(0, 0));
		this.contactKey = contactKey;
		setName(compName);
		classify.setBorder(BorderFactory.createEtchedBorder());
		classify.setUI(new hoi.addrbook.look.AComboBoxUI());
		classify.setRenderer(new ComplexCellRenderer());
		classify.setEditable(true);
		add(classify, BorderLayout.CENTER);
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
