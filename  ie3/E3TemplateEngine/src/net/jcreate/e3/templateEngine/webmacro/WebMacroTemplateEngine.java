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

package net.jcreate.e3.templateEngine.webmacro;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.DefaultTemplate;
import net.jcreate.e3.templateEngine.support.TemplateEngineSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webmacro.PropertyException;
import org.webmacro.ResourceException;
import org.webmacro.WM;

/**
 * 
 * @author 黄云辉
 *
 */
public class WebMacroTemplateEngine extends TemplateEngineSupport {
	private final Log log = LogFactory.getLog( this.getClass() );
	
	private Properties properties = null;

	public void init(){
		properties = WebMacroHelper.getDefaultProperties();
		
		
	}
	
	public void init(Properties pProperties){
        this.properties = pProperties; 		
	}
	
	/**
	 * 
	 */
	protected void mergeFileTemplate(Template pTemplate, Context pContext,
			Writer pWriter) throws MergeTemplateException {
		
		Properties props = WebMacroHelper.getDefaultProperties();
		props.putAll(this.properties);
		
	    props.setProperty("TemplateEncoding", pTemplate.getInputEncoding());
		String path = pTemplate.getResource();
		if ( log.isDebugEnabled() ){
	          log.debug("模板文件: \"" + path + "\" 采用WebMacro引擎进行合并.");
	          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
			}

		/**
		 * @FIXME
		 * 由于WebMacro没有提供运行时，设置模板编码方式，所以要求每次实例化Engine,这样性能有些影响，希望以后版本
		 * WebMacro会提供该方法。不过好在，是单机版本的，性能问题不大.
		 */
		WM wm = WebMacroHelper.getWMEngine(props);
		
		org.webmacro.Context c = new org.webmacro.Context(wm.getBroker());
		Map params = pContext.getParameters();
		for (Iterator i = params.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			Object value = params.get(key);
			c.put(key, value);
		}

		org.webmacro.Template t = null;
		try {
			t = wm.getTemplate(pTemplate.getResource());
		} catch (ResourceException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}

		String result = null;
		try {
			result = t.evaluateAsString(c);
		} catch (PropertyException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}

		try {
			pWriter.write(result);
		} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
	}

	public static void main(String[] args) {
		WebMacroTemplateEngine a = new WebMacroTemplateEngine();
		Template t = new DefaultTemplate();
		t.setResource("noservlet.wm");
		Context c = new DefaultContext();
		c.put("msg", "hello中文哦.");
		StringWriter writer = new StringWriter();
		a.mergeTemplate(t, c, writer);
		System.out.println(writer.toString());
	}
}
