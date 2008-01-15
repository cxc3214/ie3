package net.jcreate.e3.tree.support;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.NodeVisitor;

/**
 * 
 * @author 黄云辉
 *
 */
public class EmptyNodeVisitor implements NodeVisitor{

	public boolean visit(Node pNode) {
		return true;
	}

}
