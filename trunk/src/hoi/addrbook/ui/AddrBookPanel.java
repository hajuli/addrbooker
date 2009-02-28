package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class AddrBookPanel extends JPanel {

	private static final long serialVersionUID = -1863142517865697366L;
	private static final Color BORDER_COLOR = new JLabel().getBackground();
	private static final int GAP_SIZE = 2;
	private static final int BLANK_SIZE = 5;

	private JButton tbarAddButton = new JButton("添加", ImageHelper.ICON_ADD);
	private JButton tbarSaveButton = new JButton("保存", ImageHelper.ICON_SAVE);
	private JButton tbarEditButton = new JButton("编辑", ImageHelper.ICON_EDIT);
	private JButton tbarDeleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
	private JButton tbarSettingButton = new JButton("设置", ImageHelper.ICON_SETTING);
	private JButton tbarBackupButton = new JButton("备份", ImageHelper.ICON_BACKUP);

	private SreachBox searchBox = new SreachBox();
	private ContactList contactList = new ContactList();
	private StatusPanel statusPanel = new StatusPanel();

	private InfoTextField infoNameField = new InfoTextField("姓名");
	private InfoClassifyField infoClassifyField = new InfoClassifyField("分组");
	private InfoTextField infoBirthdayLunarField = new InfoTextField("生日", "农历 1900-01-01");
	private InfoTextField infoBirthdaySolarField = new InfoTextField(null, "阳历 1900-01-01");
	private InfoTimerField infoTimerField = new InfoTimerField("计时");
	private InfoTextField infoAgeField = new InfoTextField("年龄");
	private InfoTextField infoQQField = new InfoTextField("QQ");
	private InfoTextField infoMSNField = new InfoTextField("MSN");
	private InfoTextField infoMobileField = new InfoTextField("手机");
	private InfoTextField infoFetionField = new InfoTextField("飞信");
	private InfoTextField infoEMailField = new InfoTextField("电子邮箱");
	private InfoTextField infoWebsiteField = new InfoTextField("个人主页");
	private InfoAddrField infoHomeAddrField = new InfoAddrField("家庭住址");
	private InfoTextField infoCompanyField = new InfoTextField("公司信息");
	private InfoNotesArea infoNotesArea = new InfoNotesArea();

	public AddrBookPanel() {
		JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		wPanel.add(searchBox, BorderLayout.NORTH);
		wPanel.add(contactList, BorderLayout.CENTER);
		wPanel.setPreferredSize(new Dimension(135, 1));

		JToolBar toolbar = new JToolBar("工具条");
		toolbar.setFloatable(false);
		toolbar.setLayout(new GridLayout(1, 6));
		toolbar.add(tbarAddButton);
		toolbar.add(tbarSaveButton);
		toolbar.add(tbarEditButton);
		toolbar.add(tbarDeleteButton);
		toolbar.add(tbarSettingButton);
		toolbar.add(tbarBackupButton);

		JPanel cPanel = new JPanel(new BorderLayout(BLANK_SIZE, BLANK_SIZE));
		cPanel.add(wPanel, BorderLayout.WEST);
		cPanel.add(createInfoPanel(), BorderLayout.CENTER);
		cPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BLANK_SIZE));

		setLayout(new BorderLayout(0, 0));
		add(toolbar, BorderLayout.NORTH);
		add(cPanel, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);
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
