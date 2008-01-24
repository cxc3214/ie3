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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class FreeMarkerHelper {
	private static Log log = LogFactory.getLog(FreeMarkerHelper.class);
	
    private static Configuration cfg = null;
    static{
		cfg = new Configuration();
		try {
			TemplateLoader ct = new ClasspathTemplateLoader();
			TemplateLoader[] loaders = new TemplateLoader[] { ct };
			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
			cfg.setTemplateLoader(mtl);
		} catch (Exception e) {
            final String MSG = 
            	"初始化FreeMarker引擎失败." + e.getMessage();
            if ( log.isErrorEnabled() ){
            	log.error(MSG);
            }   
            throw new InitFreeMarkerEngineException(MSG, e);
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
    }
	/**
	 * 为了避免Configuration跟其他项目用到的Configuration产生冲突，所以
	 * 这里使用多实例引擎，而不是singleton引擎.
	 * @return
	 */
	public static Configuration  getConfiguration() {
		return cfg;
	}

}
