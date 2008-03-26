package net.jcreate.home.index;

public class ResourceItem {

	/**
	 * 资源名称
	 */
	private String resName;
	/**
	 * 资源描述
	 */
	private String resDesc;
	/**
	 * 下载地址
	 */
	private String downloadURL;
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResDesc() {
		return resDesc;
	}
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	public String getDownloadURL() {
		return downloadURL;
	}
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
}
