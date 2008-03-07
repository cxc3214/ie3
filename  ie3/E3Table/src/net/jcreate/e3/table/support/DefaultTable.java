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

import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.Header;

public class DefaultTable implements Table{

	private String caption;
	private String captionKey;
	private String noDataTip;
	private String noDataTipKey;
	private Header header;
	
	private List rows = new ArrayList();
	
	public List getColumns() {
		return header.getColumns();
	}
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCaptionKey() {
		return captionKey;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public String getNoDataTip() {
		return noDataTip;
	}

	public void setNoDataTip(String noDataTip) {
		this.noDataTip = noDataTip;
	}

	public String getNoDataTipKey() {
		return noDataTipKey;
	}

	public void setNoDataTipKey(String noDataTipKey) {
		this.noDataTipKey = noDataTipKey;
	}


	public Column getColumn(String pProperty) {
		return header.getColumn(pProperty);
	}
	public void addColumn(Column pColumn){
		header.addColumn(pColumn);	
	}
	
	public void removeColumn(String pProperty){   
		header.removeColumn(pProperty);	
	}

	public Column getColumn(int pIndex) {
		return header.getColumn(pIndex);
	}

	public List getRows() {
		return new ArrayList( rows );
	}

	public Row getRow(int pRowIndex) {
		return (Row)rows.get(pRowIndex);
	}
	
	public void addRow(Row pRow){
		this.rows.add(pRow);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header tableHeader) {
		this.header = tableHeader;
	}


	public int getRowSize() {
		return rows.size();
	}


	public int getColumnSize() {
		return this.header.getSize();
	}

}
