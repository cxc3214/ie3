package net.jcreate.e3.tree.support;

/**
 * 
 * @author 黄云辉
 *
 */
public class WebTreeDynamicNode extends WebTreeNode{

	private static final long serialVersionUID = 1L;
	
	//负责导入子树的URL
	private String subTreeURL;

	public WebTreeDynamicNode() {
		super();
	}

	public WebTreeDynamicNode(String pName, String pId){
		super(pName, pId);
	}
	
	public WebTreeDynamicNode(String pName, Object pUserData) {
		super(pName, pUserData);
	}

	public WebTreeDynamicNode(String pName, String pId, Object pUserData) {
		super(pName, pId, pUserData);
	}

	public WebTreeDynamicNode(String pName) {
		super(pName);
	}

	public String getSubTreeURL() {
		return subTreeURL;
	}

	public void setSubTreeURL(String subTreeURL) {
		this.subTreeURL = subTreeURL;
	}
}
