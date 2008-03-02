/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)ResourceTag.java
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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.xkins.Skin;
import net.jcreate.xkins.XkinProcessor;
import net.jcreate.xkins.resources.Resource;


/**
 * Este tag permite acceder a un resource del Skin o de un template. Por ejemplo:
 * <pre>
 *         &lt;body background="&lt;xkins:resource name='bg'/&gt;"&gt;
 * </pre>
 * Generar�a el siguiente HTML:
 * <pre>
 *         &lt;body background="/app/skins/default/imagenes/backgrounds/bg.gif"&gt;
 * </pre>
 * teniendo en cuenta que el resource "bg" es un Element.
 * @see net.jcreate.xkins.resources.ElementResource
 * @author Guillermo Meyer
 **/
public class ResourceTag
        extends TagSupport {
    //~ Instance fields ----------------------------------------------------------------------------

    protected String name = null;
    protected String skinName = null;
    protected String template = null;

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
     * @param skinName DOCUMENT ME!
     */
    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    /**
     * DOCUMENT ME!
     *
     * @param template DOCUMENT ME!
     */
    public void setTemplate(String template) {
        this.template = template;
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
        Resource res = null;
        Skin skin = null;
        if (this.skinName == null) {
            skin = XkinProcessor.getCurrentSkin(pageContext);
        } else {
            skin = XkinProcessor.getSkin(pageContext, this.skinName);
        }
        if (this.template == null) {
            res = skin.getResource(this.name);
        } else {
            res = skin.getTemplate(this.template).getResource(this.name);
        }
        try {
            pageContext.getOut().write(res.getData());
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

	public void release() {
		super.release();
		name = null;
		skinName = null;
		template=null;
	}

}
