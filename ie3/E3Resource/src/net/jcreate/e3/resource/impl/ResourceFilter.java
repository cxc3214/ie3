package net.jcreate.e3.resource.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.FilterConfig;
import net.jcreate.e3.resource.AbstractResourceFilter;
import net.jcreate.e3.resource.ResourceManager;
import net.jcreate.e3.resource.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceFilter extends AbstractResourceFilter{

	private static final long serialVersionUID = 1L;
	
	private final Log logger = LogFactory.getLog( this.getClass() );

	public static final String CONFIG_PARAM_KEY = "config";
	public static final String DEFAULT_CONFIG = "/WEB-INF/config/E3.Resource.properties";
	protected ResourceManager createResourceManager(FilterConfig pFilterConfig) {
		final String config = pFilterConfig.getInitParameter(CONFIG_PARAM_KEY);
		
		String useConifg = config;
		
		Configuration configuration = new Configuration();
		if ( StringUtils.hasLength(config) == false ){//没设置了配置文件
			logger.info("没有指定资源管理器的配置文件，采用默认的配置: " + DEFAULT_CONFIG);
			useConifg = DEFAULT_CONFIG;
		}
			logger.debug("E3.Resource配置文件是:" + useConifg);
			InputStream is = pFilterConfig.getServletContext().getResourceAsStream(useConifg);
			if ( is == null ){
				final String MSG = "没有发现配置文件:" + useConifg + "\n" +
						           "系统启用默认的配置!";
				logger.warn(MSG);
				configuration.build();
			}else{
				try{
				    configuration.buildInputStream(is);
				}finally{
					try {
						if ( is != null ) is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	
			}
		  ResourceManager result = configuration.buildResourceManager();
		  return result;
	}

}
