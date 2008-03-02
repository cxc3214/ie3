/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsEvent.java
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

import java.util.EventObject;


/**
 * Evento de Xkins para uso en notificaciones
 * @author wzrzjb
 */
public class XkinsEvent extends EventObject {
	public static final String XKINS_RELOAD = "XKINS_RELOAD";
	public static final String TEMPLATE_RELOAD = "TEMPLATE_RELOAD";
    //~ Constructors -------------------------------------------------------------------------------

	/**
	 * @param source
	 */
	public XkinsEvent(Object source) {
		super(source);
	}

}
