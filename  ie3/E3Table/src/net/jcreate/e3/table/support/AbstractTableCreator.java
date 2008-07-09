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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.CreateTableException;
import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.TableCreator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractTableCreator implements TableCreator{

	private final Log logger = LogFactory.getLog( AbstractTableCreator.class );
    public Table createTable(
    		final DataModel pDataModel , 
    		final String[] pProperties,
    		final String[] pBeanProperties
    		) throws CreateTableException{

		if (pDataModel == null || pProperties == null ){
			return null;
		}
		if ( pProperties.length < 1 ){
			final String MSG =
				"至少需要存在一列!";
			logger.error(MSG);
			throw new IllegalArgumentException(MSG);
		}
		Table result = createTable();
		if ( result  == null ){
			return null;
		}
		Header header = createHeader();
		if (header == null ){
			final String MSG =
				"表头对象为空null";
			logger.error(MSG);
			throw new IllegalArgumentException(MSG);
		}
		header.setTable(result);
		result.setHeader(header);
		Map columnsMap = new HashMap();
        for(int i=0; i<pProperties.length; i++){
        	String property = pProperties[i];
        	Column cln = createColumn();        	
        	if (cln == null ){
    			final String MSG =
    				"列:" + property + "对象为空null";
    			logger.error(MSG);
    			throw new IllegalArgumentException(MSG);
        	}
        	cln.setColumnIndex(i);
        	cln.setProperty(property);        	
        	cln.setTable(result);
        	columnsMap.put(property, cln);
        	header.addColumn(cln);
        	
        }
        
        int rowIndex = 0;
        while( pDataModel.hasNext() ){
        	Object item = pDataModel.next();
        	if ( item == null ){
        		continue;
        	}
        	Row row = createRow();
        	if ( row == null ){
        		continue;
        	}
        	//row.setRowIndex(rowIndex);
        	row.setTable(result);
        	row.setRowObject(item);
        	result.addRow(row);

            for(int i=0; i<pBeanProperties.length; i++){
            	String beanProperty = pBeanProperties[i];
            	Object cellValue = pDataModel.getCellValue(item, beanProperty);
            	Cell cell = createCell();
            	cell.setRow(row);
            	cell.setValue(cellValue);
            	String columnName = pProperties[i];
            	cell.setColumn((Column)columnsMap.get(columnName));
            	row.addCell(cell);
            }
        	rowIndex++;                    	
        }
		return result;
	}
	protected abstract Table  createTable();
	protected abstract Header createHeader();	
	protected abstract Column createColumn();
    protected abstract Row  createRow();
	protected abstract Cell createCell();
	
	

}
