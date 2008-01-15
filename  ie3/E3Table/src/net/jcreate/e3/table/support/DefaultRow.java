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
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;

public class DefaultRow implements Row {

	private int rowIndex;
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
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public boolean isOdd() {
		if ( (this.getRowIndex()+1) % 2 == 0){
			return false;
		}else{
			return true;
		}
	}

	public boolean isFirst() {
        if ( rowIndex == 0 ){
        	return true;
        }else{
    		return false;        	
        }
	}

	public boolean isLast() {
		int size = table.getRows().size();
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
		this.cells.add(pCell);
		this.columns.put(pCell.getColumn().getProperty(), pCell);
	}
	
	public Cell getCell(String pProperty){
		return (Cell)columns.get(pProperty);
    }

	public int getRowIndex() {
		return rowIndex;
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
		return (Cell)cells.get(pIndex);
	}

}
