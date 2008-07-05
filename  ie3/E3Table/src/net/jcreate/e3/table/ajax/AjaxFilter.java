package net.jcreate.e3.table.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jcreate.e3.table.support.TableConstants;

import org.ajaxanywhere.AAFilter;
import org.ajaxanywhere.BufferResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * e3.table的ajax的实现是用ajaxanywhere实现的.
 * 这种方式的实现方式最大好处传统实现方式跟ajax方式
 * 没任何区别（对开发人员来说），只需要设置mode为ajax
 * 就可以，但是弊端是：性能上跟传统模式比没有提升，不过可以
 * 获得ajax的UI体验.所以e3.table会在很长一段时间内使用
 * 这种模式. 
 * @author 黄云辉
 * 补充说明： 你看到的AjaxFilter没有自己任何实现，这么做是为了
 * 屏蔽e3.table显式的依赖ajaxanywhere. 以后要换成其他实现
 * 方式，对e3.table用户没有影响，基于这样的考虑，e3.table 把
 * ajaxanywhere的源代码也打包进来，没以jar的形式发布.
 */
public class AjaxFilter extends AAFilter{
	
	private final Log logger = LogFactory.getLog( this.getClass() );

	public AjaxFilter() {
		super();
	}

	public void doFilter(ServletRequest pRequest, ServletResponse pResponse,
			FilterChain pFilterChain) throws IOException, ServletException {
		if ( AjaxUtil.isAjaxRequest((HttpServletRequest)pRequest) == false ){
			super.doFilter(pRequest, pResponse, pFilterChain);
			return;
		} 
        HttpServletRequest request = (HttpServletRequest) pRequest;
        HttpServletResponse response = (HttpServletResponse) pResponse;
        //request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        
	    BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
	    pFilterChain.doFilter(request, bufferResponseWrapper);
	    String buffer = bufferResponseWrapper.getBuffer();
	    System.err.println("原始结果:" + buffer);
	    String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
	    String resultData = AjaxUtil.getAjaxData(refreshZone, buffer);
	    System.err.println("输出结果是:" + resultData);
	    HttpServletResponse originalResponse = bufferResponseWrapper.getOriginalResponse();
	    PrintWriter writer = originalResponse.getWriter();
	    writer.print(resultData);
	    
	    originalResponse.flushBuffer();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}
	
	

}
