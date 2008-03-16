/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PropertyTag  extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String name;
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspException {
		DecoratorTag decoratorTag = (DecoratorTag) findAncestorWithClass(this, DecoratorTag.class);
		if ( decoratorTag == null ){
			throw new JspException("property 必须是decorator的子元素!");
		}
		Object propertyValue = null;
		if ( value == null ){
			BodyContent context = this.bodyContent;
			if ( context != null ){
			  propertyValue = context.getString();
			  context.clearBody();
			  this.setBodyContent(null);//tomcat5.028好象不会自动清除,所以我们显示设置为nulll
			}
		}else{
			propertyValue = value;
		}
		decoratorTag.setProperty(name, propertyValue);
		return super.doEndTag();
		
	}

	
}
