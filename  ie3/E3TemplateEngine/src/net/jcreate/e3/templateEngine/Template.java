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

package net.jcreate.e3.templateEngine;

/**
 * 模板. 我们只支持文件模板. TODO: 考虑支持其他字符串模板
 */
public interface Template {
	
	public String getInputEncoding();
	public void setInputEncoding(String inputEncoding);

	/**
	 * 字符串资源前缀
	 */
	public static final String STR_PREFIX = "str:";
	
	public String getResource();
	public void setResource(String resource);
}
