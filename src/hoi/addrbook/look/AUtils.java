/*
 * Copyright (c) 2001-2005 JGoodies Karsten Lentzsch. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of JGoodies Karsten Lentzsch nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

package hoi.addrbook.look;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.UIManager;

/**
 * Consists exclusively of static methods that provide convenience behavior.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.3 $
 */

public final class AUtils {

    static void drawDark3DBorder(Graphics g, int x, int y, int w, int h) {
        drawFlush3DBorder(g, x, y, w, h);
        g.setColor(ALookAndFeel.getControl());
        g.drawLine(x + 1, y + 1, 1, h - 3);
        g.drawLine(y + 1, y + 1, w - 3, 1);
    }

    static void drawDisabledBorder(Graphics g, int x, int y, int w, int h) {
        g.setColor(ALookAndFeel.getControlShadow());
        drawRect(g, x, y, w - 1, h - 1);
    }

    /*
     * Unlike <code>MetalUtils</code> we first draw with highlight then dark
     * shadow
     */
    static void drawFlush3DBorder(Graphics g, int x, int y, int w, int h) {
        g.translate(x, y);
        g.setColor(ALookAndFeel.getControlHighlight());
        drawRect(g, 1, 1, w - 2, h - 2);
        g.drawLine(0, h - 1, 0, h - 1);
        g.drawLine(w - 1, 0, w - 1, 0);
        g.setColor(ALookAndFeel.getControlDarkShadow());
        drawRect(g, 0, 0, w - 2, h - 2);
        g.translate(-x, -y);
    }

    /*
     * Copied from <code>MetalUtils</code>.
     */
    static void drawPressed3DBorder(Graphics g, int x, int y, int w, int h) {
        g.translate(x, y);
        drawFlush3DBorder(g, 0, 0, w, h);
        g.setColor(ALookAndFeel.getControlShadow());
        g.drawLine(1, 1, 1, h - 3);
        g.drawLine(1, 1, w - 3, 1);
        g.translate(-x, -y);
    }

    /*
     * Copied from <code>MetalUtils</code>.
     */
    static void drawButtonBorder(Graphics g, int x, int y, int w, int h, boolean active) {
        if (active) {
            drawActiveButtonBorder(g, x, y, w, h);
        } else {
            drawFlush3DBorder(g, x, y, w, h);
        }
    }

    /*
     * Copied from <code>MetalUtils</code>.
     */
    static void drawActiveButtonBorder(Graphics g, int x, int y, int w, int h) {
        drawFlush3DBorder(g, x, y, w, h);
        g.setColor(ALookAndFeel.getPrimaryControl());
        g.drawLine(x + 1, y + 1, x + 1, h - 3);
        g.drawLine(x + 1, y + 1, w - 3, x + 1);
        g.setColor(ALookAndFeel.getPrimaryControlDarkShadow());
        g.drawLine(x + 2, h - 2, w - 2, h - 2);
        g.drawLine(w - 2, y + 2, w - 2, h - 2);
    }

    /*
     * Modified edges.
     */
    static void drawDefaultButtonBorder(Graphics g, int x, int y, int w, int h, boolean active) {
        drawButtonBorder(g, x + 1, y + 1, w - 1, h - 1, active);
        g.translate(x, y);
        g.setColor(ALookAndFeel.getControlDarkShadow());
        drawRect(g, 0, 0, w - 3, h - 3);
        g.drawLine(w - 2, 0, w - 2, 0);
        g.drawLine(0, h - 2, 0, h - 2);
        g.setColor(ALookAndFeel.getControl());
        g.drawLine(w - 1, 0, w - 1, 0);
        g.drawLine(0, h - 1, 0, h - 1);
        g.translate(-x, -y);
    }

    static void drawDefaultButtonPressedBorder(Graphics g, int x, int y, int w, int h) {
        drawPressed3DBorder(g, x + 1, y + 1, w - 1, h - 1);
        g.translate(x, y);
        g.setColor(ALookAndFeel.getControlDarkShadow());
        drawRect(g, 0, 0, w - 3, h - 3);
        g.drawLine(w - 2, 0, w - 2, 0);
        g.drawLine(0, h - 2, 0, h - 2);
        g.setColor(ALookAndFeel.getControl());
        g.drawLine(w - 1, 0, w - 1, 0);
        g.drawLine(0, h - 1, 0, h - 1);
        g.translate(-x, -y);
    }

    static void drawThinFlush3DBorder(Graphics g, int x, int y, int w, int h) {
        g.translate(x, y);
        g.setColor(ALookAndFeel.getControlHighlight());
        g.drawLine(0, 0, w - 2, 0);
        g.drawLine(0, 0, 0, h - 2);
        g.setColor(ALookAndFeel.getControlDarkShadow());
        g.drawLine(w - 1, 0, w - 1, h - 1);
        g.drawLine(0, h - 1, w - 1, h - 1);
        g.translate(-x, -y);
    }

    static void drawThinPressed3DBorder(Graphics g, int x, int y, int w, int h) {
        g.translate(x, y);
        g.setColor(ALookAndFeel.getControlDarkShadow());
        g.drawLine(0, 0, w - 2, 0);
        g.drawLine(0, 0, 0, h - 2);
        g.setColor(ALookAndFeel.getControlHighlight());
        g.drawLine(w - 1, 0, w - 1, h - 1);
        g.drawLine(0, h - 1, w - 1, h - 1);
        g.translate(-x, -y);
    }

    /*
     * Convenience function for determining ComponentOrientation. Helps us avoid
     * having Munge directives throughout the code.
     */
    static boolean isLeftToRight(Component c) {
        return c.getComponentOrientation().isLeftToRight();
    }

    // 3D Effects ***********************************************************************

    /**
     * Checks and returns whether the specified component type has 3D effects.
     * 
     * @param keyPrefix
     *            the prefix of the key used to lookup the setting
     * @return true if the component type shall be rendered with a 3D effect
     * @see #force3D(JComponent)
     * @see #forceFlat(JComponent)
     */
    static boolean is3D(String keyPrefix) {
        Object value = UIManager.get(keyPrefix + "is3DEnabled");
        return Boolean.TRUE.equals(value);
    }

    /**
     * Checks and returns whether we have a custom hint that forces the 3D mode.
     * 
     * @param c
     *            the component to inspect
     * @return true if the given component has a 3D hint set
     * @see #forceFlat(JComponent)
     */
    static boolean force3D(JComponent c) {
        Object value = c.getClientProperty(ALookAndFeel.IS_3D_KEY);
        return Boolean.TRUE.equals(value);
    }

    /**
     * Checks and returns whether we have a custom hint that prevents the 3D
     * mode.
     * 
     * @param c
     *            the component to inspect
     * @return true if the given component has a flat hint set
     * @see #force3D(JComponent)
     */
    static boolean forceFlat(JComponent c) {
        Object value = c.getClientProperty(ALookAndFeel.IS_3D_KEY);
        return Boolean.FALSE.equals(value);
    }

    // Low level graphics ***************************************************

    /*
     * An optimized version of Graphics.drawRect.
     */
    private static void drawRect(Graphics g, int x, int y, int w, int h) {
        g.fillRect(x, y, w + 1, 1);
        g.fillRect(x, y + 1, 1, h);
        g.fillRect(x + 1, y + h, w, 1);
        g.fillRect(x + w, y + 1, 1, h);
    }

}