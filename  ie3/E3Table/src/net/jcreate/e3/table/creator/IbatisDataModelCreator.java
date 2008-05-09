package net.jcreate.e3.table.creator;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.table.CreateDataModelException;
import net.jcreate.e3.table.NavRequest;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @name TempAction
 * @title 测试Action类
 * @description 操作临时表
 * @author 熊春 [留个名,跟的E3一起火 :)]
 * @date 2008-05-01
 * @version  
 */
public class IbatisDataModelCreator extends AbstractDataModelCreator {
	private  int totalSize;
	private  Map map;
	private SqlMapExecutor executor;
	private String sqlId;
	
	/**
	 * @name IbatisDataModelCreator
	 * @param pTotalSize 总记录数
	 * @param pExecutor SqlMapExecutor
	 * @param pMap 查询条件
	 * @param pSqlId SqlMap ID号
	 * @return IbatisDataModelCreator
	 * @description
	 */
	public IbatisDataModelCreator(int pTotalSize, SqlMapExecutor pExecutor, Map pMap, String pSqlId){
		if ( pTotalSize < 0 ){
			throw new java.lang.IllegalArgumentException("总记录数不能小于0");
		}
		this.totalSize = pTotalSize;
		this.map = pMap;
		this.sqlId = pSqlId;
		this.executor = pExecutor;
	}
	
	protected int getTotalSize()throws CreateDataModelException {
		return totalSize;
	}

	/**
	 * @name queryData
	 * @param start 起始位置
	 * @param pageSize 每页记录数
	 * @param navRequest NavRequest
	 * @return list 结果集
	 * @description
	 */
	protected List queryData(int start, int pageSize, NavRequest navRequest) throws CreateDataModelException {
		List list;
		try {
		    list = this.executor.queryForList(sqlId, map, start, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			return java.util.Collections.EMPTY_LIST;
		}
		return list;
	}
}