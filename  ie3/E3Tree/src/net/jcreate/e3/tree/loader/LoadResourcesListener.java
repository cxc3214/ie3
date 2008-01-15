package net.jcreate.e3.tree.loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 导出js依赖的资源
 * 为了方便调试，可以通过设置 EXPORT_RESOURCES_PARAM 参数来控制是否覆盖资源，如果为false,则不覆盖
 * @author Grace
 *
 */
public class LoadResourcesListener implements ServletContextListener {
    public static final String EXPORT_RESOURCES_PARAM = "exportResources";
    
    private final static Log logger = LogFactory.getLog ( LoadResourcesListener.class );
    
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		final String WEB_HOME = pServletContextEvent.getServletContext().getRealPath("/");
		final String exportResources =  pServletContextEvent.getServletContext().getInitParameter(EXPORT_RESOURCES_PARAM);
		logger.debug("导出E3.Tree资源的控制参数:" + EXPORT_RESOURCES_PARAM + "的值为：" + exportResources);
		if ( "false".equalsIgnoreCase(exportResources) ){		
		  ;//donothing
		}else{
			ResourcesLoader.load(WEB_HOME);
		}
	}

	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
	}

}
