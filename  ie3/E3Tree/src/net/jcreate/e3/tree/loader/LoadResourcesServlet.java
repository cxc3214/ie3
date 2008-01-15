package net.jcreate.e3.tree.loader;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadResourcesServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public static final String EXPORT_RESOURCES_PARAM = "exportResources";
	private final static Log logger = LogFactory.getLog ( LoadResourcesServlet.class );

	public void init() throws ServletException {
		final String WEB_HOME = this.getServletContext().getRealPath("/");
		final String exportResources =  this.getInitParameter(EXPORT_RESOURCES_PARAM);
		
		logger.debug("导出E3.Tree资源的控制参数:" + EXPORT_RESOURCES_PARAM + "的值为：" + exportResources);
		
		if ( "false".equalsIgnoreCase(exportResources) ){		
		  ;//donothing
		}else{
			ResourcesLoader.load(WEB_HOME);
		}		
		ResourcesLoader.load(WEB_HOME);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().log(
				"Attempt to call service method on LoadResourcesServlet as [" +
				request.getRequestURI() + "] was ignored");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}
	

}
