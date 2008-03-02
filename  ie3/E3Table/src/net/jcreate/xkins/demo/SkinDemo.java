
/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.                                    
 * Project: Xkins                                                         
 * (#)SkinDemo.java                  
 *                                         
 * Permission is granted to copy, distribute and/or modify this document  
 * under the terms of the GNU Free Documentation License, Version 1.1 or  
 * any later version published by the Free Software Foundation; with no   
 * Invariant Sections, with no Front-Cover Texts.                     
 * A copy of the license is included in the section entitled 
 * "GNU Free Documentation License".                                 
 * ====================================================================
 */            
package net.jcreate.xkins.demo;

import net.jcreate.xkins.Skin;

/**
 * Skin extension for adding attributes
 * @author Guille
 */
public class SkinDemo
        extends Skin {
	private boolean userSelectable;
	/**
	 * @return
	 */
	public boolean isUserSelectable() {
		return userSelectable;
	}

	/**
	 * @param b
	 */
	public void setUserSelectable(boolean b) {
		userSelectable = b;
	}

}
