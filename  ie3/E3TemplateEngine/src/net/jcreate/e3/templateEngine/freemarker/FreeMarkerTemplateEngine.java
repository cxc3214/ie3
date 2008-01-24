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

package net.jcreate.e3.templateEngine.freemarker;

import java.io.IOException;
import java.io.Writer;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.support.TemplateEngineSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @todo: 提供FilePathTemplate
 * @author new
 *
 */
public class FreeMarkerTemplateEngine extends TemplateEngineSupport {
    private final Log log = LogFactory.getLog( this.getClass() );
    
	public void mergeFileTemplate(Template pTemplate, Context pContext, Writer pWriter) throws MergeTemplateException {
		Configuration cfg = FreeMarkerHelper.getConfiguration();
		String path = pTemplate.getResource();
		
		if ( log.isDebugEnabled() ){
          log.debug("模板文件: \"" + path + "\" 采用freemarker引擎进行合并.");
          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
		}
		

		freemarker.template.Template  template = null;
		try {
			template = cfg.getTemplate(path);
			if ( pTemplate.getInputEncoding() != null ){
				template.setEncoding(pTemplate.getInputEncoding());
			}			
    	} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
    	
		try {
			template.process(pContext.getParameters(), pWriter);
		} catch (TemplateException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
	
	}


}
