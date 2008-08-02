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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.jcreate.e3.table.BuildTableException;
import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.ColumnGroup;
import net.jcreate.e3.table.DirectorSupport;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.I18nResourceProvider;
import net.jcreate.e3.table.MessageSource;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableContext;

public class DefaultTableDirector extends AbstractTableDirector{

	public DefaultTableDirector() {
		super();
	}

	public DefaultTableDirector(TableContext tableContext) {
		super(tableContext);
	}

	public void build(TableBuilder pBuilder, Table pTable) throws BuildTableException {
		   if ( pBuilder == null || pTable == null){
			   return;
		   }	

		   setObjectWebContext(pBuilder);
		   if ( pBuilder instanceof DirectorSupport ){
			 ((DirectorSupport)pBuilder).setTableDirector(this);
		   }
		   if ( logger.isDebugEnabled() ){
			   logger.debug("控制参数：");
			   logger.debug("showCaption=" + this.showCaption);
			   logger.debug("showHeader=" + this.showHeader);
			   logger.debug("showTopPanel=" + this.showTopPanel);
			   logger.debug("showTopToolbar=" + this.showTopToolbar);
			   logger.debug("showBottomToolbar=" + this.showBottomToolbar);
			   logger.debug("showBody=" + this.showBody);
		   }
		   pBuilder.init(pTable);
		   pBuilder.buildBeginScript(pTable);
		   I18nResourceProvider i18n = this.tableContext.getI18nResourceProvider();
		   
		   
		   /**
		    * @todo: 这里的代码有点乱，需要整理下
		    */
			   String noDataTip = null;
			   if ( i18n != null ){
				   noDataTip = i18n.getMessage(pTable.getNoDataTipKey(), pTable.getNoDataTip(), this.tableContext.getWebContext());		   
			   }
			   if ( noDataTip == null ){
				   Locale locale = null;
				   if ( i18n != null ){
					   locale = i18n.resolveLocale( this.tableContext.getWebContext() ); 
				   }
				   MessageSource message = tableContext.getMessageSource();
				   if ( message != null ){
				     noDataTip = message.getMessage(TableConstants.NO_DATA_TIP_KEY, null, "", locale);
				   }
			   }			   
			  pTable.setNoDataTip(noDataTip);
		   		    
		   pBuilder.buildDocBegin(pTable);
	       if ( this.showCaption ){
    		   
    		   if ( i18n != null ){
    			  String title = i18n.getMessage(pTable.getCaptionKey(), pTable.getCaption(), this.tableContext.getWebContext());
    			  pTable.setCaption(title);
    		   }
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
	    	   int size = header.getSize();
	    	   if ( size < 1 ){
	    		   final String MSG = "至少应该存在一个列!";
	    		   logger.debug(MSG);
	    		   throw new BuildTableException(MSG);
	    	   }
	    	   
	    	   
	    	   List columnGroupsList = this.getColumnGroups(header);
	    	   for(int i=0; i<columnGroupsList.size(); i++){
	    		   Set columnGroups = (Set)columnGroupsList.get(i);
	    		   java.util.Iterator groupIterator = columnGroups.iterator();
	    		   pBuilder.buildColumnGroupsBegin(pTable);
	    		   while( groupIterator.hasNext() ){
	    			   ColumnGroup group = (ColumnGroup)groupIterator.next();
		    		   if ( i18n != null ){
			    			  String title = i18n.getMessage(group.getTitleKey(), group.getTitle(), this.tableContext.getWebContext());
			    			  group.setTitle(title);
			    	   }	    			   
	    			   pBuilder.buildColumnGroupBegin(group);
	    			   pBuilder.buildColumnGroup(group);
	    			   pBuilder.buildColumnGroupEnd(group);
	    		   }
	    		   pBuilder.buildColumnGroupsEnd(pTable);
	    	   }
	    	   
		    	   pBuilder.buildHeaderBegin(header);
		    	   for(int i=0; i<size; i++){
		    		   Column cln = header.getColumn(i);
		    		   if ( cln == null ){
			    		   final String MSG = "第:" + (i+1) + "列对象为空!";
			    		   logger.debug(MSG);
			    		   throw new BuildTableException(MSG);
		    		   }
		    		   String titleKey = cln.getTitleKey();
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
        	    		   continue;
        	    		   //throw new NullPointerException(MSG);
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

	       int size = pTable.getRowSize();
	       if ( size == 0 ){
	    	   pBuilder.buildNoDataRow(pTable);
	       }
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

    private List getColumnGroups(Header pHeader){
    	if ( pHeader == null ){
    		return java.util.Collections.EMPTY_LIST;
    	}
    	List result = new ArrayList();
    	Map temp = new java.util.TreeMap();
    	List columns = pHeader.getColumns();
    	if ( columns == null ){
    		return java.util.Collections.EMPTY_LIST;
    	}
    	for(int i=0; i<columns.size(); i++){
    		Column column = (Column)columns.get(i);
    		ColumnGroup parentGroup = column.getColumnGroup();
    		while( parentGroup != null ){
    			int level = parentGroup.getLevel();
    			if ( temp.containsKey(new Integer(level)) == false ){
    				Set groupSet = new java.util.LinkedHashSet();
    				temp.put(new Integer(level), groupSet);
    			}
    			Set groupSet = (Set)temp.get(new Integer(level));
    			ColumnGroup group = parentGroup;
    			groupSet.add(group);
    			parentGroup = parentGroup.getParent();
    		}    		
    	}
    	java.util.Iterator keyIterator = temp.keySet().iterator();
    	while( keyIterator.hasNext() ){
    		Object key = keyIterator.next();
    		Set values = (Set)temp.get(key);
    		result.add(values);
    	}    	
    	return result;
    }
 
}
