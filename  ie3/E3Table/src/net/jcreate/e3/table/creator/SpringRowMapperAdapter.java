package net.jcreate.e3.table.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * spring rowmapper适配器
 * @author 黄云辉
 *
 */
public class SpringRowMapperAdapter implements RowMapper {
	org.springframework.jdbc.core.RowMapper springRowMapper = null;
	public SpringRowMapperAdapter(org.springframework.jdbc.core.RowMapper pRowMapper){
    	if ( pRowMapper == null ){
    		throw new java.lang.IllegalArgumentException("行对象不能为空null");
    	}
    	this.springRowMapper = pRowMapper;
    }
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {              
		return springRowMapper.mapRow(rs, rowNum);
	}

}
