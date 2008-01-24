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

import java.util.HashMap;
import java.util.Map;
import net.jcreate.e3.templateEngine.TemplateEngine;
import net.jcreate.e3.templateEngine.freemarker.FreeMarkerTemplateEngine;
import net.jcreate.e3.templateEngine.jxp.JxpTemplateEngine;
import net.jcreate.e3.templateEngine.velocity.VelocityTemplateEngine;
import net.jcreate.e3.templateEngine.webmacro.WebMacroTemplateEngine;

/**
 * 根据引擎类别选择引擎实例
 * @author Administrator
 *
 */
public class TemplateEngineFactory {
	private static Map ENGINES = new HashMap();

	static {
		//Velocity
		if ( isExistClass("org.apache.velocity.app.VelocityEngine") ){
			VelocityTemplateEngine ve = new VelocityTemplateEngine();
			ve.init();
			ENGINES.put(TemplateType.VELOCITY, ve);
		}
		//FreeMarker
		if ( isExistClass("freemarker.template.Template") ){
			ENGINES.put(TemplateType.FREE_MARKER, new FreeMarkerTemplateEngine());
		}
		//WebMacro
		if ( isExistClass("org.webmacro.WM") ){		
			WebMacroTemplateEngine wm = new WebMacroTemplateEngine();
			wm.init();
			ENGINES.put(TemplateType.WEB_MACRO, wm);
		}
		//JxpProcessor
		if ( isExistClass("org.onemind.jxp.JxpProcessor") ){
			ENGINES.put(TemplateType.JXP, new JxpTemplateEngine());
		}
	}

	private TemplateEngineFactory() {

	}

	/**
	 * 检查当前ClassLoader种,是否存在指定class
	 * @param pClass
	 * @return
	 */
	private static boolean isExistClass(String pClass) {
		try {
			Class.forName(pClass);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取模板引擎.
	 * @param pTemplateType
	 * @return
	 */
	public static TemplateEngine getInstance(TemplateType pType) {
		if (pType == null) {
			return null;
		}
		if (ENGINES.containsKey(pType) == false) {
			throw new IllegalArgumentException("不支持的模板类别:" + pType.getType());
		}
		return (TemplateEngine) ENGINES.get(pType);
	}
}
