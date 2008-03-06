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

public interface Column {
	/**
	 * 是否是奇数列
	 * @return
	 */
	public boolean isOdd();
	
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
	 * 列属性名
	 * @return
	 */
    public String getProperty();
    /**
     * 设置类属性
     * @param pProperty
     */
    public void setProperty(String pProperty);
    
    /**
     * 获取列标题
     * @return
     */
    public String getTitle();    
    /**
     * 设置标题
     */
    public void setTitle(String pTitle);
    
	public String getTitleKey();

	public void setTitleKey(String titleKey);
    
    
    /**
     * 获取列索引
     * @return
     */
    public int getColumnIndex();
    
    /**
     * 设置列索引
     * @param columnIndex
     */
	public void setColumnIndex(int columnIndex);     
    
    /**
     * 获取列宽度
     * @return
     */
    public String getWidth();
    /**
     * 设置宽度
     * @param pWidth
     */
    public void setWidth(String pWidth);
    
    /**
     * 获取列所在表格
     * @return
     */
    public Table getTable();
    
    /**
     * 设置表格
     * @param pTable
     */
    public void setTable(Table pTable);
    
    /**
     * 获取单元格修饰器
     * 补充说明： 该修饰器是全局的，所有该列的单元格共享的。 
     * @return
     */
    public CellDecorator getCellDecorator();
    
    /**
     * 设置单元格修饰器
     * @param pDecorator
     */
    public void setCellDecorator(CellDecorator pDecorator);
    
    /**
     * 获取所属组,如果没有返回null
     * @return
     */
    public ColumnGroup getColumnGroup();
    /**
     * 设置所属组
     * @param pColumnGroup
     * @return
     */
    public void setColumnGroup(ColumnGroup pColumnGroup);
        
  
}
