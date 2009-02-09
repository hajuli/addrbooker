package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
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

	private JTextField filterField = new JTextField("搜索");
	private JComboBox orderbyChoose = new JComboBox(new String[] { "排序", "aba" });
	private JList contactList = new JList();
	private JTextArea notesArea = new JTextArea();
	private JTextField nameField = new JTextField();
	private JTextField[] infoFields = new JTextField[13];

	private JTextField addrPathField = new JTextField("abcS");
	private JMenuItem syncMailItem = new JMenuItem("Sync mail");
	private JMenuItem configMailItem = new JMenuItem("Config mail");
	private JMenuItem usageItem = new JMenuItem("Usage");
	private JMenuItem aboutItem = new JMenuItem("About");

	class MyCellRenderer extends JLabel implements ListCellRenderer {
		public MyCellRenderer() {
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			//	setBackground(isSelected ? Color.BLUE : Color.LIGHT_GRAY);
			setBackground(isSelected ? Color.red : Color.white);
			setForeground(isSelected ? Color.white : Color.black);

			setHorizontalAlignment(SwingConstants.RIGHT);
			setText(value.toString());
			return this;
		}
	}

	public AddrBookPanel() {
		addrPathField.setEditable(false);
		addrPathField.setBorder(BorderFactory.createEmptyBorder());
		addrPathField.setCursor(new Cursor(Cursor.TEXT_CURSOR));

		JPanel tPanel = new JPanel(new BorderLayout(0, 0));
		filterField.setPreferredSize(new Dimension(-1, 25));
		//	orderbyChoose.setPreferredSize(new Dimension(-1, 25));
		//	orderbyChoose.setRenderer(new MyCellRenderer());
		tPanel.add(filterField, BorderLayout.CENTER);
		filterField.setBorder(BorderFactory.createEmptyBorder());
		//	tPanel.add(orderbyChoose, BorderLayout.EAST);
		filterField.setBorder(BorderFactory.createEtchedBorder());
		filterField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				filterField.setBackground(Color.WHITE);
				filterField.setBorder(BorderFactory.createEtchedBorder());
			}

			@Override
			public void focusLost(FocusEvent e) {
				filterField.setBackground(new JLabel().getBackground());
				filterField.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}
			
		});

		notesArea.setPreferredSize(new Dimension(-1, 100));

		JPanel wPanel = new JPanel(new BorderLayout(0,0));
		wPanel.add(tPanel, BorderLayout.NORTH);

		JScrollPane a = new JScrollPane(contactList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, //
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		a.setBorder(BorderFactory.createEmptyBorder());
		a.setPreferredSize(new Dimension(-1, 200));
		//JSplitPane wsPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, a, b);
		
		contactList.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				contactList.setBackground(Color.WHITE);
				contactList.setBorder(BorderFactory.createEtchedBorder());
			}

			@Override
			public void focusLost(FocusEvent e) {
				contactList.setBackground(new JLabel().getBackground());
				contactList.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}
			
		});

		wPanel.add(a, BorderLayout.CENTER);
		//wPanel.setBorder(BorderFactory.createEtchedBorder());
		wPanel.setPreferredSize(new Dimension(180, -1));

		InfoPanel infoPanel = new InfoPanel();
		infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));

		JButton sortButton = new JButton("排序", ImageHelper.ICON_SORT);
		JButton addButton = new JButton("添加", ImageHelper.ICON_ADD);
		JButton saveButton = new JButton("保存", ImageHelper.ICON_SAVE);
		JButton editButton = new JButton("编辑", ImageHelper.ICON_EDIT);
		JButton deleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
		JButton settingsButton = new JButton("设置", ImageHelper.ICON_SETTINGS);
		JButton backupButton = new JButton("备份", ImageHelper.ICON_BACKUP);
		JButton helpButton = new JButton("帮助", ImageHelper.ICON_HELP);
		JButton exitButton = new JButton("退出", ImageHelper.ICON_EXIT);

		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setLayout(new GridLayout(1, 9));
	//	toolbar.add(sortButton);
		toolbar.add(addButton);
		toolbar.add(saveButton);
		toolbar.add(editButton);
		toolbar.add(deleteButton);
		toolbar.add(settingsButton);
		toolbar.add(backupButton);
		//toolbar.add(printButton);
	//	toolbar.add(helpButton);
	//	toolbar.add(exitButton);

		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		JSplitPane wtPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, wPanel, infoPanel);
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		wtPane.setBorder(border);
		add(wtPane, BorderLayout.CENTER);
	}
}

class InfoPanel extends JPanel {
	private static final long serialVersionUID = -2588763425591993699L;
	private String[][] info = { { "姓名", "联系" },//
			{ "生日", "年龄" }, //
			{ "QQ", "MSN" },//ＭＳＮ
			{ "手机", "飞信", },// 
			{ "电子邮箱", },//
			{ "个人主页", },//
			{ "公司名称", }, //
			{ "公司地址" }, //
	};

	public InfoPanel() {
		JPanel cp = new JPanel(new GridLayout(info.length, 1, 2, 3));
		for (int i = 0; i < info.length; i++) {
			JPanel tp = new JPanel(new GridLayout(1, info[i].length, 2, 3));
			for (int j = 0; j < info[i].length; j++)
				tp.add(new AddrBookTextField(info[i][j]));
			cp.add(tp);
		}

		setLayout(new BorderLayout(5, 5));
		JTextField k = new JTextField();
		k.setEditable(false);
		//add(k, BorderLayout.NORTH);
		add(cp, BorderLayout.NORTH);

		final JTextArea kd = new JTextArea();
		JScrollPane b = new JScrollPane(kd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, //
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		kd.setBackground(new JLabel().getBackground());
		kd.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				kd.setBackground(Color.WHITE);
				kd.setBorder(BorderFactory.createEtchedBorder());
			}

			@Override
			public void focusLost(FocusEvent e) {
				kd.setBackground(new JLabel().getBackground());
				kd.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}
			
		});
		add(kd, BorderLayout.CENTER);
	}
}
