package net.jcreate.e3.tree.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeModel;

/**
 * 
 * @author 黄云辉
 *
 */
public class DefaultTreeModel implements TreeModel{
    
    /**
     * 用户存储树的跟节点
     */
    private List rootNodes = new ArrayList();
    
	public Iterator getRootNodes() {
		return rootNodes.iterator();
	}
	
	public void addRootNode(Node pRootNode){
		rootNodes.add(pRootNode);
	}

	public int getRootNodeCount(){
		return this.rootNodes.size();
	}
	public Node getRootNodeAt(int pRootIndex){
	  return (Node)rootNodes.get(pRootIndex);	
	}
	 public int getRootNodeIndex(Node node){
		return this.rootNodes.indexOf(node); 
	 }
	public boolean removeRoot(Node pRootNode){
		return rootNodes.remove(pRootNode);
	}
	
}
