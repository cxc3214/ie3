package net.jcreate.e3.tree.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.jcreate.e3.tree.Node;

/**
 * 
 * @author 黄云辉
 *
 */
public class DefaultNode implements Node, Serializable{
	private static final long serialVersionUID = 1L;
	private Node parent;      //父亲节点，跟节点的父亲为null
	private Object userData;  //用户数据
	private List children = new ArrayList();
	private String name;

	
	public DefaultNode(){
		name = "no name";
	}
	
	public DefaultNode(String pName){
		if ( pName == null ){
			throw new NullPointerException("节点名称不能为空null");
		}
		name = pName;
	}
	
	public DefaultNode(String pName, Object pUserData){
		this(pName);
		this.userData = pUserData;
	}
	
	public String toString(){
		if ( name == null ){
			return super.toString();	
		} else {
			return name.toString();
		}		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void detachNode(Node pNode){
		if ( pNode == null ){
			return;
		}
		//不包含pNode
		if ( children.contains(pNode) == false ){
			return;
		}
		children.remove(pNode);		
		pNode.setParent(null);

	}
	
	public Object getUserData() {
		return userData;
	}
	public void setUserData(Object userData) {
		this.userData = userData;
	}
	public Node getParent() {
		return parent;
	}
	
	public boolean isRoot(){
		return this.parent == null ? true : false;
	}
	public void setParent(Node pParentNode) {
		if ( pParentNode == this.parent ){
			return;
		}
		if ( this.parent != null ){//解除跟以前父亲菜单的关系.
			this.parent.detachNode(this);
		}
		if (pParentNode != null) {
			int index = pParentNode.getIndex(this);
			if (index == -1) {
				pParentNode.addNode(this);
			}
		}
		this.parent = pParentNode;
	}
	public void addNode(Node pNode) {
		if ( pNode == null ){
			return;
		}
		if (children.contains(pNode) == false) {
			children.add(pNode);
			pNode.setParent(this);
		}
	}
	
	public boolean isHaveChildren(){
		return !this.children.isEmpty();
	}


	public Iterator getChildren() {
		return this.children.iterator();
	}

	public boolean isLeaf() {
		return this.children.size() == 0 ? true : false;
	}

	public int getChildCount() {
		return this.children.size();
	}

	public Node getChildAt(int pChildIndex) {
		return (Node)this.children.get(pChildIndex);
	}

	public int getIndex(Node pChildIndex) {
		return this.children.indexOf(pChildIndex);
	}
}
