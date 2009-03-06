package hoi.addrbook.ui;

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

	public InfoTimerField(String name) {
		super(new BorderLayout(1, 1));
		setName(name);
		clear.setBorder(BorderFactory.createCompoundBorder( //
				BorderFactory.createEtchedBorder(), new EmptyBorder(new Insets(1, 7, 1, 7))));

		add(delta, BorderLayout.CENTER);
		add(clear, BorderLayout.EAST);
	}

	public String getInfo() {
		return null;
	}

	public void setInfo(String info) {
	}
}
