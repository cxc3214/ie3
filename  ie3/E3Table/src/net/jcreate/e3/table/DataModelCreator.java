package net.jcreate.e3.table;

/**
 * 数据模型创建器
 * @author 黄云辉
 *
 */
public interface DataModelCreator {
	
	/**
	 * 
	 * @param pNavRequest 翻页请求
	 * @return            数据模型
	 * @throws TableException
	 */
	public DataModel create(NavRequest pNavRequest) throws CreateDataModelException;
}
