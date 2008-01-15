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

public interface Header {
	  /**
     * 获取包含的列对象
     * @return
     */
    public List getColumns();
    
    /**
     * 获取总列数
     * @return
     */
    public int getSize();
    
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
     * 获取指定列
     * @param property 列名
     * @return
     */
    public Column getColumn(String property);

    /**
     * 获取指定列
     * @param index 列序号
     * @return
     */
    public Column getColumn(int index);
    

    /**
     * 添加列
     * @param pColumn
     */
    public void addColumn(Column pColumn);
    /**
     * 删除列
     * @param pProperty
     */
    public void removeColumn(String pProperty);

}
