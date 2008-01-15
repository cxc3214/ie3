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

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.model.DataModelFactory;
import net.jcreate.e3.table.support.HttpServletRequestWebContext;

public class HTMLTableFacade {
	
	
	private final String id;
	private final HttpServletRequest request;
	private final WebContext webContext ;
	private DataModel dataModel = null;
	private String[] columnsProperties = null;
	
   public HTMLTableFacade(String pTableID, final HttpServletRequest pRequest){
	  this.id = pTableID;
	  this.request = pRequest;
	  webContext = new HttpServletRequestWebContext(pRequest);
	}
	
	public NavRequest getNavRequest(){
		return null;
	}
	
	public String exportScript(){
		return null;
	}
	public void setItems(Object pItems){
		dataModel = DataModelFactory.getInstance( pItems ); 
	}
	
	public void setColumnsProperties(String[] pColumns){
		this.columnsProperties = pColumns;
	}
}
