package net.jcreate.home.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.home.common.BaseAction;
import net.jcreate.xkins.XkinProcessor;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IndexAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/**
		 * TODO: 设置初始化皮肤E3Home001
		 */
		XkinProcessor.setCurrentSkinName(request, "E3Home001");
		/**
		 * 首页菜单
		 */
		List menus = getMenus(request);
		request.setAttribute("menus", menus);
		
		/**
		 * 图片连接
		 */
		List picLinks = getPicLinks(request);
		request.setAttribute("picLinks", picLinks);
		/**
		 * 文本连接
		 */
		List textLinks = getTextLinks(request);
		request.setAttribute("textLinks", textLinks);
		
		/**
		 * 获取组件列表
		 */
		List components = getComponents(request);
		request.setAttribute("components", components);

		/**
		 * 获取资源列表
		 */
		List resources = getResurces(request);
		request.setAttribute("resources", resources);
		
		return mapping.findForward("index");
		
	}

	private List getComponents(HttpServletRequest request){
		List result = new ArrayList();
		ComponentItem  e3Tree = new ComponentItem();
		e3Tree.setComponentName("E3.Tree");
		e3Tree.setComponentDesc("很好，很强大!");
		e3Tree.setDemoURL("#");
		e3Tree.setDownloadURL("#");
		result.add(e3Tree);

		ComponentItem  e3Table = new ComponentItem();
		e3Table.setComponentName("E3.Table");
		e3Table.setComponentDesc("很好，很强大!");
		e3Table.setDemoURL("#");
		e3Table.setDownloadURL("#");
		result.add(e3Table);

		ComponentItem  e3Resource = new ComponentItem();
		e3Resource.setComponentName("E3.Resource");
		e3Resource.setComponentDesc("很好，很强大!");
		e3Resource.setDemoURL("#");
		e3Resource.setDownloadURL("#");
		result.add(e3Resource);
		
		return result;
	}

	private List getResurces(HttpServletRequest request){
		List result = new ArrayList();
		ResourceItem e3TreeSrc = new ResourceItem();
		e3TreeSrc.setResName("E3.Tree.src1.5.rar");
		e3TreeSrc.setDownloadURL("#");
		e3TreeSrc.setResDesc("源代码");
		result.add(e3TreeSrc);

		ResourceItem e3TreeJar = new ResourceItem();
		e3TreeJar.setResName("E3.Tree.dist1.5.rar");
		e3TreeJar.setDownloadURL("#");
		e3TreeJar.setResDesc("jar包");
		result.add(e3TreeJar);

		ResourceItem e3TreeDoc = new ResourceItem();
		e3TreeDoc.setResName("E3.Tree.doc1.5.rar");
		e3TreeDoc.setDownloadURL("#");
		e3TreeDoc.setResDesc("开发手册");
		result.add(e3TreeDoc);

		ResourceItem e3TableJar = new ResourceItem();
		e3TableJar.setResName("E3.Table.src1.5.rar");
		e3TableJar.setDownloadURL("#");
		e3TableJar.setResDesc("源代码");
		result.add(e3TableJar);
		
		return result;
	}
	private List getTextLinks(HttpServletRequest request){
		List result = new ArrayList();
		LinkItem csdnLink = new LinkItem();
		csdnLink.setLinkName("eaiyi");
		csdnLink.setLinkURL("http://www.eaiyi.com");
		csdnLink.setPicPath("#");
		result.add(csdnLink);

		LinkItem yiLink = new LinkItem();
		yiLink.setLinkName("yiyi51");
		yiLink.setLinkURL("http://www.yiyi51.com");
		yiLink.setPicPath("#");
		result.add(yiLink);

		LinkItem javaeyeLink = new LinkItem();
		javaeyeLink.setLinkName("javae3");
		javaeyeLink.setLinkURL("http://www.javae3.com");
		javaeyeLink.setPicPath("#");
		result.add(javaeyeLink);
		
		return result;
	}
	private List getPicLinks(HttpServletRequest request){
		List result = new ArrayList();
		LinkItem csdnLink = new LinkItem();
		csdnLink.setLinkName("csdn");
		csdnLink.setLinkURL("http://www.csdn.net");
		csdnLink.setPicPath("#");
		result.add(csdnLink);

		LinkItem yiLink = new LinkItem();
		yiLink.setLinkName("126");
		yiLink.setLinkURL("http://www.126.com");
		yiLink.setPicPath("#");
		result.add(yiLink);

		LinkItem javaeyeLink = new LinkItem();
		javaeyeLink.setLinkName("javaeye");
		javaeyeLink.setLinkURL("http://www.javaeye.com");
		javaeyeLink.setPicPath("#");
		result.add(javaeyeLink);
		
		return result;
	}
	private List getMenus(HttpServletRequest request){
		List result = new ArrayList();
		MenuItem homeItem = new MenuItem();
		homeItem.setMenuID("home");
		homeItem.setMenuName("首页");
		homeItem.setUrl("#");
		homeItem.setTooltip("首页");
		result.add(homeItem);
		
		MenuItem donwloadItem = new MenuItem();
		donwloadItem.setMenuID("donwload");
		donwloadItem.setMenuName("下载");
		donwloadItem.setUrl("#");
		donwloadItem.setTooltip("下载");
		result.add(donwloadItem);
		
		MenuItem demoItem = new MenuItem();
		demoItem.setMenuID("demo");
		demoItem.setMenuName("演示");
		demoItem.setUrl("#");
		demoItem.setTooltip("演示");
		result.add(demoItem);
		
		MenuItem forumItem = new MenuItem();
		forumItem.setMenuID("forum");
		forumItem.setMenuName("论坛");
		forumItem.setUrl("#");
		forumItem.setTooltip("论坛");
		result.add(forumItem);
		
		MenuItem aboutItem = new MenuItem();
		aboutItem.setMenuID("about");
		aboutItem.setMenuName("联系");
		aboutItem.setUrl("#");
		aboutItem.setTooltip("联系");
		result.add(aboutItem);
		
		return result;
	}

}
