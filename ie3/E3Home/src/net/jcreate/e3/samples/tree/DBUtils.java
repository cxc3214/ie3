package net.jcreate.e3.samples.tree;

import java.io.InputStreamReader;
import java.sql.Connection;
import javax.sql.DataSource;
import com.ibatis.common.jdbc.ScriptRunner;


public class DBUtils {
	public static void initDB() throws Exception{
		DataSource ds = TreeBeanFactory.getDataSource();
		Connection conn = ds.getConnection();
        ScriptRunner runner = new ScriptRunner(conn, false, false);
        runner.setErrorLogWriter(null);
        runner.setLogWriter(null);
        runner.runScript(
        		new InputStreamReader( 
        		DBUtils.class.getClassLoader().
        		getResourceAsStream("net/jcreate/e3/samples/tree/config/Tree.sql"),"UTF-8") 
        		);
        conn.close();
	}
}
