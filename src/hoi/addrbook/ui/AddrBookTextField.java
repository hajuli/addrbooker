package hoi.addrbook.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class AddrBookTextField extends JPanel {

	private static final long serialVersionUID = -2073768620128729286L;

	public AddrBookTextField(String leadinfo) {
		this(leadinfo, true);
	}

	public AddrBookTextField(String leadinfo, final boolean left) {
		this.setLayout(new BorderLayout());
		JLabel b = new JLabel(leadinfo + "：");
		//b.setBorderPainted(false);
		b.setForeground(Color.BLUE);
		b.setFont(new Font("Dialog", Font.BOLD, 12));
		//this.add(b, BorderLayout.WEST);
		final JTextField a = new JTextField();
		a.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		a.setBackground(new JLabel().getBackground());
		a.setPreferredSize(new Dimension(60, -1));
		a.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				a.setBackground(Color.WHITE);
				a.setBorder(BorderFactory.createEtchedBorder());
			}

			@Override
			public void focusLost(FocusEvent e) {
				a.setBackground(new JLabel().getBackground());
				a.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}

		});
		this.add(a, BorderLayout.CENTER);
		final JLabel c = new JLabel(leadinfo + ":") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.setColor(new JLabel().getBackground().darker());
					//					if (left)
					//						g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
					//					else
					//						g.drawLine(0, 0, 0, getHeight());

				} catch (Exception ignore) {
				}
			}
		};
		//	c.setPreferredSize(new Dimension(60, -1) );
		c.setForeground(Color.BLUE);
		c.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(c.getSize());

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				c.setForeground(Color.BLUE);
				//c.setText("<html><u>link");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				c.setForeground(Color.GREEN);
				//c.setText("link");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//c.setText("<html><u>liŋk");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				//c.setText("liŋk");
			}

		});
		c.setForeground(Color.BLACK);
		c.setForeground(Color.GREEN);
		//c.setFont(c.getFont().deriveFont((float)16));
		//	c.setBorderPainted(false);
		//c.setFont(new Font("Dialog", Font.BOLD, 12));
		if (left)
			this.add(c, BorderLayout.WEST);
		else
			this.add(c, BorderLayout.EAST);
		//this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		//setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
}