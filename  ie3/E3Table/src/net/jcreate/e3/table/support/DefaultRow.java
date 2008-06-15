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

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;

public class DefaultRow implements Row {

 
	private Table table;
	private List cells = new ArrayList();
	private Map  columns = new HashMap();
	private Object rowObject;
	
	public DefaultRow(){
		
	}
	public DefaultRow(Object pRowObject){
		this.rowObject = pRowObject;
	}
	
    public Object getRowObject(){
    	return rowObject;
    }

	public boolean isOdd() {
		if ( (this.getRowIndex()+1) % 2 == 0){
			return false;
		}else{
			return true;
		}
	}

	public boolean isFirst() {
		int rowIndex = this.getRowIndex();
        if ( rowIndex == 0 ){
        	return true;
        }else{
    		return false;        	
        }
	}

	public boolean isLast() {
		int size = table.getRows().size();
		int rowIndex = this.getRowIndex();
		if ( rowIndex == (size-1) ){
			return true;
		}else{
		   return false;
		}
	}

	public List getCells() {
		return new ArrayList(cells);
	}
	
	public void addCell(Cell pCell){
		if ( pCell == null ){
			return;
		}
		/**
		 * @fixme: 如果单元格在其他行，要先从其他行把他删除
		 */
		pCell.setRow(this);
		//pCell.setRow(pRow)
		this.cells.add(pCell);
		Column column = pCell.getColumn();
	    if ( column == null ){
	    	return;
	    }
	    String property = column.getProperty();
	    if ( property == null ){
	    	return;
	    }
		this.columns.put(property, pCell);
	}
	
	public Cell getCell(String pProperty){
		return (Cell)columns.get(pProperty);
    }

    /**
     * 获取单元格索引
     * @param pCell
     * @return
     */
    public int getCellIndex(Cell pCell){
    	return this.cells.indexOf(pCell);
    }

	public int getRowIndex() {
		if ( table == null ){
			return -1;
		}
		return this.table.getRowIndex(this);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	public void setRowObject(Object rowObject) {
		this.rowObject = rowObject;
	}
	public Cell getCell(int pIndex) {
		if ( pIndex > (this.cells.size()-1) ){
			return null;
		}
		return (Cell)cells.get(pIndex);
	}

}
