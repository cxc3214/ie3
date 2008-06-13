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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.Header;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DefaultHeader implements Header{

	private List columns = new ArrayList();
	private Map  columnsMap = new HashMap();
	private Table table;
	private final Log logger = LogFactory.getLog( DefaultHeader.class );
	
	public Table getTable(){
		return table;
	}
	public void addColumn(Column pColumn){
		if ( pColumn == null ){
			throw new RuntimeException("列不能为空null");
		}
		String property = pColumn.getProperty(); 
		if (  property== null ){
			throw new RuntimeException("列名不能为空null");
		}
		if ( columnsMap.containsKey(property) ){
			final String MSG =
				"已经存在名为:" + property + "的列!,请设置其他名称!";
			logger.error(MSG);
			throw new IllegalArgumentException(MSG);
		}
		columnsMap.put(property, pColumn);
		columns.add(pColumn);
		if ( logger.isDebugEnabled() ){
			logger.debug("新增加列:" + property);
		}
	}
	
	public void removeColumn(String pProperty){
		Column column = (Column)columnsMap.get(pProperty);
		if ( column == null ){//不存在
			final String MSG =
			  "删除列失败，因为不存在列:" + pProperty;
			logger.debug(MSG);
			return;
		}
		this.columns.remove(column);
		this.columnsMap.remove(pProperty);
		if ( logger.isDebugEnabled() ){
		  logger.debug("列:" + pProperty + "已被删除！");
		}
	}
	public List getColumns() {
		return new ArrayList( this.columns );
	}

	public Column getColumn(String property) {
		return (Column)columnsMap.get(property);
	}

	public Column getColumn(int index) {
		return (Column)this.columns.get(index);
	}

	public int getSize() {
		return columns.size();
	}
	public void setTable(Table pTable) {
      this.table = pTable;
	}
	public int getColumnIndex(Column column) {
		return this.columns.indexOf(column);
	}

}
