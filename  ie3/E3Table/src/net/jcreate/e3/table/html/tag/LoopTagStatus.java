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
package net.jcreate.e3.table.html.tag;

/**
 * 改类的设计，借鉴JSTL
 * @author 黄云辉
 *
 */
public class LoopTagStatus {
	/**
	 * 当前这次迭代的（集合中的）项。
	 */
	private Object current;
	/**
	 * 奇数行标志
	 */
	private boolean odd;
	/**
	 * 当前这次迭代从0开始的迭代索引
	 */
	private int index; 
	/**
	 * 当前这次迭代从1开始的迭代计数
	 */
	private int count;
	
	/**
	 * 用来表明当前这轮迭代是否为第一次迭代
	 */
	private boolean first;
	
	/**
	 * 用来表明当前这轮迭代是否为最后
	 */
	private boolean last;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isOdd() {
		return odd;
	}

	public void setOdd(boolean odd) {
		this.odd = odd;
	}

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(Object current) {
		this.current = current;
	}

}
