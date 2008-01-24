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

package net.jcreate.e3.templateEngine.velocity;

import java.io.Writer;
import java.util.Properties;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.support.TemplateEngineSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author 黄云辉
 *
 */
public class VelocityTemplateEngine extends TemplateEngineSupport{
	
    private final Log log = LogFactory.getLog( this.getClass() );
    private VelocityEngine ve = null;

    public void init(){
    	ve = VelocityHelper.getVeocityEngine(VelocityHelper.getDefaultProperties());
    }
    
    public void init(Properties pProperties){
    	Properties props = VelocityHelper.getDefaultProperties();
    	props.putAll(pProperties);
    	ve = VelocityHelper.getVeocityEngine(props);	
    }
    
	public void mergeStringTemplate(Template pTemplate,
			Context pContext,
            Writer pWriter) throws MergeTemplateException{
		if ( ve == null ){
			throw new NullPointerException("没有初始化模板引擎!");
		}
		VelocityContext context = VelocityHelper.context2VelocityContext(pContext);
        String templateStr = this.getStrTemplate(pTemplate);		
		try {
			ve.evaluate(context, pWriter,"VelocityMergeString.log", templateStr);
		} catch (Exception e) {
			final String MSG =
				"合并字符串 \"" + templateStr + "\" 失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
				  log.error(MSG, e);
			}
			throw new MergeTemplateException(MSG, e);
		}
	}


	public void mergeFileTemplate(Template pTemplate, Context pContext, Writer pWriter) throws MergeTemplateException {
		if ( ve == null ){
			throw new NullPointerException("没有初始化模板引擎!");
		}
		
		VelocityContext context = VelocityHelper.context2VelocityContext(pContext);
		String path = pTemplate.getResource();
		
		if ( log.isDebugEnabled() ){
          log.debug("模板文件: \"" + path + "\" 采用Velocity引擎进行合并.");
          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
		}
		
		org.apache.velocity.Template template = null;
		try {
			if ( pTemplate.getInputEncoding() == null ){
			  template = ve.getTemplate(path);
			}else{
				template = ve.getTemplate(path, pTemplate.getInputEncoding());	
			}
		} catch (Exception e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
 				  log.error(MSG, e);
 	 			}
			throw new MergeTemplateException(MSG, e);
		}
		
		try {
			template.merge(context,pWriter);
		} catch (Exception e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
				  log.error(MSG, e);
			}
			throw new MergeTemplateException(MSG, e);
		}
		
		
	}

}
