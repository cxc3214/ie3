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
package net.jcreate.e3.resource.handler;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.jcreate.e3.resource.HandleResourceException;
import net.jcreate.e3.resource.HttpHolder;
import net.jcreate.e3.resource.Resource;
import net.jcreate.e3.resource.ResourceHandler;

public class CSSMinResourceHandler implements ResourceHandler {
	
	private final Log logger = LogFactory.getLog( this.getClass() );
	private static final int LINEBREAK_AFTER_CHARACTERS = 8000;
	public void handle(Resource pResource) throws HandleResourceException {	
		String charset = pResource.getCharset();
		if ( charset == null ){
			charset = HttpHolder.getResponse().getCharacterEncoding();
		}
		logger.info("正在对资源:" + pResource.getUri() + "进行css min压缩...");
		try{
		  InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(pResource.getTreatedData()), charset);
		  CssCompressor cssc = new CssCompressor(isr);
		  StringWriter sw = new StringWriter();
		  cssc.compress(sw, LINEBREAK_AFTER_CHARACTERS);
		  sw.flush();
          sw.toString();
          sw.close();
          logger.info("css min资源:" + pResource.getUri() + "成功." );
		}catch (Exception ex){
			final String MSG = "css min资源:" + pResource.getUri() + "失败！"; 
			logger.warn(MSG ,ex);
			return; 
			
		}
		
	}

}
