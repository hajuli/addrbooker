package hoi.addrbook.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InfoTextField extends JTextField {

	private static final long serialVersionUID = 1215758246370170020L;
	private String backtip = null;

	public InfoTextField(String backtip, int right) {
		super();
		this.backtip = backtip;
		setBorder(BorderFactory.createCompoundBorder( //
				BorderFactory.createEtchedBorder(), new EmptyBorder(new Insets(1, 0, 1, right))));
	}

	public InfoTextField(String backtip) {
		this(backtip, 1);
	}

	public InfoTextField() {
		this(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (getText().equals("") && backtip != null) {
			Font font = g.getFont();
			Color color = g.getColor();

			g.setFont(new Font("Dialog", Font.PLAIN, 12));
			g.setColor(Color.GRAY);
			g.drawString(backtip, 4, (getHeight() - 12) / 2 + 10);

			g.setColor(color);
			g.setFont(font);
		}
	}
}
