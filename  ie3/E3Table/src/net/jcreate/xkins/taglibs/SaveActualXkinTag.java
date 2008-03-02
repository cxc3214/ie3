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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.xkins.Skin;
import net.jcreate.xkins.XkinProcessor;


/**
 * Guarda el nombre del xkin actual para el posterior recupero. Permite, junto con RestoreActualSkin, modificar el skin
 * durante una p�gina. Por ejemplo:
 * <pre>
 *         &lt;tag:tagQueUsaXkins&gt;
 *             Texto formateado con Skins
 *         &lt;/tag:tagQueUsaXkins&gt;
 *         &lt;xkins:saveActualXkin/&gt;
 *         &lt;xkins:xkin name="skinNuevo"/&gt;
 *         &lt;tag:tagQueUsaXkins&gt;
 *             Texto formateado con Skins, pero usando un nuevo skin.
 *         &lt;/tag:tagQueUsaXkins&gt;
 *         &lt;xkins:restoreActualXkin/&gt;
 * </pre>
 * En el ejemplo, el tagQueUsaXkins aplica los templates correspondientes para formatear un texto seg�n el Skin actual.
 * Cuando se desea cambiar de Skin durante la p�gina, primero se guarda el xkin actual (saveActualXkin), se aplica el cambio
 * de skin (xkin) a "skinNuevo", se ejecuta nuevamente tagQueUsaXkins, esta vez formateando el texto seg�n el skinNuevo,
 * y finalmente recupera el skin actual mediante restoreActualXkin.
 * @see net.jcreate.xkins.taglibs.RestoreActualXkinTag
 * @see net.jcreate.xkins.taglibs.XkinTag
 * @author Guillermo Meyer
 **/
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
        Object actual = XkinProcessor.getCurrentSkinName(pageContext);
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
