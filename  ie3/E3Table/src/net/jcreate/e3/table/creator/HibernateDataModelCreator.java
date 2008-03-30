package net.jcreate.e3.table.creator;

import java.util.List;

import net.jcreate.e3.table.CreateDataModelException;
import net.jcreate.e3.table.NavRequest;

import org.hibernate.Query;

/**
 * hibernate 数据模型creator
 * @author 黄云辉
 *
 */
public class HibernateDataModelCreator extends AbstractDataModelCreator{

	private final int totoalSize;
	private final Query query;  
	/**
	 * 
	 * @param pTotalSize 总记录数
	 * @param pQuery     Query对象,参数已经设置好了
	 */
	public HibernateDataModelCreator(int pTotalSize, Query pQuery){
		if ( pTotalSize < 0 ){
			throw new java.lang.IllegalArgumentException("总记录数不能小于0");
		}
		this.totoalSize = pTotalSize;
		this.query = pQuery;
	}
	protected int getTotalSize()throws CreateDataModelException {
		return totoalSize;
	}

	protected List queryData(int start, int pageSize, NavRequest navRequest) throws CreateDataModelException{
		if  ( query == null ){
			return java.util.Collections.EMPTY_LIST;
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
