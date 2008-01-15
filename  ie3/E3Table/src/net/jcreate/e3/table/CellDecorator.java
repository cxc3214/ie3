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
 * 单元格修饰器
 * @author 黄云辉
 *
 */
public interface CellDecorator {
	
	/**
	 * 对单元格进行修饰 
	 * @param pValue 待修饰值，初始值是单元格的值
	 * @param pCell  单元格对象
	 * @return  修饰后的值.
	 * @exception 抛出修饰异常.当修饰过程出现异常，自己不想确定返回修饰值时，自己扔出异常即可.
	 */
  public Object decorate(Object pValue, Cell pCell) throws Exception;
}
