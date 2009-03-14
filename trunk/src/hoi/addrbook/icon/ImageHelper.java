package hoi.addrbook.icon;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelper {

    private ImageHelper() {
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        URL imgURL = ImageHelper.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static BufferedImage getBufferedImage(String path) {
        URL imgURL = ImageHelper.class.getResource(path);
        if (imgURL != null) {
            try {
                return ImageIO.read(imgURL);
            } catch (IOException e) {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static final ImageIcon ICON_LOGO = createImageIcon("128/kugar.png");
    public static final ImageIcon ICON_SEARCH = createImageIcon("search.png");

    public static final ImageIcon ICON_ADD = createImageIcon("32/add.png");
    public static final ImageIcon ICON_SAVE = createImageIcon("32/save.png");
    public static final ImageIcon ICON_DELETE = createImageIcon("32/delete.png");
    public static final ImageIcon ICON_SETTING = createImageIcon("32/setting.png");
    public static final ImageIcon ICON_BACKUP = createImageIcon("32/backup.png");
    public static final ImageIcon ICON_EXIT = createImageIcon("32/exit.png");
}
