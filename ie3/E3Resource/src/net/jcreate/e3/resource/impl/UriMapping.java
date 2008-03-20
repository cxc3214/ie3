package net.jcreate.e3.resource.impl;

import java.io.Serializable;

public class UriMapping implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String charset;
	private String mimeType;
	private String loader = Constants.DEFAULT_LOADER_NAME;	
	private String handlers;
	private String uriPattern;
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
	public String getUriPattern() {
		return uriPattern;
	}
	public void setUriPattern(String uriPattern) {
		this.uriPattern = uriPattern;
	}
	
}
