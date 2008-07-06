/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.SortInfo;
import net.jcreate.e3.table.decorator.CompositeCellDecorator;
import net.jcreate.e3.table.decorator.JspDecorator;
import net.jcreate.e3.table.html.Attributeable;
import net.jcreate.e3.table.html.Decorateable;
import net.jcreate.e3.table.html.HTMLCell;
import net.jcreate.e3.table.html.HTMLColumn;
import net.jcreate.e3.table.html.HTMLRow;
import net.jcreate.e3.table.message.MessageSourceFactory;
import net.jcreate.e3.table.support.TableConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ColumnTag extends BodyTagSupport implements Attributeable,  Decorateable{
	
	private final Log logger = LogFactory.getLog( ColumnTag.class );
	private static final long serialVersionUID = 1L;

	/**
	 * 列名 
	 */
	private String property;
	private String style;
	private boolean hidden = false;
	/**
	 * @deprecated
	 */
	private String styleClass;
	
	private String headerStyle;
	
	/**
	 * 排序属性，默等于property
	 */
	private String sortName;

	/**
	 * 列标题
	 */
	private String title;

	/**
	 * 列宽
	 */
	private String width;
	
	/**
	 * 能否排序
	 */
	private Boolean sortable = null;
	
	/**
	 * 标题key,用于从消息文件中获取标题
	 */
	private String titleKey;
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	private TableTag getTableTag(){
		return (TableTag) findAncestorWithClass(this, TableTag.class);
	}
	public int doStartTag() throws JspException {
		this.setBodyContent(null);
		TableTag tableTag = getTableTag();
		if ( tableTag == null ){
			throw new JspException("column 必须是 table的子元素");
		}
		boolean isCreatedTable = tableTag.isCreatedTable();
		if ( isCreatedTable == false ){
			tableTag.addColumnProperty(this.property);
			return SKIP_BODY;
		}
		
		boolean isCreatedHeader = tableTag.isCreatedHeader();
		if ( isCreatedHeader == false ){
			return SKIP_BODY;
		}
		HTMLRow currRow = tableTag.getCurrRow();
		if ( currRow == null ){
			return super.doStartTag();
		}
		currCell =(HTMLCell)currRow.getCell(this.property);
		currCell.setCellDecorator(new CompositeCellDecorator());
		
		return EVAL_BODY_BUFFERED;
	}
	private HTMLCell currCell = null;
	
	private boolean getDefaultSortable(){
		boolean result = true;
		TableTag tableTag = getTableTag();
		String sortable = MessageSourceFactory.getInstance().getMessage(TableConstants.SORTABLE_KEY,null, String.valueOf(TableConstants.DEFAULT_SORTABLE),tableTag.getLocale());
		try{
			result = Boolean.valueOf(sortable).booleanValue();
		}catch(Exception ex){
			logger.warn("默认排序:[" + sortable + "]不是有效布尔类型!使用默认值:" + TableConstants.DEFAULT_SORTABLE);
		}
		return result;
	}
	
	public int doEndTag() throws JspException {
		TableTag tableTag = getTableTag();
		if ( tableTag == null ){
			throw new JspException("column 必须是 table的子元素");
		}
		boolean isCreatedTable = tableTag.isCreatedTable();
		if ( isCreatedTable == false ){//没创建表格
			return super.doEndTag();
		}
		

		boolean isCreatedHeader = tableTag.isCreatedHeader();
		if ( isCreatedHeader == false ){
			//设置column
			HTMLColumn column = (HTMLColumn)tableTag.getTable().getColumn(this.property);
			column.setTitle(this.title);
			column.setWidth(this.width);
			column.setHidden(this.hidden);
			column.setSortable(this.sortable == null ? getDefaultSortable() : this.sortable.booleanValue());
			column.setTitleKey(this.titleKey);
			column.setStyle(this.style);
			column.setStyleClass(this.styleClass);
			column.setHeaderStyle(headerStyle);
			SortInfo sortInfo = tableTag.getSortInfo();
			if ( sortInfo != null ){//存在排序信息
				String sortColumn = sortInfo.getSortProperty();
				if ( this.property.equals(sortColumn) ){//当前列是排序列
					column.setSortDir(sortInfo.getSortDir());//设置排序方向
				}
			}
			column.setSortName(this.sortName);			
			ColumnGroupTag groupTag = (ColumnGroupTag) findAncestorWithClass(this, ColumnGroupTag.class);
			if ( groupTag != null ){//如果列存在分组，则添加进去
				groupTag.addColumn(column);
			}
			/**
			 * @todo:设置其他column属性
			 */
			return super.doEndTag();
		}

		BodyContent content  = this.bodyContent;
	    if ( content != null){
			String bodyContext = content.getString();//获取body信息
			/**
			 * @fixme: 没有办法识别是没有设置bodyContent还是经过修饰处理之后bodyContent内容为空.
			 * 现在只要发现值为""时,就使用原始的属性值.
			 * 
			 */
			if ( "".equals( bodyContext ) == false ){
			//只有不存在修饰器的时候,body context才作为修饰器进行处理.否则忽略body content的内容.
				//这个问题到jsp2.0可以解决
				if ( ((CompositeCellDecorator)currCell.getCellDecorator()).getSize() == 0 ){
					JspDecorator jsp = new JspDecorator();
					jsp.setJsp(bodyContext);
					content.clearBody();
				    this.addCellDecorator(jsp);
				}
			}
			super.setBodyContent(null);//tomcat5.028好象不会自动清除,所以我们显示设置为nulll					
		}
		
		cleanUp();
		return super.doEndTag();
	}

	private void cleanUp(){
		
		
	}
	public void addCellDecorator(CellDecorator pCellDecorator){
		if ( currCell == null ){
			return ;
		}
		((CompositeCellDecorator)currCell.getCellDecorator()).addCellDecorator( pCellDecorator );
	}
	public void release() {
		this.property = null;
		this.title = null;
		this.width = null;
		this.hidden = false;
		this.headerStyle = null;
		this.style = null;
		this.styleClass = null;
		this.titleKey = null;
		this.sortable = null;;
		super.release();
	}



	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	public String getSortName() {
		if ( this.sortName == null ){
			return this.property;
		}else{
			return this.sortName;
		}
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public void setAttribute(String name, String value) {
		if ( currCell != null ){
		   currCell.setAttribute(name, value);
		}
	}

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

	public String getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(String headerStyle) {
		this.headerStyle = headerStyle;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	
	
}
