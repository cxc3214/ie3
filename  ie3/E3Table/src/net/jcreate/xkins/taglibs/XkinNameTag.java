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
 * Escribe en el output el nombre del skin actual
 * @see net.jcreate.xkins.XkinProcessor
 * @see net.jcreate.xkins.taglibs.RestoreActualXkinTag
 * @see net.jcreate.xkins.taglibs.SaveActualXkinTag
 * @see net.jcreate.xkins.taglibs.XkinTag
 * @author Guillermo Meyer
 **/
public class XkinNameTag
        extends TagSupport {

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
		try {
			this.pageContext.getOut().write(XkinProcessor.getCurrentSkinName((HttpServletRequest)this.pageContext.getRequest()));
		} catch (Exception e) {
			throw new JspException("IO Error: " + e.toString());
		}
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
}
