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
package net.jcreate.e3.table.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.PageInfo;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.templateEngine.Context;
import net.jcreate.e3.templateEngine.support.DefaultContext;
import net.jcreate.xkins.XkinProcessor;

public class SkinHTMLTableBuilder extends AbstractHTMLTableBuilder{
	

	
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
	
	private void appendScript(String pScript){
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
   	
   	   
//	     <form name="userTableForm" method="post" action="uri">
//		     <input type="hidden" name="start" value="10"/>
//		     <input type="hidden" name="pagesize" value="10"/>
//			 <input type="hidden" name="sortColumn" value="userID"/>
//			 <input type="hidden" name="sortDir" value="asc"/>
//		  </form>    	
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
		appendScript(getTemplateValue(TableConstants.PARAMS_FORM_ID, context));
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
    private String getTemplateValue(String pTemplateID, Context pContext){
    	XkinProcessor processor = getXkinProcessor(pTemplateID);
    	if ( processor == null ){
    		return null;
    	}
    	processor.addParameters(pContext.getParameters());
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
//	private static Map skins = new HashMap();
//	private Skin skin = null;
//	protected void init(HTMLTable pTable) {
//		   if ( tableScript == null )
//			     tableScript = new StringBuffer(bufferSize);
//			   else
//			     tableScript.delete(0, tableScript.length());
//		String skinID = pTable.getSkin();
//		if ( skinID == null ){
	//		skinID = TableConstants.DEFAULT_SKIN;
//		}
//		String path = getSkinPath(skinID);
//		  if ( skins.containsKey(path) == false ){
//			  synchronized(this){
//				  /**
//				   * 说明：: 这里采用最简单的锁策略，因为这种碰撞几率很小，这样做不会有什么问题
//				   */
//			     InputStream is = getSkinInputStream(path, this.tableContext.getWebContext());
//			     SkinLoader skinLoader = new SkinLoader();
//			     Skin result = skinLoader.load(is);
//			     skins.put(path, result);
//			  }
//		  }
//		  skin = (Skin)skins.get(path);
//		
//	}
	
//	 protected InputStream getSkinInputStream(final String pSkinDefFile, final WebContext pWebContext) throws LoadSkinException{
//		  InputStream result = pWebContext.getResourceAsStream(pSkinDefFile);
//		  if ( result == null ){
//			  result = this.getClass().getClassLoader().getResourceAsStream(pSkinDefFile);
//		  }
//		  if ( result == null ){
//			  throw new LoadSkinException("没有找到皮肤定义文件:" + pSkinDefFile  );
//		  }
//		  return result;
//	  }	
//	
//	protected String getSkinPath(String pTheme){
//		 String path = "e3/table/skins/" + pTheme + ".e3table.xml";
//		 return path;
//	}
}
