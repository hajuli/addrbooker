package hoi.addrbook.ui;

import hoi.addrbook.AddrBookInfo;
import hoi.addrbook.VersionCtrl;
import hoi.addrbook.util.Browser;
import hoi.addrbook.util.Localization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class StatusPanel extends JPanel {
    private static final long serialVersionUID = -8423064815808187356L;
    private static final int GAP_SIZE = 2;
    private static final int BLANK_SIZE = 5;

    private JLabel versionLabel = new JLabel();

    public StatusPanel() {
        super(new BorderLayout(0, 0));
        add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        add(new JLabel(" "), BorderLayout.CENTER);
        add(versionLabel, BorderLayout.EAST);

        versionLabel.setBorder(BorderFactory.createCompoundBorder(versionLabel.getBorder(), //
                BorderFactory.createEmptyBorder(0, BLANK_SIZE, GAP_SIZE, BLANK_SIZE)));

        new Thread(new Runnable() {
            public void run() {
                checkNewVersion();
            }
        }).start();
    }

    private void checkNewVersion() {
        if (VersionCtrl.hasNewVersion()) {
            versionLabel.setForeground(Color.BLUE);
            versionLabel.setText("<html><u><i>" + Localization.getLocalString("Found New Version") + "</i></u></html>");
            versionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            versionLabel.setToolTipText(AddrBookInfo.HOME_WEBSITE);
            versionLabel.addMouseListener(new MouseListener() {
                private boolean clicked = false;
                private boolean entered = false;

                public void mouseClicked(MouseEvent e) {
                    try {
                        Browser.openUrl(AddrBookInfo.HOME_WEBSITE);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        JOptionPane.showMessageDialog(StatusPanel.this.getTopLevelAncestor(), //
                                AddrBookInfo.HOME_WEBSITE, //
                                Localization.getLocalString("Error attempting to launch web browser"), //
                                JOptionPane.ERROR_MESSAGE);
                    }
                    clicked = true;
                }

                public void mouseEntered(MouseEvent e) {
                    versionLabel.setForeground(Color.BLACK);
                    entered = true;
                }

                public void mouseExited(MouseEvent e) {
                    versionLabel.setForeground(Color.BLUE);
                    if (clicked)
                        versionLabel.setForeground(Color.GRAY.brighter());
                    entered = false;
                }

                public void mousePressed(MouseEvent e) {
                    versionLabel.setForeground(Color.BLUE);
                }

                public void mouseReleased(MouseEvent e) {
                    if (entered)
                        versionLabel.setForeground(Color.BLACK);
                }
            });
        } else {
            versionLabel.setForeground(Color.GRAY.brighter());
            versionLabel.setText(Localization.getLocalString("Version") + ": " + VersionCtrl.FULL_VERSION);
            versionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            versionLabel.setToolTipText(AddrBookInfo.HOME_WEBSITE);
            versionLabel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        Browser.openUrl(AddrBookInfo.HOME_WEBSITE);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        JOptionPane.showMessageDialog(StatusPanel.this.getTopLevelAncestor(), //
                                AddrBookInfo.HOME_WEBSITE, //
                                Localization.getLocalString("Error attempting to launch web browser"), //
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }
            });
        }
    }
}
