package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AddrBookSettingDialog extends JDialog {

	private static final Color BORDER_COLOR = new JLabel().getBackground();

	private JButton addPasswordButton = new JButton("设置密码");
	private JButton changePasswordButton = new JButton("更改密码");
	private JButton deletePasswordButton = new JButton("删除密码");

	private JButton donateButton = new JButton("捐赠");
	private JButton closeButton = new JButton("关闭");

	private JRadioButton defaultColorButton = new JRadioButton("默认");
	private JRadioButton greenColorButton = new JRadioButton("绿色");
	private JRadioButton lemmonColorButton = new JRadioButton("柠檬");
	private JRadioButton redColorButton = new JRadioButton("红色");

	public AddrBookSettingDialog() {
		JPanel aPanel = new JPanel(new GridLayout(1, 4));
		aPanel.add(defaultColorButton);
		aPanel.add(greenColorButton);
		aPanel.add(lemmonColorButton);
		aPanel.add(redColorButton);
		aPanel.setBorder(BorderFactory.createTitledBorder("配色方案"));

		JPanel bPanel = new JPanel(new GridLayout(3, 1));
		bPanel.add(addPasswordButton);
		bPanel.add(changePasswordButton);
		bPanel.add(deletePasswordButton);
		bPanel.setBorder(BorderFactory.createTitledBorder("密码保护"));

		JPanel cPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cPanel.add(donateButton);
		cPanel.add(closeButton);

		JPanel mPanel = new JPanel(new BorderLayout());
		mPanel.add(aPanel, BorderLayout.NORTH);
		mPanel.add(bPanel, BorderLayout.CENTER);
		mPanel.add(cPanel, BorderLayout.SOUTH);
		mPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 5));
		this.setContentPane(mPanel);
	}

	private static void setLookAndFeel() {
		try { // Default, Green, Lemmon, Red
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default", "??", "Addrbooker");
			UIManager.setLookAndFeel(new com.jtattoo.plaf.acryl.AcrylLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		setLookAndFeel();
		AddrBookSettingDialog d = new AddrBookSettingDialog();
		d.pack();
		d.setIconImage(ImageHelper.ICON_LOGO.getImage());
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}

}
