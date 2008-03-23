package net.jcreate.home.taglib;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class LayoutTag extends TemplateExTag{

	private static final long serialVersionUID = 1L;
	private String id = BoxTag.DEFAULT_LAYOUT;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
    public int doEndTag() throws JspTagException, JspException{
		Map paramsMap = (Map)this.pageContext.getAttribute(BoxTag.LAYOUT_PREFIX + id);
		if ( paramsMap != null ){
			java.util.Iterator keys = paramsMap.keySet().iterator();
			while( keys.hasNext() ){
				Object key = keys.next();
				if ( key instanceof String == false ){
					continue;
				}
				Object value = paramsMap.get(key);
				this.addParameter((String)key, value);
			}
		}
		return super.doEndTag();
    }

	public void release() {
		super.release();
		this.id = null;
	}

}
