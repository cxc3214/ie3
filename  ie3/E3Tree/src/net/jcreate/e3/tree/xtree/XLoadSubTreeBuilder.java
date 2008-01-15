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

package net.jcreate.e3.tree.xtree;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.StrTemplateUtil;
import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.support.AbstractWebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;

import org.apache.velocity.util.StringUtils;

/**
 * 
 * @author 黄云辉
 *
 */
public class XLoadSubTreeBuilder extends AbstractWebTreeBuilder{
	public static final String DEFAULT_VERSION = "1.0";
	public static final String DEFAULT_ENCODING = "utf-8";
	//XML文件版本
    private String version = DEFAULT_VERSION;
    //XML编码方式
    private String encoding;

    public void init(HttpServletRequest pRequest){
    	super.init(pRequest);
    	if ( this.encoding == null ){
    		this.encoding = pRequest.getCharacterEncoding();
    	}
    	if ( this.encoding == null ){
    		this.encoding = DEFAULT_ENCODING;
    	}
	}    
	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{
		StringBuffer resouces = new StringBuffer();
		resouces.append("<?xml version=\"${version}\" encoding=\"${encoding}\" ?>").append(ENTER);		
		resouces.append("<tree>").append(ENTER);
		
		Context context = new DefaultContext();
		context.put("version", this.version);
		context.put("encoding", this.encoding);
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	/**
	 * 结束构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeEnd() throws BuildTreeException{
		treeScript.append("</tree>").append(ENTER);
	}
	
	/**
	 * 开始构造普通节点（除跟节点之外的节点)
	 * @param pNode 当前节点
	 * @param pParentNode 父亲节点
	 * @param pLevel 节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException 
	 */
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow)
			throws BuildTreeException{
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}		
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		StringBuffer nodeXml = new StringBuffer();
		nodeXml.append(" <tree text=\"${text}\" value=\"${value}\" checked=\"${checked}\"  " +
				       " disabled=\"${disabled}\" type=\"${type}\" action=\"${action}\" icon=\"${icon}\" " +
				       " openIcon=\"${openIcon}\"");
		Context context = new DefaultContext();
		context.put("text", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("type", webTreeNode.getNodeProperty() );
		if ( webTreeNode.getAction() != null ){
		    context.put("action", webTreeNode.getAction().replaceAll("&","&amp;"));
		}else{
			context.put("action", webTreeNode.getAction());
		}
		
		if ( webTreeNode instanceof WebTreeDynamicNode){
			WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
			if ( dynamicNode.getSubTreeURL() != null ){
				nodeXml.append(" src=\"${src}\"");
			} 
			if ( dynamicNode.getSubTreeURL() != null ){
			  context.put("src", dynamicNode.getSubTreeURL().replaceAll("&","&amp;"));
			}
		}
		nodeXml.append(">" + ENTER);
		treeScript.append(StrTemplateUtil.merge(nodeXml.toString(), context));		
	}

	/**
	 * 结束构造普通节点（除跟节点之外的节点)
	 * @param pNode 当前节点
	 * @param pParentNode 父亲节点
	 * @param pLevel 节点级别，根节点为0级，根节点直接儿子节点为1级，依次类推，2,3,....
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException 
	 */
	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow)
	throws BuildTreeException{
		treeScript.append("</tree>").append(ENTER);
	}
	

	/**
	 * 开始构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeStart(Node pRootNode,int pLevel, int pRow) throws BuildTreeException{
		this.buildNodeStart(pRootNode, null, pLevel, pRow);
	}
	
	/**
	 * 结束构造跟节点
	 * @param pRootNode 跟节点，非空
	 * @param pLevel 根节点级别
	 * @param pRow   在兄弟节点里的序号，第一个兄弟节点为0，第2个为1，第3个为2，依次类推.3,4.... 
	 * @throws BuildTreeException
	 */
	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow) throws BuildTreeException{
		this.buildNodeEnd(pRootNode, null, pLevel, pRow);
	}
}
