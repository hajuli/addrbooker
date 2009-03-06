package hoi.addrbook.ui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class InfoClassifyField extends JPanel implements AccessInterface {

	private static final long serialVersionUID = 7531465834540410473L;
	private JComboBox classify = new JComboBox();

	public InfoClassifyField(String name) {
		super(new BorderLayout(0, 0));
		setName(name);
		classify.setBorder(BorderFactory.createEtchedBorder());
		classify.setUI(new com.jgoodies.looks.plastic.PlasticComboBoxUI());
		classify.setRenderer(new ComplexCellRenderer());
		classify.setEditable(true);
		add(classify, BorderLayout.CENTER);
	}

	public String getInfo() {
		return null;
	}

	public void setInfo(String info) {
	}
}
