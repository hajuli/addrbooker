package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class InfoTimerField extends JPanel {
	private static final long serialVersionUID = 3640198100387397959L;

	private static final Border COMMON_BORDER = BorderFactory.createCompoundBorder( //
			BorderFactory.createEtchedBorder(), //
			new EmptyBorder(new Insets(1, 7, 1, 7)));

	private InfoTextField dateField = new InfoTextField();
	private JButton now = new JButton("清零");

	public InfoTimerField() {
		now.setBorder(COMMON_BORDER);

		setLayout(new BorderLayout(1, 1));
		add(dateField, BorderLayout.CENTER);
		add(now, BorderLayout.EAST);
		//setBorder(BorderFactory.createEtchedBorder());
	}
}
