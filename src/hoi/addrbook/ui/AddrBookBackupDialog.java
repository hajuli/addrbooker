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

public class AddrBookBackupDialog extends JDialog {

	private static final long serialVersionUID = -3557688446186478068L;

	private static final int BLANK_SIZE = 20;
	private static final int GAP_SIZE = 5;

	private JButton backupButton = new JButton("备份数据");
	private JButton restoreButton = new JButton("恢复数据");

	private JButton helpButton = new JButton("帮助");
	private JButton closeButton = new JButton("关闭");

	public AddrBookBackupDialog(Frame owner) {
		super(owner);

		JPanel aPanel = new JPanel(new GridLayout(1, 2, GAP_SIZE * 2, GAP_SIZE));
		aPanel.add(backupButton);
		aPanel.add(restoreButton);
		aPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), //
				new EmptyBorder(new Insets(BLANK_SIZE, BLANK_SIZE * 2, BLANK_SIZE, BLANK_SIZE * 2))));

		JPanel bPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, GAP_SIZE * 2, GAP_SIZE));
		bPanel.add(helpButton);
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
		setTitle("备份/恢复 数据");
		pack();
		setResizable(false);
		setLocationRelativeTo(owner);
	}

	public static void main(String[] args) {
		new AddrBookBackupDialog(null).setVisible(true);
	}
}
