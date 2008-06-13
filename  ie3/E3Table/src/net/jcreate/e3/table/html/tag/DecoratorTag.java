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

import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.DecorateException;
import net.jcreate.e3.table.decorator.DecoratorFactory;
import net.jcreate.e3.table.html.Decorateable;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DecoratorTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private String cls;
	private CellDecorator cellDecorator = null;
	private final Log logger = LogFactory.getLog( this.getClass() );
	

	
	public void setProperty(String pProperty, Object pValue){
		try {
			PropertyUtils.setProperty(cellDecorator, pProperty, pValue);
		} catch (Exception ex){
			final String msg =
				"设置修饰器：" + this.cellDecorator.getClass().getName() + "的属性:" + pProperty + "失败！" +
				"属性值为:" + pValue;
			logger.error(msg, ex);
			throw new DecorateException(msg, ex);
		}
		
	}

	public int doEndTag() throws JspException {
		Decorateable decorateable = (Decorateable) findAncestorWithClass(this, Decorateable.class);
		if ( decorateable != null ){
			decorateable.addCellDecorator(cellDecorator);
		}
		return super.doEndTag();
	}

	public int doStartTag() throws JspException {
		cellDecorator = DecoratorFactory.getInstance(cls);
		if ( cellDecorator == null ){
			final String msg =
				"创建修饰器：" + cls + "对象失败!";
			logger.error(msg);
			throw new DecorateException(msg);
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

}
