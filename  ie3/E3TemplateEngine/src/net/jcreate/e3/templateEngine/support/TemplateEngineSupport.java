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

package net.jcreate.e3.templateEngine.support;

import java.io.Writer;
import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.TemplateEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 黄云辉
 *
 */
public abstract class TemplateEngineSupport implements TemplateEngine{
	private final Log log = LogFactory.getLog( TemplateEngineSupport.class );
	
	
	protected final String getStrTemplatePrefix(){
		return DefaultTemplate.STR_PREFIX;
	}
	
	protected final String getStrTemplate(Template pTemplate){
		final String resource = pTemplate.getResource();
		if ( resource == null ){
			return null;
		}
		if ( resource.startsWith( DefaultTemplate.STR_PREFIX ) == false){
			return null;
		}
	    int index = resource.indexOf(":");
	    final String noPrefixResource = resource.substring(index+1);
		return noPrefixResource;
	}
	public void mergeTemplate(Template pTemplate,
            Context pContext,
            Writer pWriter) throws MergeTemplateException{
		final String resource = pTemplate.getResource();
		String property = pTemplate.getResource();
		if ( property == null ){
			throw new MergeTemplateException("resource must be not null");
		}
		if (resource.startsWith(DefaultTemplate.STR_PREFIX) == false){
            final String FILE_PATH = resource;
			if ( FILE_PATH== null ){
			  final String MSG =
				  "模板文件不能为空.";
			  if ( log.isErrorEnabled() ){
                log.error(MSG);				
			  }
			  throw new MergeTemplateException(MSG);
	       }
	 		mergeFileTemplate(pTemplate, pContext, pWriter);
			if ( log.isInfoEnabled() ){
				log.info("合并模板文件成功：\"" + FILE_PATH + "\"");
			}
		}else{
//			final String TEMPLATE_STR = getStrTemplate(pTemplate);
//			//合并字符串日志级别设置为debug,因为主要是fcg内部使用的，没必要呈现给终端用户.
//			if ( log.isDebugEnabled() ){
//				log.debug("开始合并字符串 \""+ TEMPLATE_STR + "\"");
//			}
			mergeStringTemplate(pTemplate, pContext, pWriter);
//			if ( log.isDebugEnabled() ){
//				log.debug("合并字符串 \"" + TEMPLATE_STR + "\" 成功!");
//			}
			
		}
		
	}
	
	protected abstract void mergeFileTemplate(Template pTemplate,
            Context pContext,
            Writer pWriter) throws MergeTemplateException;
	
	protected void mergeStringTemplate(Template pTemplate,
			Context pContext,
            Writer pWriter) throws MergeTemplateException{
		throw new UnsupportedOperationException(getClass() + ":mergeStringTemplate方法" );
		
	}

}
