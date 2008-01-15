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
package net.jcreate.e3.table.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jcreate.e3.table.Table;
import net.jcreate.e3.table.TableCreator;
import net.jcreate.e3.table.TableDirector;
import net.jcreate.e3.table.decorator.DateDecorator;
import net.jcreate.e3.table.html.DefaultHTMLTableBuilder;
import net.jcreate.e3.table.html.HTMLTableCreator;
import net.jcreate.e3.table.model.CollectionDataModel;
import net.jcreate.e3.table.support.DefaultTableDirector;

public class Hello {
 public static void main(String[] args){
//	 DefaultHeader header = new DefaultHeader();
//	 //header.addColumn()
//	 TableDirector director = null;
//	 Table table = null;//构造出来的Table对象
//	 TableBuilder builder = null;
//	 director.build(builder, table);
//	 //System.out.println(builder.getScript());
	 String[] clns = new String[]{"userID", "userName", "birthday"};
	 List datas = new ArrayList();
	 Map data1 = new HashMap();
	 data1.put("userID", "huangyh");
	 data1.put("userName", "黄云辉");
	 data1.put("birthday", new Date());
	 datas.add(data1);
	 
	 Map data2 = new HashMap();
	 data2.put("userID", "e3");
	 data2.put("userName", "J2EE平台");
	 data2.put("birthday", new Date());	 
	 datas.add(data2);
	 
	 TableCreator creator = new HTMLTableCreator();
	 Table table = creator.createTable( new CollectionDataModel(datas), clns );
	 
	 DateDecorator dateDecorator = new DateDecorator();
	 dateDecorator.setPattern("yyyy-MM-dd");
	 table.getColumn("birthday").setCellDecorator(dateDecorator);
	 
	 TableDirector director = new DefaultTableDirector();
	 DefaultHTMLTableBuilder builder = new DefaultHTMLTableBuilder();
	 director.build(builder, table);
	 System.out.println( builder.getTableScript() );
	 
	 director.build(builder, table);
	 System.out.println( builder.getTableScript() );	 
 }
}
