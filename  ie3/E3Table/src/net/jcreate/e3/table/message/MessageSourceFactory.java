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
package net.jcreate.e3.table.message;

import java.io.InputStream;

import net.jcreate.e3.table.MessageSource;
import net.jcreate.e3.table.support.TableConstants;

/**
 * 获取e3table MessageSource,现在采用写死的办法，以后考虑允许业务系统定置属性文件路径
 * @author 黄云辉
 *
 */
public class MessageSourceFactory {
	
	private static MessageSource  messageSource = null;
	
	static{
		ResourceBundleMessageSource tempMessageSource = new ResourceBundleMessageSource();
		tempMessageSource.setBasename(TableConstants.CHILD_MESSAGE_SOURCE_BASE);	
		ResourceBundleMessageSource parentMessageSoruce = new ResourceBundleMessageSource();
		parentMessageSoruce.setBasename(TableConstants.PARENT_MESSAGE_SOURCE_BASE);
		tempMessageSource.setParentMessageSource(parentMessageSoruce);
		messageSource = tempMessageSource;

	}
    private MessageSourceFactory(){
	  
  }
  
  public static MessageSource getInstance(){
	 return messageSource; 
  }
}
