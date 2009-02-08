/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.text.View;

/**
 * @author Michael Hagen
 */
public class BaseRadioButtonUI extends BasicRadioButtonUI {

    private static BaseRadioButtonUI radioButtonUI = null;

    public static ComponentUI createUI(JComponent c) {
        if (radioButtonUI == null) {
            radioButtonUI = new BaseRadioButtonUI();
        }
        return radioButtonUI;
    }

    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setRolloverEnabled(true);
        icon = UIManager.getIcon("RadioButton.icon");
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        Dimension size = c.getSize();
        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        Rectangle viewRect = new Rectangle(size);
        Rectangle iconRect = new Rectangle();
        Rectangle textRect = new Rectangle();

        Icon altIcon = b.getIcon();
        String text = SwingUtilities.layoutCompoundLabel(
                c,
                fm,
                b.getText(),
                altIcon != null ? altIcon : getDefaultIcon(),
                b.getVerticalAlignment(),
                b.getHorizontalAlignment(),
                b.getVerticalTextPosition(),
                b.getHorizontalTextPosition(),
                viewRect,
                iconRect,
                textRect,
                getDefaultTextIconGap(b));

        // fill background
        if (c.isOpaque()) {
            paintBackground(g, c);
        }

        paintIcon(g, c, iconRect);

        if (text != null) {
            paintText(g, c, text, textRect);
        }

        if (b.hasFocus() && b.isFocusPainted() && (textRect.width > 0) && (textRect.height > 0)) {
            paintFocus(g, textRect, size);
        }
    }

    protected void paintBackground(Graphics g, JComponent c) {
        g.setColor(c.getBackground());
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
        AbstractButton b = (AbstractButton) c;
        if (b.getIcon() != null) {
            b.getIcon().paintIcon(c, g, iconRect.x, iconRect.y);
        } else {
            getDefaultIcon().paintIcon(c, g, iconRect.x, iconRect.y);
        }
    }

    protected void paintText(Graphics g, JComponent c, String text, Rectangle textRect) {
        View v = (View) c.getClientProperty(BasicHTML.propertyKey);
        if (v != null) {
            Graphics2D g2D = (Graphics2D) g;
            Object savedRenderingHint = null;
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
            }
            v.paint(g, textRect);
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
            }
        } else {
            AbstractButton b = (AbstractButton) c;
            ButtonModel model = b.getModel();
            Font f = c.getFont();
            g.setFont(f);
            FontMetrics fm = g.getFontMetrics();
            int mnemIndex = model.getMnemonic();
            if (JTattooUtilities.getJavaVersion() >= 1.4) {
                mnemIndex = b.getDisplayedMnemonicIndex();
            }
            if (model.isEnabled()) {
                g.setColor(b.getForeground());
                JTattooUtilities.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
            } else {
                g.setColor(Color.white);
                JTattooUtilities.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x + 1, textRect.y + 1 + fm.getAscent());
                g.setColor(AbstractLookAndFeel.getDisabledForegroundColor());
                JTattooUtilities.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
            }
        }
    }

    protected void paintFocus(Graphics g, Rectangle t, Dimension d) {
        g.setColor(AbstractLookAndFeel.getFocusColor());
        BasicGraphicsUtils.drawDashedRect(g, t.x, t.y - 1, t.width + 1, t.height + 1);
    }
}
