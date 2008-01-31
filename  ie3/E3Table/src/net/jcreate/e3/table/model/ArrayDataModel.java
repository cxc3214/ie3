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
package net.jcreate.e3.table.model;

import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;

public class ArrayDataModel extends AbstractDataModel{

	private final Object[] datas;
	private int total;
	private int index = 0;
	
	public ArrayDataModel(Object[] pDatas,SortInfo pSortInfo, PageInfo pNavInfo){
		super(pSortInfo,pNavInfo);
		if ( pDatas == null ){
		  this.datas = new Object[0];	
		}else{
		  this.datas = pDatas;
		}
		this.total = this.datas.length;
	}
	
	public ArrayDataModel(Object[] pDatas){
		if ( pDatas == null ){
			  this.datas = new Object[0];	
			}else{
			  this.datas = pDatas;
			}
			this.total = this.datas.length;
	}
	public boolean hasNext() {
		if ( index < total){
			return true;
		} else {
			return false;
		}
	}

	public Object next() {
		Object result = datas[index];
		index++;
		return result;
	}

}
