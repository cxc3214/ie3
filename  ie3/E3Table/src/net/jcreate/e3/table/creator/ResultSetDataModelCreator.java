package net.jcreate.e3.table.creator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.jcreate.e3.table.CreateDataModelException;
import net.jcreate.e3.table.NavRequest;

public class ResultSetDataModelCreator extends AbstractDataModelCreator {

	private java.sql.ResultSet rs = null;
	private RowMapper rowMapper = null;
	
	/**
	 * 
	 * @param pRS 结果集,要是可滚动的结果集 ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * @param pRowMapper 
	 */
	public ResultSetDataModelCreator(java.sql.ResultSet pRS, RowMapper pRowMapper){
		this.rs = pRS;
		this.rowMapper = pRowMapper; 
	}
	
	protected int getTotalSize()throws CreateDataModelException {
		if ( rs == null ){
			return 0;
		} else {
			try {
				rs.last();
				return rs.getRow();
			} catch (SQLException e) {
				final String msg =
					"获取记录总数失败!" + e.getMessage();
				throw new CreateDataModelException(msg, e);
			}
			
		}
	}

	protected List queryData(int start, int pageSize, NavRequest pNavRequest) throws CreateDataModelException{
		List result = new ArrayList();				
		try{
		    rs.first();
	        rs.absolute(start);	//定位到指定位置
	        int rowNum = start+1;//结果行号,第一行为1,第2行位,依次类推
	        do {
	            Object obj = rowMapper.mapRow(rs, rowNum);//行对象
	            result.add(obj);
	            rowNum++;
	        } while (rs.next() && --pageSize > 0);
	        
		}catch(SQLException ex){
			final String msg =
				"获取结果集数据失败!" + ex.getMessage();
			throw new CreateDataModelException(msg, ex);
		}
		return result;
	}

}