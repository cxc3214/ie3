package net.jcreate.home.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import net.jcreate.xkins.taglibs.TemplateParameterTag;

public class TemplateParameterExTag extends TemplateParameterTag {
	
	private static final long serialVersionUID = 1L;
	//参数名,
    protected String name = null;
	//存储参数值的变量
	protected String var = null;
	
	//变量所在范围，可以取值page,request,session, application
	//默认采用pageContext的findAttribute方法寻找变量值.
	protected String scope = null; 
	
	

	
	public int doEndTag() throws javax.servlet.jsp.JspTagException,
			JspException {
		TemplateExTag parent = (TemplateExTag) findAncestorWithClass(this,
				TemplateExTag.class);
        if (parent == null) {
            throw new JspTagException("发现错误: "
                                      + TemplateParameterExTag.class.getName()
                                      + "应该是 " 
                                      + TemplateExTag.class.getName() 
                                      + "的子标签");
        }
		if ( var == null ){
		  return super.doEndTag();	
		}else{
			/**
			 * @fixme: 根据scope来获取
			 */
				Object value = pageContext.findAttribute(var);
				parent.addParameter(this.name, (Object)value);
	        }
		return (EVAL_PAGE);
	}


	public void release() {
		super.release();
		name = null;
        var = null;
		scope = null;
	}


	public void setName(String name) {
		//注意，必须调用super.setName,否则父类不会实例化.
		super.setName(name);
		this.name = name;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


	public void setVar(String var) {
		this.var = var;
	}

}
