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
package net.jcreate.e3.table.builder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.html.HTMLCell;
import net.jcreate.e3.table.html.HTMLColumn;
import net.jcreate.e3.table.html.HTMLColumnGroup;
import net.jcreate.e3.table.html.HTMLHeader;
import net.jcreate.e3.table.html.HTMLRow;
import net.jcreate.e3.table.html.HTMLTable;
import net.jcreate.e3.table.html.MessageHelper;
import net.jcreate.e3.table.html.util.JspUtils;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.xkins.XkinProcessor;

public class SkinHTMLTableBuilder extends DefaultTextTableBuilder{
	

	
	/*
	 <table>
	    <thead>
		  <tr>
		    <td>用户ID</td>
		    <td>用户名称</td>
		  </tr>
		</thead>
		<tbody>
		  <tr>
		    <td>huangyh</td>
	        <td>黄云辉</td>
		  </tr>
		  
		</tbody>
	  </table>
	  */
	
	
	protected void buildHTMLBodyBegin(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);		
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.BODY_BEGIN_ID, context));
	}

	protected void buildHTMLBodyEnd(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);		
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.BODY_END_ID, context));
	}

	protected void buildHTMLBottomToolbar(HTMLTable pTable) throws BuildTableException {
		PageInfo pageInfo = pTable.getPageInfo();
		if ( pageInfo == null ){
			return;
		}
		Context context = new DefaultContext();
		context.put("table", pTable);		
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("pageInfo", pageInfo);
		appendScript(getTemplateValue(TableConstants.BOTTOM_NAVIGATION_ID, context));
	}
    private boolean isEmpty(String pValue ){
    	if ( pValue == null ){
    		return true;
    	}
    	if ( "".equals(pValue.trim()) ){
    		return true;
    	}
    	return false;
    }
	protected void buildHTMLCaption(HTMLTable pTable) throws BuildTableException {
		String caption = pTable.getCaption();
		if ( isEmpty(caption) ){
			return;
		}
		Context context = new DefaultContext();
		context.put("table", pTable);		
		context.put("caption", caption);
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.CAPTION_ID, context));
	}
	
	final protected void appendScript(String pScript){
		if ( pScript == null ){
			return;
		}
		tableScript.append(pScript);
	}

	protected void buildHTMLCell(HTMLCell pCell) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("cell", pCell);		
		context.put("cellValue", pCell.getValue());
		context.put("row", pCell.getRow());		
		context.put("column", pCell.getColumn());
		context.put("table", pCell.getRow().getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.CELL_ID, context));
	}

	protected void buildHTMLCellBegin(HTMLCell pCell) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("cell", pCell);		
		context.put("cellValue", pCell.getValue());
		context.put("row", pCell.getRow());
		context.put("column", pCell.getColumn());
		context.put("table", pCell.getRow().getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.CELL_BEGIN_ID, context));

	}

	protected void buildHTMLCellEnd(HTMLCell pCell) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("cell", pCell);		
		context.put("cellValue", pCell.getValue());
		context.put("row", pCell.getRow());
		context.put("column", pCell.getColumn());
		context.put("table", pCell.getRow().getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		appendScript(getTemplateValue(TableConstants.CELL_END_ID, context));

	}
	

	protected void buildHTMLColumn(HTMLColumn pColumn) throws BuildTableException {
		String showTitle = null;
		String title = pColumn.getTitle();
		if ( title == null || "".equals(title.trim())){
			showTitle = pColumn.getProperty();	
		}else{
			showTitle = pColumn.getTitle();
		}
		Context context = new DefaultContext();
		context.put("title", showTitle);
		context.put("column", pColumn);
		context.put("table", pColumn.getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.COLUMN_ID, context));
			
	}

	protected void buildHTMLColumnBegin(HTMLColumn pColumn) throws BuildTableException {
		String showTitle = null;
		String title = pColumn.getTitle();
		if ( title == null || "".equals(title.trim())){
			showTitle = pColumn.getProperty();	
		}else{
			showTitle = pColumn.getTitle();
		}
		Context context = new DefaultContext();
		context.put("title", showTitle);
		context.put("column", pColumn);
		context.put("table", pColumn.getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.COLUMN_BEGIN_ID, context));
	}

	protected void buildHTMLColumnEnd(HTMLColumn pColumn) throws BuildTableException {
		String showTitle = null;
		String title = pColumn.getTitle();
		if ( title == null || "".equals(title.trim())){
			showTitle = pColumn.getProperty();	
		}else{
			showTitle = pColumn.getTitle();
		}
		Context context = new DefaultContext();
		context.put("title", showTitle);
		context.put("column", pColumn);
		context.put("table", pColumn.getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.COLUMN_END_ID, context));
	}

	protected void buildHTMLEndScript(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.END_SCRIPT_ID, context));
	}

	protected void buildHTMLHeaderBegin(HTMLHeader pHeader) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("header", pHeader);
		context.put("table", pHeader.getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.HEADER_BEGIN_ID, context));

	}

	protected void buildHTMLHeaderEnd(HTMLHeader pHeader) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("header", pHeader);
		context.put("table", pHeader.getTable());
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.HEADER_END_ID, context));

	}

	protected void buildHTMLBeginScript(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());
		PageInfo pageInfo = pTable.getPageInfo();
		context.put("pageInfo", pageInfo);
		appendScript(getTemplateValue(TableConstants.BEGIN_SCRIPT_ID, context));
	}

	protected void buildHTMLRowBegin(HTMLRow pRow) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pRow.getTable());
		context.put("row", pRow);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.ROW_BEGIN_ID, context));
	}

	protected void buildHTMLRowEnd(HTMLRow pRow) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pRow.getTable());
		context.put("row", pRow);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.ROW_END_ID, context));
	}


   protected void buildHTMLDocBegin(HTMLTable pTable) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.DOC_BEGIN_ID, context));
   }
   
   protected void buildHTMLDocEnd(HTMLTable pTable) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.DOC_END_ID, context));
   }
	
	protected void buildHTMLTableBegin(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.TABLE_BEGIN_ID, context));
		
	}
	protected void buildHTMLColumnGroupsBegin(HTMLTable pTable) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("table", pTable);
		appendScript(getTemplateValue(TableConstants.COLUMN_GROUPS_BEGIN_ID, context));
	}
	
	protected void buildHTMLColumnGroupBegin(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("columnGroup", pColumnGroup);
		appendScript(getTemplateValue(TableConstants.COLUMN_GROUP_BEGIN_ID, context));
	}
	
	protected void buildHTMLColumnGroup(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("columnGroup", pColumnGroup);
		context.put("title", pColumnGroup.getTitle());
		appendScript(getTemplateValue(TableConstants.COLUMN_GROUP_ID, context));
		
	}
	
	protected void buildHTMLNoDataRow(HTMLTable pTable) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("table", pTable);
		context.put("columnNum", pTable.getColumnSize());
		context.put("noDataTip", pTable.getNoDataTip());
		appendScript(getTemplateValue(TableConstants.NO_DATA_ROW_ID, context));
	}
	
	
	protected void buildHTMLColumnGroupEnd(HTMLColumnGroup pColumnGroup) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("columnGroup", pColumnGroup);
		appendScript(getTemplateValue(TableConstants.COLUMN_GROUP_END_ID, context));
	}
	
	protected void buildHTMLColumnGroupsEnd(HTMLTable pTable) throws BuildTableException{
		Context context = new DefaultContext();
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		context.put("table", pTable);
		appendScript(getTemplateValue(TableConstants.COLUMN_GROUPS_END_ID, context));
	}
	protected void buildHTMLTableEnd(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("contextPath", this.getTableContext().getWebContext().getContextPath());		
		appendScript(getTemplateValue(TableConstants.TABLE_END_ID, context));

	}

	protected void buildHTMLParamsForm(HTMLTable pTable) throws BuildTableException {
		Context context = new DefaultContext();
		context.put("table", pTable);		
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("params", pTable.getParams());
		if (  pTable.getParamsFormVar() != null ){//认为设置了paramForms,需要把参数值导出
		  	//导出参数变量,解决form嵌套问题
			JspUtils.setAttribute(
					tableContext.getWebContext(),
					pTable.getParamsFormVar(),
					getTemplateValue(TableConstants.PARAMS_FORM_ID, context), 
				    pTable.getParamsFormScope());
			//super..
		}else{
		 appendScript(getTemplateValue(TableConstants.PARAMS_FORM_ID, context));
		}
	}
	protected void buildHTMLTopToolbar(HTMLTable pTable) throws BuildTableException {
		PageInfo pageInfo = pTable.getPageInfo();
		if ( pageInfo == null ){
			return;
		}		
		Context context = new DefaultContext();
		context.put("table", pTable);
		context.put("webContext", this.getTableContext().getWebContext());
		context.put("pageInfo", pageInfo);
		appendScript(getTemplateValue(TableConstants.TOP_NAVIGATION_ID, context)) ;
	}

	/**
	 * 获取模板值.
	 * @param pTemplateID
	 * @param pContext
	 * @return
	 */
    final protected String getTemplateValue(String pTemplateID, Context pContext){
    	XkinProcessor processor = getXkinProcessor(pTemplateID);
    	if ( processor == null ){
    		return null;
    	}
    	processor.addParameters(pContext.getParameters());
    	processor.addParameter("messageResource",new MessageHelper(this.getTableContext()) );
    	return processor.processContent();
    }
	
	/**
	 * 获取模板请求处理器.
	 * @param pTemplateID
	 * @return
	 */
	private XkinProcessor getXkinProcessor(String pTemplateID){
		    XkinProcessor result = null;
		   Object backingObject = this.getTableContext().getWebContext().getBackingObject();
		   if ( backingObject instanceof PageContext ){
			   result = new XkinProcessor(table.getSkin(), pTemplateID, (PageContext)backingObject); 
		   } else if ( backingObject instanceof HttpServletRequest ){
			   result = new XkinProcessor(table.getSkin(), pTemplateID, (HttpServletRequest)backingObject, null);
		   }else{
			  throw  new java.lang.IllegalStateException();   
		   }
           return result;
		
	}
}
