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
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultPageInfo implements PageInfo{
	
	private final Log logger = LogFactory.getLog( DefaultPageInfo.class );
	/**
	 * 起始记录索引号
	 */
	private int start;

	/**
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 每页显示记录数
	 */
	private int pageSize = 20;
	
	/**
	 * 是否已导出
	 */
	private boolean exported;
	
	public DefaultPageInfo(){
		
	}
	
	public DefaultPageInfo(int pStart, int pTotal){
	  this.start = pStart;
	  this.total = pTotal;
	}
	
	public DefaultPageInfo(int pStart, int pTotal, int pPageSize){
		  this.start = pStart;
		  this.total = pTotal;
		  this.pageSize = pPageSize;
		}
	
	
	

	public int getPageSize() {
		return pageSize;
	}

	 
	public void setPageSize(int pageSize) {
		if ( pageSize < 1 ){
			final String msg =
				"每页记录数必须大于或者等于1";
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		this.pageSize = pageSize;
	}


	public int getStartOfCurrPage() {
		return start;
	}

	public void setStartOfCurrPage(int startOfCurrPage) {
		this.start = startOfCurrPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStartOfNextPage() {
		int thePage = this.getCurrPage() + 1;
		return getStartOfThePage(thePage);
	}

	public int getStartOfPrevPage() {
		int thePage = this.getCurrPage() - 1;
		return getStartOfThePage(thePage);
	}

	public int getStartOfFirstPage() {
		int thePage = 1;
		return getStartOfThePage(thePage);
	}

	public int getStartOfLastPage() {
		int thePage = this.getTotalPages();
		return getStartOfThePage(thePage);
	}

	/**
	 * 0-9
	 * 10-19
	 * 20-29
	 */
	public int getStartOfThePage(int pPage) {
		if ( pPage < 1 || pPage > this.getTotalPages() ){
			final String msg =
				"指定参数:" + pPage + "越界!" +
				"有效值范围是:" + "[1," + this.getTotalPages() +"]";
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
        return (pPage - 1)* this.getPageSize();
	}


	/**
	 * 获取当前页
	 */
	public int getCurrPage() {
		 return (start/ pageSize) + 1;
	}

	
	/**
	 * 是否有上一页
	 */
	public boolean isHasPrevPage() {
        int currPage = getCurrPage();
        if ( currPage > 1){
        	return true;
        } else {
        	return false;
        }
	}

	/**
	 * 是否有下一页
	 */
	public boolean isHasNextPage() {
        int currPage = getCurrPage();
        int totalPages = this.getTotalPages();
        if ( currPage < totalPages){//当前页数小于总也数
        	return true;
        } else {
        	return false;
        }
	}

	

	/**
	 * 总页数
	 */
	public int getTotalPages() {
		return  (((total - 1) / pageSize) + 1);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public boolean isFirstPage() {
		return this.getCurrPage() == 1 ? true : false;
	}

	public boolean isLastPage() {
       return  this.getCurrPage() == this.getTotalPages() ? true : false;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean isExported() {
		return exported;
	}

	public void setExported(boolean exported) {
		this.exported = exported;
	}
	


}
