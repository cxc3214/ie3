package net.jcreate.e3.resource.support;

import net.jcreate.e3.resource.Resource;

public class DefaultResource implements Resource{

	private static final long serialVersionUID = 1L;

	/**
	 * 资源mimeType
	 */
	private String mimeType;

	/**
	 * 编码类型
	 */
	private String charset;
	
	/**
	 * 上次修改时间
	 */
	private long lastModified;

	/**
	 * 资源uri
	 */
	private final String uri;
	
	/**
	 * 是否经过gzip压缩 
	 */
	private boolean gzip = false;

	/**
	 * 资源数据
	 */
	private final byte[] data;
	/**
	 * 已经过处理的数据
	 */
	private byte[] treatedData;//初始化值为data

	public DefaultResource(String pUri, byte[] pData){
		this.uri = pUri;
		this.data = pData;
		treatedData = new byte[pData.length];
		for(int i=0;i<pData.length; i++){
			treatedData[i] = pData[i];
		}
	}
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


	public String getUri() {
		return uri;
	}
	public byte[] getData() {
		return data;
	}
	public long getLastModified() {
		return lastModified;
	}
	public void setLastModified(long pLastModified) {
		lastModified = pLastModified;
	}
	public boolean isGzip() {
		return gzip;
	}
	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}
	public byte[] getTreatedData() {
		return treatedData;
	}
	public void setTreatedData(byte[] treatedData) {
		this.treatedData = treatedData;
	}
	
}
