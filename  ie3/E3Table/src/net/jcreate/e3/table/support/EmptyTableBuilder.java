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

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.ColumnGroup;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.TableContextSupport;

public abstract class EmptyTableBuilder implements TableBuilder, TableContextSupport{

	protected TableContext tableContext;
	/**
	 * 初始化
	 *
	 */
	public void init(Table pTable){
		
	}
	
	/**
	 * destory
	 *
	 */
	public void destory(Table pTable){
		
	}
	
	public void buildNoDataRow(Table pTable) throws BuildTableException{
		
	}
	
	public void buildBeginScript(Table pTable) throws BuildTableException {
		
		
	}

	public void buildEndScript(Table pTable) throws BuildTableException {
		
		
	}

    public void buildDocBegin(Table pTable) throws BuildTableException{
    	
    }
    public void buildDocEnd(Table pTable) throws BuildTableException{
    	
    }
	
	public void buildTableBegin(Table pTable) throws BuildTableException {
		
		
	}

	public void buildTableEnd(Table pTable) throws BuildTableException {
		
		
	}


	public void buildCaption(Table pTable) throws BuildTableException {
		
		
	}

	public void buildTopPanel(Table pTable) throws BuildTableException {
		
		
	}

	public void buildTopToolbar(Table pTable) throws BuildTableException {
		
		
	}

	public void buildBodyBegin(Table pTable) throws BuildTableException {
		
		
	}


	public void buildColumnBegin(Column pColumn) throws BuildTableException {
		
		
	}

	public void buildColumn(Column pColumn) throws BuildTableException {
		
		
	}

	public void buildColumnEnd(Column pHeader) throws BuildTableException {
		
		
	}



	public void buildRowBegin(Row pRow) throws BuildTableException {
		
		
	}

	public void buildCellBegin(Cell pCell) throws BuildTableException {
		
		
	}

	public void buildCell(Cell pCell) throws BuildTableException {
		
		
	}

	public void buildCellEnd(Cell pCell) throws BuildTableException {
		
		
	}

	public void buildRowEnd(Row pRwo) throws BuildTableException {
		
		
	}


	public void buildBodyEnd(Table pTable) throws BuildTableException {
		
		
	}

	public void buildBottomToolbar(Table pTable) throws BuildTableException{
		
		
	}
	
	public void buildColumnGroupsBegin(Table pTable) throws BuildTableException{
		
	}
	public void buildColumnGroupBegin(ColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	public void buildColumnGroup(ColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	public void buildColumnGroupEnd(ColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	public void buildColumnGroupsEnd(Table pTable) throws BuildTableException{
		
	}
	

	public void buildBottomPanel(Table pTable) throws BuildTableException {
		
		
	}

	public void buildHeaderBegin(Header pHeader) throws BuildTableException {
	}

	public void buildHeaderEnd(Header pHeader) throws BuildTableException {
	}

	public TableContext getTableContext() {
		return tableContext;
	}

	public void setTableContext(TableContext tableContext) {
		this.tableContext = tableContext;
	}


}
