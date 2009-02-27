package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class SearchField extends StringField {

	private static final long serialVersionUID = -5022351203995487292L;
	private Image image = ImageHelper.ICON_SEARCH.getImage();

	public SearchField(String backtip) {
		super(backtip);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			URL url = this.getClass().getResource("../icons/tiny/search.png");
			final java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(url);
			Border border = UIManager.getBorder("TextField.border");
			JTextField defaultField = new JTextField();
			final int x = getWidth() - border.getBorderInsets(defaultField).right - image.getWidth();
			setMargin(new Insets(2, 2, 2, getWidth() - x));
			int y = (getHeight() - image.getHeight()) / 2;
			g.drawImage(image, x, y, this);
		} catch (Exception ignore) {
		}
	}

	//	public void paint(Graphics g) {
	//		super.paint(g);
	//		this.setMargin(new Insets(1, 1, 1, getWidth() - image.getWidth(this) - 3));
	//		g.drawImage(image, getWidth() - image.getWidth(this) - 3, (getHeight() - image.getHeight(this)) / 2, this);
	//	}
}
