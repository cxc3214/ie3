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

import java.util.Iterator;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;

public class IteratorDataModel  extends AbstractDataModel{

	private final Iterator datasIterator;
	
	public IteratorDataModel(Iterator pDatas,SortInfo pSortInfo, PageInfo pNavInfo){
		super(pSortInfo,pNavInfo);
		this.datasIterator = pDatas;
	}
	
	public IteratorDataModel(Iterator pDatas){
		this.datasIterator = pDatas;
	}
	public boolean hasNext() {
		if ( this.datasIterator == null ){
			return false;
		}
		return datasIterator.hasNext();
	}

	public Object next() {
		return datasIterator.next();
	}

}