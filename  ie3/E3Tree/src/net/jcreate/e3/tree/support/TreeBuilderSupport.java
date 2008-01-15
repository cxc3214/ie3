package net.jcreate.e3.tree.support;

import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeBuilder;

/**
 * 
 * @author 黄云辉
 *
 */
public abstract class TreeBuilderSupport implements TreeBuilder{

	//换行符
	protected static final String ENTER = "\r\n";
	

	public void buildNodeEnd(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
	}

	public void buildRootNodeEnd(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
	}

	public void buildTreeEnd() throws BuildTreeException {
	}
	

	public void buildTreeStart() throws BuildTreeException {
	
	}

}
