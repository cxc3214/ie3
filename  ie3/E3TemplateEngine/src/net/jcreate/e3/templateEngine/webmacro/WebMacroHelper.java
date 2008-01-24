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
import java.io.InputStream;
import java.util.Properties;

import net.jcreate.e3.templateEngine.velocity.VelocityHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webmacro.Broker;
import org.webmacro.InitException;
import org.webmacro.WM;

/**
 * 
 * @author 黄云辉
 *
 */
public class WebMacroHelper {

	private static Log log = LogFactory.getLog(VelocityHelper.class);

	public static WM getWMEngine(Properties pProps) throws InitWebMacroEngineException {
		Broker broker = null;
		try {
			broker =   Broker.getBroker(pProps);
		} catch (InitException e) {
			e.printStackTrace();
			final String MSG = 
				"初始化WebMacro引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
				log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}
		WM wm = null;
		try {
			wm = new WM( broker );			
		} catch (InitException e) {
			e.printStackTrace();
			final String MSG = 
				"初始化WebMacro引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
				log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}   
		return wm;
	}  
	

	public static Properties getDefaultProperties() throws InitWebMacroEngineException {
		InputStream is = WebMacroHelper.class
				.getResourceAsStream("WebMacro.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			final String MSG = "导入属性文件出错!" + e.getMessage();
			if ( log.isErrorEnabled() ){
			  log.error(MSG, e);
			}
			throw new InitWebMacroEngineException(MSG, e);
		}
		return props;
	}
	
		
}