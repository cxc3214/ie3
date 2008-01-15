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
package net.jcreate.e3.table.html;

import net.jcreate.e3.table.decorator.DecoratorFactory;
import net.jcreate.e3.table.util.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HTMLBuilderFactory {
	

	private static final Log logger = LogFactory.getLog( DecoratorFactory.class );
	private HTMLBuilderFactory(){
		
	}
	
	public static AbstractHTMLTableBuilder getInstance(String pClassName){
		if ( pClassName == null ){
			final String msg =
				"builder类名为空null";
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		if ( "default".equalsIgnoreCase(pClassName) ){
			return new SkinHTMLTableBuilder();
		}
		
		
		/**
		 * TODO: 根据别名，创建单元格对象
		 */
		try {
			Object obj = ClassUtils.getNewInstance(pClassName);
			if ( obj instanceof AbstractHTMLTableBuilder == false ){
				final String msg = 
					"类:" + pClassName + "没有从:" + AbstractHTMLTableBuilder.class.getName() + " 派生!" ;
				logger.error(msg);
				throw new IllegalArgumentException(msg);
			}
			return (AbstractHTMLTableBuilder)obj;
		} catch (Exception e) {
           final String msg =
        	   "创建类:" + pClassName + "实例失败";
           logger.error(msg, e);
           throw new RuntimeException( msg );
		}
		
	}	
}
