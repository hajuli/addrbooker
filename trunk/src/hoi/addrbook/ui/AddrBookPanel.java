package hoi.addrbook.ui;

import hoi.addrbook.data.AddrBookProps;
import hoi.addrbook.data.ContactProps;
import hoi.addrbook.data.ContactPropsEnum;
import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddrBookPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -1863142517865697366L;
	private static final Color BORDER_COLOR = new JLabel().getBackground();
	private static final int GAP_SIZE = 2;
	private static final int BLANK_SIZE = 5;

	private AddrBookBackupDialog backupDialog = null;
	private AddrBookSettingDialog settingDialog = null;

	private JButton tbarAddButton = new JButton("新增", ImageHelper.ICON_ADD);
	private JButton tbarSaveButton = new JButton("保存", ImageHelper.ICON_SAVE);
	private JButton tbarDeleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
	private JButton tbarSettingButton = new JButton("设置", ImageHelper.ICON_SETTING);
	private JButton tbarBackupButton = new JButton("备份", ImageHelper.ICON_BACKUP);
	private JButton tbarExitButton = new JButton("退出", ImageHelper.ICON_EXIT);

	private DefaultListModel contactListModel = new DefaultListModel();
	private JList contactList = new JList(contactListModel);

	private SreachBox searchBox = new SreachBox();
	private ContactListPanel contactListPanel = new ContactListPanel(contactList);
	private StatusPanel statusPanel = new StatusPanel();

	private InfoTextField infoNameField = new InfoTextField(ContactPropsEnum.NAME, "姓名");
	private InfoClassifyField infoClassifyField = new InfoClassifyField(ContactPropsEnum.CLASSIFY, "分组");
	private InfoTextField infoBirthdayLunarField = new InfoTextField(ContactPropsEnum.BIRTHDAY_LUNAR, "生日", "农历 1900-01-01");
	private InfoTextField infoBirthdaySolarField = new InfoTextField(ContactPropsEnum.BIRTHDAY_SOLAR, null, "阳历 1900-01-01");
	private InfoTimerField infoTimerField = new InfoTimerField(ContactPropsEnum.TIMER, "计时");
	private InfoTextField infoAgeField = new InfoTextField(ContactPropsEnum.AGE, "年龄");
	private InfoTextField infoQQField = new InfoTextField(ContactPropsEnum.QQ, "QQ");
	private InfoTextField infoMSNField = new InfoTextField(ContactPropsEnum.MSN, "MSN");
	private InfoTextField infoMobileField = new InfoTextField(ContactPropsEnum.MOBILE, "手机");
	private InfoTextField infoFetionField = new InfoTextField(ContactPropsEnum.FETION, "飞信");
	private InfoTextField infoEMailField = new InfoTextField(ContactPropsEnum.EMAIL, "电子邮箱");
	private InfoTextField infoWebsiteField = new InfoTextField(ContactPropsEnum.WEBSITE, "个人主页");
	private InfoAddrField infoHomeAddrField = new InfoAddrField(ContactPropsEnum.HOMEADDR, "家庭住址");
	private InfoTextField infoCompanyField = new InfoTextField(ContactPropsEnum.COMPANY, "公司信息");
	private InfoNotesArea infoNotesArea = new InfoNotesArea(ContactPropsEnum.NOTES);

	private AccessInterface[] infoFields = new AccessInterface[] {
			infoNameField, infoClassifyField, infoBirthdayLunarField, infoBirthdaySolarField, infoTimerField, //
			infoQQField, infoMSNField, infoMobileField, infoFetionField, infoEMailField, //
			infoWebsiteField, infoHomeAddrField, infoCompanyField, infoNotesArea };

	private AddrBookProps addrbook = AddrBookProps.load();

	public AddrBookPanel() {
		contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		wPanel.add(searchBox, BorderLayout.NORTH);
		wPanel.add(contactListPanel, BorderLayout.CENTER);
		wPanel.setPreferredSize(new Dimension(135, 1));

		JToolBar toolbar = new JToolBar("工具条");
		toolbar.setFloatable(false);
		toolbar.setLayout(new GridLayout(1, 6));
		toolbar.add(tbarAddButton);
		toolbar.add(tbarSaveButton);
		toolbar.add(tbarDeleteButton);
		toolbar.add(tbarSettingButton);
		toolbar.add(tbarBackupButton);
		toolbar.add(tbarExitButton);

		JPanel cPanel = new JPanel(new BorderLayout(BLANK_SIZE, BLANK_SIZE));
		cPanel.add(wPanel, BorderLayout.WEST);
		cPanel.add(createInfoPanel(), BorderLayout.CENTER);
		cPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BLANK_SIZE));

		setLayout(new BorderLayout(0, 0));
		add(toolbar, BorderLayout.NORTH);
		add(cPanel, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);

		setActionListener();
		refreshContactList();
		add();
		updateToolBar();
		searchBox.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				System.out.println(contactList.getSelectedValue());
				//contactList.setSelectedIndex(-1);
				//contactListModel.fireSelected(contactList.getSelectedIndex());
			}

			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void clearAllInfo() {
		infoNameField.setText(null);
		infoAgeField.setText(null);
	}

	private void add() {
		searchBox.setText(null);
		contactList.clearSelection();
		clearAllInfo();
	}

	private void save() {

	}

	private void edit() {

	}

	private void delete() {
		contactListModel.removeElementAt(contactList.getSelectedIndex());
	}

	private void updateToolBar() {
		boolean k = contactList.getSelectedIndex() != -1;
		//tbarEditButton.setEnabled(k);
		//tbarDeleteButton.setEnabled(k);
	}

	private void setActionListener() {
		tbarAddButton.addActionListener(this);
		tbarSaveButton.addActionListener(this);
		tbarExitButton.addActionListener(this);
		tbarDeleteButton.addActionListener(this);
		tbarSettingButton.addActionListener(this);
		tbarBackupButton.addActionListener(this);
		contactList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateToolBar();
			}
		});
	}

	private void refreshContactList() {
		for (String key : addrbook.keySet())
			contactListModel.addElement(key);
		AddrBookProps.save(addrbook);
	}

	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == tbarSettingButton) {
			if (settingDialog == null)
				settingDialog = new AddrBookSettingDialog((Frame) getTopLevelAncestor());
			settingDialog.setVisible(true);
		} else if (object == tbarBackupButton) {
			if (backupDialog == null)
				backupDialog = new AddrBookBackupDialog((Frame) getTopLevelAncestor());
			backupDialog.setVisible(true);
		} else if (object == tbarSaveButton) {
			ContactProps contact = new ContactProps();
			for (AccessInterface infoField : infoFields)
				contact.setProperty(infoField.getContactKey(), infoField.getContent());
			addrbook.put(contact.getProperty(ContactPropsEnum.NAME), contact);
			refreshContactList();
		} else if (object == tbarAddButton) {
			add();
		} else if (object == tbarExitButton) {
			edit();
		} else if (object == tbarDeleteButton) {
			delete();
		}
	}

	private JPanel createTempPanel(JComponent... comps) {
		JPanel aPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
		JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
		for (JComponent comp : comps) {
			JLabel label = new JLabel(String.format(" %s: ", comp.getName()), JLabel.RIGHT);
			if (comp.getName() == null) // 避免匿名组件
				label.setText(null);
			label.setBorder(BorderFactory.createEtchedBorder());
			aPanel.add(label);
			bPanel.add(comp);
		}
		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(aPanel, BorderLayout.WEST);
		cPanel.add(bPanel, BorderLayout.CENTER);
		return cPanel;
	}

	private JPanel createInfoPanel() {
		JPanel aPanel = new JPanel(new GridLayout(1, 2, GAP_SIZE, GAP_SIZE));
		aPanel.add(createTempPanel(infoNameField, infoBirthdayLunarField, infoBirthdaySolarField, infoQQField, infoMobileField));
		aPanel.add(createTempPanel(infoClassifyField, infoTimerField, infoAgeField, infoMSNField, infoFetionField));

		JPanel bPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		bPanel.add(aPanel, BorderLayout.NORTH);
		bPanel.add(createTempPanel(infoEMailField, infoWebsiteField, infoHomeAddrField, infoCompanyField), BorderLayout.CENTER);

		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(bPanel, BorderLayout.NORTH);
		cPanel.add(infoNotesArea, BorderLayout.CENTER);
		return cPanel;
	}
}
