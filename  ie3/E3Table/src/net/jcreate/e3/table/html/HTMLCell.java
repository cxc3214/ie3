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

import net.jcreate.e3.table.support.DefaultCell;

public class HTMLCell extends DefaultCell implements Attributeable{
    
	public HTMLCell() {
		super();
	}

	public HTMLCell(Object pValue) {
		super(pValue);
	}
	private Attributes attributes = new Attributes();
	public void setAttribute(String name, String value) {
		attributes.setAttribute(name, value);
	}
	
	public String getAttributesAsString(){
		return this.attributes.geAttributesAsString();
	}

}
