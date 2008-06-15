package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.e3.table.html.Attributeable;
import net.jcreate.e3.table.html.VirtualHTMLCell;
import net.jcreate.e3.table.html.VirtualHTMLRow;

public class AppendRowTag extends TagSupport implements Attributeable{
	private static final long serialVersionUID = 1L;

	private VirtualHTMLRow currRow = null;
	private String style; 
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}


	public void setAttribute(String name, String value) {
		currRow.setAttribute(name, value);
	}
	
	public void addCell(VirtualHTMLCell pCell){
		if ( this.currRow == null ){
			return;
		}
		if ( pCell == null ){
			return;
		}
		this.currRow.addCell(pCell);
	}
	
	

	public int doStartTag() throws JspException {
		TableTag tableTag = (TableTag) findAncestorWithClass(this, TableTag.class);
		if ( tableTag == null ){
			throw new JspException("row 必须是table的子元素!");
		}		
		if ( tableTag.isCreatedHeader() == false ){
			return SKIP_BODY;
		}
		currRow = new VirtualHTMLRow();
		tableTag.addVirtualRow(currRow, getOffset());
		if ( style != null ){
		  setAttribute("style", this.style);
		}
		return EVAL_BODY_INCLUDE;
	}
	protected int getOffset(){
		return 0;
	}
	public int doEndTag() throws JspException {		
		return super.doEndTag();
	}
	
	

}
