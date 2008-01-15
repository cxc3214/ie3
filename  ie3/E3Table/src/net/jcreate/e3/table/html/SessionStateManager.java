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

import net.jcreate.e3.table.StateInfo;
import net.jcreate.e3.table.StateManager;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.support.TableConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionStateManager implements StateManager {

	private final Log logger = LogFactory.getLog( this.getClass() );
	private final String id;
	private final WebContext webContext;
	
	public SessionStateManager(String pId, WebContext pWebContext){
		if ( pId == null ){
			throw new NullPointerException("id不能为空null");
		}
		this.id = pId;
		this.webContext = pWebContext;
	}
	public StateInfo loadStateInfo() {
		String stateParam = (String)webContext.getSessionAttribute(TableConstants.STATE_PARAM_PREFIX + this.id);
		 String stateParamValue = webContext.getParameter(stateParam);
		 if ( "true".equalsIgnoreCase(stateParamValue)){
			 return (StateInfo)webContext.getSessionAttribute(TableConstants.STATE_INFO_PREFIX + this.id);
		 }
		 stateParamValue = (String)webContext.getRequestAttribute(stateParam);
		 if ( "true".equalsIgnoreCase(stateParamValue)){
			 return (StateInfo)webContext.getSessionAttribute(TableConstants.STATE_INFO_PREFIX + this.id);
		 }
		 
		return null;
	}

	public void saveStateInfo(StateInfo pStateInfo) {
		webContext.setSessionAttribute(TableConstants.STATE_INFO_PREFIX + this.id, pStateInfo);
		
	}
}
