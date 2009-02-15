package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class AddrBookPanel extends JPanel {

	private static final long serialVersionUID = -4479991916447167635L;

	private static final Color BORDER_COLOR = new JLabel().getBackground();

	private JButton tbarAddButton = new JButton("添加", ImageHelper.ICON_ADD);
	private JButton tbarSaveButton = new JButton("保存", ImageHelper.ICON_SAVE);
	private JButton tbarEditButton = new JButton("编辑", ImageHelper.ICON_EDIT);
	private JButton tbarDeleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
	private JButton tbarSettingButton = new JButton("设置", ImageHelper.ICON_SETTING);
	private JButton tbarBackupButton = new JButton("备份", ImageHelper.ICON_BACKUP);

	private JTextField filterField = new JTextField();
	private JList contactList = new JList();

	private JTextField infoNameField = new JTextField();
	private JTextField infoContactField = new JTextField();
	private JTextField infoBirthdayField = new JTextField();
	private JTextField infoAgeField = new JTextField();
	private JTextField infoQQField = new JTextField();
	private JTextField infoMSNField = new JTextField();
	private JTextField infoMobileField = new JTextField();
	private JTextField infoFetionField = new JTextField();
	private JTextField infoEMailField = new JTextField();
	private JTextField infoWebsiteField = new JTextField();
	private JTextField infoHomeAddrField = new JTextField();
	private JTextField infoCompanyNameField = new JTextField();
	private JTextField infoCompanyAddrField = new JTextField();
	private JTextArea infoNoteArea = new JTextArea();

	private JPanel createInfoPanel(String[] names, JComponent[] comps) {
		JPanel aPanel = new JPanel(new GridLayout(names.length, 1, 3, 3));
		for (String name : names) {
			JLabel l = new JLabel(" " + name + ":", JLabel.RIGHT);
			l.setBorder(BorderFactory.createEtchedBorder());
			aPanel.add(l); // 两端对齐
		}
		JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, 3, 3));
		for (JComponent comp : comps) {
			comp.setBorder(BorderFactory.createEtchedBorder());
			bPanel.add(comp);
		}
		JPanel cPanel = new JPanel(new BorderLayout(3, 3));
		cPanel.add(aPanel, BorderLayout.WEST);
		cPanel.add(bPanel, BorderLayout.CENTER);
		return cPanel;
	}

	private JPanel createInfoPanel() {
		JPanel aPanel = new JPanel(new GridLayout(1, 2, 3, 0));
		aPanel.add(createInfoPanel(new String[] { "姓名", "生日", "QQ", "手机" },//
				new JComponent[] { infoNameField, infoBirthdayField, infoQQField, infoMobileField }));
		aPanel.add(createInfoPanel(new String[] { "联系", "年龄", "MSN", "飞信" },//
				new JComponent[] { infoContactField, infoAgeField, infoMSNField, infoFetionField }));

		JPanel bPanel = new JPanel(new BorderLayout(2, 2));
		bPanel.add(aPanel, BorderLayout.NORTH);
		bPanel.add(createInfoPanel(new String[] { "电子邮箱", "个人主页", "家庭住址", "公司名称", "公司地址" },//
				new JComponent[] { infoEMailField, infoWebsiteField, infoHomeAddrField, infoCompanyNameField, infoCompanyAddrField })//
				, BorderLayout.SOUTH);

		JPanel cPanel = new JPanel(new BorderLayout(3, 3));
		cPanel.add(bPanel, BorderLayout.NORTH);
		infoNoteArea.setBorder(BorderFactory.createEtchedBorder());
		cPanel.add(infoNoteArea, BorderLayout.CENTER);
		cPanel.setBorder(BorderFactory.createEtchedBorder());
		cPanel.setMinimumSize(new Dimension(320, -1));
		return cPanel;
	}

	public AddrBookPanel() {
		filterField.setBorder(BorderFactory.createEtchedBorder());
		//	filterField.setInsets(new Insets(2,2,2,2));
		contactList.setBorder(BorderFactory.createEmptyBorder());
		JScrollPane scroll = new JScrollPane(contactList);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		JPanel wPanel = new JPanel(new BorderLayout(0, 2));
		wPanel.add(filterField, BorderLayout.NORTH);
		wPanel.add(scroll, BorderLayout.CENTER);
		wPanel.setMinimumSize(new Dimension(150, -1));

		JToolBar toolbar = new JToolBar("工具条");
		toolbar.setFloatable(false);
		toolbar.setLayout(new GridLayout(1, 6));
		toolbar.add(tbarAddButton);
		toolbar.add(tbarSaveButton);
		toolbar.add(tbarEditButton);
		toolbar.add(tbarDeleteButton);
		toolbar.add(tbarSettingButton);
		toolbar.add(tbarBackupButton);

		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		JSplitPane wtPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, wPanel, createInfoPanel());
		wtPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 5));
		add(wtPane, BorderLayout.CENTER);
	}
}
