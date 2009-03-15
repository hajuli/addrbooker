package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import hoi.addrbook.VersionCtrl;
import hoi.addrbook.icon.ImageHelper;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AddrBookFrame extends JFrame {

    private static final long serialVersionUID = -3116155141455555134L;
    public static final String TITLE = String.format("%s v%s", UINamesCtrl.getLocalName("AddrBooker"), VersionCtrl.SHORT_VERSION);

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
                    add(new AddrBookPanel(), BorderLayout.CENTER);
                }
            });
        } catch (Exception exc) {
            System.err.println("Can't create because of " + exc);
            exc.printStackTrace();
        }
        setResizable(false);
    }

    public static void main(String[] args) {
        new AddrBookFrame().setVisible(true);
    }
}
