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

import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.StateInfo;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.message.MessageSourceFactory;
import net.jcreate.e3.table.support.HttpServletRequestWebContext;
import net.jcreate.e3.table.support.TableConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class HTMLTableHelper {

	private final static Log logger = LogFactory.getLog( HTMLTableHelper.class );
	private HTMLTableHelper(){
		
	}
	
	private static NavRequest getNavRequestFromState(String pTableID, WebContext pWebContext){
		NavRequest result = new NavRequest();
		SessionStateManager stateManager = new SessionStateManager(pTableID, pWebContext);
		StateInfo stateInfo = stateManager.loadStateInfo();
		if ( stateInfo == null ){
			return null;
		}
		SortInfo sortInfo =stateInfo.getSortInfo();
		if ( sortInfo != null ){
			result.setSortProperty(sortInfo.getSortProperty());
			result.setSortDir(sortInfo.getSortDir());
		}
		PageInfo pageInfo = stateInfo.getPageInfo();
		if (pageInfo != null){
			result.setStart(pageInfo.getStart());
			result.setPageSize(pageInfo.getPageSize());
		}
		return result;
		
	}
	public static NavRequest getNavRequest(String pTableID, WebContext pWebContext, int pPageSize){
 
		Boolean enableStateManager =
		 (Boolean)pWebContext.getSessionAttribute(TableConstants.ENABLED_STATE_MANAGER_PREFIX + pTableID);
		String start = pWebContext.getParameter(TableConstants.START_PARAM);
		if ( start == null ){//如果没有传递导航参数，就要考虑是否启用状态管理的信息.
		if ( enableStateManager != null ){
			if ( enableStateManager.booleanValue() == true ){
				NavRequest result = getNavRequestFromState(pTableID, pWebContext);
				if ( result != null ){
					return result;
				}
			}
		}
		}
		NavRequest result = new NavRequest();		
		
		String pageSize = pWebContext.getParameter(TableConstants.PAGE_SIZE_PARAM);
		String sortColumn = pWebContext.getParameter(TableConstants.SORT_PROPERTY_PARAM);
		String sortName = pWebContext.getParameter(TableConstants.SORT_NAME_PARAM);
		String sortDir = pWebContext.getParameter(TableConstants.SORT_DIR_PARAM);
		if ( start == null ){
			result.setStart(0);
			result.setPageSize(pPageSize);
		} else {
			result.setStart(Integer.parseInt(start));
			result.setPageSize(Integer.parseInt(pageSize));
		}
		
		result.setSortProperty(sortColumn);
		result.setSortDir(sortDir);
		result.setSortName(sortName);
	  return result;	
		
	}
	/**
	 * TODO: 这些工具到写的很简单，需要进行更多处理 
	 * @param pTableID
	 * @param pRequest
	 * @return
	 */
	public static NavRequest getNavRequest(String pTableID, HttpServletRequest pRequest, int pPageSize){
         return getNavRequest(pTableID, new HttpServletRequestWebContext(pRequest), pPageSize );		
	}
	
	/**
	 * TODO: 这些工具到写的很简单，需要进行更多处理 
	 * @param pTableID
	 * @param pRequest
	 * @return
	 */
	public static NavRequest getNavRequest(String pTableID, HttpServletRequest pRequest){
		String strPageSize = MessageSourceFactory.getInstance().getMessage(TableConstants.PAGE_SIZE_KEY,null,null);
		int pageSize = TableConstants.DEFAULT_PAGE_SIZE;
		try{
		  pageSize = Integer.parseInt(strPageSize);
		}catch(Exception ex){
			logger.warn("每页记录数:[" + strPageSize + "]不是有效数字!使用默认值:" + TableConstants.DEFAULT_PAGE_SIZE);
		}

      return getNavRequest(pTableID, pRequest, pageSize );    
	}
	
}
