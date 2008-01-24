package net.jcreate.e3.web;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public abstract class BaseFilter implements Filter{

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	protected FilterConfig filterConfig = null;
	
	public void init(FilterConfig pFilterConfig) throws ServletException {
		Assert.notNull(pFilterConfig, "FilterConfig must not be null");		
        filterConfig = pFilterConfig;
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing filter '" + filterConfig.getFilterName() + "'");
		}
         doInit();
     	if (logger.isDebugEnabled()) {
			logger.debug("Filter '" + filterConfig.getFilterName() + "' configured successfully");
		}         
	}
	protected void doInit() throws ServletException{
	}
	 
	public void destroy() {
	}

}
