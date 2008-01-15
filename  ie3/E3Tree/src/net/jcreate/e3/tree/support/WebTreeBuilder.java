package net.jcreate.e3.tree.support;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.tree.TreeBuilder;

/**
 * 
 * @author 黄云辉
 *
 */
public interface WebTreeBuilder extends TreeBuilder{
	public void init(HttpServletRequest pRequest);
	public String getTreeScript();
}
