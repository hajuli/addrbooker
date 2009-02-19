package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AddrBookBackupDialog extends JDialog {

	private static final Color BORDER_COLOR = new JLabel().getBackground();

	private String infoAddrBooker = "<html>草根cǎo gēn通讯录<br>AddrBooker<br>http://code.google.com/p/addrbooker/<br>" + //
			"合群之草，才有力量<br>野火烧不尽，春风吹又生";

	private JButton backupButton = new JButton("备份数据");
	private JButton restoreButton = new JButton("恢复数据");

	private JButton helpButton = new JButton("帮助");
	private JButton closeButton = new JButton("关闭");

	public AddrBookBackupDialog() {
		JPanel aPanel = new JPanel();
		final JButton b = new JButton(infoAddrBooker, ImageHelper.ICON_LOGO);
		aPanel.add(b);
		aPanel.setBorder(BorderFactory.createEtchedBorder());
		b.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				b.setIcon(ImageHelper.ICON_LOGO_KUD);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setIcon(ImageHelper.ICON_LOGO);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				b.setIcon(ImageHelper.ICON_LOGO_DATA);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				b.setIcon(ImageHelper.ICON_LOGO_KUD);
			}

		});

		JPanel bPanel = new JPanel(new GridLayout(1, 2));
		bPanel.add(backupButton);
		bPanel.add(restoreButton);
		bPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel cPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cPanel.add(helpButton);
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
		AddrBookBackupDialog d = new AddrBookBackupDialog();
		d.pack();
		d.setTitle("abc");
		d.setIconImage(ImageHelper.ICON_LOGO.getImage());
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}
}
