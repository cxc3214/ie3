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

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.StrTemplateUtil;
import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.support.WebTreeNode;

/**
 * 
 * @author 黄云辉
 *
 */
public class RadioXTreeBuilder  extends XTreeBuilder{
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {
		StringBuffer resouces = new StringBuffer();
		if ( this.importJs ){
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/radioTreeItem.js'></script>").append(ENTER);
		}
		if ( this.importCss ){
		   resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		}
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer node = new StringBuffer();
		node.append("   var ${nodeScriptName}=new WebFXRadioTreeItem(\"${name}\",").
		     append("\"${value}\",\"${action}\", ${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled}); ");
		node.append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("action", webTreeNode.getAction());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(node.toString(), context));
		
	}	
	
	
}