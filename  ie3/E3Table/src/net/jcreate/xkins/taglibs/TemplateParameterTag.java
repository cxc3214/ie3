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

public class TemplateParameterTag
        extends BodyTagSupport {
    //~ Instance fields ----------------------------------------------------------------------------
	//参数名,
    protected String name = null;
	//存储参数值的变量
	protected String var = null;
	
	//变量所在范围，可以取值page,request,session, application
	//默认采用pageContext的findAttribute方法寻找变量值.
	protected String scope = null; 
    

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
    	
		TemplateTag parent = (TemplateTag) findAncestorWithClass(this,
				TemplateTag.class);
        if (parent == null) {
            throw new JspTagException(TemplateParameterTag.class.getName()
                                      + "应该是 " 
                                      + TemplateTag.class.getName() 
                                      + "的子标签");
        }
		if ( var == null ){
            parent.addParameter(this.name, (this.bodyContent!=null?this.bodyContent.getString():""));	
		}else{
			/**
			 * @fixme: 根据scope来获取
			 */
				Object value = pageContext.findAttribute(var);
				parent.addParameter(this.name, (Object)value);
	        }
		return (EVAL_PAGE);
    }

    public int doStartTag() {
        return (TemplateParameterTag.EVAL_BODY_BUFFERED);
    }

	public void release() {
		super.release();
		name = null;
		var = null;
		scope = null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

}
