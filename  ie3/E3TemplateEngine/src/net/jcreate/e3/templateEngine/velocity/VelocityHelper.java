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

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.jcreate.e3.templateEngine.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author 黄云辉
 *
 */
public class VelocityHelper {

	private static Log log = LogFactory.getLog(VelocityHelper.class);

	/**
	 * 为了避免VelocityEngine跟其他项目用到的VelocityEngine产生冲突，所以
	 * 这里使用多实例引擎，而不是singleton引擎.
	 * @return
	 */
	public static VelocityEngine getVeocityEngine(Properties pProperties) throws InitVelocityEngineException{
		VelocityEngine ve = new VelocityEngine();
		try {
				ve.init( pProperties );				
		} catch (Exception e) {
			e.printStackTrace();
			final String MSG =
				"初始化Velocity引擎失败!" + e.getMessage();
			if ( log.isErrorEnabled() ){
			  log.error(MSG);
			}
			throw new InitVelocityEngineException(MSG, e);
		}
		return ve;
	}

	public static Properties getDefaultProperties()throws InitVelocityEngineException {
   	   InputStream is = VelocityHelper.class
				.getResourceAsStream("Velocity.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			final String MSG =
				"导入属性文件出错!" + e.getMessage();
			if ( log.isErrorEnabled()){
			  log.error("导入属性文件出错!" + e.getMessage());
			}
			throw new InitVelocityEngineException(MSG, e);
		}
		return props;
	}
	
	public static VelocityContext context2VelocityContext(Context pContext){
		if ( pContext == null ){
			return null;
		}
		VelocityContext result = new VelocityContext();
		Map params = pContext.getParameters();
		for(Iterator i= params.keySet().iterator(); i.hasNext();){
			String key = (String)i.next();
			Object value = params.get(key);
			result.put(key, value);
		}
	  return result;	
	}
		
}
