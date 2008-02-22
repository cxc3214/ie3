package net.jcreate.e3.tree.taglib;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.support.DefaultTreeModel;
import net.jcreate.e3.tree.support.WebTreeNode;

public abstract class NodeUtils {
  private NodeUtils(){
	  
  }
  
  /**
   * 将pNodes列表转换成TreeModel对象,注意pNodes里的元素是
   * WebTreeNode或者子元素.这些节点
   * @param pNodes WebTreeNode 列表
   * @param pNodeMap key是id, value是parentId;
   * @return
   */
  public static TreeModel convert(final List pNodes,final Map pNodeMap){
		DefaultTreeModel result = new DefaultTreeModel();
		if ( pNodes == null ){
			return result;
		}
		if ( pNodes.isEmpty() ){
			return result;
		}
		Map nodes = new LinkedHashMap();
		for(int i=0; i<pNodes.size(); i++){
			WebTreeNode node = (WebTreeNode)pNodes.get(i);
			nodes.put(node.getId(), node);
		}
		for(int i=0; i<pNodes.size(); i++){
			WebTreeNode node = (WebTreeNode)pNodes.get(i);
			final String id = node.getId();
			final String parentId = (String)pNodeMap.get(id);
			Node parentNode = (Node)nodes.get(parentId);
			if (parentNode == null) {//跟节点
				result.addRootNode(node);
				continue;
			}
			node.setParent(parentNode);
		}
	    return result;
  }
}
