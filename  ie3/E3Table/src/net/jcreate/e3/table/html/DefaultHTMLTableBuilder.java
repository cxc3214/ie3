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

import java.util.List;

import net.jcreate.e3.table.BuildTableException;

public class DefaultHTMLTableBuilder extends AbstractHTMLTableBuilder{

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
		tableScript.append("<tbody>").append(ENTER);
	}

	protected void buildHTMLBodyEnd(HTMLTable pTable) throws BuildTableException {
		tableScript.append("</tbody>").append(ENTER);		
	}

	protected void buildHTMLBottomToolbar(HTMLTable pTable) throws BuildTableException {
	}

	protected void buildHTMLCaption(HTMLTable pTable) throws BuildTableException {
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='100%' border='0'>").append(ENTER);
		sb.append("  <tr>").append(ENTER);
		sb.append("    <td>").append(ENTER);
		sb.append("      <div class='caption'>").append(ENTER);
		sb.append(            pTable.getCaption() == null ? "" : pTable.getCaption()).append(ENTER);
		sb.append("      </div>").append(ENTER);
		sb.append("    </td>").append(ENTER);
		sb.append("   </tr>").append(ENTER);
		sb.append("</table>").append(ENTER);
		tableScript.append(sb.toString());
	}

	protected void buildHTMLCell(HTMLCell pCell) throws BuildTableException {
		Object cellValue = pCell.getValue();
		
		tableScript.append(cellValue == null ? "" : cellValue);		
	}

	protected void buildHTMLCellBegin(HTMLCell pCell) throws BuildTableException {
		tableScript.append("<td>");
	}

	protected void buildHTMLCellEnd(HTMLCell pCell) throws BuildTableException {
		tableScript.append("</td>");
	}
	

	protected void buildHTMLColumn(HTMLColumn pColumn) throws BuildTableException {
		String showTitle = null;
		String title = pColumn.getTitle();
		if ( title == null || "".equals(title.trim())){
			showTitle = pColumn.getProperty();	
		}else{
			showTitle = pColumn.getTitle();
		}
        final String contextPath = this.getTableContext().getWebContext().getContextPath();
		tableScript.append("<a href='#'>").append(showTitle).append("&nbsp;<IMG      src='").
		append(contextPath).append("/e3/table/img/sort(-1).png'     border=0></a>");
		      
			
	}

	protected void buildHTMLColumnBegin(HTMLColumn pColumn) throws BuildTableException {
		tableScript.append("<td>");	
	}

	protected void buildHTMLColumnEnd(HTMLColumn pColumn) throws BuildTableException {
		tableScript.append("</td>");		
	}

	protected void buildHTMLEndScript(HTMLTable pTable) throws BuildTableException {
	}

	protected void buildHTMLHeaderBegin(HTMLHeader pHeader) throws BuildTableException {
		tableScript.append("<thead><tr class='head'>");
	}

	protected void buildHTMLHeaderEnd(HTMLHeader pHeader) throws BuildTableException {
		tableScript.append("</tr></thead>");
	}

	protected void buildHTMLBeginScript(HTMLTable pTable) throws BuildTableException {
	}

	protected void buildHTMLRowBegin(HTMLRow pRow) throws BuildTableException {
		tableScript.append("<tr class='body'>");		
	}

	protected void buildHTMLRowEnd(HTMLRow pRow) throws BuildTableException {
		tableScript.append("</tr>");
	}


    protected void buildHTMLDocBegin(HTMLTable pTable) throws BuildTableException{
    	String theme = pTable.getSkin();
    	if ( theme == null ){
    		theme = "e3table";
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append("<table class='").append(theme).append("' align='center'>").append(ENTER);
    	sb.append("  <tr>").append(ENTER);
    	sb.append("    <td>").append(ENTER);
    	final String tableID = pTable.getId();    	
    	final String uri = this.tableContext.getWebContext().getContextPath() + pTable.getUri();
    	sb.append("<form name='").append(tableID).append("Form' method='post' action='").append(uri).append("'>").append(ENTER);
    	List params = pTable.getParams();
    	for(int i=0; i<params.size(); i++){
    		HTMLParam param = (HTMLParam)params.get(i);
    		sb.append("<input type='hidden' name='").append(param.getName()).append("' value='").append(param.getValue()).append("' />");
    	}
    	sb.append("</form>").append(ENTER);
    	
    	tableScript.append(sb.toString());    	
    	
    	   
//	     <form name="userTableForm" method="post" action="uri">
//		     <input type="hidden" name="start" value="10"/>
//		     <input type="hidden" name="pagesize" value="10"/>
//			 <input type="hidden" name="sortColumn" value="userID"/>
//			 <input type="hidden" name="sortDir" value="asc"/>
//		  </form>    	
    }
    
    protected void buildHTMLDocEnd(HTMLTable pTable) throws BuildTableException{
    	StringBuffer sb = new StringBuffer();
    	sb.append("    </td>").append(ENTER);
    	sb.append("  </tr>").append(ENTER);
    	sb.append("</table>").append(ENTER);
    	tableScript.append(sb.toString());    	
    }
	
	protected void buildHTMLTableBegin(HTMLTable pTable) throws BuildTableException {
		tableScript.append("<table  class='listPage' cellspacing='1'>");		
	}

	protected void buildHTMLTableEnd(HTMLTable pTable) throws BuildTableException {
		tableScript.append("</table>").append(ENTER);
	}

	protected void buildHTMLTopToolbar(HTMLTable pTable) throws BuildTableException {
	}

}
