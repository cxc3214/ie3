package net.jcreate.e3.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.Assert;

public class WebAppListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		Assert.notNull(event, "ServletContext must not be null");
		final String webRootPath = event.getServletContext().getRealPath("/");
		WebUtils.setWebAppRoot(webRootPath);
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}
