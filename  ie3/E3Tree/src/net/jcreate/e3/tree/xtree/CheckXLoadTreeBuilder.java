package net.jcreate.e3.tree.xtree;

import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.e3.templateEngine.support.StrTemplateUtil;
import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;

public class CheckXLoadTreeBuilder extends XLoadTreeBuilder{
	
	protected boolean cascadeCheck = false;
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
			resouces.append("<script src='${resouceHome}/map.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/checkboxTreeItem.js'></script>").append(ENTER);
			resouces.append("<script src='${resouceHome}/checkboxXLoadTree.js'></script>").append(ENTER);
		}
		resouces.append("<script>").append(ENTER);
		resouces.append("   webFXTreeConfig.cascadeCheck = ${cascadeCheck};").append(ENTER);
		resouces.append("</script>").append(ENTER);

		Context context = new DefaultContext();
		context.put("resouceHome", getResourceHome());
		context.put("cascadeCheck", new Boolean(cascadeCheck));
		context.put("xtreeStyle", this.getXtreeStyle());
		treeScript.append(StrTemplateUtil.merge(resouces.toString(), context));		
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
		if ( pNode instanceof WebTreeNode == false ){
			throw new IllegalArgumentException("node type is error, should be WebTreeNode!");
		}
		WebTreeNode webTreeNode = (WebTreeNode)pNode;
		if ( webTreeNode instanceof WebTreeDynamicNode == false ){
			super.buildNodeStart(pNode, pParentNode, pLevel, pRow);
			return;
		}
		WebTreeDynamicNode dynamicNode = (WebTreeDynamicNode)webTreeNode;
		StringBuffer nodeTemplate = new StringBuffer();
		Context context = new DefaultContext();		
			nodeTemplate.append("   var ${nodeScriptName}=new ${treeItem}(\"${name}\",").
		     append("\"${value}\",\"${action}\",\"${subTreeURL}\",${parent},\"${icon}\",\"${openIcon}\",${checked},${disabled});"); 			
	    nodeTemplate.append(ENTER);
	    
		context.put("nodeScriptName", getNodeScriptName(webTreeNode));		
		context.put("treeItem", "WebFXLoadCheckBoxTreeItem");

		String parentNodeScriptName = getNodeScriptName((WebTreeNode)pParentNode);
		context.put("name", webTreeNode.getName());
		context.put("value", webTreeNode.getValue());
		context.put("subTreeURL", dynamicNode.getSubTreeURL());
		context.put("checked", new Boolean(webTreeNode.isSelected()) );
		context.put("disabled", new Boolean(webTreeNode.isDisabled()) );
		context.put("icon", webTreeNode.getIcon());
		context.put("action", webTreeNode.getAction());
		context.put("openIcon", webTreeNode.getOpenIcon() );
		context.put("parent", parentNodeScriptName);
		
		
		treeScript.append(StrTemplateUtil.merge(nodeTemplate.toString(), context));
		
	}

	public boolean isCascadeCheck() {
		return cascadeCheck;
	}

	public void setCascadeCheck(boolean cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}		
	
}