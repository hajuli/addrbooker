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

package hoi.addrbook.looks;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Initializes class and component defaults for the JGoodies Plastic
 * look&amp;feel.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.7 $
 */
public class PlasticLookAndFeel extends MetalLookAndFeel {

	// System and Client Property Keys ****************************************

	private static final long serialVersionUID = 7215321672137400256L;

	/**
	 * Client property key to set a border style - shadows the header style.
	 */
	public static final String BORDER_STYLE_KEY = "Plastic.borderStyle";

	/**
	 * Client property key to disable the pseudo 3D effect.
	 */
	public static final String IS_3D_KEY = "Plastic.is3D";

	/**
	 * A System property key to set the default theme.
	 */
	public static final String DEFAULT_THEME_KEY = "Plastic.defaultTheme";

	/**
	 * A System property key that indicates that the high contrast focus colors
	 * shall be choosen - if applicable. If not set, some focus colors look good
	 * but have low contrast. Basically, the low contrast scheme uses the
	 * Plastic colors before 1.0.7, and the high contrast scheme is 1.0.7 -
	 * 1.0.9.
	 */
	public static final String HIGH_CONTRAST_FOCUS_ENABLED_KEY = "Plastic.highContrastFocus";

	/**
	 * A System property key for the rendering style of the Plastic TabbedPane.
	 * Valid values are: <tt>default</tt> for the Plastic 1.0 tabs, and
	 * <tt>metal</tt> for the Metal L&amp;F tabs.
	 */
	protected static final String TAB_STYLE_KEY = "Plastic.tabStyle";

	/**
	 * A System property value that indicates that Plastic shall render tabs in
	 * the Plastic 1.0 style. This is the default.
	 */
	public static final String TAB_STYLE_DEFAULT_VALUE = "default";

	/**
	 * A System property value that indicates that Plastic shall render tabs in
	 * the Metal L&amp;F style.
	 */
	public static final String TAB_STYLE_METAL_VALUE = "metal";

	// State *****************************************************************

	/** The look-global state for the 3D enabledment. */
	private static boolean is3DEnabled = false;

	public String getID() {
		return "JGoodies Plastic";
	}

	public String getName() {
		return "JGoodies Plastic";
	}

	public String getDescription() {
		return "The JGoodies Plastic Look and Feel" + " - \u00a9 2001-2005 JGoodies Karsten Lentzsch";
	}

	// Special Properties ***************************************************

	protected boolean is3DEnabled() {
		return is3DEnabled;
	}

	public static void set3DEnabled(boolean b) {
		is3DEnabled = b;
	}

	// Overriding Superclass Behavior ***************************************

	/**
	 * Unlike my superclass I register a unified shadow color. This color is
	 * used by my ThinBevelBorder class.
	 * 
	 * @param table
	 *            the UIDefaults table to work with
	 */
	protected void initSystemColorDefaults(UIDefaults table) {
		super.initSystemColorDefaults(table);
		table.put("unifiedControlShadow", table.getColor("controlDkShadow"));
		table.put("primaryControlHighlight", getPrimaryControlHighlight());
	}

}