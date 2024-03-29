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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

/**
 * The JGoodies Plastic Look and Feel implementation of <code>ComboBoxUI</code>.
 * Has the same height as text fields - unless you change the renderer.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.5 $
 */

public final class AComboBoxUI extends MetalComboBoxUI {

    public static ComponentUI createUI(JComponent b) {
        return new AComboBoxUI();
    }

    /**
     * Creates the editor that is to be used in editable combo boxes. This
     * method only gets called if a custom editor has not already been installed
     * in the JComboBox.
     */
    protected ComboBoxEditor createEditor() {
        return new AComboBoxEditor.UIResource();
    }

    protected ComboPopup createPopup() {
        return new PlasticComboPopup(comboBox);
    }

    /**
     * Gets the insets from the JComboBox.
     */
    private Insets getEditorInsets() {
        if (editor instanceof JComponent) {
            return ((JComponent) editor).getInsets();
        }
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Computes and returns the width of the arrow button in editable state. The
     * perceived width shall be equal to the width of a scroll bar. Therefore we
     * subtract a pixel that is perceived as part of the arrow button but that
     * is painted by the editor's border.
     * 
     * @return the width of the arrow button in editable state
     */
    private int getEditableButtonWidth() {
        return UIManager.getInt("ScrollBar.width") - 1;
    }

    /**
     * Overriden to correct the combobox height.
     */
    public Dimension getMinimumSize(JComponent c) {
        if (!isMinimumSizeDirty) {
            return new Dimension(cachedMinimumSize);
        }

        Dimension size = null;

        if (!comboBox.isEditable() && arrowButton != null && arrowButton instanceof AComboBoxButton) {

            AComboBoxButton button = (AComboBoxButton) arrowButton;
            Insets buttonInsets = button.getInsets();
            Insets buttonMargin = button.getMargin();
            Insets insets = comboBox.getInsets();
            size = getDisplaySize();

            // System.out.println("button insets=" + buttonInsets);
            // System.out.println("button margin=" + buttonMargin);

            /*
             * The next line will lead to good results if used with standard
             * renderers; In case, a custom renderer is used, it may use a
             * different height, and we can't help much.
             */
            size.height += 2;
            size.width += insets.left + insets.right;
            size.width += buttonInsets.left + buttonInsets.right;
            size.width += buttonMargin.left + buttonMargin.right;
            size.width += button.getComboIcon().getIconWidth();
            size.height += insets.top + insets.bottom;
            size.height += buttonInsets.top + buttonInsets.bottom;
        } else if (comboBox.isEditable() && arrowButton != null && editor != null) {

            // The display size does often not include the editor's insets
            size = getDisplaySize();
            Insets insets = comboBox.getInsets();
            Insets editorInsets = getEditorInsets();
            int buttonWidth = getEditableButtonWidth();

            size.width += insets.left + insets.right;
            size.width += editorInsets.left + editorInsets.right - 1;
            size.width += buttonWidth;
            size.height += insets.top + insets.bottom;
        } else {
            size = super.getMinimumSize(c);
        }

        cachedMinimumSize.setSize(size.width, size.height);
        isMinimumSizeDirty = false;

        return new Dimension(cachedMinimumSize);
    }

    /**
     * Creates and answers the arrow button that is to be used in the combo box.
     * <p>
     * 
     * Overridden to use a button that can have a pseudo 3D effect.
     */
    protected JButton createArrowButton() {
        AComboBoxButton temp = new AComboBoxButton(comboBox, AIconFactory.getComboBoxButtonIcon(), comboBox.isEditable(), currentValuePane, listBox);
        temp.setBorder(BorderFactory.createEtchedBorder());
        return temp;
    }

    /**
     * Creates a layout manager for managing the components which make up the
     * combo box.
     * <p>
     * 
     * Overriden to use a layout that has a fixed width arrow button.
     * 
     * @return an instance of a layout manager
     */
    protected LayoutManager createLayoutManager() {
        return new PlasticComboBoxLayoutManager();
    }

    // Painting ***************************************************************

    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            g.setColor(c.getBackground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
            if (isToolBarComboBox(c)) {
                c.setOpaque(false);
            }
        }
        paint(g, c);
    }

    /**
     * Checks and answers if this combo is in a tool bar.
     * 
     * @param c
     *            the component to check
     * @return true if in tool bar, false otherwise
     */
    protected boolean isToolBarComboBox(JComponent c) {
        Container parent = c.getParent();
        return parent != null && (parent instanceof JToolBar || parent.getParent() instanceof JToolBar);
    }

    // Helper Classes *********************************************************

    /**
     * This layout manager handles the 'standard' layout of combo boxes. It puts
     * the arrow button to the right and the editor to the left. If there is no
     * editor it still keeps the arrow button to the right.
     * 
     * Overriden to use a fixed arrow button width.
     */
    private class PlasticComboBoxLayoutManager extends MetalComboBoxUI.MetalComboBoxLayoutManager {

        public void layoutContainer(Container parent) {
            JComboBox cb = (JComboBox) parent;

            // Use superclass behavior if the combobox is not editable.
            if (!cb.isEditable()) {
                super.layoutContainer(parent);
                return;
            }

            int width = cb.getWidth();
            int height = cb.getHeight();

            Insets insets = getInsets();
            int buttonWidth = getEditableButtonWidth();
            int buttonHeight = height - (insets.top + insets.bottom);

            if (arrowButton != null) {
                if (cb.getComponentOrientation().isLeftToRight()) {
                    arrowButton.setBounds(width - (insets.right + buttonWidth), insets.top, buttonWidth, buttonHeight);
                } else {
                    arrowButton.setBounds(insets.left, insets.top, buttonWidth, buttonHeight);
                }
            }
            if (editor != null) {
                editor.setBounds(rectangleForCurrentValue());
            }
        }
    }

    // Required if we have a combobox button that does not extend MetalComboBoxButton
    public PropertyChangeListener createPropertyChangeListener() {
        return new PlasticPropertyChangeListener();
    }

    // Overriden to use PlasticComboBoxButton instead of a MetalComboBoxButton.
    // Required if we have a combobox button that does not extend MetalComboBoxButton
    private class PlasticPropertyChangeListener extends BasicComboBoxUI.PropertyChangeHandler {

        public void propertyChange(PropertyChangeEvent e) {
            super.propertyChange(e);
            String propertyName = e.getPropertyName();

            if (propertyName.equals("editable")) {
                AComboBoxButton button = (AComboBoxButton) arrowButton;
                button.setIconOnly(comboBox.isEditable());
                comboBox.repaint();
            } else if (propertyName.equals("background")) {
                Color color = (Color) e.getNewValue();
                arrowButton.setBackground(color);
                listBox.setBackground(color);

            } else if (propertyName.equals("foreground")) {
                Color color = (Color) e.getNewValue();
                arrowButton.setForeground(color);
                listBox.setForeground(color);
            }
        }
    }

    // Differs from the MetalComboPopup in that it uses the standard popmenu border.
    @SuppressWarnings("deprecation")
    private class PlasticComboPopup extends MetalComboPopup {

        private static final long serialVersionUID = -7654624163275114451L;

        private PlasticComboPopup(JComboBox combo) {
            super(combo);
        }

        /**
         * Configures the list created by #createList().
         */
        protected void configureList() {
            super.configureList();
            list.setForeground(UIManager.getColor("MenuItem.foreground"));
            list.setBackground(UIManager.getColor("MenuItem.background"));
        }

        /**
         * Configures the JScrollPane created by #createScroller().
         */
        protected void configureScroller() {
            super.configureScroller();
            scroller.getVerticalScrollBar().putClientProperty(MetalScrollBarUI.FREE_STANDING_PROP, Boolean.FALSE);
        }

    }

}