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
 * 分页信息
 * 第一页的页号为1，第二页的页号是2，依此类推
 * 第一页的起始索引号是0(如果每页显示10条记录的话），那第二页的起始索引号是10，依此类推 
 * 导航信息.翻页，排序需要的数据
 * @author 黄云辉
 *
 */
public interface PageInfo {
	
	public boolean isExported();
     /**
      * 起始记录索引号，可能发生变化，因为有可能数据库记录已经被删除了
      * @return
      */
     public int getStart();
     
     
     /**
      * 获取下一页开始位置
      * @return
      */
     public int getStartOfNextPage();
     
     /**
      * 获取上一页开始位置
      * @return
      */
     public int getStartOfPrevPage();
     
     /**
      * 获取第一页开始位置
      * @return
      */
     public int getStartOfFirstPage();
     
     /**
      * 获取最后一页开始位置
      * @return
      */
     public int getStartOfLastPage();
     
     /**
      * 获取指定页开始位置
      * @param pPage
      * @return
      */
     public int getStartOfThePage(int pPage);
     
     /**
      * 总记录数 
      * @return
      */
     public int getTotal();
     
     /**
      * 每页记录数,跟请求时值一样
      * @return
      */
     public int getPageSize();
     
     /**
      * 当前页
      * @return
      */
     public int getCurrPage();
     
     /**
      * 是否有上一页
      * @return
      */
     public boolean isHasPrevPage();
     
     /**
      * 是否有下一页
      * @return
      */
     public boolean isHasNextPage();
     
     /**
      * 总页数
      * @return
      */
     public int getTotalPages();
     
     /**
      * 是否是第一页
      * @return
      */
     public boolean isFirstPage();
     
     /**
      * 是否是最后一页
      * @return
      */
     public boolean isLastPage();
}
