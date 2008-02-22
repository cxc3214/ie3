package net.jcreate.e3.tree.support;


import java.io.InputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

/**
 * 改接口的设计来自jmesa 
 * 主页：http://code.google.com/p/jmesa/
 * @author 黄云辉
 *
 */
public interface WebContext {
	
	public InputStream getResourceAsStream(String pPath);
    public Object getApplicationInitParameter(String name);

    public Object getApplicationAttribute(String name);

    public void setApplicationAttribute(String name, Object value);

    public void removeApplicationAttribute(String name);

    public Object getPageAttribute(String name);

    public void setPageAttribute(String name, Object value);

    public void removePageAttribute(String name);

    public String getParameter(String name);

    public Map getParameterMap();

    public void setParameterMap(Map parameterMap);

    public Object getRequestAttribute(String name);

    public void setRequestAttribute(String name, Object value);

    public void removeRequestAttribute(String name);

    public Object getSessionAttribute(String name);

    public void setSessionAttribute(String name, Object value);

    public void removeSessionAttribute(String name);

    public Writer getWriter();

    public Locale getLocale();

    public void setLocale(Locale locale);

    public String getContextPath();
    
    public String getRealPath(String path);
}
