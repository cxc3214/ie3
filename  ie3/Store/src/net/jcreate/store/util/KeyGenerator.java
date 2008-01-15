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
package net.jcreate.store.util;

import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;

public abstract class KeyGenerator {
	private static IdentifierGenerator gen = new UUIDHexGenerator();
	
	static{
    	Properties props = new Properties();	
   	 props.setProperty("separator", "");
   	( (Configurable) gen ).configure(Hibernate.STRING, props, null);
		
	}
	/**
	 * ID统一加上e3,这样ID就可以作为合法的js变量，因为直接生成的ID可能以数字开头.
	 */
	public static final String PREFIX = "e3";
  public static String getKey(){
		return PREFIX + (String)gen.generate(null, null);  
  }
  
  public static void main(String[] args){
	  System.out.println(KeyGenerator.getKey());
	  System.out.println(KeyGenerator.getKey().length());
  }
}
