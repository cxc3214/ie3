package net.jcreate.e3.resource;


/**
 * 负责资源装载
 * @author 黄云辉
 *
 */
public interface ResourceLoader {

	/**
	 * 获取资源上次修改时间
	 * @param pUri
	 * @return
	 */
	public long getLastModified(String pUri);
	
	/**
	 * @param pUri 资源标识符
	 * @return 资源对象
	 * @throws Exception 装载资源失败
	 */
    public Resource load(final String pUri) throws LoadResoruceException; 
}
