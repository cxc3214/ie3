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

import java.util.Comparator;


/**
 * 负责节点遍历
 * @author new
 *
 */
public interface TreeDirector {

	/**
	 * 设置节点比较器
	 * @param pComparator 节点比较器，用于进行兄弟节点比较
	 */
	public void setComparator(Comparator pComparator);
	
   /**
    * 节点访问者
    * @param pVisitor
    */
   public void setNodeVisitor(NodeVisitor pVisitor);
   
	/**
	 *  build树 
	 * @param pTree 
	 * @param pTreeBuilder Tree构造器（非空)
	 * @throws BuildTreeException
	 */
  public void build(TreeModel pTree, TreeBuilder pTreeBuilder) throws BuildTreeException;  
}
