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

import java.util.HashMap;
import java.util.Map;

public class Sort {
	/**
	 * 排序代码
	 */
	private final String code;
	/**
	 * 备注
	 */
	private final String remark;
	
	public static final Sort ASC = new Sort("asc", "升序"); 
	public static final Sort DESC = new Sort("desc", "降序");
	public static final Sort NONE = new Sort("none", "无序");
	
	private static final Map ALL_SORTS = new HashMap();
	
	static{
		ALL_SORTS.put(ASC.code, ASC);
		ALL_SORTS.put(DESC.code, DESC);
		ALL_SORTS.put(NONE.code, NONE);
		ALL_SORTS.put(null, NONE);
	}
  private Sort(String pCode, String pRemark){
	  this.code = pCode;
	  this.remark = pRemark;
  }
  
  public static Sort getSort(String pCode){
	  if ( ALL_SORTS.containsKey(pCode) == false ){
		  throw new IllegalArgumentException("不存在代码为：" + pCode + "的Sort对象!");
	  }
	  return (Sort)ALL_SORTS.get(pCode);
  }
  
  public Sort nextSort(){
	  if ( ASC.equals(this) ){
		  return DESC;
	  }else if ( DESC.equals(this) ){
		  return ASC;
	  }else{
		  return ASC;
	  }
  }

public boolean equals(Object obj) {
	if ( obj instanceof Sort == false){
		return false;
	}
	Sort objSort = (Sort)obj;
	return this.code.equals(objSort.code);
}

public String getCode(){
	return this.code;
}

public int hashCode() {
	return code.hashCode();
}

public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("code=").append(this.code).append("remark=").append(this.remark);
	return sb.toString();
}
  
  
}
