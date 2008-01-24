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

import java.net.URL;

import freemarker.cache.URLTemplateLoader;

public class ClasspathTemplateLoader extends URLTemplateLoader{
    protected URL getURL(String name)
    {
    	URL result = null;
        
        if (name == null || name.length() == 0)
        {
            throw new  IllegalArgumentException("No template name provided");
        }
        
        try 
        {
            ClassLoader classLoader = this.getClass().getClassLoader();
            result= classLoader.getResource( name );
        }
        catch( Exception fnfe )
        {
            throw new RuntimeException("获取资源失败!资源:" + name);
        }
        
        return result;
    }

}
