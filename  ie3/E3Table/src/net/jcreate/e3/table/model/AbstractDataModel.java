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
package net.jcreate.e3.table.model;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;

/**
 * 当单元格对象全部可以通过反射获取时，请从该类派生 ,只需要实现hasNext,next方法即可
 * @author 黄云辉
 *
 */
public abstract class AbstractDataModel implements DataModel {
	
    private final Log logger = LogFactory.getLog( AbstractDataModel.class );	

    /**
     * 获取指定列的值
     * @param pItem 通过next跌代出来行对象
     * @param pProperty 列名
     * @return 单元格的值
     */
    public Object getCellValue(Object pItem, String pProperty){
    	if ( pItem == null ){
    		return null;
    	}
        Object itemValue = null;
        if ( pItem instanceof Map ){
        	itemValue = ((Map)pItem).get(pProperty); 
        }else{
        	try {
				itemValue = PropertyUtils.getProperty(pItem, pProperty);
        	}catch(Exception ex){
        		if ( logger.isDebugEnabled() ){
        		   logger.debug("类:" + pItem.getClass().getName() + "中不存在属性:" + pProperty);
        		}
			}//end try-catch
        }//end else
        return itemValue;
    }
    private final PageInfo navInfo;
    private final SortInfo sortInfo;
    
    public AbstractDataModel(){
    	this.navInfo = null;
    	this.sortInfo = null;
    	
    }
    public AbstractDataModel(SortInfo pSortInfo,PageInfo pNavInfo){
    	this.navInfo = pNavInfo;
    	this.sortInfo = pSortInfo;
    	
    }
	/**
	 * 获取导航信息
	 * @return
	 */
	public PageInfo getNavInfo(){
		return navInfo;
	}
	
	/**
	 * 获取排序信息
	 * @return
	 */
	public SortInfo getSortInfo(){
		return sortInfo;
	}

	
}
