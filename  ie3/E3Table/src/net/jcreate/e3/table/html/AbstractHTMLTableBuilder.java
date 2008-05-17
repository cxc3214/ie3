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

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.ColumnGroup;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.support.EmptyTableBuilder;

public abstract class AbstractHTMLTableBuilder extends EmptyTableBuilder{

   protected StringBuffer tableScript = null;
   private static final int DEFAULT_BUFFER_SIZE = 200;
   protected int bufferSize = DEFAULT_BUFFER_SIZE;
   protected HTMLTable table = null;
   public static final String ENTER = "\n";
   
   public AbstractHTMLTableBuilder(){
	   
   }
   

	/**
	 * 初始化
	 *
	 */
	final public void init(Table pTable){
	   init( (HTMLTable)pTable );

	}
   
   protected void init(HTMLTable pTable){
	   if ( tableScript == null )
	     tableScript = new StringBuffer(bufferSize);
	   else
	     tableScript.delete(0, tableScript.length());
	   table = pTable;	   
   }
   
	
	/**
	 * destory	 
	 */
	final public void destory(Table pTable){
		destory((HTMLTable)pTable);
	}
	protected void destory(HTMLTable pTable){		
	}
	
   
   final public void buildBeginScript(Table pTable) throws BuildTableException {
	   buildHTMLBeginScript((HTMLTable)pTable);		
	}
   
   protected void buildHTMLBeginScript(HTMLTable pTable) throws BuildTableException {	   
   }

	final public void buildEndScript(Table pTable) throws BuildTableException {
		buildHTMLEndScript((HTMLTable)pTable);
	}
	
	protected void buildHTMLEndScript(HTMLTable pTable) throws BuildTableException {
	}

	

    final public void buildDocBegin(Table pTable) throws BuildTableException{
    	buildHTMLDocBegin((HTMLTable)pTable);
    	buildHTMLParamsForm((HTMLTable)pTable);
    }
    protected void buildHTMLDocBegin(HTMLTable pTable) throws BuildTableException{
    	
    }
    
    protected void buildHTMLParamsForm(HTMLTable pTable) throws BuildTableException{
    	
    }
    
	final public void buildNoDataRow(Table pTable) throws BuildTableException{
		buildHTMLNoDataRow((HTMLTable)pTable);
	}
	protected void buildHTMLNoDataRow(HTMLTable pTable) throws BuildTableException{
		
	}

    
    final public void buildDocEnd(Table pTable) throws BuildTableException{
    	buildHTMLDocEnd((HTMLTable)pTable);    	
    }
    protected void buildHTMLDocEnd(HTMLTable pTable) throws BuildTableException{
    }
    
	
	final public void buildTableBegin(Table pTable) throws BuildTableException {
		buildHTMLTableBegin((HTMLTable)pTable);
	}
	protected void buildHTMLTableBegin(HTMLTable pTable) throws BuildTableException {
	}
	

	final public void buildTableEnd(Table pTable) throws BuildTableException {
		buildHTMLTableEnd((HTMLTable)pTable);
	}
	protected void buildHTMLTableEnd(HTMLTable pTable) throws BuildTableException {
	}
	


	public final  void buildCaption(Table pTable) throws BuildTableException {
		buildHTMLCaption((HTMLTable)pTable);
	}
	protected void buildHTMLCaption(HTMLTable pTable) throws BuildTableException {
	}
	

	final public void buildTopPanel(Table pTable) throws BuildTableException {
		buildHTMLTopPanel((HTMLTable)pTable);
	}
	protected void buildHTMLTopPanel(HTMLTable pTable) throws BuildTableException {
	}
	

	final public void buildTopToolbar(Table pTable) throws BuildTableException {
		buildHTMLTopToolbar((HTMLTable)pTable);
	}
	protected void buildHTMLTopToolbar(HTMLTable pTable) throws BuildTableException {
	}
	

	final public void buildBodyBegin(Table pTable) throws BuildTableException {
		buildHTMLBodyBegin((HTMLTable)pTable);
	}
	protected  void buildHTMLBodyBegin(HTMLTable pTable) throws BuildTableException {
	}


	final public void buildColumnGroupsBegin(Table pTable) throws BuildTableException{
		buildHTMLColumnGroupsBegin((HTMLTable)pTable);
	}	
	protected void buildHTMLColumnGroupsBegin(HTMLTable pTable) throws BuildTableException{
		
	}
	
	final public void buildColumnGroupBegin(ColumnGroup pColumnGroup) throws BuildTableException{
		buildHTMLColumnGroupBegin((HTMLColumnGroup)pColumnGroup);
	}
	protected void buildHTMLColumnGroupBegin(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	
	final public void buildColumnGroup(ColumnGroup pColumnGroup) throws BuildTableException{
		buildHTMLColumnGroup((HTMLColumnGroup)pColumnGroup);
	}
	protected void buildHTMLColumnGroup(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	
	final public void buildColumnGroupEnd(ColumnGroup pColumnGroup) throws BuildTableException{
		buildHTMLColumnGroupEnd((HTMLColumnGroup)pColumnGroup);
	}
	protected void buildHTMLColumnGroupEnd(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		
	}
	
	final public void buildColumnGroupsEnd(Table pTable) throws BuildTableException{
		buildHTMLColumnGroupsEnd((HTMLTable)pTable);
	}
	protected void buildHTMLColumnGroupsEnd(HTMLTable pTable) throws BuildTableException{
		
	}
	
	
	
	
	final public void buildColumnBegin(Column pColumn) throws BuildTableException {
		buildHTMLColumnBegin((HTMLColumn)pColumn);
	}
		
	protected void buildHTMLColumnBegin(HTMLColumn pColumn) throws BuildTableException {
	}

	final public void buildColumn(Column pColumn) throws BuildTableException {
		buildHTMLColumn((HTMLColumn)pColumn);
	}
	protected void buildHTMLColumn(HTMLColumn pColumn) throws BuildTableException {
	}

	final public void buildColumnEnd(Column pColumn) throws BuildTableException {
		buildHTMLColumnEnd((HTMLColumn)pColumn);
	}
	protected void buildHTMLColumnEnd(HTMLColumn pColumn) throws BuildTableException {
	}


	

	final public void buildRowBegin(Row pRow) throws BuildTableException {
		buildHTMLRowBegin((HTMLRow)pRow);
	}
	protected void buildHTMLRowBegin(HTMLRow pRow) throws BuildTableException {
	}
	

	final public void buildCellBegin(Cell pCell) throws BuildTableException {
		buildHTMLCellBegin ( (HTMLCell)pCell );
	}
	protected void buildHTMLCellBegin(HTMLCell pCell) throws BuildTableException {
	}
	

	final public void buildCell(Cell pCell) throws BuildTableException {
		buildHTMLCell((HTMLCell)pCell);
	}
	protected void buildHTMLCell(HTMLCell pCell) throws BuildTableException {
	}
	

	final public void buildCellEnd(Cell pCell) throws BuildTableException {
		buildHTMLCellEnd((HTMLCell)pCell);
	}
	protected void buildHTMLCellEnd(HTMLCell pCell) throws BuildTableException {
	}
	

	final public void buildRowEnd(Row pRow) throws BuildTableException {
		buildHTMLRowEnd((HTMLRow)pRow);
	}
	protected void buildHTMLRowEnd(HTMLRow pRow) throws BuildTableException {
	}
	

	

	final public void buildBodyEnd(Table pTable) throws BuildTableException {
		buildHTMLBodyEnd((HTMLTable)pTable);
	}
	protected void buildHTMLBodyEnd(HTMLTable pTable) throws BuildTableException {
	}
	

	final public void buildBottomToolbar(Table pTable) throws BuildTableException{
		buildHTMLBottomToolbar((HTMLTable)pTable);
	}
	protected void buildHTMLBottomToolbar(HTMLTable pTable) throws BuildTableException{
	}
	

	final public void buildBottomPanel(Table pTable) throws BuildTableException {
		buildHTMLBottomPanel((HTMLTable)pTable);
	}
	protected void buildHTMLBottomPanel(HTMLTable pTable) throws BuildTableException {
	}

	final public void buildHeaderBegin(Header pHeader) throws BuildTableException {
		buildHTMLHeaderBegin((HTMLHeader)pHeader);
	}
	protected void buildHTMLHeaderBegin(HTMLHeader pHeader) throws BuildTableException {
	}
	

	final public void buildHeaderEnd(Header pHeader) throws BuildTableException {
		buildHTMLHeaderEnd((HTMLHeader)pHeader);
	}
	protected void buildHTMLHeaderEnd(HTMLHeader pHeader) throws BuildTableException {
	}
	public String getTableScript() {
		return tableScript.toString().trim();
	}
	
	
}
