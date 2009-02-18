package hoi.addrbook.ui;

import hoi.addrbook.icons.ImageHelper;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import javax.swing.border.LineBorder;

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
		JPanel aPanel = new JPanel(new GridLayout(names.length, 1, GAP_SIZE, GAP_SIZE));
		for (String name : names) {
			JLabel label = new JLabel(" " + name + ":", JLabel.RIGHT);
			label.setBorder(BorderFactory.createEtchedBorder());
			aPanel.add(label); // 两端对齐??
		}
		JPanel bPanel = new JPanel(new GridLayout(comps.length, 1, GAP_SIZE, GAP_SIZE));
		for (JComponent comp : comps) {
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
		aPanel.add(createInfoPanel(new String[] { "姓名", "生日", "QQ", "手机" }, //
				new JComponent[] { infoNameField, infoBirthdayField, infoQQField, infoMobileField }));
		aPanel.add(createInfoPanel(new String[] { "联系", "年龄", "MSN", "飞信" }, //
				new JComponent[] { infoContactField, infoAgeField, infoMSNField, infoFetionField }));

		JPanel bPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		bPanel.add(aPanel, BorderLayout.NORTH);
		bPanel.add(createInfoPanel(new String[] { "电子邮箱", "个人主页", "家庭住址", "公司名称", "公司地址" }, //
				new JComponent[] { infoEMailField, infoWebsiteField, infoHomeAddrField, infoCompanyNameField, infoCompanyAddrField }), //
				BorderLayout.CENTER);

		JPanel cPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
		cPanel.add(bPanel, BorderLayout.NORTH);
		infoNoteArea.setBorder(COMMON_BORDER);
		cPanel.add(infoNoteArea, BorderLayout.CENTER);
		cPanel.setBorder(BorderFactory.createEtchedBorder());
		cPanel.setMinimumSize(new Dimension(320, -1));
		return cPanel;
	}

	public AddrBookPanel() {
		filterField.setBorder(COMMON_BORDER);
		filterField.getInsets();
		contactList.setBorder(BorderFactory.createEmptyBorder());
		JScrollPane scroll = new JScrollPane(contactList);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		JPanel wPanel = new JPanel(new BorderLayout(GAP_SIZE, GAP_SIZE));
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
		//add(wtPane, BorderLayout.CENTER);
		add(new JLabel() {
			public void paint(Graphics g) {
				new Arrow().draw((Graphics2D) g);
			}
		}, BorderLayout.CENTER);
	}
}

class Arrow // 箭头类   
{
	void draw(Graphics2D g2d) {
		int R = 0, G = 0, B = 0;
		int x1 = 10, y1 = 10, x2 = 250, y2 = 250;
		float stroke = 2;

		g2d.setPaint(new Color(R, G, B));
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		// 利用向量来进行计算   
		int len = 50; // 箭头的边长   
		int x0 = 0, y0 = 0;
		double dx = x2 - x0, dy = y2 - y0;

		//共线 模长为len   
		//0 = (x2 -x1)*dy  - dx*(y2 -y1); dx*dx + dy*dy = len*len   
		double mAB = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
		dx = (x2 - x1) * len / java.lang.Math.sqrt(mAB);
		dy = (y2 - y1) * len / java.lang.Math.sqrt(mAB);
		x0 = (int) (x2 - dx);
		y0 = (int) (y2 - dy);

		int x3 = 0, y3 = 0, x4, y4;
		dx = x3 - x0;
		dy = y3 - y0;
		double mCB = (x2 - x0) * (x2 - x0) + (y2 - y0) * (y2 - y0);
		dx = -(y2 - y0) * len / java.lang.Math.sqrt(mCB);
		dy = (x2 - x0) * len / java.lang.Math.sqrt(mCB);
		x3 = (int) (dx + x0);
		y3 = (int) (dy + y0);
		x4 = 2 * x0 - x3;
		y4 = 2 * y0 - y3;

		g2d.drawLine(x1, y1, x0, y0);
		g2d.setPaint(new Color(255, 0, 0));
		Polygon p = new Polygon();
		int x33 = (x3 * 1 + x4 * 3) / 4;
		int y33 = (y3 * 1 + y4 * 3) / 4;
		int x44 = (x3 * 3 + x4 * 1) / 4;
		int y44 = (y3 * 3 + y4 * 1) / 4;
		p.addPoint(x2, y2);
		p.addPoint(x33, y33);
		p.addPoint(x44, y44);
		g2d.drawPolygon(p);
		//g2d.fillPolygon(p);
	}
}
