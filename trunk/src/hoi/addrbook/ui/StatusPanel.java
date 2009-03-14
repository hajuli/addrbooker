package hoi.addrbook.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class StatusPanel extends JPanel {
    private static final long serialVersionUID = -8423064815808187356L;

    public StatusPanel() {
        super(new BorderLayout(0, 0));
        add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        add(new JLabel(" "), BorderLayout.CENTER);
    }
}
