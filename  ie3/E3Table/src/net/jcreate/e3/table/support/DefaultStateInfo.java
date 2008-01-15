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
package net.jcreate.e3.table.support;

import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.StateInfo;

public class DefaultStateInfo implements StateInfo{

	private final SortInfo sortInfo;
	private final PageInfo pageInfo;
	private final String id;
	public DefaultStateInfo(String pId,SortInfo pSortInfo, PageInfo pPageInfo){
		this.id = pId;
		this.sortInfo = pSortInfo;
		this.pageInfo = pPageInfo;
	}
	public SortInfo getSortInfo() {
		return sortInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public String getId() {
		return id;
	}

}
