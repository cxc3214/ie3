package net.jcreate.e3.tree.support;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 黄云辉
 *
 */
public abstract class AbstractWebTreeModelCreator extends AbstractTreeModelCreator{
	
	/**
	 * @deprecated 当使用init(WebContext pWebContext)初始化时,request对象没有值
	 */
	protected HttpServletRequest request = null;
	protected WebContext webContext = null;
	public void init(HttpServletRequest pRequest){
		this.request = pRequest;
		init(new HttpServletRequestWebContext(pRequest));
	}
	public void init(WebContext pWebContext){
		this.webContext = pWebContext;	
	}
	
	
	/**
	 * 获取url连接地址，就是在url地址前添加WebContextPath路径. 
	 * @param pUrl
	 * @return
	 */
	protected String getUrl(String pUrl){
		if ( pUrl == null )
			return "";
		if ( pUrl.trim().length() == 0)
			return "";
		if ( webContext == null )
			throw new IllegalStateException("webContext is null, you should invoke init(WebContext pWebContext) method!");
		return RequestUtil.getUrl(pUrl, webContext);
	}
}
