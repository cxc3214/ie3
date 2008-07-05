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
package net.jcreate.e3.table.html;

import java.util.ArrayList;
import java.util.List;

import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.support.DefaultTable;
import net.jcreate.e3.table.support.TableConstants;

public class HTMLTable extends DefaultTable implements Attributeable{
	private PageInfo pageInfo;
	private String skin = TableConstants.DEFAULT_SKIN;
	private String uri;
	private String id;
	private String style;
	//ajax, mvc,default, 表格模式
	private String mode;
	private HTMLForm form;
	/**
	 * 表结构是否导出过.这个属性当构造ajax table的时候有用.
	 * ajax表格的构造分2个过程.1:导出表定义 2:读数据
	 */
	private boolean exported = true;
	
	private String paramsFormVar;//参数form变量，如果设置了则不输出，包参数form存储到变量中，由用户自己确定位置，这样
	                             //设置是为了解决form嵌套的问题
	private String paramsFormScope = TableConstants.DEFAULT_PARAMSFORM_SCOPE;//默认是request
	private List params = new ArrayList();

	private Attributes attributes = new Attributes();
	public void setAttribute(String name, String value) {
		attributes.setAttribute(name, value);
	}
	
	public String getAttributesAsString(){
		return this.attributes.geAttributesAsString();
	}
	
	public void addParam(HTMLParam pParam){
		params.add(pParam);
	}
	
	public List getParams(){
		return new ArrayList( this.params );
	}
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}



	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pPageInfo) {
		this.pageInfo = pPageInfo;
	}

	public String getParamsFormVar() {
		return paramsFormVar;
	}

	public void setParamsFormVar(String paramsFormVar) {
		this.paramsFormVar = paramsFormVar;
	}

	public String getParamsFormScope() {
		return paramsFormScope;
	}

	public void setParamsFormScope(String paramsFormScope) {
		this.paramsFormScope = paramsFormScope;
	}


	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public HTMLForm getForm() {
		return form;
	}

	public void setForm(HTMLForm form) {
		this.form = form;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isExported() {
		return exported;
	}

	public void setExported(boolean exported) {
		this.exported = exported;
	}


}
