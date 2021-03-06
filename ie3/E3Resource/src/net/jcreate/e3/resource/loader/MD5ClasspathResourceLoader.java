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
package net.jcreate.e3.resource.loader;

import net.jcreate.e3.resource.util.MD5;

/**
 * 对uri进行md5运算,计算出资源的实际路径.这种资源装载方式
 * 是专门用来读取 MD5FileNameTask 处理过的资源.
 * @author 黄云辉
 *
 */
public class MD5ClasspathResourceLoader extends ClasspathResourceLoader{
	public static final String FILE_PREFIX = "E3Res_";
	public static final String FILE_POSTFIX = ".e3";
	private MD5 m = new MD5();
	/**
	 * 如果要进行url转换处理，覆盖该方法.
	 * @param pUri
	 * @return
	 */
	protected String urlMapping(String pUri){
    	String filename = pUri.replace('\\','/');           // 4
		return FILE_PREFIX + m.getMD5ofStr(filename) + FILE_POSTFIX;
	}

}
