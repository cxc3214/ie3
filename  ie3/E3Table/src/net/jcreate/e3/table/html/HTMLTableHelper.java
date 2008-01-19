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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.Sort;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.StateInfo;
import net.jcreate.e3.table.message.MessageSourceFactory;
import net.jcreate.e3.table.model.CollectionDataModel;
import net.jcreate.e3.table.model.EmptyDataModel;
import net.jcreate.e3.table.support.DefaultPageInfo;
import net.jcreate.e3.table.support.DefaultSortInfo;
import net.jcreate.e3.table.support.EmptySortInfo;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.table.support.HttpServletRequestWebContext;

import org.apache.commons.beanutils.BeanComparator;
//import org.apache.commons.beanutils.

public abstract class HTMLTableHelper {

	private HTMLTableHelper(){
		
	}
	
	private static NavRequest getNavRequestFromState(String pTableID, HttpServletRequest pRequest){
		NavRequest result = new NavRequest();
		SessionStateManager stateManager = new SessionStateManager(pTableID, new HttpServletRequestWebContext(pRequest));
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
	/**
	 * TODO: 这些工具到写的很简单，需要进行更多处理 
	 * @param pTableID
	 * @param pRequest
	 * @return
	 */
	public static NavRequest getNavRequest(String pTableID, HttpServletRequest pRequest, int pPageSize){
		HttpSession session = pRequest.getSession(true); 
		Boolean enableStateManager =
		 (Boolean)session.getAttribute(TableConstants.ENABLED_STATE_MANAGER_PREFIX + pTableID);
		String start = pRequest.getParameter(TableConstants.START_PARAM);
		if ( start == null ){//如果没有传递导航参数，就要考虑是否启用状态管理的信息.
		if ( enableStateManager != null ){
			if ( enableStateManager.booleanValue() == true ){
				NavRequest result = getNavRequestFromState(pTableID, pRequest);
				if ( result != null ){
					return result;
				}
			}
		}
		}
		NavRequest result = new NavRequest();		
		
		String pageSize = pRequest.getParameter(TableConstants.PAGE_SIZE_PARAM);
		String sortColumn = pRequest.getParameter(TableConstants.SORT_PROPERTY_PARAM);
		String sortName = pRequest.getParameter(TableConstants.SORT_NAME_PARAM);
		String sortDir = pRequest.getParameter(TableConstants.SORT_DIR_PARAM);
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
	public static NavRequest getNavRequest(String pTableID, HttpServletRequest pRequest){
	  String pageSize = MessageSourceFactory.getInstance().getMessage(TableConstants.PAGE_SIZE_KEY,null,null);
      return getNavRequest(pTableID, pRequest,Integer.parseInt(pageSize) );    
	}
	
	public static DataModel getDataModel(NavRequest pNavRequest, Collection pList ){
		if ( pList == null ){
			return EmptyDataModel.getInstance();
		}
		List allData = new ArrayList(pList);		
		String property = pNavRequest.getSortProperty();
		if ( property != null ){
			String sortDir = pNavRequest.getSortDir();
			if ( Sort.ASC.getCode().equals(sortDir) ){//升序
		       Collections.sort(allData, new BeanComparator(property));//beanutil的通用排序器
			}else if ( Sort.DESC.getCode().equals(sortDir) ){
				Collections.sort(allData, new BeanComparator(property));
				Collections.reverse(allData);	
			}else{
				;// do nothing;这种情况应该不会出现.
			}
		   
		}
		
		int start = pNavRequest.getStart();
		int pageSize = pNavRequest.getPageSize();
		int min = Math.max(start, 0);
		int max = Math.min(allData.size(), min + pageSize);
		SortInfo sortInfo = null;
		if ( pNavRequest.getSortProperty() != null ){
			sortInfo = new DefaultSortInfo(pNavRequest.getSortProperty(), pNavRequest.getSortDir());
		}else{
			sortInfo = EmptySortInfo.getInstance();
		}
		DefaultPageInfo pageInfo = new DefaultPageInfo(start, allData.size(), pNavRequest.getPageSize());
		List data = allData.subList(min, max);
		DataModel result = new CollectionDataModel(data, sortInfo, pageInfo);
		return result;
	}
}
