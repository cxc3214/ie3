package net.jcreate.e3.table.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * 该接口来自spring
 * 
 * An interface used by JdbcTemplate for mapping returned result sets.
 * Implementations of this interface perform the actual work of mapping
 * rows, but don't need to worry about exception handling. SQLExceptions
 * will be caught and handled correctly by the JdbcTemplate class.
 *
 * <p>Typically used either for JdbcTemplate's query methods (with
 * RowMapperResultSetExtractor adapters) or for out parameters of stored procedures.
 * RowMapper objects are typically stateless and thus reusable; they are
 * ideal choices for implementing row-mapping logic in a single place.
 *
 * <p>Alternatively, consider subclassing MappingSqlQuery from the jdbc.object
 * package: Instead of working with separate JdbcTemplate and RowMapper objects,
 * you can have executable query objects (containing row-mapping logic) there.
 *
 * @author Thomas Risberg
 * @see JdbcTemplate
 * @see RowMapperResultSetExtractor
 * @see org.springframework.jdbc.object.MappingSqlQuery
 */
public interface RowMapper {
	
	/** 
	 * Implementations must implement this method to map each row of data
	 * in the ResultSet. This method should not call <code>next()</code>
	 * on the ResultSet; it should only extract the values of the current row.
	 * @param rs the ResultSet to map
	 * @param rowNum the number of the current row
	 * @throws SQLException if a SQLException is encountered getting
	 * column values (that is, there's no need to catch SQLException)
	 */
	Object mapRow(ResultSet rs, int rowNum) throws SQLException; 

}
 
