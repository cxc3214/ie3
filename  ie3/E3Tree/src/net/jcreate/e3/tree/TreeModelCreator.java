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

package net.jcreate.e3.tree;

import java.util.Collection;


/**
 * 将业务数据构造成TreeModel
 * @author new
 *
 */
public interface TreeModelCreator {

	/**
	 * 创建树模型
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 *                   要求集合元素读必须实现Uncodable接口                     
	 * @return 返回根节点.
	 * @throws CreateTreeModelException 如果集合元素没有实现Uncodable接口，会抛出
	 *                                  ClassCastException异常
	 */
   public TreeModel create(Collection pUserDatas) throws CreateTreeModelException;
	
	/**
	 * 创建树模型 
	 * @param pUserDatas 业务数据,至少要存在一个跟节点（不存在父亲节点的节点)
	 * @param pUncoder   解码器，对每个业务数据进行解码，返回主键对象和父亲主键对象.
	 * @return 返回根节点.
	 * @throws CreateTreeModelException
	 */
  public TreeModel create(Collection pUserDatas, UserDataUncoder pUncoder) throws CreateTreeModelException;
}
