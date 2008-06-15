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

import java.util.List;

public interface Row {
	/**
	 * 是否是奇数行
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
	 * 获取行包含的单元格对象 
	 * @return
	 */
     public List getCells(); 
     
     /**
      * 获取行序号,第一行为0，第2行为1,依此类退
      * @return
      */
     public int getRowIndex();
     
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
      * 获取指定位置单元格
      * @param pIndex
      * @return
      */
     public Cell getCell(int pIndex);
     
     /**
      * 获取指定单元格式对象
      * @param pProperty
      * @return
      */
     public Cell getCell(String pProperty);
     
     /**
      * 添加单元格
      * @param pCell
      */
     public void addCell(Cell pCell);
     
     /**
      * 获取单元格索引
      * @param pCell
      * @return
      */
     public int getCellIndex(Cell pCell);
     
     /**
      * 设置行对象
      * @param pObj
      */
     public void setRowObject(Object pObj);
     /**
      * 获取行对象
      * @return
      */
     public Object getRowObject(); 
}
