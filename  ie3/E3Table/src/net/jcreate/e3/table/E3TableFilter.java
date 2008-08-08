package net.jcreate.e3.table;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.jcreate.e3.table.ajax.AjaxUtil;
import net.jcreate.e3.table.support.TableConstants;
import net.jcreate.e3.table.util.StringUtils;
import net.jcreate.e3.table.util.TableUtils;
import net.jcreate.xkins.Context;
import net.jcreate.xkins.ContextHolder;
import net.jcreate.xkins.Xkins;

import org.ajaxanywhere.AAFilter;
import org.ajaxanywhere.BufferResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 将来
 * @author 黄云辉
 *
 */
public class E3TableFilter  extends AAFilter{
	
	private final Log logger = LogFactory.getLog( this.getClass() );

	public E3TableFilter() {
		super();
	}
	
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
			executeFilter(pRequest, pResponse, pFilterChain);
		}finally{
			ContextHolder.setContext(null);
		}
	}
	
private String getParameterJson(HttpServletRequest pRequest){
	java.util.Enumeration paramNames = pRequest.getParameterNames();
	StringBuffer sb = new StringBuffer();
	final String ENTER = "\n";
	sb.append("{ ").append(ENTER);
	while( paramNames.hasMoreElements() ){
		Object param = paramNames.nextElement();
		if ( param instanceof String == false){
			continue;
		}
		String paramName = (String)param;
		String[] paramValues = pRequest.getParameterValues(paramName);
		if ( paramValues.length != 1 ){
			sb.append("{ ").append(ENTER);
			for(int i=0; i<paramValues.length; i++){
				if ( paramValues.length == (i+1) ){//最后一个参数
					sb.append(paramName).append(" : '").append(pRequest.getParameter(paramName)).append("' ").append(ENTER);	
				} else {
					sb.append(paramName).append(" : '").append(pRequest.getParameter(paramName)).append("' ,").append(ENTER);
				}
			}
			sb.append(" } ,").append(ENTER);
		} else {
			sb.append(paramName).append(" : '").append(pRequest.getParameter(paramName)).append("' ,").append(ENTER);					
		}
	}
	//删除最后一个多余的空格.
	int last = sb.lastIndexOf(",") ; 
	if ( last!= -1 ){
		sb.deleteCharAt(last);				
	}
	sb.append(" }").append(ENTER);
	return sb.toString();

}
	/**
	 * TODO:  这里的代码特别重要，要重点设计，优化.
	 * @param pRequest
	 * @param pResponse
	 * @param pFilterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void executeFilter(ServletRequest pRequest, ServletResponse pResponse,
			FilterChain pFilterChain) throws IOException, ServletException {
		//是否是导出数据的请求
        HttpServletRequest request = (HttpServletRequest) pRequest;
        HttpServletResponse response = (HttpServletResponse) pResponse;
	   if ( TableUtils.isExportParamRequest(request)  ){
	        response.setContentType("text/xml;charset=utf-8");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setHeader("Pragma", "no-cache");
		   
		   PrintWriter writer = response.getWriter();
		      String json = getParameterJson(request);
	          final String resultJson = "{ exportTable : true, params : " + json + " }";
	          System.err.println( resultJson);
	          writer.print(resultJson);
	          response.flushBuffer();
		   
		   /**
		    * TODO: 在这导出参数
		    */
		  return;   
	   }
			
		/**
		 * 进行导出数据处理.
		 */
		if ( TableUtils.isExportTableRequest(request)  ){

	        response.setContentType("application/msexcel;charset=utf-8");
	        response.setHeader("Content-disposition","attachment; filename=datas.xls"); 
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setHeader("Pragma", "no-cache");
			PrintWriter writer = response.getWriter();	        
	        BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
        
	        try{
		      pFilterChain.doFilter(request, bufferResponseWrapper);
		      String buffer = bufferResponseWrapper.getBuffer();
		      String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
		      String resultData = AjaxUtil.getAjaxData(refreshZone, buffer);
		      writer.print(resultData);
		      System.err.println(resultData);
	        }catch(Exception ex){
	          final String msg = ex.getMessage();
	          final String resultJson = "{ msg : '" + StringUtils.escapeJavaScript(msg) + "' }";
	          writer.print(resultJson);
	        }
	        response.flushBuffer();
	        
	        return;
			
		}
		
		//不是ajax请求.
		if ( TableUtils.isAjaxRequest(request) == false ){
			super.doFilter(pRequest, pResponse, pFilterChain);
			return;
		} 
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
        PrintWriter writer = response.getWriter();
        try{
        	
	      pFilterChain.doFilter(request, bufferResponseWrapper);
	      String buffer = bufferResponseWrapper.getBuffer();
	      String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
	      String resultData = AjaxUtil.getAjaxData(refreshZone, buffer);
	      writer.print(resultData);
        }catch(Exception ex){
          final String msg = ex.getMessage();
          final String resultJson = "{ msg : '" + StringUtils.escapeJavaScript(msg) + "' }";
          writer.print(resultJson);
        }
        response.flushBuffer();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

}
