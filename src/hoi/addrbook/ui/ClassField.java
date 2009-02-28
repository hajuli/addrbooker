package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ClassField extends JPanel {

	private static final long serialVersionUID = 7531465834540410473L;
	private JComboBox note = new JComboBox(new String[] {
			"", "a", "b", "a", "bsadfasdfadfasdfdddddddddddddddddddddddddddddd", "a", "b", "a", "ba", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b",
			"a", "b" }) {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	};

	public ClassField() { // http://www.wangchao.net.cn/bbsdetail_1756152.html
		super();
		setLayout(new BorderLayout());
		note.setBorder(BorderFactory.createEtchedBorder());
		//	note.setUI(new com.jgoodies.looks.windows.WindowsComboBoxUI());
		note.setUI(new hoi.addrbook.looks.ComboBoxUI());
		note.setRenderer(new ComplexCellRenderer());
		//	note.setUI(new MyComboBoxUI());
		note.setEditable(true);
		add(note, BorderLayout.CENTER);
	}
}

class ComplexCellRenderer implements ListCellRenderer {
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		renderer.setText(" " + (String) value);
		//		renderer.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		return renderer;
	}
}
