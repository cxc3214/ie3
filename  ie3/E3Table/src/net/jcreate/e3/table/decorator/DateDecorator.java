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

import java.text.SimpleDateFormat;
import java.util.Date;

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.CellDecorator;

/**
 * 格式化日期
 * @author 黄云辉
 *
 */
public class DateDecorator extends AbstractDecorator{

	private String pattern;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public Object decorate(Object pValue, Cell pCell) throws Exception {
        if ( pValue instanceof Date == false ){
        	return pValue;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);
        return sdf.format((Date)pValue);
	}

}
