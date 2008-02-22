package net.jcreate.e3.tree.support;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.tree.TreeBuilder;

/**
 * 
 * @author 黄云辉
 *
 */
public interface WebTreeBuilder extends TreeBuilder{
	public void init(WebContext pWebContext);
	/**
	 * @deprecated, 使用 public void init(WebContext pWebContext);
	 * @param pRequest
	 */
	public void init(HttpServletRequest pRequest);
	public String getTreeScript();
}
