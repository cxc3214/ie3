package net.jcreate.e3.tree.swing;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class SwingTree extends JTree{

	private static final long serialVersionUID = 1L;

	public SwingTree() {
		super();
	}

	public SwingTree(Hashtable value) {
		super(value);
	}

	public SwingTree(Object[] value) {
		super(value);
	}

	public SwingTree(TreeModel newModel) {
		super(newModel);
	}

	public SwingTree(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
	}

	public SwingTree(TreeNode root) {
		super(root);
	}

	public SwingTree(Vector value) {
		super(value);
	}

}
