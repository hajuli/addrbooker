package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class SreachBox extends InfoTextField {

	private static final long serialVersionUID = -5022351203995487292L;
	private static Image image = ImageHelper.ICON_SEARCH.getImage();

	public SreachBox(String backtip) {
		super(backtip, image.getWidth(null));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			URL url = this.getClass().getResource("../icons/search.png");
			final java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(url);
			Border border = UIManager.getBorder("TextField.border");
			JTextField defaultField = new JTextField();
			final int x = getWidth() - border.getBorderInsets(defaultField).right - image.getWidth();
			int y = (getHeight() - image.getHeight()) / 2;
			g.drawImage(image, x, y, this);
		} catch (Exception ignore) {
		}
	}
}
