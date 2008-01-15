 package net.jcreate.e3.samples.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.NodeVisitor;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.ext.ExtLoadTreeBuilder;
import net.jcreate.e3.tree.ext.ExtSubTreeBuilder;
import net.jcreate.e3.tree.ext.ExtTreeBuilder;
import net.jcreate.e3.tree.support.AbstractNodeComparator;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.DefaultNodeComparator;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.DefaultTreeModel;
import net.jcreate.e3.tree.support.RequestUtil;
import net.jcreate.e3.tree.support.ReverseComparator;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeDynamicNode;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.xtree.CheckXLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.CheckXTreeBuilder;
import net.jcreate.e3.tree.xtree.CompositeXTreeBuilder;
import net.jcreate.e3.tree.xtree.RadioXLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.RadioXTreeBuilder;
import net.jcreate.e3.tree.xtree.XLoadSubTreeBuilder;
import net.jcreate.e3.tree.xtree.XLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.XTreeBuilder;
import net.jcreate.e3.tree.yui.YUIMenuBuilder;
import net.jcreate.e3.web.DispatchServlet;

public class XTreeServlet extends DispatchServlet{

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig pServletConfig) throws ServletException {
		super.init(pServletConfig);
		try {
			DBUtils.initDB();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
		
	}
	public void showNavTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		DefaultTreeModel treeModel = new DefaultTreeModel();
		//跟节点
		WebTreeNode rootNode = new WebTreeNode("E3.Tree", "root");	
		treeModel.addRootNode(rootNode);
		rootNode.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/e3/samples/tree/Blank.jsp" +
	              "')"				            		  
			, pRequest));
		
		
		
		WebTreeNode xtreeNode = new WebTreeNode("xtree", "xtree");		
		rootNode.addNode(xtreeNode);
		xtreeNode.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/e3/samples/tree/Blank.jsp" +
	              "')"				            		  
			, pRequest));
		
		
		WebTreeNode simpleTree = new WebTreeNode("简单树", "xtree_simple");
		simpleTree.setAction("javascript:openURL('" +
				              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showTree" +
				              "')"				            		  
						, pRequest));
		
		WebTreeNode radioTree = new WebTreeNode("单选树", "xtree_radio");
		radioTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showRadioTree" +
	              "')"				            		  
			, pRequest));

		
		WebTreeNode checkTree = new WebTreeNode("复选树", "xtree_check");
		checkTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showCheckTree" +
	              "')"				            		  
			, pRequest));
		
		
		WebTreeNode compositeTree = new WebTreeNode("组合树", "xtree_composite");
		compositeTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showCompositeTree" +
	              "')"				            		  
			, pRequest));
		
		WebTreeNode mixTree = new WebTreeNode("混合树", "mix_composite");
		mixTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showMixTree" +
	              "')"				            		  
			, pRequest));
		

		WebTreeNode loadTree = new WebTreeNode("动态树", "xtree_dynamic");
		loadTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showLoadTree" +
	              "')"				            		  
			, pRequest));
		
		WebTreeNode radioLoadTree = new WebTreeNode("动态radio树", "xtree_radioDynamic");
		radioLoadTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showRadioLoadTree" +
	              "')"				            		  
			, pRequest));
		
		
		/*
		WebTreeNode checkLoadTree = new WebTreeNode("动态check树", "xtree_checkDynamic");
		checkLoadTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showCheckLoadTree" +
	              "')"				            		  
			, pRequest));
		*/
		
		xtreeNode.addNode(simpleTree);
		xtreeNode.addNode(radioTree);
		xtreeNode.addNode(checkTree);
		xtreeNode.addNode(compositeTree);
		xtreeNode.addNode(mixTree);
 		xtreeNode.addNode(loadTree);
 		xtreeNode.addNode(radioLoadTree);
 		//xtreeNode.addNode(checkLoadTree);
 		
		
		WebTreeNode yuiNode = new WebTreeNode("yui", "yui");		
		rootNode.addNode(yuiNode);
		yuiNode.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/e3/samples/tree/Blank.jsp" +
	              "')"				            		  
			, pRequest));
		
		WebTreeNode yuiHMenu = new WebTreeNode("菜单", "yuiHMenu");
		yuiHMenu.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showYUIHMenu" +
	              "')"				            		  
			, pRequest));
		yuiNode.addNode(yuiHMenu);
		
		WebTreeNode extNode = new WebTreeNode("ext[beta1.0]", "ext");		
		rootNode.addNode(extNode);
		extNode.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/e3/samples/tree/Blank.jsp" +
	              "')"				            		  
			, pRequest));
		WebTreeNode extTree = new WebTreeNode("普通树", "extTree");
		extTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showExtTree" +
	              "')"				            		  
			, pRequest));
		extNode.addNode(extTree);
		
		WebTreeNode extLoadTree = new WebTreeNode("动态树", "extLoadTree");
		extLoadTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showExtLoadTree" +
	              "')"				            		  
			, pRequest));
		extNode.addNode(extLoadTree);
		
		WebTreeNode extDragTree = new WebTreeNode("拖动的树", "extDragTree");
		extDragTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showExtDragTree" +
	              "')"				            		  
			, pRequest));
		extNode.addNode(extDragTree);
		
		WebTreeNode showExtIconTree = new WebTreeNode("自定义图标", "showExtIconTree");
		showExtIconTree.setAction("javascript:openURL('" +
	              RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=showExtIconTree" +
	              "')"				            		  
			, pRequest));
		extNode.addNode(showExtIconTree);
		
		
		
		
		TreeDirector director = new DefaultTreeDirector();
		WebTreeBuilder treeBuilder = new XTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/NavTree.jsp").forward(pRequest,pResponse);
	}
	
	public void	showYUIHMenu(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
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
		director.setComparator(new AbstractNodeComparator(){
			protected Comparable getComparableProperty(Node pNode) {
				Object userData = pNode.getUserData();//获取业务对象
				Org org = (Org)userData;
				return new Integer( org.getViewOrder() );
			}
		});
		WebTreeBuilder treeBuilder = new YUIMenuBuilder();//构造树Builder
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		pRequest.getRequestDispatcher("/e3/samples/tree/XTree.jsp").forward(pRequest,pResponse);
	}
	
	public void	loadSubOrgs(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		final String parentID = pRequest.getParameter("parentID");
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List subOrgs = treeService.getSubOrgs(parentID);
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeDynamicNode result = new WebTreeDynamicNode(org.getName(), "org" +org.getId());
				result.setSubTreeURL(
						getUrl("/servlet/xtreeServlet?_actionType=" +
								           "loadSubOrgs&parentID=" + org.getId()));
				result.setValue(org.getId());			
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(subOrgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new DefaultNodeComparator());
		WebTreeBuilder treeBuilder = new XLoadSubTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pResponse.setBufferSize(1024*10);
		pResponse.setContentType("text/xml;charset=utf-8");
		pResponse.getWriter().write(treeScript);
		pResponse.flushBuffer();	
		return;
	}
	
	public void	loadExtSubOrgs(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		final String parentID = pRequest.getParameter("parentID");
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List subOrgs = treeService.getSubOrgs(parentID);
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeDynamicNode result = new WebTreeDynamicNode(org.getName(), "org" +org.getId());
				result.setSubTreeURL(
						getUrl("/servlet/xtreeServlet?_actionType=" +
								           "loadExtSubOrgs&parentID=" + org.getId()));
				
				result.setValue(org.getId());			
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(subOrgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new DefaultNodeComparator());
		WebTreeBuilder treeBuilder = new ExtSubTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pResponse.setBufferSize(1024*10);
		pResponse.setContentType("text/json;charset=utf-8");
		pResponse.getWriter().write(treeScript);
		pResponse.flushBuffer();	
		return;
	}
	

	
	public void	showRadioLoadTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
WebTreeDynamicNode rootNode = new WebTreeDynamicNode("进创集团", "org" + "001");

WebTreeDynamicNode jcrjNode = new WebTreeDynamicNode("进创软件", "org" + "001001");
jcrjNode.setSubTreeURL(
		RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
				           "loadSubOrgs&parentID=" + "001001", pRequest));
jcrjNode.setValue("001001");

WebTreeDynamicNode jcjyNode = new WebTreeDynamicNode("进创教育", "org" + "001002");
jcjyNode.setSubTreeURL(
		RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
				           "loadSubOrgs&parentID=" + "001002", pRequest));
jcjyNode.setValue("001002");


rootNode.addNode(jcrjNode);
rootNode.addNode(jcjyNode);

DefaultTreeModel treeModel = new DefaultTreeModel();
treeModel.addRootNode(rootNode);		
TreeDirector director = new DefaultTreeDirector();
director.setComparator(new DefaultNodeComparator());
WebTreeBuilder treeBuilder = new RadioXLoadTreeBuilder();
treeBuilder.init(pRequest);		
director.build(treeModel, treeBuilder);		
String treeScript = treeBuilder.getTreeScript();
pRequest.setAttribute("treeScript", treeScript);
pRequest.getRequestDispatcher("/e3/samples/tree/RadioTree.jsp").forward(pRequest,pResponse);

}
	
	public void	showCheckLoadTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
WebTreeDynamicNode rootNode = new WebTreeDynamicNode("进创集团", "org" + "001");

WebTreeDynamicNode jcrjNode = new WebTreeDynamicNode("进创软件", "org" + "001001");
jcrjNode.setSubTreeURL(
		RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
				           "loadSubOrgs&parentID=" + "001001", pRequest));
jcrjNode.setValue("001001");


WebTreeDynamicNode jcjyNode = new WebTreeDynamicNode("进创教育", "org" + "001002");
jcjyNode.setSubTreeURL(
		RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
				           "loadSubOrgs&parentID=" + "001002", pRequest));
jcjyNode.setValue("001002");


rootNode.addNode(jcrjNode);
rootNode.addNode(jcjyNode);

DefaultTreeModel treeModel = new DefaultTreeModel();
treeModel.addRootNode(rootNode);		
TreeDirector director = new DefaultTreeDirector();
director.setComparator(new DefaultNodeComparator());
WebTreeBuilder treeBuilder = new CheckXLoadTreeBuilder();
treeBuilder.init(pRequest);		
director.build(treeModel, treeBuilder);		
String treeScript = treeBuilder.getTreeScript();
pRequest.setAttribute("treeScript", treeScript);
pRequest.getRequestDispatcher("/e3/samples/tree/CheckTree.jsp").forward(pRequest,pResponse);

}

	
	public void	showLoadTree(final HttpServletRequest pRequest, 
			                 final HttpServletResponse pResponse) throws Exception{
		WebTreeDynamicNode rootNode = new WebTreeDynamicNode("进创集团", "org" + "001");
		rootNode.setSubTreeURL(
				RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
						           "loadSubOrgs&parentID=" + "001", pRequest));
		DefaultTreeModel treeModel = new DefaultTreeModel();
		treeModel.addRootNode(rootNode);		
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new DefaultNodeComparator());
		WebTreeBuilder treeBuilder = new XLoadTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/XTree.jsp").forward(pRequest,pResponse);
		
	}
	
	
	public void	showExtLoadTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
			WebTreeDynamicNode rootNode = new WebTreeDynamicNode("进创集团", "org" + "001");
			rootNode.setSubTreeURL(
			RequestUtil.getUrl("/servlet/xtreeServlet?_actionType=" +
					           "loadExtSubOrgs&parentID=" + "001", pRequest));
			
			DefaultTreeModel treeModel = new DefaultTreeModel();
			treeModel.addRootNode(rootNode);		
			TreeDirector director = new DefaultTreeDirector();
			director.setComparator(new DefaultNodeComparator());
			ExtLoadTreeBuilder treeBuilder = new ExtLoadTreeBuilder();
			treeBuilder.init(pRequest);	
			treeBuilder.setTitle("请选择节点!");
			director.build(treeModel, treeBuilder);		
			String treeScript = treeBuilder.getTreeScript();
			pRequest.setAttribute("treeScript", treeScript);
			pRequest.getRequestDispatcher("/e3/samples/tree/ExtLoadTree.jsp").forward(pRequest,pResponse);
   }
	

	
	public void	showTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		//业务数据
		TreeService treeService =  TreeBeanFactory.getTreeService();		
		List orgs =  treeService.getOrgs();
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
				//result.setAction("javascript:alert(' " + org.getName() + "')");
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		XTreeBuilder treeBuilder = new XTreeBuilder();//构造树Builder
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		pRequest.getRequestDispatcher("/e3/samples/tree/XTree.jsp").forward(pRequest,pResponse);
	}

	public void	saveTree(final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) throws Exception{
		String parentChangedOrgs = pRequest.getParameter("parentChangedOrgs");
		if ( parentChangedOrgs == null ){
		  pRequest.getRequestDispatcher("/servlet/xtreeServlet?_actionType=showExtDragTree").forward(pRequest,pResponse);
		  return;
		}
		final String SPLITER = ",";
		StringTokenizer st = new StringTokenizer(parentChangedOrgs, SPLITER);
		//key:orgId value:parentOrgId
		Map changedOrgs = new HashMap();
		while( st.hasMoreTokens() ){
			changedOrgs.put(st.nextToken(), st.nextToken());
		}
		TreeService treeService =  TreeBeanFactory.getTreeService();
		treeService.updateOrgs(changedOrgs);
		pRequest.getRequestDispatcher("/servlet/xtreeServlet?_actionType=showExtDragTree").forward(pRequest,pResponse);
	}

	
	public void	showExtDragTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List orgs =  treeService.getOrgs();
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeNode result = new WebTreeNode(org.getName() , "org" + org.getId());
				result.setDragable(true);//允许拖
				result.setDropable(true);//允许放
				result.setAttribute("orgID", org.getId());//记录机构的本身ID，和父亲机构ID
				result.setAttribute("oldParentOrgID", org.getParentId());
				result.setAttribute("newParentOrgID", org.getParentId()); 
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new ReverseComparator(new AbstractNodeComparator(){
			protected Comparable getComparableProperty(Node pNode) {
				Org org = (Org)pNode.getUserData();
				return new Integer( org.getViewOrder() );
			}
			
		}));
		
		ExtTreeBuilder treeBuilder = new ExtTreeBuilder();
		treeBuilder.init(pRequest);
		treeBuilder.setTitle("拖动节点看看");
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/DragTree.jsp").forward(pRequest,pResponse);
	}

	public void	showExtIconTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List orgs =  treeService.getOrgs();
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
				result.setIcon(this.getUrl("/e3/samples/tree/Org.gif"));
				result.setOpenIcon(this.getUrl("/e3/samples/tree/User.gif"));
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator() ));
		ExtTreeBuilder treeBuilder = new ExtTreeBuilder();//构造树Builder
		treeBuilder.init(pRequest);		
		treeBuilder.setTitle("测试树");
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		pRequest.getRequestDispatcher("/e3/samples/tree/ExtTree.jsp").forward(pRequest,pResponse);
	}
	public void	showExtTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
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
				WebTreeNode result = new WebTreeNode(org.getName(), "node" + org.getId());
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		director.setComparator(new ReverseComparator(new DefaultNodeComparator() ));
		ExtTreeBuilder treeBuilder = new ExtTreeBuilder();//构造树Builder		
		treeBuilder.init(pRequest);
		treeBuilder.setTitle("请选择节点!");
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		pRequest.getRequestDispatcher("/e3/samples/tree/ExtTree.jsp").forward(pRequest,pResponse);
	}
	
	public void	showMixTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		//业务数据
		List orgs =  new ArrayList();
		Org jcjtOrg = new Org("001",null,"进创集团", 1);
		Org jcrjOrg = new Org("001001","001","进创软件", 1);
		orgs.add(jcjtOrg);
		orgs.add(jcrjOrg);
		
		User huangy = new User("huangyh", "黄云辉", "001");//直属集团
		User guohp = new User("guohp", "郭鸿鹏", "001");//直属集团
		
		User caogp = new User("caogp", "曹高平", "001001");//进创软件
		
		List users = new ArrayList();
		users.add(huangy);
		users.add(guohp);
		users.add(caogp);
		
		List allData = new ArrayList();
		allData.addAll(orgs);
		allData.addAll(users);
		
	
		
		//业务数据解码器，从业务数据中分解出id和parentid
		
		UserDataUncoder uncoder = new UserDataUncoder(){
			final String USERID_PREFIX = "USER_";//为了避免用户ID和机构ID出现相同的情况，所以构造树时
            //所有用户ID带个前缀.
			
			public Object getID(Object pUserData) throws UncodeException {
				if ( pUserData instanceof Org){
				  Org org = (Org)pUserData;
				  return org.getId();
				}
				if ( pUserData instanceof User ){
					User user = (User)pUserData;
					return USERID_PREFIX + user.getId();
				}
				throw new UncodeException("不支持的数据对象." + pUserData.getClass().getName());
			} 
			public Object getParentID(Object pUserData) throws UncodeException {
				if ( pUserData instanceof Org){
					  Org org = (Org)pUserData;
					  return org.getParentId();
					}
					if ( pUserData instanceof User ){
						User user = (User)pUserData;
						return user.getOrgId();
					}
				throw new UncodeException("不支持的数据对象." + pUserData.getClass().getName());
			}
		};
		
		//Tree模型构造器，用于生成树模型
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			//该方法负责将业务数据映射到树型节点
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				if ( pUserData instanceof Org ){
					Org org = (Org)pUserData;
					WebTreeNode result = new WebTreeNode(org.getName(), "org" + org.getId());
					result.setIcon(this.getUrl("/e3/samples/tree/Org.gif"));
					result.setOpenIcon(this.getUrl("/e3/samples/tree/Org.gif"));
					//action是点击按纽执行的方法.可以是url,或者javascript函数
					result.setAction("javascript:alert(' " + org.getName() + "')");
					return result;
				}
				
				if ( pUserData instanceof User ){
					User user = (User)pUserData;
					WebTreeNode result = new WebTreeNode(user.getName(), "user" + user.getId());
					result.setIcon(this.getUrl("/e3/samples/tree/User.gif"));
					result.setOpenIcon(this.getUrl("/e3/samples/tree/User.gif"));
					//action是点击按纽执行的方法.可以是url,或者javascript函数
					result.setAction("javascript:alert(' " + user.getName() + "')");
					return result;
				}
				throw new UncodeException("不支持的数据对象." + pUserData.getClass().getName());
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(allData,uncoder);
		TreeDirector director = new DefaultTreeDirector();//构造树导向器
		WebTreeBuilder treeBuilder = new XTreeBuilder();//构造树Builder
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);//执行构造		
		String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
		pRequest.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
		pRequest.getRequestDispatcher("/e3/samples/tree/XTree.jsp").forward(pRequest,pResponse);
	}
	
	public void	showRadioTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List orgs =  treeService.getOrgs();
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeNode result = new WebTreeNode(org.getName() + "[" + org.getViewOrder() + "]", "org" + org.getId());
				result.setValue(org.getId());//值是机构ID
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new ReverseComparator(new AbstractNodeComparator(){
			protected Comparable getComparableProperty(Node pNode) {
				Org org = (Org)pNode.getUserData();
				return new Integer( org.getViewOrder() );
			}
			
		}));
		
		WebTreeBuilder treeBuilder = new RadioXTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/RadioTree.jsp").forward(pRequest,pResponse);
	}
	
	
	public void	showCheckTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List orgs =  treeService.getOrgs();
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeNode result = new WebTreeNode(org.getName(), "org" + org.getId());
				result.setValue(org.getId());
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new AbstractNodeComparator(){
			protected Comparable getComparableProperty(Node pNode) {
				Org org = (Org)pNode.getUserData();
				return new Integer( org.getViewOrder() );
			}
			
		});
		WebTreeBuilder treeBuilder = new CheckXTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/CheckTree.jsp").forward(pRequest,pResponse);
	}
	
	public void	showCompositeTree(final HttpServletRequest pRequest, 
            final HttpServletResponse pResponse) throws Exception{
		TreeService treeService =  TreeBeanFactory.getTreeService();
		List orgs =  treeService.getOrgs();
		UserDataUncoder orgUncoder = new OrgUncoder();
		AbstractWebTreeModelCreator treeModelCreator =
		 new AbstractWebTreeModelCreator(){
			protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
				Org org = (Org)pUserData;
				WebTreeNode result = new WebTreeNode(org.getName(), "org" + org.getId());
				result.setValue( org.getId() );
				return result;
			}
		};
		treeModelCreator.init(pRequest);
		
		TreeModel treeModel = treeModelCreator.create(orgs,orgUncoder);
		TreeDirector director = new DefaultTreeDirector();
		director.setComparator(new AbstractNodeComparator(){
			protected Comparable getComparableProperty(Node pNode) {
				Org org = (Org)pNode.getUserData();
				return new Integer( org.getViewOrder() );
			}
			
		});
		director.setNodeVisitor(new NodeVisitor(){
			public boolean visit(Node pNode) {
				
				WebTreeNode webNode = (WebTreeNode)pNode;
				if ( webNode.isLeaf() ){
					webNode.setNodeProperty(WebTreeNode.RADIO);
				} else if ( webNode.isRoot() ){
					webNode.setNodeProperty(WebTreeNode.NONE);
				} else {
					webNode.setNodeProperty(WebTreeNode.CHECKBOX);
				}
				return true;
			}
			
		});
		WebTreeBuilder treeBuilder = new CompositeXTreeBuilder();
		treeBuilder.init(pRequest);		
		director.build(treeModel, treeBuilder);		
		String treeScript = treeBuilder.getTreeScript();
		pRequest.setAttribute("treeScript", treeScript);
		pRequest.getRequestDispatcher("/e3/samples/tree/CompositeTree.jsp").forward(pRequest,pResponse);
	}
	
	
	


}
