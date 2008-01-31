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
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;

public class MapDataModel extends AbstractDataModel{

	private final Map datas ;
	private final Iterator datasIterator;
	private final Log logger = LogFactory.getLog( MapDataModel.class );
	
	public MapDataModel(Map pDatas,SortInfo pSortInfo, PageInfo pNavInfo){
		super(pSortInfo,pNavInfo);
		if ( pDatas == null ){
			this.datas = Collections.EMPTY_MAP;
		}else{
		    this.datas = pDatas;
		}
		this.datasIterator = datas.entrySet().iterator();
	}
	
	public MapDataModel(Map pDatas){
		if ( pDatas == null ){
			this.datas = Collections.EMPTY_MAP;
		}else{
		    this.datas = pDatas;
		}
		this.datasIterator = datas.entrySet().iterator();
	}
	public boolean hasNext() {
		if ( this.datasIterator == null ){
			return false;
		}
		return datasIterator.hasNext();
	}
	
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
        java.util.Map.Entry entry = (java.util.Map.Entry)pItem;
        
        Object row = entry.getValue();
        if ( row instanceof Map ){
        	itemValue = ((Map)row).get(pProperty); 
        }else{
        	try {
				itemValue = PropertyUtils.getProperty(row, pProperty);
        	}catch(Exception ex){
        		if ( logger.isDebugEnabled() ){
        		   logger.debug("类:" + pItem.getClass().getName() + "中不存在属性:" + pProperty);
        		}
			}//end try-catch
        }//end else
        return itemValue;
    }
	

	
	public Object next() {
		return datasIterator.next();
	}

}