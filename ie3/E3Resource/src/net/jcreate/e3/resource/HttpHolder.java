package net.jcreate.e3.resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源Holder,保存Request,Response和ServletContext
 * @author 黄云辉
 *
 */
public class HttpHolder {
  private static ThreadLocal requestThreadLocal = new ThreadLocal();
  private static ThreadLocal responseThreadLocal = new ThreadLocal();
  private static ServletContext servletContext = null;
  
  private HttpHolder(){
  }
  
  public static void setRequest(HttpServletRequest pRequest){
	  requestThreadLocal.set(pRequest);
  }
  
  public static void setResponse(HttpServletResponse pResponse){
	  responseThreadLocal.set(pResponse);  
  }
  
  public static void setServletContext(ServletContext pServletContext){
	  servletContext =  pServletContext;
  }
  public static ServletContext getServletContext(){
	  return servletContext;
  }
  
  
  public static HttpServletRequest getRequest(){
	  return (HttpServletRequest)requestThreadLocal.get();
  }
  
  public static HttpServletResponse getResponse(){
	  return (HttpServletResponse)responseThreadLocal.get();
  }
  
}
