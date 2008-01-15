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

/**
 * 在使用TreeBuilder构造节点前进行访问.可以通过NodeVisitor
 * 设置Node的属性，过滤节点.当访问一个节点返回false时，
 * 该节点和他所有儿子节点不会传递个TreeBuilder
 * 节点访问.
 * @author 黄云辉
 * 
 */
public interface NodeVisitor {
  public boolean visit(Node pNode);
}
