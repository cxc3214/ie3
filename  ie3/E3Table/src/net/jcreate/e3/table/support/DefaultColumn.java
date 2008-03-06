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

import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.ColumnGroup;
import net.jcreate.e3.table.Table;

public class DefaultColumn implements Column{

	private Table table;
	private String property;
	private String title;	
	private String width;
	/**
	 * 标题key,用于从消息文件中获取标题
	 */
	private String titleKey;

	private int columnIndex;
	private CellDecorator cellDecorator;
	private ColumnGroup columnGroup;
	
	
	public ColumnGroup getColumnGroup() {
		return columnGroup;
	}

	public void setColumnGroup(ColumnGroup columnGroup) {
		/**
		 * todo: 如果之前属于别的组时，这里要进行处理，要从别的组中删除
		 * 目前版本column一旦加入一个组，不会出现换组的情况，所以暂时不
		 * 处理.
		 */
		this.columnGroup = columnGroup;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}	

	public CellDecorator getCellDecorator() {
		return cellDecorator;
	}

	public void setCellDecorator(CellDecorator cellDecorator) {
		this.cellDecorator = cellDecorator;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public DefaultColumn(String pProperty){
		this.property = pProperty;
	}

	public DefaultColumn(String pProperty, Table pTable){
		this.property = pProperty;
		this.table = pTable;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean isOdd() {
		if ( (this.getColumnIndex()+1) % 2 == 0){
			return false;
		}else{
			return true;
		}
	}

	public boolean isFirst() {
		if ( this.getColumnIndex() == 0 ){
			return true;
		}else{
			return false;
		}
	}

	public boolean isLast() {
		int size = this.getTable().getColumns().size();
		if ( this.getColumnIndex() == (size-1) ){
			return true;
		}else{
			return false;
		}
	}

	public String getProperty() {
		return property;
	}
	
	public boolean equals(Object pObj){
		if ( pObj instanceof DefaultColumn == false){
			return false;
		}
		DefaultColumn objColumn = (DefaultColumn)pObj;
		if ( this.property == null ){
			return super.equals(pObj);
		}
		return this.property.equals(objColumn.getProperty());
	}
	
	public int hashCode(){
		if ( this.property == null ){
			return super.hashCode();
		}
		return this.property.hashCode();
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		final String ENTER = "\n";
		sb.append("property=").append(this.property).append(ENTER);
		sb.append("title=").append(this.title).append(ENTER);
		sb.append("width=").append(this.width).append(ENTER);
		return sb.toString();
	}

	public String getTitle() {
		return this.title;
	}


	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public Table getTable() {
		return table;
	}



}
