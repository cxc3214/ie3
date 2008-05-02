package net.jcreate.e3.commons.id;

public class DBSequenceStorer implements SequenceStorer{

	private javax.sql.DataSource dataSource;
	private String tableName;
	private String idColumnName;
	private String valueColumnName;
	public long load(String sequenceID) throws StoreSequenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void save(long sequence, String sequenceID)
			throws StoreSequenceException {
		// TODO Auto-generated method stub
		
	}

	public javax.sql.DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdColumnName() {
		return idColumnName;
	}

	public void setIdColumnName(String idColumnName) {
		this.idColumnName = idColumnName;
	}

	public String getValueColumnName() {
		return valueColumnName;
	}

	public void setValueColumnName(String valueColumnName) {
		this.valueColumnName = valueColumnName;
	}

}
