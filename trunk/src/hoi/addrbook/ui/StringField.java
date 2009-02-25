package hoi.addrbook.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

public class StringField extends JTextField {

	private static final long serialVersionUID = -9018451259268831946L;
	private String backtip = null;

	public StringField(String backtip) {
		super();
		this.backtip = backtip;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (getText().equals("") && backtip != null) {
			Font font = g.getFont();
			Color color = g.getColor();

			g.setFont(new Font("Dialog", Font.PLAIN, 12));
			g.setColor(Color.GRAY);
			g.drawString(backtip, 5, (getHeight() - 12) / 2 + 10);

			g.setColor(color);
			g.setFont(font);
		}
	}
}
