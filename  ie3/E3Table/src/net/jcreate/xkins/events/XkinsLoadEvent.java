/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsLoadEvent.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins.events;

import net.jcreate.xkins.Xkins;


/**
 * xkins reload会触发该事件
 * Evento de carga de Xkins
 * @author Guillermo Meyer
 */
public class XkinsLoadEvent
        extends XkinsEvent {
    private Xkins xkins=null;
    //~ Constructors -------------------------------------------------------------------------------

    /**
     * @param source
     */
    public XkinsLoadEvent(Object source) {
        super(source);        
    }
	/**
	 * @return
	 */
	public Xkins getXkins() {
		return xkins;
	}

	/**
	 * @param xkins
	 */
	public void setXkins(Xkins xkins) {
		this.xkins = xkins;
	}

}
