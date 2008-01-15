package net.jcreate.e3.tree.support;

import java.util.Comparator;

import net.jcreate.e3.tree.Node;

/**
 * 当兄弟节点是同类节点时，可以使用该节点比较器.
 * @author 黄云辉
 *
 */
public abstract class AbstractNodeComparator implements Comparator{

	public int compare(Object pObj1, Object pObj2) {
		if ( pObj1 == null ){
			return -1;
		}
		if ( pObj2 == null ){
			return 1;
		}
		Node node1 = (Node)pObj1;
		Node node2 = (Node)pObj2;
		Comparable obj1 =  getComparableProperty(node1);
		Comparable obj2 =  getComparableProperty(node2);
		if ( obj1 == null ){
			return -1;
		}
		if ( obj2 == null ){
			return 1;
		}
		return obj1.compareTo(obj2);
	}
	
	public Comparator reverseOrder(){
		return new ReverseComparator( this );
	}
	
	/**
	 * 获取排序属性 
	 * @param pNode 树节点,非空
	 * @return 返回节点对应的排序属性
	 */
	protected abstract Comparable getComparableProperty(Node pNode);

}
