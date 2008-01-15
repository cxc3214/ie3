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

import net.jcreate.e3.table.html.HTMLParam;

public class ParamTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 参数名
	 */
	private String name;

	/**
	 * 参数值
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	private TableTag getTableTag(){
		return (TableTag) findAncestorWithClass(this, TableTag.class);
	}
	
	public int doStartTag() throws JspException {
		TableTag tableTag = getTableTag();
		if ( tableTag == null ){
			throw new JspException("param 必须在为table的子元素!");
		}
		//参数只会调用一次.
		if ( tableTag.isCreatedTable() && tableTag.isCreatedHeader() == false ){
		  tableTag.addParam(new HTMLParam(this.name, this.value));
		}
		return super.doStartTag();
	}

	public void release() {
		this.name = null;
		this.value = null;
		super.release();
	}
	
}
