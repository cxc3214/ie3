package net.jcreate.home.taglib;

import net.jcreate.xkins.taglibs.TemplateTag;

public class TemplateExTag extends TemplateTag {
	
	private static final long serialVersionUID = 1L;

	/**
     * 添加参数.
     * @param name 参数名
     * @param value 参数值
     */
	public void addParameter(String name, Object value) {
		super.getParameters().put(name, value);
	}
	
}
