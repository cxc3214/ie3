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
package net.jcreate.e3.tree.taglib;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.JspPageWebContext;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;

/**
 * 
 * @todo:  完成该Tag
 * @author 黄云辉
 *
 */
public class TreeTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = 1L;
	private String var;
	private String items;
	private String scope;
	private String builder = "default";
	private final Log logger = LogFactory.getLog( this.getClass() );
	/**
	 * 动态属性
	 */
	private java.util.LinkedHashMap dynamicAttributes = new java.util.LinkedHashMap();
	
	//
	private java.util.List nodes = new ArrayList();
	private java.util.Map idParentIds = new java.util.HashMap();
	private java.util.Collection userDatas = null;
	private java.util.Iterator userDatasIterator = null;
	private Object currUserData;
	
	public void addNode(WebTreeNode pNode){
		nodes.add(pNode);
	}
	public void addIdParentIds(String pId, String pParentId){
		idParentIds.put(pId, pParentId);
	}

	public int doStartTag() throws JspException {
		userDatas = (java.util.Collection)this.pageContext.findAttribute(this.items);
		if ( userDatas == null ){
			return SKIP_BODY;
		}
		if ( userDatas.isEmpty() ){
			return SKIP_BODY;	
		}
		userDatasIterator = userDatas.iterator();
		currUserData = userDatasIterator.next();
		this.pageContext.setAttribute(this.var, currUserData);
		return EVAL_BODY_INCLUDE;
	}
	public int doAfterBody() throws JspException {
		/**
		 * @todo: 提供添加节点的方法.
		 */
		if ( userDatasIterator.hasNext() ){
			currUserData = userDatasIterator.next();
			this.pageContext.setAttribute(this.var, currUserData);
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}
	
	private void setProperty(Object pObj, String pProperty, Object pValue) throws JspException{
		try {
			PropertyUtils.setProperty(pObj, pProperty, pValue);
		} catch (Exception ex){
			final String msg =
				"设置节点：" + pObj.getClass().getName() + "的属性:" + pProperty + "失败！" +
				"属性值为:" + pValue;
			logger.error(msg, ex);
			throw new JspException(msg, ex);
		}
		
	}
	
	public int doEndTag() throws JspException {
		TreeModel treeModel = NodeUtils.convert(this.nodes, this.idParentIds);
		WebTreeBuilder treeBuilder = BuilderFactory.getInstance(this.builder);
		treeBuilder.init( new JspPageWebContext(this.pageContext) );
		java.util.Iterator keys = dynamicAttributes.keySet().iterator();
		while( keys.hasNext() ){
			Object key = keys.next();
			Object value = dynamicAttributes.get(key);
			this.setProperty(treeBuilder, (String)key, value);
		}
		/**
		 * @todo: 允许设置不同的TreeDirector
		 */
		TreeDirector director = new DefaultTreeDirector();
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		JspWriter writer = this.pageContext.getOut();
		cleanUp();
		try {
			writer.print(treeScript);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e.getMessage(), e);
		}
		return super.doEndTag();
	}
	  private void cleanUp()
	    {
		    dynamicAttributes.clear();
		    nodes.clear();
		    idParentIds.clear();
		    currUserData = null;
		}

	
	public void setDynamicAttribute(String uri, String name, Object value) throws JspException {
		dynamicAttributes.put(name, value);
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}
	public void release() {
		this.var = null;
		this.items = null;
		this.scope = null;
		this.builder = "default";
		super.release();
	}
	public Object getCurrUserData() {
		return currUserData;
	}
	
	
	
}
