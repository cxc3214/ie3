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

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Row;

public class DefaultCell implements Cell{

	private Object value;
	private Row row;
	private Column column;
	private CellDecorator cellDecorator;
	
	public DefaultCell(){
		
	}	
	public DefaultCell(Object pValue){
		this.value = pValue;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public CellDecorator getCellDecorator() {
		return cellDecorator;
	}

	public void setCellDecorator(CellDecorator cellDecorator) {
		this.cellDecorator = cellDecorator;
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
		int size = row.getCells().size();
		int rowIndex = this.getRowIndex();		
        if ( rowIndex == (size - 1) ){
        	return true;
        }else{
        	return false;
        }
	}

	public Object getValue() {
		return this.value;
	}

	public Row getRow() {
      return this.row;
	}

	public Column getColumn() {
		return column;
	}

	public int getRowIndex() {
		return this.getRow().getRowIndex();
	}

	public int getColumnIndex() {
		return this.getColumn().getColumnIndex();
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isOddColumn() {
		return this.getColumn().isOdd();
	}

	public boolean isOddRow() {
		return this.getRow().isOdd();
	}

}
