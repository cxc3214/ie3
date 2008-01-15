package net.jcreate.e3.samples.tree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.core.BusinessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class TreeService  {
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 修改机构
	 * @param pChangedOrgs
	 * @throws BusinessException
	 */
	public void updateOrgs(final Map pChangedOrgs)throws BusinessException{
		Iterator orgIds = pChangedOrgs.keySet().iterator();
		/**
		 * TODO: 如果数据量大的话，使用batch 更新
		 */
		while( orgIds.hasNext() ){
			final String orgId = (String)orgIds.next();
			final String parentOrgId = (String)pChangedOrgs.get(orgId);

			String sql =
				"update TORG " +
				"  set parentId = ? " +
				"where id = ? ";
			jdbcTemplate.update(sql, new PreparedStatementSetter(){
				public void setValues(PreparedStatement pPS) throws SQLException {
					int index = 1;
					pPS.setString(index++, parentOrgId);
					pPS.setString(index++, orgId);
				}
			});
			
		}
	}
	public List getSubOrgs(final String pParentID) throws BusinessException{
		final String SQL = "select * from TORG where parentId = ?";
		return jdbcTemplate.query(SQL,new PreparedStatementSetter(){
			public void setValues(PreparedStatement pPS) throws SQLException {
				int index = 1;
				pPS.setString(index, pParentID);
			}
		},
		new RowMapper(){
			public Object mapRow(ResultSet pRS, int arg1) throws SQLException {
				Org org = new Org();
				org.setId(pRS.getString("id"));
				org.setName(pRS.getString("name"));
				org.setParentId(pRS.getString("parentId"));
				org.setViewOrder(pRS.getInt("viewOrder"));
				return org;
			}
		});
	}
	public List getRootOrg() throws BusinessException {
		final String SQL = "select * from TORG where parentId is null";
		return jdbcTemplate.query(SQL, new RowMapper(){
			public Object mapRow(ResultSet pRS, int arg1) throws SQLException {
				Org org = new Org();
				org.setId(pRS.getString("id"));
				org.setName(pRS.getString("name"));
				org.setParentId(pRS.getString("parentId"));
				org.setViewOrder(pRS.getInt("viewOrder"));
				return org;
			}
		});
	}
	
	public List getOrgs() throws BusinessException {
		final String SQL = "select * from TORG";
		return jdbcTemplate.query(SQL, new RowMapper(){
			public Object mapRow(ResultSet pRS, int arg1) throws SQLException {
				Org org = new Org();
				org.setId(pRS.getString("id"));
				org.setName(pRS.getString("name"));
				org.setParentId(pRS.getString("parentId"));
				org.setViewOrder(pRS.getInt("viewOrder"));
				return org;
			}
		});
	}
	
}
   