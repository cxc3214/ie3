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
package net.jcreate.e3.table.i18n;

import java.util.Locale;

import net.jcreate.e3.table.I18nResourceProvider;
import net.jcreate.e3.table.NoSuchMessageException;
import net.jcreate.e3.table.WebContext;

public class EmptyI18nResourceProvider implements I18nResourceProvider {

    public String getMessage(String pTitleKey,String defaultMessage, WebContext pWebContext){
      return null;	
    }
    public String getMessage(String pTitleKey,  WebContext pWebContext) throws NoSuchMessageException{
       throw new NoSuchMessageException(pTitleKey);	
    }
	public Locale resolveLocale(WebContext webContext) {
		return webContext.getLocale();
	}

}
