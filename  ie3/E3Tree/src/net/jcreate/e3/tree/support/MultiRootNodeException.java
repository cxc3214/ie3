package net.jcreate.e3.tree.support;

import net.jcreate.e3.tree.CreateTreeModelException;

/**
 * 
 * @author 黄云辉
 *
 */
public class MultiRootNodeException extends CreateTreeModelException{
 
	private static final long serialVersionUID = 1L;

	public MultiRootNodeException() {
		super("存在多个跟节点");
	}

	public MultiRootNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MultiRootNodeException(String message) {
		super(message);
	}

	public MultiRootNodeException(Throwable cause) {
		super(cause);
	}


}
