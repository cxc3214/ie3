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
 * 单元格对象
 * @author 黄云辉 
 *
 */
public interface Cell {
	
	/**
	 * 是否是第一个
	 * @return
	 */
	public boolean isFirst();
	
	/**
	 * 是否是最后一个
	 * @return
	 */
	public boolean isLast();
	
	/**
	 * 是否是奇数列
	 * @return
	 */
	public boolean isOddColumn();
	
	/**
	 * 是否是奇数行
	 * @return
	 */
	public boolean isOddRow();
	/**
	 * 获取单元格的值 
	 * @return
	 */
    public Object getValue();
    /**
     * 设置单元格对象
     * @param pValue
     */
    public void setValue(Object pValue);
    
    /**
     * 获取单元格在行对象
     * @return
     */
    public Row getRow();
    /**
     * 设置行对象
     * @param pRow
     */
    public void setRow(Row pRow);
    
    /**
     * 获取单元格所在列
     * @return
     */
    public Column getColumn();
    /**
     * 设置单元格所在列
     * @param pColumn
     */
    public void setColumn(Column pColumn);
    
    
    /**
     * 获取行索引号,行索引号，从0开始。第一行是0，第二行是1,第三行是2，以此类推
     * @return
     */
    public int getRowIndex();
    
    /**
     * 获取列索引号,列索引号，从0开始，第一列是0，第二列是1，第三列是2，以此类推
     * @return
     */
    public int getColumnIndex();
    
    /**
     * 获取单元格修饰器 
     * @return
     */
    public CellDecorator getCellDecorator();
    
    /**
     * 设置单元格修饰器
     * @param pDecorator
     */
    public void setCellDecorator(CellDecorator pDecorator);
    
}
