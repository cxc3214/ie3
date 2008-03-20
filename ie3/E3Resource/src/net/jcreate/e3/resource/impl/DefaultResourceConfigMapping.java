package net.jcreate.e3.resource.impl;

import net.jcreate.e3.resource.ResourceException;
import net.jcreate.e3.resource.support.ResourceConfig;
import net.jcreate.e3.resource.support.ResourceConfigMapping;
import net.jcreate.e3.resource.util.AntPathMatcher;
import net.jcreate.e3.resource.util.StringUtils;

public class DefaultResourceConfigMapping implements ResourceConfigMapping {
    private UriMapping[] uriMappings = null;
    
	public UriMapping[] getUriMappings() {
		return uriMappings;
	}
	public void setUriMappings(UriMapping[] uriMappings) {
		/**
		 * @todo: clone数组
		 */
		this.uriMappings = uriMappings;
	}
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	public ResourceConfig mapping(String pUri) throws ResourceException {
		if ( uriMappings == null ){
			return null;
		}
		for(int i=0; i<uriMappings.length; i++){
			UriMapping uriMapping = uriMappings[i];
			if  ( pathMatcher.match(uriMapping.getUriPattern(), pUri) == false ){
				continue;
			} else {
				String loaderName = uriMapping.getLoader();
				String handlerNames = uriMapping.getHandlers();
				String charset = uriMapping.getCharset();
				String mimeType = uriMapping.getMimeType();
				String[] hanlderNameArray = StringUtils.tokenizeToStringArray(handlerNames,  Constants.SPLITER);				
				ResourceConfig result = new 
				   ResourceConfig(loaderName,hanlderNameArray, charset, mimeType);
				return result;
			}
			
		}
		return null;
	}

}
