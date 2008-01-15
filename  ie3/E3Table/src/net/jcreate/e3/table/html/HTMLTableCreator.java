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

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.Column;
import net.jcreate.e3.table.Row;
import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.Header;
import net.jcreate.e3.table.support.AbstractTableCreator;

public class HTMLTableCreator extends AbstractTableCreator{

	protected Table createTable() {
		return new HTMLTable();
	}

	protected Header createHeader() {
		return new HTMLHeader();
	}

	protected Column createColumn() {
		return new HTMLColumn();
	}

	protected Row createRow() {
		return new HTMLRow();
	}

	protected Cell createCell() {
		return new HTMLCell();
	}

}
