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
package net.jcreate.e3.table.decorator;

import java.util.ArrayList;

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.CellDecorator;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.WebContext;
import net.jcreate.e3.table.TableContextSupport;

public class CompositeCellDecorator implements CellDecorator, TableContextSupport {
	
	private ArrayList decorators = new ArrayList();
	
			private TableContext webContext;

			public TableContext getTableContext() {
				return webContext;
			}

			public void setTableContext(TableContext pTableContext) {
				this.webContext = pTableContext;
			}
    public int getSize(){
    	return this.decorators.size();
    }
	public void addCellDecorator(CellDecorator pCellDecorator){
		decorators.add(pCellDecorator);
	}
	
	public void removeCellDecorator(CellDecorator pCellDecorator){
		decorators.remove(pCellDecorator);
	}
	
	public void clear(){
		decorators.clear();
	}

	final public Object decorate(Object pValue, Cell pCell) throws Exception {
		Object needDecorateValue = pValue;
		for(int i=0; i<decorators.size(); i++){
			CellDecorator cellDecorator = (CellDecorator)decorators.get(i);
            if ( cellDecorator instanceof TableContextSupport){
            	TableContextSupport support = (TableContextSupport)cellDecorator;
            	support.setTableContext(this.webContext);
            }
			Object decoratedValue = cellDecorator.decorate(needDecorateValue, pCell);
			needDecorateValue = decoratedValue;
		}
		return needDecorateValue;
	}
}
