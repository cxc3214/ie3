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
package net.jcreate.home.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import net.jcreate.e3.core.E3BeanFactoryBuilder;

import org.springframework.beans.factory.BeanFactory;

public class HomeBeanFactory {
	private static BeanFactory beanFactory = null;
	
	static{
		beanFactory = E3BeanFactoryBuilder.build("net/jcreate/home/config/Global.spring.xml");
	}
	
	private HomeBeanFactory() {

	}

	/**
	 * 获取Bean对象
	 * 
	 * @param pBeanID 
	 * @return
	 */
	public static Object getBean(String pBeanID) {
		return beanFactory.getBean(pBeanID);
	}
	 
	public static void main(String[] args){
		DataSource ds = (DataSource)HomeBeanFactory.getBean("dataSource");
		try {
			System.out.println( ds.getConnection().getCatalog() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}