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
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.e3.table.html.Attributeable;
import net.jcreate.e3.table.html.HTMLRow;

public class RowTag extends TagSupport implements Attributeable{

	private static final long serialVersionUID = 1L;

	private HTMLRow currRow = null;
	private String style; 
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public void setAttribute(String name, String value) {
		currRow.setAttribute(name, value);
	}
	
	

	public int doStartTag() throws JspException {
		TableTag tableTag = (TableTag) findAncestorWithClass(this, TableTag.class);
		if ( tableTag == null ){
			throw new JspException("row 必须是table的子元素!");
		}		
		if ( tableTag.isCreatedHeader() == false ){
			return SKIP_BODY;
		}
		currRow = tableTag.getCurrRow();
		if ( style != null ){
		  setAttribute("style", this.style);
		}
		return EVAL_BODY_INCLUDE;
	}

}
