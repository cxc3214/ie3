package net.jcreate.e3.samples.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.xtree.XTreeBuilder;

public class TestServlet extends HttpServlet{

	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
				
			//业务数据
				List orgs =  new ArrayList();
				Org jcjtOrg = new Org("001",null,"进创集团", 1);
				Org jcrjOrg = new Org("001001","001","进创软件", 1);
				Org xrjOrg = new Org("0010010011","001001","X软件公司", 1);
				Org yrjOrg = new Org("0010010012","001001","Y软件公司", 2);
				Org zrjOrg = new Org("0010010013","001001","Z软件公司", 3);
				orgs.add(jcjtOrg);
				orgs.add(jcrjOrg);
				orgs.add(xrjOrg);
				orgs.add(yrjOrg);
				orgs.add(zrjOrg);

				//业务数据解码器，从业务数据中分解出id和parentid
				UserDataUncoder orgUncoder = new UserDataUncoder(){
					public Object getID(Object pUserData) throws UncodeException {
						Org org = (Org)pUserData;
						return org.getId();
					} 
					public Object getParentID(Object pUserData) throws UncodeException {
						Org org = (Org)pUserData;
						return org.getParentId();
					}
				};
				
				//Tree模型构造器，用于生成树模型
				AbstractWebTreeModelCreator treeModelCreator =
				 new AbstractWebTreeModelCreator(){
					//该方法负责将业务数据映射到树型节点
					protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
						Org org = (Org)pUserData;
						WebTreeNode result = new WebTreeNode(org.getName(), "org" + org.getId());
						//action是点击按纽执行的方法.可以是url,或者javascript函数
						result.setAction("javascript:alert(' " + org.getName() + "')");
						return result;
					}
				};
				treeModelCreator.init(pRequest);
				
				TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
				TreeDirector director = new DefaultTreeDirector();//构造树导向器
				WebTreeBuilder treeBuilder = new XTreeBuilder();//构造树Builder
				treeBuilder.init(pRequest);		
				director.build(treeModel, treeBuilder);//执行构造		
				String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
				pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		        pRequest.getRequestDispatcher("/e3/samples/tree/XTree.jsp").forward(pRequest,pResponse);
		}


}
