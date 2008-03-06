package net.jcreate.e3.table.html;

import net.jcreate.e3.table.support.DefaultColumnGroup;

public class HTMLColumnGroup extends DefaultColumnGroup{

	private String style;
	private String styleClass;
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public HTMLColumnGroup() {
		super();
	}

}
