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
package net.jcreate.e3.table;

import java.util.Locale;

/**
 * 来自spring
 * @author 黄云辉
 *
 */
public class NoSuchMessageException extends TableException{

	private static final long serialVersionUID = 1L;

	public NoSuchMessageException() {
		super();
	}
	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 * @param locale locale that was used to search for the code within
	 */
	public NoSuchMessageException(String code, Locale locale) {
		super("No message found under code '" + code + "' for locale '" + locale + "'.");
	}

	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 */
	public NoSuchMessageException(String code) {
		super("No message found under code '" + code + "' for locale '" + Locale.getDefault() + "'.");
	}

	public NoSuchMessageException(String message, Throwable cause) {
		super(message, cause);
	}


	public NoSuchMessageException(Throwable cause) {
		super(cause);
	}

}