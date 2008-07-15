/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Context.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.collections.FastHashMap;

/**
 * xkins运行上下文，
 * @author 黄云辉
 *
 */
public class Context {
    //~ Instance fields ----------------------------------------------------------------------------

    protected Map parameters = null;
    protected ServletResponse servletResponse = null;
    protected ServletRequest servletRequest = null;
	protected Xkins xkins = null;



    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Context() {
    }

    /**
     * Constructor que recibe el pageContext y de ah� saca el request, session y servletContext.
     * @param pctx
     */
    public Context(PageContext pctx) {
        this.servletRequest = pctx.getRequest();
        this.servletResponse = pctx.getResponse();
    }

    /**
     * Este constructor recibe el request y el servlet context y lo popula a las variables del context.
     * @param request
     * @param sc
     */
    public Context(ServletRequest request, ServletResponse response) {
        this.servletRequest = request;
        this.servletResponse = response;
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Devuelve los par�metros del contexto.
     * @return
     */
    public Map getParameters() {
        if (this.parameters == null) {
            this.parameters = new FastHashMap();
        }

        return this.parameters;
    }

    /**
     * Obtiene el request que tiene el context.
     * @return Returns a ServletRequest
     */
    public ServletRequest getServletRequest() {
        return servletRequest;
    }
    
    public HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest)servletRequest;
    }
    

	/**
	 * Obtiene el response que tiene el context.
	 * @return Returns a ServletRequest
	 */
	public ServletResponse getServletResponse() {
		return servletResponse;
	}
	public HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse)servletResponse;
	}


    /**
     * Devuelve la sesi�n del contexto.
     * @return Returns a HttpSession
     */
    public HttpSession getSession() {
		if(this.getServletRequest()!=null && this.getServletRequest() instanceof HttpServletRequest)
        	return ((HttpServletRequest)this.getServletRequest()).getSession();
        
        return null;
    }

	/**
	 * Devuelve el servletContext del contexto.
	 * @return Returns a HttpSession
	 */
	public ServletContext getServletContext() {
		if(this.getServletRequest()!=null && this.getServletRequest() instanceof HttpServletRequest)
		    if(((HttpServletRequest)this.getServletRequest()).getSession() !=null)
		        return ((HttpServletRequest)this.getServletRequest()).getSession().getServletContext();
	
		return null;
	}
	

    /**
     * Agrega un par�metro al contexto. Los par�metros son los utilizados por el Processor al procesar el template.
     * @param name
     * @param value
     */
    public void addParameter(String name, String value) {
        this.getParameters().put(name, value);
    }

    /**
     * Agrega muchos par�metros a la vez en el contexto.
     * @param parameters
     */
    public void addParameters(Map parameters) {
        this.getParameters().putAll(parameters);
    }

    /**
     * Elimina los par�metros del contexto.
     *
     */
    public void clearParameters() {
        this.getParameters().clear();
    }
    
	protected void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * @return
	 */
	public Xkins getXkins() {
		return xkins;
	}
	
	public Skin getSkin(String pSkinName) {
		return xkins.getSkin(pSkinName);
	}
	

	/**
	 * @param xkins
	 */
	public void setXkins(Xkins xkins) {
		this.xkins = xkins;
	}


}
