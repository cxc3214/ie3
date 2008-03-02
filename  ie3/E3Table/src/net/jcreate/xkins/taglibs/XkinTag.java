/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinTag.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.xkins.XkinProcessor;


/**
 * Asigna el nombre del skin actual de trabajo. Este es el nombre que se usar� por todos los componentes de skin
 * cuando se rendericen los templates y no se especifique el skin. Generalmente el SkinName se deber�a setear luego de
 * un login a la aplicaci�n mediante el llamado a <code>XkinProcessor.setCurrentSkin(request, skinName)</code>, pero
 * puede quererse utilizar desde un jsp el cambio del nombre del skin actual, por ejemplo, en conjunci�n con
 * SaveActualXkinTag y RestoreActualXkinTag.
 * @see net.jcreate.xkins.XkinProcessor
 * @see net.jcreate.xkins.taglibs.RestoreActualXkinTag
 * @see net.jcreate.xkins.taglibs.SaveActualXkinTag
 * @author Guillermo Meyer
 **/
public class XkinTag
        extends TagSupport {
    //~ Instance fields ----------------------------------------------------------------------------

    protected String name = null;

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param name DOCUMENT ME!
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws javax.servlet.jsp.JspTagException DOCUMENT ME!
     * @throws JspException DOCUMENT ME!
     */
    public int doEndTag()
            throws javax.servlet.jsp.JspTagException, JspException {
        XkinProcessor.setCurrentSkinName((HttpServletRequest) this.pageContext.getRequest(),
                                         this.name);
        return EVAL_PAGE;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int doStartTag() {
        return (SKIP_BODY);
    }

	public void release() {
		super.release();
		name = null;
	}
}
