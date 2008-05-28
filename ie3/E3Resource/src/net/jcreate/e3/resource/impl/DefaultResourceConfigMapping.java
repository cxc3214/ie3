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
			if  ( isMatch(uriMapping, pUri) == false ){
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
	
	protected boolean isMatch(UriMapping pUriMapping, String pUri){
		final String includes = pUriMapping.getIncludes();
		final String excludes = pUriMapping.getExcludes(); 
		if ( includes == null ){
			return false;
		}
		if ( pUri == null ){
			return false;
		}
		String[] includeArray = StringUtils.tokenizeToStringArray(includes,  Constants.SPLITER);
		boolean match = false;
		for(int i=0; i<includeArray.length; i++){
			String include = includeArray[i];
			if ( pathMatcher.match(include, pUri)) {
				match = true;	
				break;
			}
		}
		if ( match ){
			//检查includes
			String[] excludeArray = StringUtils.tokenizeToStringArray(excludes,  Constants.SPLITER);
			for(int i=0; i<excludeArray.length; i++){
				String exclude = excludeArray[i];
				if ( pathMatcher.match(exclude, pUri)) {
					match = false;
					break;
				}
			}
						
		}
		return match;
	}

}
