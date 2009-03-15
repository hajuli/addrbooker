package hoi.addrbook.ui;

import hoi.addrbook.icon.ImageHelper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SreachBox extends InfoTextField {

    private static final long serialVersionUID = -5022351203995487292L;
    private static final BufferedImage image = ImageHelper.getBufferedImage("search.png");

    public SreachBox() {
        super(null, null, UINamesCtrl.getLocalName("Typing to Search"), image.getWidth());
    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            int x = getWidth() - image.getWidth() - 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
