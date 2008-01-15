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
package net.jcreate.store.dict;

import net.jcreate.store.common.BaseForm;

public class DictForm extends BaseForm{
	/**
	 * 字典代码
	 */
	private String dictCode;
	/**
	 * 字典名称
	 */
	private String dictName;
	/**
	 * 显示顺序
	 */
	private int viewOrder;
	/**
	 * 备注信息
	 */
	private String remark;
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getViewOrder() {
		return viewOrder;
	}
	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
