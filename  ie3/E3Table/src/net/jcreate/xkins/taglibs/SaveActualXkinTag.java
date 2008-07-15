/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)SaveActualXkinTag.java
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

import net.jcreate.xkins.Skin;
import net.jcreate.xkins.XkinProcessor;


public class SaveActualXkinTag
        extends TagSupport {
    //~ Static fields/initializers -----------------------------------------------------------------

    public static final String SUFIX = ".SAVED";

    //~ Instance fields ----------------------------------------------------------------------------

    protected String property = null;

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param property DOCUMENT ME!
     */
    public void setProperty(String property) {
        this.property = property;
    }

    public int doEndTag()
            throws javax.servlet.jsp.JspTagException, JspException {
        Object actual = XkinProcessor.getCurrentSkinName((HttpServletRequest)pageContext.getRequest());
        String attr = (this.property == null) ? (Skin.ATTR_SKIN_NAME + SUFIX) : this.property;
        pageContext.getSession().setAttribute(attr, actual);
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
		property = null;
	}

}
