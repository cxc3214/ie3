package net.jcreate.e3.table.creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.Sort;

/**
 * 内存数据模型
 * @author 黄云辉
 *
 */
public class CollectionDataModelCreator extends AbstractDataModelCreator {

	private java.util.Collection datas = null;
	
	public CollectionDataModelCreator(java.util.Collection pDatas){
		if ( pDatas == null ){
			datas = java.util.Collections.EMPTY_LIST;
		}
		this.datas = pDatas;
	}
	protected int getTotalSize() {
		return datas.size();
	}

	protected List queryData(int start, int pageSize, NavRequest pNavRequest) {
		List allData = new ArrayList(datas);		
		String property = pNavRequest.getSortProperty();
		if ( property != null ){
			String sortDir = pNavRequest.getSortDir();
			if ( Sort.ASC.getCode().equals(sortDir) ){//升序
		       Collections.sort(allData, new BeanComparator(property));//beanutil的通用排序器
			}else if ( Sort.DESC.getCode().equals(sortDir) ){
				Collections.sort(allData, new BeanComparator(property));
				Collections.reverse(allData);	
			}else{
				;// do nothing;这种情况应该不会出现.
			}		   
		}
		int min = Math.max(start, 0);
		int max = Math.min(getTotalSize(), min + pageSize);
		List result = allData.subList(min, max);
		return result;
	}

}
