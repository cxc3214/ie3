package net.jcreate.e3.resource.impl;

import java.io.Serializable;

public class UriMapping implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String charset;
	private String mimeType;
	private String loader = Constants.DEFAULT_LOADER_NAME;	
	private String handlers;
	private String includes;
	private String excludes;
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getHandlers() {
		return handlers;
	}
	public void setHandlers(String handlers) {
		this.handlers = handlers;
	}
	public String getLoader() {
		return loader;
	}
	public void setLoader(String loader) {
		this.loader = loader;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @deprecated 历史原因,以后使用getIncludes
	 */
	public String getUriPattern() {
		return includes;
	}
	/**
	 * @deprecated
	 * 历史原因,以后使用setIncludes
	 */	
	public void setUriPattern(String uriPattern) {
		this.includes = uriPattern;
	}
	public String getIncludes() {
		return includes;
	}
	public void setIncludes(String includes) {
		this.includes = includes;
	}
	public String getExcludes() {
		return excludes;
	}
	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}
	
}
