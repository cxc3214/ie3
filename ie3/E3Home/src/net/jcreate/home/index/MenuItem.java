package net.jcreate.home.index;

public class MenuItem {
	/**
	 * 菜单ID
	 */
	private String menuID;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * url
	 */
	private String url;
	/**
	 * 提示信息
	 */
	private String tooltip;

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
}
