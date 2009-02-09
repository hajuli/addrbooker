package hoi.addrbook.icons;

import javax.swing.ImageIcon;

public class ImageHelper {

	private ImageHelper() {
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ImageHelper.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static final ImageIcon ICON_LOGO = createImageIcon("128/kugar.png");

	public static final ImageIcon ICON_ADD = createImageIcon("32/User1_Add.png");
	public static final ImageIcon ICON_SAVE = createImageIcon("32/filesave.png");
	public static final ImageIcon ICON_EDIT = createImageIcon("32/Edit.png");
	public static final ImageIcon ICON_DELETE = createImageIcon("32/stop.png");
	public static final ImageIcon ICON_SORT = createImageIcon("32/download4.png");
	public static final ImageIcon ICON_HELP = createImageIcon("32/32px-Help.png");
	public static final ImageIcon ICON_EXIT = createImageIcon("32/exit.png");
	public static final ImageIcon ICON_SETTINGS = createImageIcon("32/settings.png");
	public static final ImageIcon ICON_BACKUP = createImageIcon("32/order.png");
}
