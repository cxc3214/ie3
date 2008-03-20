package net.jcreate.e3.resource;

/**
 * 资源管理器
 * @author 黄云辉
 *
 */
public interface ResourceManager {

	/**
	 * 执行资源管理的初始化操作
	 * 只会被调用一次
	 * @throws ResourceException
	 */
	public void init() throws ResourceException;
	
	/**
	 * 获取资源
	 * @param pUri
	 * @return
	 * @throws ResourceException
	 */
    public Resource get(final String pUri) throws ResourceException;
    
    /**
     * 执行清楚处理
	 * 只会被调用一次 
     * @throws ResourceException
     */
    public void destroy() throws ResourceException;
    
    
}
