package net.jcreate.home.index;

public class ComponentItem {
	/**
	 * 组件名称
	 */
	  private String componentName;
	  /**
	   * 组件描述
	   */
	  private String componentDesc;
	  /**
	   * 下载URL
	   */
	  private String downloadURL;
	  /**
	   * 演示URL
	   */
	  private String demoURL;
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentDesc() {
		return componentDesc;
	}
	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}
	public String getDownloadURL() {
		return downloadURL;
	}
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
	public String getDemoURL() {
		return demoURL;
	}
	public void setDemoURL(String demoURL) {
		this.demoURL = demoURL;
	}

}
