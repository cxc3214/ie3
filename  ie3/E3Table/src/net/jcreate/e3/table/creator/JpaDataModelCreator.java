package net.jcreate.e3.table.creator;

import java.util.List;
import javax.persistence.Query;
import net.jcreate.e3.table.CreateDataModelException;
import net.jcreate.e3.table.NavRequest;
/**
 * JPA 数据模型creator
 * @author mht
 *
 */
public class JpaDataModelCreator extends AbstractDataModelCreator{
	private final int totoalSize;
	private final Query query;  
	
	public JpaDataModelCreator(int pTotalSize, Query pQuery){
		if ( pTotalSize < 0 ){
			throw new java.lang.IllegalArgumentException("总记录数不能小于0");
		}
		this.totoalSize = pTotalSize;
		this.query = pQuery;
	}

	protected int getTotalSize() throws CreateDataModelException {
		return totoalSize;
	}

	protected List queryData(int start, int pageSize, NavRequest navRequest) throws CreateDataModelException {
		if  ( query == null ){
			return java.util.Collections.EMPTY_LIST;
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}

