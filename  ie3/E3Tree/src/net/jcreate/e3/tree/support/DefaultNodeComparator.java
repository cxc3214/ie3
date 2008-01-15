package net.jcreate.e3.tree.support;

import net.jcreate.e3.tree.Node;

/**
 * 采用节点文本进行排序
 * @author 黄云辉
 *
 */
public class DefaultNodeComparator extends AbstractNodeComparator {

	protected Comparable getComparableProperty(Node pNode) {
		if ( pNode instanceof DefaultNode == false){
			throw new IllegalArgumentException("node type is error, should be WebNode!");
		}
		DefaultNode defaultNode = (DefaultNode)pNode;
		return defaultNode.getName();
	}

}
