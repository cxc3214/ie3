package net.jcreate.e3.table.html;

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;

public class FastSkinHTMLTableBuilder extends SkinHTMLTableBuilder{
	//整个body的构造过程进行合并处理.
	final protected void buildHTMLCellBegin(HTMLCell pCell) throws BuildTableException {
	}
	final protected void buildHTMLCellEnd(HTMLCell pCell) throws BuildTableException {
		
	}
	final protected void buildHTMLCell(HTMLCell cell) throws BuildTableException {
	}
	final protected void buildHTMLColumnBegin(HTMLColumn pColumn) throws BuildTableException {
		
	}
	final protected void buildHTMLColumnEnd(HTMLColumn pColumn) throws BuildTableException {
		
	}
	final protected void buildHTMLBodyBegin(HTMLTable table)
			throws BuildTableException {
	}
	
	//在body end 里实现整个处理过程
	final protected void buildHTMLBodyEnd(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("rows", pTable.getRows());		
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.BODY_END_ID, context));

	}
	
	final protected void buildHTMLRowBegin(HTMLRow row) throws BuildTableException {
	}
	final  protected void buildHTMLRowEnd(HTMLRow row) throws BuildTableException {
	}
	
	
	
}
