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
package net.jcreate.e3.table.skin;

import java.util.HashMap;
import java.util.Map;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.StrTemplateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Skin {
	
	private final Log logger = LogFactory.getLog( this.getClass() ); 
	private Map templates = new HashMap();
	
	public void addTemplate(Template pTemplate){
		templates.put(pTemplate.getName(), pTemplate);
	}
	
	/**
	 * 获取指定模板的内容，是合并后的内容
	 * @param pTemplateName 模板名，如果名字不存在，throw NoSuchTemplateExcepiton
	 * @param pContext      对象上下文了  
	 * @return
	 * @throws SkinException 出错
	 */
	public String getTemplateValue(String pTemplateName, Context pContext) throws SkinException{
		String templateContext = getTemplate(pTemplateName);
		if ( templateContext == null ){
			return "";
		}
		/**
		 * @todo: 以后可以支持其他类型的模板引擎.目前只支持velocity.
		 */
		return StrTemplateUtil.merge(templateContext, pContext);
	}
	
	public String getTemplate(String pName){
		if ( templates.containsKey(pName) == false ){
			logger.debug("不存在指定模板:" + pName);
			return null;
		}
		Template template = (Template)templates.get(pName);
		return template.getContent();
	}

	/**
	 * 皮肤名称
	 */
     private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
