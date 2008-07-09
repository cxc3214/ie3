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
 * 创建表格 
 * @author 黄云辉
 *
 */
public interface TableCreator {
	
	/**
	 * 创建Table对象
	 * @param pDataModel  数据模型
	 * @param pProperties 属性数组,非空 
	 * @return 表格对象
	 */
    public Table createTable(
    		final DataModel pDataModel , 
    		final String[] pProperties,
    		final String[] pBeanProperties
    		) throws CreateTableException;
}
