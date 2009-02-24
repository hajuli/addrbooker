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
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class AddrBookSettingDialog extends JDialog {

	private static final long serialVersionUID = 7423268241876733586L;

	private static final int BLANK_SIZE = 20;
	private static final int GAP_SIZE = 5;

	private JButton addPasswordButton = new JButton("设置密码");
	private JButton changePasswordButton = new JButton("更改密码");
	private JButton deletePasswordButton = new JButton("删除密码");

	private JButton donateButton = new JButton("捐赠");
	private JButton closeButton = new JButton("关闭");

	private JRadioButton defaultColorButton = new JRadioButton("默认");
	private JRadioButton greenColorButton = new JRadioButton("绿色");
	private JRadioButton lemmonColorButton = new JRadioButton("柠檬");
	private JRadioButton redColorButton = new JRadioButton("红色");

	public AddrBookSettingDialog(Frame owner) {
		super(owner);

		JPanel aPanel = new JPanel(new GridLayout(1, 4, GAP_SIZE, GAP_SIZE));
		aPanel.add(defaultColorButton);
		aPanel.add(greenColorButton);
		aPanel.add(lemmonColorButton);
		aPanel.add(redColorButton);
		aPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("外观配色"), //
				new EmptyBorder(new Insets(BLANK_SIZE / 2, BLANK_SIZE, BLANK_SIZE / 2, BLANK_SIZE))));

		JPanel bPanel = new JPanel(new GridLayout(3, 1, GAP_SIZE * 2, GAP_SIZE));
		bPanel.add(addPasswordButton);
		bPanel.add(changePasswordButton);
		bPanel.add(deletePasswordButton);
		bPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("密码保护"), //
				new EmptyBorder(new Insets(BLANK_SIZE / 2, BLANK_SIZE, BLANK_SIZE / 2, BLANK_SIZE))));

		JPanel cPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, GAP_SIZE * 2, GAP_SIZE));
		cPanel.add(donateButton);
		cPanel.add(closeButton);
		// cPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel mPanel = new JPanel(new BorderLayout(BLANK_SIZE, BLANK_SIZE / 2));
		mPanel.add(aPanel, BorderLayout.NORTH);
		mPanel.add(bPanel, BorderLayout.CENTER);
		mPanel.add(cPanel, BorderLayout.SOUTH);
		mPanel.setBorder(new EmptyBorder(new Insets(BLANK_SIZE, BLANK_SIZE, BLANK_SIZE, BLANK_SIZE)));

		setLayout(new BorderLayout(0, 0));
		add(mPanel, BorderLayout.CENTER);

		if (owner != null)
			setIconImage(owner.getIconImage());
		setTitle("选项 设置");
		pack();
		setResizable(false);
		setLocationRelativeTo(owner);
	}

	public static void main(String[] args) {
		new AddrBookSettingDialog(null).setVisible(true);
	}
}
