package hoi.addrbook.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InfoTextField extends JTextField implements AccessInterface {

	private static final long serialVersionUID = 1215758246370170020L;
	private String contactKey = null;
	private String backTip = null;

	public InfoTextField(String contactKey, String compName, String backTip, int rightInset) {
		super();
		this.contactKey = contactKey;
		setName(compName);
		this.backTip = backTip;
		setBorder(BorderFactory.createCompoundBorder( //
				BorderFactory.createEtchedBorder(), new EmptyBorder(new Insets(1, 0, 1, rightInset))));
	}

	public InfoTextField(String contactKey, String compName, String backTip) {
		this(contactKey, compName, backTip, 1);
	}

	public InfoTextField(String contactKey, String compName) {
		this(contactKey, compName, null, 1);
	}

	public InfoTextField(String backTip) {
		this(null, null, backTip, 1);
	}

	public InfoTextField() {
		this(null, null, null, 1);
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (getText().equals("") && backTip != null && !backTip.equals("")) {
			Font font = g.getFont();
			Color color = g.getColor();

			g.setFont(new Font("Dialog", Font.PLAIN, 12));
			g.setColor(Color.GRAY);
			g.drawString(backTip, 4, (getHeight() - 12) / 2 + 10);

			g.setColor(color);
			g.setFont(font);
		}
	}

	public String getContactKey() {
		return contactKey;
	}

	public String getContent() {
		return getText();
	}

	public void setContactKey(String contactKey) {
		this.contactKey = contactKey;
	}

	public void setContent(String content) {
		setText(content);
	}
}
