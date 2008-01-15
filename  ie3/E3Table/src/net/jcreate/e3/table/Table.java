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

public interface Table {
	
	/**
	 * 获取所有列
	 * @return
	 */
  public List getColumns();
  /**
   * 获取指定列
   * @param pProperty 列名
   * @return
   */
  public Column getColumn(String pProperty);
  /**
   * 获取指定列
   * @param pIndex 序号，从0开始
   * @return
   */
  public Column getColumn(int pIndex);
  /**
   * 获取所有行
   * @return
   */
  public List getRows();
  /**
   * 获取指定行
   * @param pRowIndex 行号，从0开始
   * @return
   */
  public Row getRow(int pRowIndex);
  /**
   * 添加行
   * @param pRow
   */
  public void addRow(Row pRow);
  /**
   * 获取行数
   * @return
   */
  public int getRowSize();
  /**
   * 获取列数
   * @return
   */
  public int getColumnSize();
  
  /**
   * 获取表头
   * @return
   */
  public Header getHeader();
  /**
   * 设置表头
   * @param tableHeader
   */
  public void setHeader(Header tableHeader);
  
}
