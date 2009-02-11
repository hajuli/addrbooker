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

	public static final ImageIcon ICON_ADD = createImageIcon("32/add.png");
	public static final ImageIcon ICON_SAVE = createImageIcon("32/save.png");
	public static final ImageIcon ICON_EDIT = createImageIcon("32/edit.png");
	public static final ImageIcon ICON_DELETE = createImageIcon("32/delete.png");
	public static final ImageIcon ICON_SORT = createImageIcon("32/sort.png");
	public static final ImageIcon ICON_HELP = createImageIcon("32/help.png");
	public static final ImageIcon ICON_EXIT = createImageIcon("32/exit.png");
	public static final ImageIcon ICON_SETTING = createImageIcon("32/setting.png");
	public static final ImageIcon ICON_BACKUP = createImageIcon("32/backup.png");
}
