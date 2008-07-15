package net.jcreate.e3.table;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.jcreate.e3.table.ajax.AjaxFilter;
import net.jcreate.xkins.Context;
import net.jcreate.xkins.ContextHolder;
import net.jcreate.xkins.Xkins;

/**
 * 将来
 * @author 黄云辉
 *
 */
public class E3TableFilter extends AjaxFilter{

	
	public void doFilter(ServletRequest pRequest, ServletResponse pResponse,
			FilterChain pFilterChain) throws IOException, ServletException {
		try{
			//构造context信息
			Context context = new Context(pRequest, pResponse);
			HttpServletRequest httpRequest = (HttpServletRequest)pRequest;
			HttpSession session = httpRequest.getSession();
			Xkins xs = (Xkins) session.getServletContext().getAttribute(Xkins.ATTR_SKINS);
			context.setXkins(xs);			
			ContextHolder.setContext(context);
			//
		    super.doFilter(pRequest, pResponse, pFilterChain);
		}finally{
			ContextHolder.setContext(null);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}
	

}
