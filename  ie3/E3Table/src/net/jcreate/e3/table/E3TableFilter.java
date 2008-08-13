package net.jcreate.e3.table;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
 * TODO: 这个过滤器部分代码很混乱需要重构
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
			//在这要负责把xkins对象存储起来,
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
	
	/**
	 * 将所有请求参数封装成json数据,以便返回给客户端进行表格导出处理.
	 * TODO: 使用json包来对json数据进行转换处理
	 * 构造参数数组
	 *   [
	 *    { a : b},
	 *    { c : c1 },
	 *   ]
	 * @param pRequest
	 * @return
	 */
private String getParameterJson(HttpServletRequest pRequest){
	java.util.Enumeration paramNames = pRequest.getParameterNames();
	StringBuffer sb = new StringBuffer();
	final String ENTER = "\n";
	sb.append("[ ").append(ENTER);
	while( paramNames.hasMoreElements() ){
		Object param = paramNames.nextElement();
		if ( param instanceof String == false){
			continue;
		}
		String paramName = (String)param;
		String[] paramValues = pRequest.getParameterValues(paramName);
		if ( paramValues.length != 1 ){
			for(int i=0; i<paramValues.length; i++){
  			  sb.append("{ ").append(paramName).append(" : '").
  			  append(StringUtils.escapeJavaScript( paramValues[i]) ).append("'  } ,").append(ENTER);
			}
		} else {
			sb.append("{ ").append(paramName).append(" : '").
			append(StringUtils.escapeJavaScript(pRequest.getParameter(paramName))).append("'  } ,").append(ENTER);					
		}
	}
	//删除最后一个多余的空格.
	int last = sb.lastIndexOf(",") ; 
	if ( last!= -1 ){
		sb.deleteCharAt(last);				
	}
	sb.append(" ]").append(ENTER);
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
        
        if (logger.isDebugEnabled()){
          logger.debug("系统编码:" + System.getProperty("file.encoding"));	
          logger.debug("JAVA版本:" + System.getProperty("java.version"));
        }

        
        
	   if ( TableUtils.isExportParamRequest(request)  ){
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setHeader("Pragma", "no-cache");
	    	response.setContentType("text/xml;charset=" + request.getCharacterEncoding());
		   
		   PrintWriter writer = response.getWriter();
		      String json = getParameterJson(request);
	          final String resultJson = "{ exportTable : true, params : " + json + " }";
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
			/**
			 * 这是要执行导出的table id, 
			 * @fixme: 下次把参数名修改下，叫exportTable
			 */
			String exportTable = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
			Map exportBeanMap = new HashMap();
			request.setAttribute(TableConstants.EXPORT_BEAN_PREFIX + exportTable, exportBeanMap);
	        //response.setContentType("application/msexcel;charset=utf-8");
	        BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
        
	        /**
	         * 说明:导出部分代码大部分来自displaytag
	         */
	        PrintWriter writer = null;
	        try{
		      pFilterChain.doFilter(request, bufferResponseWrapper);
		      //String resultData = AjaxUtil.getAjaxData(exportTable, buffer);
		        Object  exportBean = exportBeanMap.get(TableConstants.REPORT_CONTENT_KEY);
		        if (exportBean == null)
		        {
		            if (logger.isDebugEnabled())
		            {
		            	logger.debug("Filter is enabled but exported content has not been found. Maybe an error occurred?");
		            }

		            response.setContentType(bufferResponseWrapper.getContentType());
		            PrintWriter out = response.getWriter();
		            out.write(bufferResponseWrapper.getBuffer());
		            out.flush();
		            return;
		        }
		        // clear headers
		        if (!response.isCommitted())
		        {
		            response.reset();
		        }
		        
		        response.setHeader("Content-disposition","attachment; filename=datas.xls"); 
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
		        response.setHeader("Pragma", "no-cache");
		        String contentType = (String) exportBeanMap.get(TableConstants.REPORT_CONTENT_TYPE_KEY);
		        String characterEncoding = request.getCharacterEncoding();//bufferResponseWrapper.getCharacterEncoding();
		        String wrappedContentType = bufferResponseWrapper.getContentType();

		        if (wrappedContentType != null && wrappedContentType.indexOf("charset") > -1)
		        { 
		            // charset is already specified (see #921811)
		            characterEncoding = org.apache.commons.lang.StringUtils.substringAfter(wrappedContentType, "charset=");
		        }

		        if ( contentType != null ){
		        if (characterEncoding != null && contentType.indexOf("charset") == -1) //$NON-NLS-1$
		        {
		            contentType += "; charset=" + characterEncoding; //$NON-NLS-1$
		        }
		        }

		        response.setContentType(contentType);		        
	
		        if ( exportBean instanceof String ){
			        writer = response.getWriter();
		            if (characterEncoding != null)
		            {
		                response.setContentLength(((String) exportBean).getBytes(characterEncoding).length);
		            }
		            else
		            {
		                response.setContentLength(((String) exportBean).getBytes().length);
		            }			        
			        writer.print((String)exportBean);	
			        response.flushBuffer();
		        } else {
		        	  byte[] content = (byte[]) exportBean;
		              response.setContentLength(content.length);
		              OutputStream out = response.getOutputStream();
		              out.write(content);
		              out.flush();		        	
		        }
		     
	        }catch(Exception ex){
	            response.setHeader("Cache-Control", "no-cache");
	            response.setDateHeader("Expires", 0);
	            response.setHeader("Pragma", "no-cache");
	        	response.setContentType("text/xml;charset=" + request.getCharacterEncoding());	        	
	          final String msg = ex.getMessage();
	          final String resultJson = "{ msg : '" + StringUtils.escapeJavaScript(msg) + "' }";
	          if ( logger.isErrorEnabled() ){
	        	  logger.error(ex);
	          }
	          if ( writer != null){
	            writer.print(resultJson);
	          }
	        }
	        response.flushBuffer();
	        
	        return;
			
		}
		
		//不是ajax请求.
		if ( TableUtils.isAjaxRequest(request) == false ){
			super.doFilter(pRequest, pResponse, pFilterChain);
			return;
		}
		logger.debug("新的处理方式!.................");
		//下面是ajax请求.
		/**
		 * 这是要执行导出的table id, 
		 * @fixme: 下次把参数名修改下，叫exportTable
		 */
		String exportTable = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
		Map exportBeanMap = new HashMap();
		request.setAttribute(TableConstants.EXPORT_BEAN_PREFIX + exportTable, exportBeanMap);
        //response.setContentType("application/msexcel;charset=utf-8");
        BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
    
        /**
         * 说明:导出部分代码大部分来自displaytag
         */
        PrintWriter writer = null;
        try{
	      pFilterChain.doFilter(request, bufferResponseWrapper);
	      //String resultData = AjaxUtil.getAjaxData(exportTable, buffer);
	        Object  exportBean = exportBeanMap.get(TableConstants.REPORT_CONTENT_KEY);
	        if (exportBean == null)
	        {
	            if (logger.isDebugEnabled())
	            {
	            	logger.debug("Filter is enabled but exported content has not been found. Maybe an error occurred?");
	            }

	            response.setContentType(bufferResponseWrapper.getContentType());
	            PrintWriter out = response.getWriter();
	            out.write(bufferResponseWrapper.getBuffer());
	            out.flush();
	            return;
	        }
	        // clear headers
	        if (!response.isCommitted())
	        {
	            response.reset();
	        }
	        
	        response.setHeader("Content-disposition","attachment; filename=datas.xls"); 
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setHeader("Pragma", "no-cache");
	        String contentType = (String) exportBeanMap.get(TableConstants.REPORT_CONTENT_TYPE_KEY);
	        String characterEncoding = request.getCharacterEncoding();//bufferResponseWrapper.getCharacterEncoding();
	        String wrappedContentType = bufferResponseWrapper.getContentType();

	        if (wrappedContentType != null && wrappedContentType.indexOf("charset") > -1)
	        { 
	            // charset is already specified (see #921811)
	            characterEncoding = org.apache.commons.lang.StringUtils.substringAfter(wrappedContentType, "charset=");
	        }

	        if ( contentType != null ){
	        if (characterEncoding != null && contentType.indexOf("charset") == -1) //$NON-NLS-1$
	        {
	            contentType += "; charset=" + characterEncoding; //$NON-NLS-1$
	        }
	        }

	        response.setContentType(contentType);		        

	        if ( exportBean instanceof String ){
		        writer = response.getWriter();
			      String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
			      String resultData = AjaxUtil.getAjaxData(refreshZone, (String)exportBean);
			      if (logger.isDebugEnabled()){
			    	  logger.debug("提取son之前j数据=" + (String)exportBean);
			    	  logger.debug("json数据=" + resultData);
			      }

	            if (characterEncoding != null)
	            {
	                response.setContentLength((resultData).getBytes(characterEncoding).length);
	            }
	            else
	            {
	                response.setContentLength((resultData).getBytes().length);
	            }			        
		        writer.print(resultData);	
		        writer.flush();
	        } else {
	        	  byte[] content = (byte[]) exportBean;
	              response.setContentLength(content.length);
	              OutputStream out = response.getOutputStream();
	              out.write(content);
	              out.flush();		        	
	        }
	     
        }catch(Exception ex){
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
        	response.setContentType("text/xml;charset=" + request.getCharacterEncoding());	        	
          final String msg = ex.getMessage();
          final String resultJson = "{ msg : '" + StringUtils.escapeJavaScript(msg) + "' }";
          if ( logger.isErrorEnabled() ){
        	  logger.error(ex);
          }
          if ( writer != null){
            writer.print(resultJson);
          }
        }
        response.flushBuffer();
        
        return;
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Pragma", "no-cache");
//    	response.setContentType("text/xml;charset=" + request.getCharacterEncoding());
//    	
//        BufferResponseWrapper bufferResponseWrapper = new BufferResponseWrapper(response);
//        PrintWriter writer = null;
//        try{
//        	if ( logger.isDebugEnabled() ){
//        	  logger.debug("字符编码方式是:" + response.getCharacterEncoding());
//        	}
//        	writer = response.getWriter();	
//	      pFilterChain.doFilter(request, bufferResponseWrapper);
//	      String buffer = bufferResponseWrapper.getBuffer();
//	      String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
//	      String resultData = AjaxUtil.getAjaxData(refreshZone, buffer);
//	      if (logger.isDebugEnabled()){
//	    	  logger.debug("提取son之前j数据=" + buffer);
//	    	  logger.debug("json数据=" + resultData);
//	      }
//	        writer.print(resultData);
//	        response.flushBuffer();	      
//        }catch(Exception ex){
//          final String msg = ex.getMessage();
//          final String resultJson = "{ msg : '" + StringUtils.escapeJavaScript(msg) + "' }";
//          if ( logger.isErrorEnabled()){
//            logger.error(ex);
//          }
//          if ( writer != null )writer.print(resultJson);
//        }

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

}
