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
package net.jcreate.e3.table;

/**
 * 负责构造 Table
 * @author 黄云辉
 *
 */
public interface TableBuilder {

	/**
	 * 初始化
	 *
	 */
	public void init(Table pTable);
	
	/**
	 * destory
	 *
	 */
	public void destory(Table pTable);
	/**
	 * build初始化脚本，导入script,css和定义script函数之类
	 * @param pTable
	 * @throws BuildTableException
	 */
	public void buildBeginScript(Table pTable)throws BuildTableException;
	
    /**
     * build 结束脚本，调用构造view的脚本
     * @throws BuildTableException
     */
    public void buildEndScript(Table pTable) throws BuildTableException;
	
    /**
     * build doc 开始
     * @param pTable
     * @throws BuildTableException
     */
    public void buildDocBegin(Table pTable) throws BuildTableException;
    /**
     * build doc 结束
     * @param pTable
     * @throws BuildTableException
     */
    public void buildDocEnd(Table pTable) throws BuildTableException;
	/**
	 * build 表格
	 * @param pTable
	 * @throws BuildTableException
	 */
    public void buildTableBegin(Table pTable) throws BuildTableException;
	/**
	 * build 表格结束
	 * @param pTable
	 * @throws BuildTableException
	 */
    public void buildTableEnd(Table pTable) throws BuildTableException;
    
    
	/**
	 * build 标题
	 * @param pCaption
	 * @throws BuildTableException
	 */
	public void buildCaption(Table pTable) throws BuildTableException;
	
	/**
	 * 没有数据的行
	 * @param pTable
	 * @throws BuildTableException
	 */
	public void buildNoDataRow(Table pTable) throws BuildTableException; 
	
	/**
	 * build header 面版，一般为空，作为扩展留着
	 * @throws BuildTableException
	 */
	public void buildTopPanel(Table pTable) throws BuildTableException;
	
	/**
	 * build 上面工具条
	 * @param pToolbar
	 * @throws BuildTableException
	 */
	public void buildTopToolbar(Table pTable) throws BuildTableException;
	
	/**
	 * 开始build body
	 * @throws BuildTableException
	 */
	public void buildBodyBegin(Table pTable) throws BuildTableException;

	
	public void buildColumnGroupsBegin(Table pTable) throws BuildTableException;
	public void buildColumnGroupBegin(ColumnGroup pColumnGroup) throws BuildTableException;
	public void buildColumnGroup(ColumnGroup pColumnGroup) throws BuildTableException;
	public void buildColumnGroupEnd(ColumnGroup pColumnGroup) throws BuildTableException;
	public void buildColumnGroupsEnd(Table pTable) throws BuildTableException;
	
    /**
     * build 
     * @param pHeader
     * @throws BuildTableException
     */
    public void buildHeaderBegin(Header pHeader) throws BuildTableException;

	
	/**
	 * build header单元格之前
	 * @throws BuildTableException
	 */
	public void buildColumnBegin(Column pColumn) throws BuildTableException;
	
	/**
	 * build header 单元格 
	 * @throws BuildTableException
	 */
	public void buildColumn(Column pColumn) throws BuildTableException;
	/**
	 * build header单元格之后
	 * @throws BuildTableException
	 */
	public void buildColumnEnd(Column pHeader) throws BuildTableException;
	
	/**
	 * build header行之后
	 * @throws BuildTableException
	 */
	public void buildHeaderEnd(Header pHeader) throws BuildTableException;
	
	
	/**
	 * build data 行之前
	 * @throws BuildTableException
	 */
	public void buildRowBegin(Row pRow) throws BuildTableException;
	
	/**
	 * build data单元格之前
	 * @throws BuildTableException
	 */
	public void buildCellBegin(Cell pCell) throws BuildTableException;
	
	/**
	 * build data 单元格 
	 * @throws BuildTableException
	 */
	public void buildCell(Cell pCell) throws BuildTableException;
	/**
	 * build data单元格之后
	 * @throws BuildTableException
	 */
	public void buildCellEnd(Cell pCell) throws BuildTableException;
	
	/**
	 * build data行之后
	 * @throws BuildTableException
	 */
	public void buildRowEnd(Row pRwo) throws BuildTableException;
	
	
	
	
	/**
	 * 结束 build body
	 * @throws BuildTableException
	 */
	public void buildBodyEnd(Table pTable) throws BuildTableException;
	
	/**
	 * build butoom 工具条
	 * @throws BuildTableException
	 */
	public void buildBottomToolbar(Table pTable) throws BuildTableException;
	
	/**
	 * build buttom面版,一般为空，作为扩展留着
	 * @throws BuildTableException
	 */
	public void buildBottomPanel(Table pTable) throws BuildTableException;
	
}
