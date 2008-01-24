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

package net.jcreate.e3.templateEngine.support;

import net.jcreate.e3.templateEngine.Template;

/**
 * 
 * @author 黄云辉
 *
 */
public class DefaultTemplate implements Template{
	
	/**
	 * 模板文件路径.
	 */
	private String resource = null;
	
	/**
	 * 输入编码方式,如果值为空，引擎会采用默认的编码方式
	 */
	private String inputEncoding = null;



	public String getInputEncoding() {
		if ( inputEncoding == null ){
			return System.getProperty("file.encoding");
		}else{
		  return inputEncoding;
		}
	}

	public void setInputEncoding(String inputEncoding) {
		this.inputEncoding = inputEncoding;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
