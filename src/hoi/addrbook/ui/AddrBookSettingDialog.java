package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddrBookSettingDialog extends JDialog {

    private static final long serialVersionUID = -819820995840993632L;
    private static final int BLANK_SIZE = 20;
    private static final int GAP_SIZE = 5;

    private JButton addPasswordButton = new JButton(UINamesCtrl.getLocalName("Add Password"));
    private JButton changePasswordButton = new JButton(UINamesCtrl.getLocalName("Change Password"));

    private JButton donateButton = new JButton(UINamesCtrl.getLocalName("Donate"));
    private JButton closeButton = new JButton(UINamesCtrl.getLocalName("Close"));

    public AddrBookSettingDialog(Frame owner) {
        super(owner);

        JPanel aPanel = new JPanel(new GridLayout(1, 2, GAP_SIZE * 2, GAP_SIZE));
        aPanel.add(addPasswordButton);
        aPanel.add(changePasswordButton);
        aPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), //
                new EmptyBorder(new Insets(BLANK_SIZE, BLANK_SIZE, BLANK_SIZE, BLANK_SIZE))));

        JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, GAP_SIZE * 2, GAP_SIZE));
        bPanel.add(donateButton);
        bPanel.add(closeButton);
        // bPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel cPanel = new JPanel(new BorderLayout(BLANK_SIZE, BLANK_SIZE));
        cPanel.add(aPanel, BorderLayout.CENTER);
        cPanel.add(bPanel, BorderLayout.SOUTH);
        cPanel.setBorder(new EmptyBorder(new Insets(BLANK_SIZE, BLANK_SIZE, BLANK_SIZE, BLANK_SIZE)));

        setLayout(new BorderLayout(0, 0));
        add(cPanel, BorderLayout.CENTER);

        if (owner != null)
            setIconImage(owner.getIconImage());
        setTitle(UINamesCtrl.getLocalName("Settings"));
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    public static void main(String[] args) {
        new AddrBookSettingDialog(null).setVisible(true);
    }
}
