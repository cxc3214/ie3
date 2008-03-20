package net.jcreate.e3.resource.support;

public class ResourceConfig {
	private String charset;

	private String mimeType;

	private String loaderName ;

	private String[] handlerNames;
	
	public ResourceConfig(String pLoaderName, String[] pHandlerNames){
		this.loaderName = pLoaderName;
		this.handlerNames = pHandlerNames;
	}
	public ResourceConfig(String pLoaderName, String[] pHandlerNames, String pCharset, String pMimetype){
		this.loaderName = pLoaderName;
		this.handlerNames = pHandlerNames;
		this.charset = pCharset;
		this.mimeType = pMimetype;
	}
	

	public String getCharset() {
		return charset;
	}

	public String[] getHandlerNames() {
		return handlerNames;
	}

	public String getLoaderName() {
		return loaderName;
	}

	public String getMimeType() {
		return mimeType;
	}
}
