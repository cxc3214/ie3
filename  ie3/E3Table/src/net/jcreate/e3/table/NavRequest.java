package net.jcreate.e3.table;


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
 * 翻页,排序请求
 */
public class NavRequest {
	
	/**
	 * 排序属性
	 */
	private String sortProperty;
	/**
	 * 排序方向
	 */
	private String sortDir;
	
	/**
	 * 排序名称，用于构造排序语句用
	 */
	private String sortName;
	
	/**
	 * 开始索引
	 */
	private int start;
		
	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public String getSortCode(){
		if ( this.sortName == null ){
			return "";
		}
		if ( "".equals(this.sortName.trim())){
			return "";
		}
		return " order by " + this.sortName + " " + this.sortDir;
	}
	
	public String getSortCode(String pEntity){
		if ( this.sortName == null ){
			return "";
		}
		if ( "".equals(this.sortName.trim())){
			return "";
		}
		return " order by " + pEntity + "." + this.sortName + " " + this.sortDir;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
  
}
