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
package net.jcreate.e3.samples.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.table.DataModel;
import net.jcreate.e3.table.NavRequest;
import net.jcreate.e3.table.html.HTMLTableHelper;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.ext.ExtTreeBuilder;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.DefaultTreeModel;
import net.jcreate.e3.tree.support.RequestUtil;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.web.DispatchServlet;

public class TableServlet extends DispatchServlet{
	
	public void showNavTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		pRequest.getRequestDispatcher("/e3/samples/table/NavTree.jsp").forward(pRequest,pResponse);
	}
	
	public void showSimpleTable(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		List users = this.getUsers();
		pRequest.setAttribute("users", users);
		pRequest.getRequestDispatcher("/e3/samples/table/SimpleTable.jsp").forward(pRequest,pResponse);
	}
	public void showDecoratorTable(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		List users = this.getUsers();
		pRequest.setAttribute("users", users);
		pRequest.getRequestDispatcher("/e3/samples/table/DecoratorTable.jsp").forward(pRequest,pResponse);
	}
	
	public void showSkinTable(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		List users = this.getUsers();
		pRequest.setAttribute("users", users);
		System.out.println("业务参数:" +  pRequest.getParameter("skin") );
		pRequest.getRequestDispatcher("/e3/samples/table/SkinTable.jsp").forward(pRequest,pResponse);
	}
	

	public void showPageTable(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		List users = this.getUsers();
		NavRequest navRequest = HTMLTableHelper.getNavRequest("userTable", pRequest);
		navRequest.setPageSize(2);
		DataModel dataModel = HTMLTableHelper.getDataModel(navRequest, users);
		pRequest.setAttribute("users", dataModel);
		pRequest.getRequestDispatcher("/e3/samples/table/PageTable.jsp").forward(pRequest,pResponse);
	}
	
	public void showStateTable(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		List users = this.getUsers();
		NavRequest navRequest = HTMLTableHelper.getNavRequest("userTable", pRequest);
		DataModel dataModel = HTMLTableHelper.getDataModel(navRequest, users);
		pRequest.setAttribute("users", dataModel);
		//带上参数 _e3State=true 就可以记住状态
		pRequest.getRequestDispatcher("/e3/samples/table/StateTable.jsp").forward(pRequest,pResponse);
	}

	private List getUsers(){
		List result = new ArrayList();
		User a = new User();
		a.setUserID("huangyh");
		a.setUserName("黄云辉");
		a.setSex(1);
		a.setBirthday(new Date());
		a.setRemark("进创集团主席:)");
		
		User b = new User();
		b.setUserID("caogp");
		b.setUserName("曹高平");
		b.setSex(1);
		b.setBirthday(new Date());
		b.setRemark("进创软件CEO)");
		
		User c = new User();
		c.setUserID("yangp");
		c.setUserName("扬平");
		c.setSex(1);
		c.setBirthday(new Date());
		c.setRemark("进创集团销售总监");
		
		User d = new User();
		d.setUserID("xiongc");
		d.setUserName("熊春");
		d.setSex(1);
		d.setBirthday(new Date());
		d.setRemark("进创软件技术总监");
		
		User e = new User();
		e.setUserID("dengzh");
		e.setUserName("邓志辉");
		e.setSex(1);
		e.setBirthday(new Date());
		e.setRemark("进创软件技E3平台事业部经理");
		
		User f = new User();
		f.setUserID("zhangh");
		f.setUserName("张辉");
		f.setSex(1);
		f.setBirthday(new Date());
		f.setRemark("系统集成事业部门经理");

		
		User g = new User();
		g.setUserID("xiaof");
		g.setUserName("小芳");
		g.setSex(0);
		g.setBirthday(new Date());
		g.setRemark("综合事务部经理");
		
		User h = new User();
		h.setUserID("niux");
		h.setUserName("牛迅");
		h.setSex(1);
		h.setBirthday(new Date());
		h.setRemark("进创软件培训部门经理");
		
		
		result.add(a);
		result.add(b);
		result.add(c);
		result.add(d);
		result.add(e);
		result.add(f);
		result.add(g);
		result.add(h);
		
		result.add(a);
		result.add(b);
		result.add(c);
		result.add(d);
		result.add(e);
		result.add(f);
		result.add(g);
		result.add(h);

		result.add(a);
		result.add(b);
		result.add(c);
		result.add(d);
		result.add(e);
		result.add(f);
		result.add(g);
		result.add(h);

		
		return result;
	}
	
}
