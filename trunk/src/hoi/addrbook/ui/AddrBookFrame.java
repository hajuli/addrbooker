package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import hoi.addrbook.icons.ImageHelper;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AddrBookFrame extends JFrame {

	private static final long serialVersionUID = -3116155141455555134L;

	public static final String TITLE = "草根通讯录";

	public AddrBookFrame() {
		Dimension size = new Dimension(540, 540 / 4 * 3);
		setPreferredSize(size);
		setMinimumSize(size);
		setLocationRelativeTo(null);
		setIconImage(ImageHelper.ICON_LOGO.getImage());
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					setLayout(new BorderLayout(0, 0));
					add(new AddrBookPanel(AddrBookFrame.this), BorderLayout.CENTER);
				}
			});
		} catch (Exception exc) {
			System.err.println("Can't create because of " + exc);
		}
	}

	private static void setLookAndFeel() {
		try { // Default, Green, Lemmon, Red
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default", "??", "AddrBooker");
			UIManager.setLookAndFeel(new com.jtattoo.plaf.acryl.AcrylLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		setLookAndFeel();
		new AddrBookFrame().setVisible(true);
	}
}
