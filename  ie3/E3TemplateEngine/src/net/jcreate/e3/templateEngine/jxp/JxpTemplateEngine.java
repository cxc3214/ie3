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

package net.jcreate.e3.templateEngine.jxp;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.MergeTemplateException;
import net.jcreate.e3.templateEngine.Template;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.DefaultTemplate;
import net.jcreate.e3.templateEngine.support.TemplateEngineSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.onemind.jxp.ByteArrayPageSource;
import org.onemind.jxp.JxpContext;
import org.onemind.jxp.JxpProcessor;
import org.onemind.jxp.MultiSourcePageSource;
import org.onemind.jxp.ResourceStreamPageSource;


/**
 * @todo: 暂时不支持文件FilePageSource
 * @author new
 *
 */
public class JxpTemplateEngine  extends TemplateEngineSupport {
	private final Log log = LogFactory.getLog( this.getClass());
	
	
	public JxpTemplateEngine(){
	}
	public void mergeFileTemplate(Template pTemplate, Context pContext,
			Writer pWriter) throws MergeTemplateException {
		String encoding = pTemplate.getInputEncoding();		
		 MultiSourcePageSource multiSource = new MultiSourcePageSource();
		 ResourceStreamPageSource classPathSource = new ResourceStreamPageSource("");
		 
//		 FilePageSource pageSource = new FilePageSource("./templates", encoding);
//		multiSource.addPageSource(pageSource);
		multiSource.addPageSource(classPathSource);
		 JxpContext context = new JxpContext(multiSource);
		 JxpProcessor processor = new JxpProcessor(context);

		String path = pTemplate.getResource();
		if ( log.isDebugEnabled() ){
	          log.debug("模板文件: \"" + path + "\" 采用 Jxp 引擎进行合并.");
	          log.debug("模板文件: \"" + path + "\" 输入编码方式是：" + pTemplate.getInputEncoding());
		}
		if ( pContext == null ){
			pContext = new DefaultContext();
		}
		
		Map params = pContext.getParameters();
		try {
			processor.process(path, pWriter, params);
		} catch (Exception e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
    	}
		
		try {
			pWriter.flush();
		} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + path + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
		
		
	}
	private final String STR_PATH_ID = "JxpTemplateEngine_fcg_path_id_huangyh_not_should_exist_same";
	protected void mergeStringTemplate(Template pTemplate,
			Context pContext,
            Writer pWriter) throws MergeTemplateException{
		String encoding = pTemplate.getInputEncoding();	
		 ByteArrayPageSource byteArrayPageSource = new ByteArrayPageSource();
		 JxpContext context = new JxpContext(byteArrayPageSource);
		 JxpProcessor processor = new JxpProcessor(context);
		
		String templateStr = this.getStrTemplate(pTemplate);
		try {
			byteArrayPageSource.putPageBuffer(STR_PATH_ID, templateStr.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
		if ( pContext == null ){
			pContext = new DefaultContext();
		}
		Map params = pContext.getParameters();
		try {
			processor.process(STR_PATH_ID, pWriter, params);
		} catch (Exception e) {
 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
    	}
		
		try {
			pWriter.flush();
		} catch (IOException e) {
 			final String MSG = "合并模板文件 \"" + templateStr + "\"  失败!" + e.getMessage();
 			if ( log.isErrorEnabled()){
			  log.error(MSG, e);
 			}
			throw new MergeTemplateException(MSG, e);
		}
		
	}
	
	

	public static void main(String[] args) {
		JxpTemplateEngine a = new JxpTemplateEngine();
		Template t = new DefaultTemplate();
		t.setResource("test.jxp");
		Context c = new DefaultContext();
		c.put("msg", "hello");
		c.put("msg2", "中文.");
		StringWriter writer = new StringWriter();
		a.mergeFileTemplate(t, c, writer);
		System.out.println(writer.toString());
	}
}
