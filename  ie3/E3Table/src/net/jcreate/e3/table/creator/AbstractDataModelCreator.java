package net.jcreate.e3.table.creator;

import java.util.Collections;
import java.util.List;

import net.jcreate.e3.table.CreateDataModelException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.DataModelCreator;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.model.CollectionDataModel;
import net.jcreate.e3.table.model.EmptyDataModel;
import net.jcreate.e3.table.support.DefaultPageInfo;
import net.jcreate.e3.table.support.DefaultSortInfo;
import net.jcreate.e3.table.support.EmptySortInfo;


/**
 * 
 * @author 黄云辉
 *
 */
public abstract class AbstractDataModelCreator implements DataModelCreator {

	public DataModel create(NavRequest pNavRequest) throws CreateDataModelException {
		if ( pNavRequest == null ){
			return EmptyDataModel.me;
		}
		
		SortInfo sortInfo = null;
		if ( pNavRequest.getSortProperty() != null ){
			sortInfo = new DefaultSortInfo(pNavRequest.getSortProperty(), pNavRequest.getSortDir());
		}else{
			sortInfo = EmptySortInfo.me;
		}
		//开始位置,从0开始
		 int start = pNavRequest.getStart();
		//每页记录数,
		 int pageSize = pNavRequest.getPageSize();
		//总记录数
		int totalSize = getTotalSize();
		if ( totalSize < 0 ){
			throw new CreateDataModelException("记录总数应该大于或等于零!");
		}
		DefaultPageInfo pageInfo = new DefaultPageInfo(start, totalSize, pNavRequest.getPageSize());
		//当start大于 totalSize -1 时,需要调整start的值.
		if ( start < 0 ){
			start = 0;
		}
		if ( start > (totalSize-1) ){
			//@todo: getStartOfLastPage的计算不会依赖start的值,否则这种算法有问题
			//,有空的时候把getStartOfLastPage代码挪出来
			start = pageInfo.getStartOfLastPage();
			pageInfo.setStart(start);
		}
		
		List data = null;
		if(totalSize>0){
			data = queryData(start,pageSize, pNavRequest);
		} else {
			data = Collections.EMPTY_LIST;
		}
		DataModel result = new CollectionDataModel(data, sortInfo, pageInfo);
		return result;
	}
	/**
	 * 获取记录总数
	 * @return
	 */
	abstract protected int getTotalSize() throws CreateDataModelException;
	/**
	 * 查询当前页面数据,如果需要排序,需要调用 getSortCode 获取排序代码
	 * @param pStart      记录开始索引
	 * @param pPageSize   每页记录数
	 * @param pNavRequest 导航请求对象
	 * @return
	 */
	abstract protected List queryData(int pStart, int pPageSize, NavRequest pNavRequest) throws CreateDataModelException;
}
