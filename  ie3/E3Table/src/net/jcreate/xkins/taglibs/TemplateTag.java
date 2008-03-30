/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)TemplateTag.java
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

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.jcreate.xkins.XkinProcessor;


/**
 * Dibuja un template del skin actual. Es como utilizar el XkinProcessor y el processContent de un tempalte.<br>
 * Utiliza el tag TemplateParameterTag anidado para precibir los par�metros requeridos por el template que renderiza.
 * El bodyContent del tag es pasado al XkinProcessor como bodyContent, mediante el m�todo addBodyContent.<br>
 * Por ejemplo, veamos este ejemplo de template y su uso con este tag:
 * <pre>
 *         &lt;template name="table"&gt;
 *             &lt;!-- pro:width --&gt;
 *             &lt;content&gt;&lt;![CDATA[
 *                 &lt;table border="0" cellspacing="1" cellpadding="1" bgcolor="${res:bgcolor}" width="${pro:width}"&gt;
 *                     ${jsp:bodyContent}
 *                     &lt;img src="${res:imagen}"/&gt;
 *                 &lt;/table&gt;
 *             ]]&gt;&lt;/content&gt;
 *         &lt;element name="imagen" path="images" url="/img.jpg"/&gt;
 *        &lt;constant name="bgcolor" value="#556677"/&gt;
 *         &lt;/template&gt;
 * </pre>
 * Este template definido, puede ser usado mediante los siguientes tags:
 * <pre>
 *         &lt;xkins:tempalte name="table"&gt;
 *             &lt;xkins:templateParameter name="width"&gt;75%&lt;/xkins:templateParameter&gt;
 *             Esto es el contenido original del taglib
 *         &lt;/xkins:tempalte&gt;
 * </pre>
 * El templateParameter asigna el par�metro width al templateTag. El texto dentro del bodyContent del tempalte tag es
 * usado en el comando jsp:bodyContent del template. El html generado ser� el resultado de la ejecuci�n del JSP es:
 * <pre>
 *         &lt;table border="0" cellspacing="1" cellpadding="1" bgcolor="#556677" width="75%"&gt;
 *             Esto es el contenido original del taglib
 *             &lt;img src="/skins/images/img.jpg"/&gt;
 *         &lt;/table&gt;
 * </pre>
 * @see net.jcreate.xkins.taglibs.TemplateParameterTag
 * @see net.jcreate.xkins.XkinProcessor
 * @author Guillermo Meyer
 **/
public class TemplateTag
        extends BodyTagSupport {
    //~ Instance fields ----------------------------------------------------------------------------

    protected Map parameters = null;
    protected String name = null;
    protected String skinName = null;
    protected boolean toResponse;

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
     * Permite hacer que el procesamiento del template vaya directamente al response
     * @param b
     */
    public void setToResponse(boolean b) {
        toResponse = b;
    }

    /**
     * @return
     */
    public boolean isToResponse() {
        return toResponse;
    }



	/**
     * 添加参数.
     * @param name 参数名 
     * @param value 参数值
     */
	public void addParameter(String name, Object value) {
		this.getParameters().put(name, value);
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
        XkinProcessor xsp = new XkinProcessor(this.skinName, this.name, pageContext);
        xsp.addParameters(this.getParameters());
        xsp.addBodycontent((this.bodyContent!=null?this.bodyContent.getString():""));
        try {
            if (this.isToResponse()) {
            	pageContext.getOut().write(xsp.processContent());
                this.pageContext.getResponse().getOutputStream().flush();
            } else {
                this.pageContext.getOut().write(xsp.processContent());
            }
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
    	//init the parameters
    	this.parameters=null;
        return (TemplateTag.EVAL_BODY_BUFFERED);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Map getParameters() {
        if (this.parameters == null) {
            this.parameters = new Hashtable();
        }
        return this.parameters;
    }

	public void release() {
		super.release();
		parameters = null;
		name = null;
		skinName = null;
		toResponse=false;
	}

}
