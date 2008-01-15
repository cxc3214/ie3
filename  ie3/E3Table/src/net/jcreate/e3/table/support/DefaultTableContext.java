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
package net.jcreate.e3.table.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import net.jcreate.e3.table.I18nResourceProvider;
import net.jcreate.e3.table.MessageSource;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.i18n.EmptyI18nResourceProvider;

public class DefaultTableContext implements TableContext {
	  private final WebContext webContext;
	  private final MessageSource messageSource;
	  public final I18nResourceProvider i8nResourceProvider;
	  
	  public DefaultTableContext(WebContext pWebContext){
		  this(pWebContext, new EmptyI18nResourceProvider(), null);
	  }
	  
	  public DefaultTableContext(PageContext pPageContext) {
		  this(new JspPageWebContext(pPageContext), new EmptyI18nResourceProvider(), null);
	  }
	  
	  public DefaultTableContext(HttpServletRequest pRequest) {
		  this(new HttpServletRequestWebContext(pRequest), new EmptyI18nResourceProvider(), null);
	  }	  
	  
	  
	  public DefaultTableContext(WebContext pWebContext,I18nResourceProvider pI18n, MessageSource pMessageSource){
		  this.webContext = pWebContext;
		  this.i8nResourceProvider = pI18n;
		  this.messageSource = pMessageSource;
	  }
	  
	  
	  public WebContext getWebContext() {
		return webContext;
  	  }
	  
 	  public MessageSource getMessageSource() {
 		  return this.messageSource;
	  }
 	  
	  public I18nResourceProvider getI18nResourceProvider() {
		return this.i8nResourceProvider;
  	  }
	

}
