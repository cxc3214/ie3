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
import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;

/**
 * XLoadTree Builder
 * @author 黄云辉
 *
 */
public class XLoadTreeBuilder extends XTreeBuilder {
	
	/**
	 * 负责导入Tree所需要的js,css
	 */
	public void buildTreeStart() throws BuildTreeException {	
		StringBuffer resouces = new StringBuffer();
		if ( this.importCss ){
		resouces.append("<link type='text/css' rel='stylesheet' href='${xtreeStyle}' />").append(ENTER);
		}
		
		if ( this.importJs ){
		resouces.append("<script src='${resouceHome}/xtree.js'></script>").append(ENTER);
		resouces.append("<script src='${resouceHome}/xloadtree.js'></script>").append(ENTER);		
		resouces.append("<script src='${resouceHome}/xmlextras.js'></script>").append(ENTER);
		}
		
		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	
	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pRootNode instanceof WebTreeDynamicNode == false){
			super.buildRootNodeStart(pRootNode, pLevel, pRow);
			return;
		}
		
		WebTreeDynamicNode node = (WebTreeDynamicNode)pRootNode;
		if ( node.getSubTreeURL() == null ){
			super.buildRootNodeStart(pRootNode, pLevel, pRow);
			return;
		}
		treeScript.append("<script language='javascript'>").append(ENTER);		
		StringBuffer nodeTemplate = new StringBuffer();
		nodeTemplate.append("webFXTreeConfig.usePersistence = ${usePersistence};").append(ENTER);		
		nodeTemplate.append("webFXTreeConfig.setImagePath(\"${imagePath}\");").append(ENTER);
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXLoadTree(\"${text}\",").
		     append("\"${subTreeURL}\",\"${action}\",\"${behavior}\",\"${icon}\",\"${openIcon}\", ${open}); ");	
		nodeTemplate.append(ENTER);
		
		Context context = new DefaultContext(); 
		context.put("usePersistence", new Boolean( this.isUsePersistence()) );
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());	
		context.put("subTreeURL", node.getSubTreeURL());
		context.put("behavior", this.getBehavior());
		context.put("imagePath", this.getResourceHome() + "/images/");
		context.put("action", node.getAction());		
        context.put("icon", node.getIcon() );
		context.put("openIcon", node.getOpenIcon() );
		context.put("open", this.isOpen());
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));		
	}	
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeDynamicNode == false ){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		WebTreeDynamicNode node = (WebTreeDynamicNode)pNode;
		 
		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		
		StringBuffer nodeTemplate = new StringBuffer();
		nodeTemplate.append("   var ${nodeScriptName}=new WebFXLoadTreeItem(\"${text}\",").
		     append("\"${subTreeURL}\", \"${action}\",${parent},\"${icon}\",\"${openIcon}\"); ");	
		nodeTemplate.append(ENTER);
		Context context = new DefaultContext(); 
		context.put("nodeScriptName", getNodeScriptName(node));
		context.put("text", node.getName());
		context.put("subTreeURL", node.getSubTreeURL());
		context.put("action", node.getAction());
		context.put("icon", node.getIcon());
		context.put("openIcon", node.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}

	
	
}
