package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.e3.table.html.HTMLForm;

public class FormTag extends TagSupport {
	//表单action
	private String action;
	//target
	private String target = "_self";
	private String method = "post";
	//表单名称
	private String name;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	private TableTag getTableTag(){
		return (TableTag) findAncestorWithClass(this, TableTag.class);
	}
	public int doStartTag() throws JspException {
		TableTag tableTag = getTableTag();
		if ( tableTag == null ){
			throw new JspException("form 必须是 table的子元素");
		}
		if ( tableTag.getForm() == null){
			HTMLForm form = new HTMLForm();
			form.setAction(action);
			form.setMethod(method);
			form.setTarget(target);
			form.setName(name);
			tableTag.setForm(form);
		}
		return super.doStartTag();
	}
	
	public void release() {
		this.action = null;
		this.target = null;
		this.method = null;
		this.name = null;
		super.release();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
