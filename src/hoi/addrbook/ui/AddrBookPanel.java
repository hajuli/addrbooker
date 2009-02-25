package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AddrBookPanel extends JPanel {

	private static final long serialVersionUID = -4479991916447167635L;

	private static final Color BORDER_COLOR = new JLabel().getBackground();
	private static final int GAP_SIZE = 2;
	private static final Border COMMON_BORDER = BorderFactory.createCompoundBorder( //
			BorderFactory.createEtchedBorder(), //
			new EmptyBorder(new Insets(1, 1, 1, 1)));

	private JButton tbarAddButton = new JButton("添加", ImageHelper.ICON_ADD);
	private JButton tbarSaveButton = new JButton("保存", ImageHelper.ICON_SAVE);
	private JButton tbarEditButton = new JButton("编辑", ImageHelper.ICON_EDIT);
	private JButton tbarDeleteButton = new JButton("删除", ImageHelper.ICON_DELETE);
	private JButton tbarSettingButton = new JButton("设置", ImageHelper.ICON_SETTING) {
		{
			this.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new AddrBookSettingDialog(frame).setVisible(true);
				}

			});
		}
	};
	private JButton tbarBackupButton = new JButton("备份", ImageHelper.ICON_BACKUP) {
		{
			this.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new AddrBookBackupDialog(frame).setVisible(true);
				}

			});
		}
	};

	private JTextField filterField = new JTextField() {
		public void paint(Graphics g) {
			super.paint(g);
			if (getText().equals("")) {
				String Info = "键入内容 搜索";
				FontMetrics metrics = getFontMetrics(g.getFont());
				int height = metrics.getHeight();

				//g.drawRect(0, 0, getWidth() - 2, getHeight() - 2);
				System.out.println(this.isFocusOwner());
				if (this.isFocusOwner())
					g.setColor(Color.GRAY);
				else
					g.setColor(this.getBackground());
				g.drawString(Info, 5, (getHeight() - 10 - height) / 2 + height);
			}
		}

		public void repaint(Graphics g) {
			paint(g);
		}
	};
	private JList contactList = new JList(new String[] { "姐姐", "妈妈", "姐姐", "叔叔#妈妈", "妈妈妈妈", "妈妈妈妈", "妈妈妈妈妈", "妈妈妈妈妈", "妈妈妈妈妈", "妈妈妈妈妈", "妈妈妈妈妈", "妈妈妈妈妈", });

	private StringField infoNameField = new StringField("杨全海");
	private StringField fzField = new StringField("家人");
	private ContactField infoContactField = new ContactField();
	private StringField infoBirthdayField = new StringField("");
	private StringField jlField = new StringField("");
	private StringField infoAgeField = new StringField("23");
	private StringField infoQQField = new StringField("332910789");
	private StringField infoMSNField = new StringField("haihoing@live.cn");
	private StringField infoMobileField = new StringField("13825622953");
	private StringField infoFetionField = new StringField("293401164");
	private StringField infoEMailField = new StringField("haihoing@gmail.com");
	private StringField infoWebsiteField = new StringField("http://332910789.qzone.qq.com");
	private AddressField infoHomeAddrField = new AddressField("珠海吉大海湾10栋8A");
	private StringField infoCompany = new StringField("http://332910789.qzone.qq.com");
	private AddressField infoCompanyNameField = new AddressField("珠海金山软件; 珠海吉大石花西路601号");
	private JTextArea infoNoteArea = new JTextArea("MSG");

	private JPanel createInfoPanel(String[] names, JComponent[] comps) {
		JPanel aPanel = new JPanel(new GridLayout(names.length, 1, GAP_SIZE, GAP_SIZE));
		for (String name : names) {
			JLabel label = new JLabel(name + " ", JLabel.RIGHT);
			label.setBorder(BorderFactory.createEtchedBorder());
			aPanel.add(label); // 两端对齐??
		}
		JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
		for (JComponent comp : comps) {
			if (comp != infoBirthdayField && comp != infoContactField && //
					comp != infoHomeAddrField && comp != infoCompanyNameField && //
					comp != infoCompany && comp != jlField)
				comp.setBorder(COMMON_BORDER);
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
		aPanel.add(createInfoPanel(new String[] { "<html><strong> 姓名:", " 生日:", " ", " QQ:", " 手机:" }, //
				new JComponent[] { infoNameField, infoBirthdayField, jlField, infoQQField, infoMobileField }));
		aPanel.add(createInfoPanel(new String[] { " 分组:", " 计时:", " 年龄:", " MSN:", " 飞信:" }, //
				new JComponent[] { fzField, infoContactField, infoAgeField, infoMSNField, infoFetionField }));

		JPanel bPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		bPanel.add(aPanel, BorderLayout.NORTH);
		bPanel.add(createInfoPanel(new String[] { " 电子邮箱:", " 个人主页:", " 家庭住址:", " 公司信息:" }, //
				new JComponent[] { infoEMailField, infoWebsiteField, infoHomeAddrField, infoCompany }), //
				BorderLayout.CENTER);

		JPanel xPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		//xPanel.add(new JLabel("一共 200 个联系人", JLabel.RIGHT), BorderLayout.NORTH);
		xPanel.add(bPanel, BorderLayout.CENTER);

		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(xPanel, BorderLayout.NORTH);
		//	infoNoteArea.setBorder(COMMON_BORDER);
		cPanel.add(new JScrollPane(infoNoteArea, //
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		cPanel.setBorder(BorderFactory.createEtchedBorder());
		cPanel.setMinimumSize(new Dimension(370, -1));
		return cPanel;
	}

	AddrBookFrame frame;

	public AddrBookPanel(AddrBookFrame frame) {
		this.frame = frame;
		filterField.setBorder(COMMON_BORDER);
		filterField.getInsets();
		contactList.setBorder(BorderFactory.createEmptyBorder());
		JScrollPane scroll = new JScrollPane(contactList, //
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		wPanel.add(filterField, BorderLayout.NORTH);
		wPanel.add(scroll, BorderLayout.CENTER);
		wPanel.setMinimumSize(new Dimension(130, -1));
		wPanel.setBorder(BorderFactory.createEtchedBorder());

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
		add(new JTextField("一共 200 个联系人"), BorderLayout.SOUTH);
	}
}
