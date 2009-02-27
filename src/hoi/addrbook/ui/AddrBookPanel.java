package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.metal.MetalSplitPaneUI;

public class AddrBookPanel extends JPanel {

	private static final long serialVersionUID = -1863142517865697366L;
	private static final Color BORDER_COLOR = new JLabel().getBackground();
	private static final int GAP_SIZE = 2;

	private JButton tbarAddButton = new JButton("添加", ImageHelper.ICON_ADD);
	private JButton tbarSaveButton = new JButton("保存", ImageHelper.ICON_SAVE);
	private JButton tbarEditButton = new JButton("编辑", ImageHelper.ICON_EDIT);
	private JButton tbarDeleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
	private JButton tbarSettingButton = new JButton("设置", ImageHelper.ICON_SETTING);
	private JButton tbarBackupButton = new JButton("备份", ImageHelper.ICON_BACKUP);

	private SearchField searchField = new SearchField("键入内容 搜索");
	private ListField contactList = new ListField();

	private StringField infoNameField = new StringField();
	private ClassField infoClassField = new ClassField();
	private StringField infoBirthdaySolarField = new StringField("阳历 1900-01-01");
	private StringField infoBirthdayLunarField = new StringField("农历 1900-01-01");
	private ContactField infoContactField = new ContactField();
	private StringField infoAgeField = new StringField();
	private StringField infoQQField = new StringField();
	private StringField infoMSNField = new StringField();
	private StringField infoMobileField = new StringField();
	private StringField infoFetionField = new StringField();
	private StringField infoEMailField = new StringField();
	private StringField infoWebsiteField = new StringField();
	private AddressField infoHomeAddrField = new AddressField();
	private StringField infoCompany = new StringField();
	private NoteField infoNoteArea = new NoteField();

	private JPanel createInfoPanel(String[] names, JComponent[] comps) {
		JPanel aPanel = new JPanel(new GridLayout(names.length, 1, GAP_SIZE, GAP_SIZE));
		for (String name : names) {
			JLabel label = new JLabel(name + " ", JLabel.RIGHT);
			label.setBorder(BorderFactory.createEtchedBorder());
			aPanel.add(label); // 两端对齐??
		}
		JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
		for (JComponent comp : comps) {
			//Dimension size = comp.getPreferredSize();
			//size.height = size.height * 6 / 7;
			//comp.setPreferredSize(size);
			bPanel.add(comp);
		}
		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(aPanel, BorderLayout.WEST);
		cPanel.add(bPanel, BorderLayout.CENTER);
		return cPanel;
	}

	private JPanel createInfoPanel() {
		JPanel aPanel = new JPanel(new GridLayout(1, 2, GAP_SIZE, GAP_SIZE));
		aPanel.add(createInfoPanel(new String[] {
				"<html><strong> 姓名:", " 生日:", " ", " QQ:", " 手机:" }, //
				new JComponent[] {
						infoNameField, infoBirthdayLunarField, infoBirthdaySolarField, infoQQField, infoMobileField }));

		infoClassField.setPreferredSize(infoAgeField.getPreferredSize());
		aPanel.add(createInfoPanel(new String[] {
				" 分组:", " 计时:", " 年龄:", " MSN:", " 飞信:" }, //
				new JComponent[] {
						infoClassField, infoContactField, infoAgeField, infoMSNField, infoFetionField }));

		JPanel bPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		bPanel.add(aPanel, BorderLayout.NORTH);
		bPanel.add(createInfoPanel(new String[] {
				" 电子邮箱:", " 个人主页:", " 家庭住址:", " 公司信息:" }, //
				new JComponent[] {
						infoEMailField, infoWebsiteField, infoHomeAddrField, infoCompany }), //
				BorderLayout.CENTER);

		JPanel xPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		//xPanel.add(new JLabel("一共 200 个联系人", JLabel.RIGHT), BorderLayout.NORTH);
		xPanel.add(bPanel, BorderLayout.CENTER);

		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(xPanel, BorderLayout.NORTH);
		//	infoNoteArea.setBorder(COMMON_BORDER);
		cPanel.add(infoNoteArea, BorderLayout.CENTER);
		//cPanel.setBorder(BorderFactory.createLineBorder(new JLabel().getBackground(), 2));
		cPanel.setMinimumSize(new Dimension(370, -1));
		return cPanel;
	}

	AddrBookFrame frame;

	public AddrBookPanel(AddrBookFrame frame) {
		this.frame = frame;
		searchField.getInsets();
		contactList.setBorder(BorderFactory.createEmptyBorder());
		//		JScrollPane scroll = new JScrollPane(contactList, //
		//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//		scroll.setBorder(BorderFactory.createEtchedBorder());
		JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		wPanel.add(searchField, BorderLayout.NORTH);
		wPanel.add(contactList, BorderLayout.CENTER);
		wPanel.setPreferredSize(new Dimension(135, -1));
		//wPanel.setBorder(BorderFactory.createLineBorder(new JLabel().getBackground(), 2));

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

		//		JSplitPane wtPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, wPanel, createInfoPanel());
		//		wtPane.setUI(new BasicSplitPaneUI());
		//		wtPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 5));
		JPanel wtPanel = new JPanel(new BorderLayout(5, 5));
		wtPanel.add(wPanel, BorderLayout.WEST);
		wtPanel.add(createInfoPanel(), BorderLayout.CENTER);
		wtPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 5));

		add(wtPanel, BorderLayout.CENTER);
		add(new JTextField("一共 200 个联系人"), BorderLayout.SOUTH);
	}
}
