package net.jcreate.home.index;

public class LinkItem {

	/**
	 * 连接名称
	 */
	private String linkName;
	/**
	 * 连接地址
	 */
	private String linkURL;
	/**
	 * 连接路径
	 */
	private String picPath;

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
}
