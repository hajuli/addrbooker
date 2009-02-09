package hoi.addrbook.ui;

import java.awt.BorderLayout;

import hoi.addrbook.icons.ImageHelper;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AddrBookFrame extends JFrame {

	private static final long serialVersionUID = -3116155141455555134L;

	public static final String TITLE = "??通讯录";

	public AddrBookFrame() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					setLayout(new BorderLayout());
					add(new AddrBookPanel(), BorderLayout.CENTER);
				}
			});
		} catch (Exception exc) {
			System.err.println("Can't create because of " + exc);
		}
	}

	private static void setLookAndFeel() {
		try { // Default, Green, Lemmon, Red
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Lemmon", "??", "Addrbooker");
			UIManager.setLookAndFeel(new com.jtattoo.plaf.acryl.AcrylLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		setLookAndFeel();
		AddrBookFrame frame = new AddrBookFrame();
		frame.setSize(500, 375);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(ImageHelper.ICON_LOGO.getImage());
		frame.setTitle(TITLE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
