package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.decorator.CompositeCellDecorator;
import net.jcreate.e3.table.decorator.JspDecorator;
import net.jcreate.e3.table.html.Attributeable;
import net.jcreate.e3.table.html.Decorateable;
import net.jcreate.e3.table.html.VirtualHTMLCell;

public class AddCellTag extends BodyTagSupport implements Attributeable, Decorateable{
	
	private static final long serialVersionUID = 1L;
	
	private String style;
	private VirtualHTMLCell virtualCell = null;
	private int rowspan = 1;
	private int colspan = 1;
	

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setAttribute(String name, String value) {
		if ( virtualCell == null ){
			return;
		}
		virtualCell.setAttribute(name, value);
	}
	
	private TableTag getTableTag(){
		return (TableTag) findAncestorWithClass(this, TableTag.class);
	}
	
	public int doStartTag() throws JspException {
		this.setBodyContent(null);
		TableTag tableTag = getTableTag();
		if ( tableTag == null ){
			throw new JspException("cell 必须是 table的子元素");
		}
		boolean isCreatedTable = tableTag.isCreatedTable();
		if ( isCreatedTable == false ){
			return SKIP_BODY;
		}
		boolean isCreatedHeader = tableTag.isCreatedHeader();
		if ( isCreatedHeader == false ){
			return SKIP_BODY;
		}
		virtualCell = new VirtualHTMLCell();
		virtualCell.setRowspan(this.rowspan);
		virtualCell.setColspan(this.colspan);
		AppendRowTag rowTag = (AppendRowTag) findAncestorWithClass(this, AppendRowTag.class);
		if ( rowTag != null ){
		  rowTag.addCell(virtualCell);
		}
		virtualCell.setCellDecorator(new CompositeCellDecorator());
		if ( style != null ){
		  setAttribute("style", this.style);
		}
		return EVAL_BODY_BUFFERED;
	}
	public int doEndTag() throws JspException {
		TableTag tableTag = getTableTag();
		boolean isCreatedHeader = tableTag.isCreatedHeader();
		if ( isCreatedHeader == false ){
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
				if ( ((CompositeCellDecorator)virtualCell.getCellDecorator()).getSize() == 0 ){
					JspDecorator jsp = new JspDecorator();
					jsp.setJsp(bodyContext);
					content.clearBody();
				    this.addCellDecorator(jsp);
				}
			}
			super.setBodyContent(null);//tomcat5.028好象不会自动清除,所以我们显示设置为nulll					
		}
		return super.doEndTag();
	}
	
	

	public void release() {
		this.style = null;
		this.rowspan = 1;
		this.colspan = 1;
		super.release();
	}

	public void addCellDecorator(CellDecorator pCellDecorator) {
          if ( virtualCell == null ){
        	  return;
          }
          ((CompositeCellDecorator)virtualCell.getCellDecorator()).addCellDecorator( pCellDecorator );
		
	}
	
}
