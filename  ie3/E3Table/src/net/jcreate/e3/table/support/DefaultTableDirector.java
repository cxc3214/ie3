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
package net.jcreate.e3.table.support;

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.I18nResourceProvider;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.TableContextSupport;
import net.jcreate.e3.table.TableDirector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultTableDirector implements TableDirector{

    private final Log logger = LogFactory.getLog( DefaultTableDirector.class );
	private boolean showCaption = true;	
	private boolean showHeader = true;
	private boolean showTopPanel = true;	
	private boolean showTopToolbar = true;
	private boolean showBottomToolbar = true;
	private boolean showBody = true;	
	private boolean showBottomPanel = true;
	
	private final TableContext tableContext;
	
	public DefaultTableDirector(){
		logger.debug("DefaultTableDirector未设置WebContext");
		tableContext = null;
	}
	
	public DefaultTableDirector(TableContext pTableContext){
		tableContext = pTableContext;
	}

	private void setObjectWebContext(Object pObj){
	      if ( pObj instanceof TableContextSupport ){
       	   TableContextSupport contextSupport = (TableContextSupport)pObj;
       	   if ( contextSupport.getTableContext() == null ){//未设置
       		   contextSupport.setTableContext(this.tableContext);
       	   }
          }
		
	}
	public void build(TableBuilder pBuilder, Table pTable) throws BuildTableException {
		   if ( pBuilder == null || pTable == null){
			   return;
		   }		   
		   
		   setObjectWebContext(pBuilder);
		   if ( logger.isDebugEnabled() ){
			   logger.debug("控制参数：");
			   logger.debug("showCaption=" + this.showCaption);
			   logger.debug("showHeader=" + this.showHeader);
			   logger.debug("showTopPanel=" + this.showTopPanel);
			   logger.debug("showTopToolbar=" + this.showTopToolbar);
			   logger.debug("showBottomToolbar=" + this.showBottomToolbar);
			   logger.debug("showBody=" + this.showBody);
			   logger.debug("showBody=" + this.showBody);
		   }
		   pBuilder.init(pTable);
		   pBuilder.buildBeginScript(pTable);
		   
		   pBuilder.buildDocBegin(pTable);
	       if ( this.showCaption ){
	    	   pBuilder.buildCaption(pTable);
	       }
	       if ( showTopPanel ){
	    	   pBuilder.buildTopPanel(pTable);
	       }
	       
	       if ( this.showTopToolbar){
	    	   pBuilder.buildTopToolbar(pTable);
	       }
	       pBuilder.buildTableBegin(pTable);
	       
	       if ( this.showHeader ){
	    	   Header header = pTable.getHeader();
	    	   if ( header == null ){
	    		   final String MSG = "header对象为空null";
	    		   logger.debug(MSG);
	    		   throw new NullPointerException(MSG);
	    	   }
		    	   pBuilder.buildHeaderBegin(header);
		    	   int size = header.getSize();
		    	   if ( size < 1 ){
		    		   final String MSG = "至少应该存在一个列!";
		    		   logger.debug(MSG);
		    		   throw new BuildTableException(MSG);
		    	   }
		    	   for(int i=0; i<size; i++){
		    		   Column cln = header.getColumn(i);
		    		   if ( cln == null ){
			    		   final String MSG = "第:" + (i+1) + "列对象为空!";
			    		   logger.debug(MSG);
			    		   throw new BuildTableException(MSG);
		    		   }
		    		   String titleKey = cln.getTitleKey();
		    		   I18nResourceProvider i18n = this.tableContext.getI18nResourceProvider();
		    		   if ( i18n != null ){
		    			  String title = i18n.getMessage(titleKey, cln.getTitle(), this.tableContext.getWebContext());
		    			  cln.setTitle(title);
		    		   }
		    		   pBuilder.buildColumnBegin(cln);
		    		   pBuilder.buildColumn(cln);
		    		   pBuilder.buildColumnEnd(cln);
		    	   }
		    	   pBuilder.buildHeaderEnd(header);
	       }
	       if ( this.showBody){
              int size = pTable.getRowSize();
              pBuilder.buildBodyBegin(pTable);
              for(int i=0; i<size; i++){
            	  Row row = pTable.getRow(i);
            	  if ( row == null ){
   	    		   final String MSG = "第:" + (i+1) + "行对象为空null";
	    		   logger.debug(MSG);
	    		   throw new NullPointerException(MSG);
            	  }
            	  pBuilder.buildRowBegin(row);
            	  int columSize = pTable.getColumnSize();
            	  for(int j=0; j<columSize; j++){
            		  Cell cell = row.getCell(j);
            		  if ( cell == null ){
          	    		   final String MSG = "第:" + (i+1) + "行,第:" + (j+1)+ "列单元格对象为空null";
        	    		   logger.debug(MSG);
        	    		   throw new NullPointerException(MSG);
            		  }
            		  Column cln = pTable.getColumn(j);//获取单元格修饰器
		    		   if ( cln == null ){
			    		   final String MSG = "第:" + (j+1) + "列对象为空!";
			    		   logger.debug(MSG);
			    		   throw new BuildTableException(MSG);
		    		   }            		  
            		  CellDecorator decorator = cell.getCellDecorator();
            		  if ( decorator == null ){//如果单元格对象绑定的修饰器为空，则取column的单元格修饰器
            			  decorator = cln.getCellDecorator();
            		  }
            		  if ( decorator != null){
					     try {
					    	setObjectWebContext(decorator);
							Object  decorateValue = decorator.decorate(cell.getValue(), cell);
							cell.setValue(decorateValue);
						  } catch (Exception e) {
							  final String MSG =
								  "执行修饰器：" + decorator.getClass().getName() + "失败!";
							  logger.debug(MSG, e);
						  }
            		  }
            		  pBuilder.buildCellBegin(cell);
            		  pBuilder.buildCell(cell);
            		  pBuilder.buildCellEnd(cell);
            	  }
            	  pBuilder.buildRowEnd(row);
              }//end for
              pBuilder.buildBodyEnd(pTable);
	       }//end build body

	       pBuilder.buildTableEnd(pTable);
	       
	       if ( this.isShowBottomToolbar() ){
	         pBuilder.buildBottomToolbar(pTable);
	       }
	       
	       if ( this.showBottomPanel){
	    	   pBuilder.buildBottomPanel(pTable);
	       }
	       pBuilder.buildDocEnd(pTable);
	       pBuilder.buildEndScript(pTable);
	       pBuilder.destory(pTable);	
		}

	public boolean isShowBody() {
		return showBody;
	}
	public void setShowBody(boolean showBody) {
		this.showBody = showBody;
	}
	public boolean isShowBottomPanel() {
		return showBottomPanel;
	}
	public void setShowBottomPanel(boolean showBottomPanel) {
		this.showBottomPanel = showBottomPanel;
	}
	public boolean isShowCaption() {
		return showCaption;
	}
	public void setShowCaption(boolean showCaption) {
		this.showCaption = showCaption;
	}
	public boolean isShowHeader() {
		return showHeader;
	}
	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}
	public boolean isShowTopPanel() {
		return showTopPanel;
	}
	public void setShowTopPanel(boolean showTopPanel) {
		this.showTopPanel = showTopPanel;
	}

	public boolean isShowBottomToolbar() {
		return showBottomToolbar;
	}

	public void setShowBottomToolbar(boolean showBottomToolbar) {
		this.showBottomToolbar = showBottomToolbar;
	}

	public boolean isShowTopToolbar() {
		return showTopToolbar;
	}

	public void setShowTopToolbar(boolean showTopToolbar) {
		this.showTopToolbar = showTopToolbar;
	}

}
