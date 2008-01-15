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

import net.jcreate.e3.table.Sort;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.support.DefaultColumn;
import net.jcreate.e3.table.support.TableConstants;

public class HTMLColumn extends DefaultColumn implements Attributeable{
	private Attributes attributes = new Attributes();
	public void setAttribute(String name, String value) {
		attributes.setAttribute(name, value);
	}
	
	public String getAttributesAsString(){
		return this.attributes.geAttributesAsString();
	}

	/**
	 * 能否排序
	 */
	private boolean sortable = TableConstants.DEFAULT_SORTABLE;
	/**
	 * 排序属性，默等于property
	 */
	private String sortName;
	
	/**
	 * 排序方向
	 */
	private String sortDir =  TableConstants.DEFAULT_SORT_DIR;
	
	
	
	public String getNextSortDir(){
		return Sort.getSort(this.sortDir).nextSort().getCode();
	}
	
	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getSortName() {
		if ( this.sortName == null ){
			return this.getProperty();
		}
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}



	public HTMLColumn(String pProperty, Table pTable) {
		super(pProperty, pTable);
	}

	public HTMLColumn() {
		super("no");
	}
	public HTMLColumn(String pProperty) {
		super(pProperty);
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

}
