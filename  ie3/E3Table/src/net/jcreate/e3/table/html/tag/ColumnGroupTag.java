package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.e3.table.html.HTMLColumn;
import net.jcreate.e3.table.html.HTMLColumnGroup;

public class ColumnGroupTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String title;
	private String titleKey;
	private HTMLColumnGroup group = null;
	private String style;
	/**
	 * @deprecated
	 */
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

	public int doStartTag() throws JspException {
		TableTag tableTag = (TableTag) findAncestorWithClass(this, TableTag.class);		
		boolean isCreatedTable = tableTag.isCreatedTable();
		if ( isCreatedTable == true ){
			group = new HTMLColumnGroup();
			group.setTitle(this.title);
			group.setTitleKey(this.titleKey);
			group.setStyle(this.style);
			group.setStyleClass(this.styleClass);
			ColumnGroupTag parent = (ColumnGroupTag) findAncestorWithClass(this, ColumnGroupTag.class);
			if ( parent != null ){
				parent.addGroup(group);	
			}
		}
		return ColumnGroupTag.EVAL_BODY_INCLUDE;
	}

	public void addGroup(HTMLColumnGroup pGroup){
		group.addGroup(pGroup);
	}
	
	public void addColumn(HTMLColumn pColumn){
		group.addColumn(pColumn);
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	public void release() {
		super.release();
		this.title = null;
		this.titleKey = null;
		this.style = null;
		this.styleClass = null;
	}

	
}
