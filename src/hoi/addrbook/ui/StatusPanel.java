package hoi.addrbook.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class StatusPanel extends JPanel {
	public StatusPanel() {
		this.setLayout(new BorderLayout());
		this.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		this.add(new JLabel(" "), BorderLayout.CENTER);
	}

}
