package net.jcreate.e3.samples.tree;

import javax.sql.DataSource;

import net.jcreate.e3.core.E3BeanFactoryBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;

public class TreeBeanFactory {

	private final static Log logger = LogFactory.getLog( TreeBeanFactory.class ) ; 
	private TreeBeanFactory() {

	}
	
	private final static String BEAN_DEF_FILE
	 = "e3:net/jcreate/e3/samples/tree/config/Tree.spring.xml";
	
	private static BeanFactory beanFactory = null;
	
	static{
		try{
		beanFactory = E3BeanFactoryBuilder.build(BEAN_DEF_FILE);
		}catch(Throwable ex){
			final String MSG = 
				"导入spring配置文件:'" + BEAN_DEF_FILE + "'失败!" + ex.getMessage();
			logger.error(MSG, ex);
			ex.printStackTrace();
		}
	}
	
	public static TreeService getTreeService(){
		return (TreeService)beanFactory.getBean("treeService");
	}
	
	public static DataSource getDataSource(){
		return (DataSource)beanFactory.getBean("dataSource");
	}
	

}
