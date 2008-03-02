/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)TemplateParameterTag.java
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
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Este tag se utiliza en conjunto con TemplateTag y permite asignarle los par�metros al TempalteTag.<br>
 * Ser�a algo similar como el m�todo <code>addParameter()</code> del <code>XkinProcessor</code>.
 * El valor del par�metro es lo que el tag contiene en el bodtContent. Por ejemplo:
 * <pre>
 *     &lt;xkins:templateParameter name="param1"&gt;Valor del parametro&lt;/xkins:templateParameter&gt;
 * </pre>
 * Esto ser�a similar como hacer <code>xkp.addParameter("param1", "Valor del parametro");</code>, donde
 * <code>xkp</code> es una instancia de <code>XkinProcessor</code>.
 * @see net.jcreate.xkins.XkinProcessor
 * @see net.jcreate.xkins.taglibs.TemplateTag
 * @author Guillermo Meyer
 */
public class TemplateParameterTag
        extends BodyTagSupport {
    //~ Instance fields ----------------------------------------------------------------------------

    private String name = null;

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
     * @throws JspTagException DOCUMENT ME!
     */
    public int doEndTag()
            throws javax.servlet.jsp.JspTagException, JspException {
        TemplateTag parent = (TemplateTag) findAncestorWithClass(this, TemplateTag.class);
        if (parent == null) {
            throw new JspTagException("Error de anidamiento. "
                                      + TemplateParameterTag.class.getName()
                                      + " debe estar dentro de " + TemplateTag.class.getName());
        } else {
            parent.addParameter(this.name, (this.bodyContent!=null?this.bodyContent.getString():""));
        }
        return (EVAL_PAGE);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int doStartTag() {
        return (TemplateParameterTag.EVAL_BODY_BUFFERED);
    }

	public void release() {
		super.release();
		name = null;
	}

}
