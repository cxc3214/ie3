/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)PathTag.java
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

import net.jcreate.xkins.Path;
import net.jcreate.xkins.XkinProcessor;


/**
 * Obtiene el path del skin actual. Permite acceder a todos los paths declarados en el Skin.<br>
 * Por ejemplo, un uso ser�a:
 * <pre>
 *         &lt;img src="&lt;xkins:path name='images'/&gt;/imagen.gif"&gt;
 * </pre>
 * Esto generar�a, por ejemplo, un HTML como �ste:
 * <pre>
 *         &lt;img src="/app/skins/default/imagenes/imagen.gif"&gt;
 * </pre>
 * @see net.jcreate.xkins.Path
 * @see net.jcreate.xkins.Skin
 *@author Guillermo Meyer
 **/
public class PathTag
        extends TagSupport {
    //~ Instance fields ----------------------------------------------------------------------------

    protected String name = null;
    protected String skinName = null;

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
     * @return DOCUMENT ME!
     *
     * @throws javax.servlet.jsp.JspTagException DOCUMENT ME!
     * @throws JspException DOCUMENT ME!
     */
    public int doEndTag()
            throws javax.servlet.jsp.JspTagException, JspException {
        //Obtengo el skin actual
        Path path = null;
        if (this.skinName == null) {
            path = XkinProcessor.getCurrentSkin(pageContext).getPath(this.name);
        } else {
            path = XkinProcessor.getSkin(pageContext, this.skinName).getPath(this.name);
        }
        if(path==null)
        	throw new JspException("Nonexisting path: " + this.name);
        try {
            pageContext.getOut().write(path.getFullUrl());
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
	}
    
}
