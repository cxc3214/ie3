package net.jcreate.e3.tree.support;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.tree.BuildTreeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 黄云辉
 *
 */
public abstract class AbstractWebTreeBuilder extends TreeBuilderSupport implements WebTreeBuilder {
	
	protected WebContext webContext;
	
	private static final int DEFAULT_BUFFER_SIZE = 200;
	private final Log log = LogFactory.getLog( this.getClass() );
	
	protected StringBuffer treeScript = null;
	private int bufferSize = DEFAULT_BUFFER_SIZE;
	
	public AbstractWebTreeBuilder(){
		
	}
	
	
	public void init(WebContext pWebContext){
		this.webContext = pWebContext;
	    treeScript = new StringBuffer(bufferSize);		
	}
	public void init(HttpServletRequest pRequest){ 
		init(new HttpServletRequestWebContext(pRequest));
	}
	
	private static boolean isJsIdentifier(String s) {
		if ( s == null ){
			return false;
		}
        if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i=1; i<s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
	
	/**
	 * 注意：
	 * 1:必须保证，同一个节点，获取的脚本名称必须相同。
	 *  也就是说,不管是第一次调用，还是第2次，返回的名字都一样
	 *  ，绝对不允许出现随机名称.
	 * 2:这里的命名规则不能再被修改，因为有很多页面依赖这种
	 *   命名方式，如果要修改，只能是写一个之类，覆盖这种命名方法.
	 */
	protected String getNodeScriptName(WebTreeNode pNode){
		String result = pNode.getId();
		if ( result == null ){
			throw new BuildTreeException("节点:" + pNode + "没有设置节点ID!");
		}
		if ( isJsIdentifier( result ) == false ){
			throw new BuildTreeException("节点:" + pNode + "的ID:" + result + "不是有效的js标识符号!");
		}
		return result;
	}
	 
	protected boolean isEmpty(final String pUrl){
		if (pUrl == null ){
			return true;
		}
		if ( "".equals(pUrl.trim()) ){
			return true;
		} else {
			return false;
		}
	}
	
	public void clearScript(){
		if ( treeScript != null ){
			treeScript = new StringBuffer(bufferSize);
		}
	}
	public String getTreeScript(){
		String result = treeScript.toString();
		if ( log.isDebugEnabled() ){
			log.debug("script:\n" + result);
		}
		return result;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
}
