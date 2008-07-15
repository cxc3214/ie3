/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Path.java
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

import javax.servlet.http.HttpServletRequest;


/**
 * Un path representa una URL dentro del contexto del Skin. Se utiliza para facilitar el armado de las URLs
 * seg�n el skin. Los paths son usados por los elements y pueden ser obtenidos desde el XkinProcessor para
 * otros usos.<br>
 * Si un path se declara como absoluto (absolute="true") no utiliza el path del contexto de skin.
 * @author Guillermo Meyer
 */
public class Path 
        implements Cloneable {
    //~ Instance fields ----------------------------------------------------------------------------

    private Skin skin = null;
    private String absolute = "false";
    private String name = null;
    private String url = null;
    private String server = null;

    //~ Constructors -------------------------------------------------------------------------------

    /**
     *Constructor por defecto.
     */
    public Path() {
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Sets the absolute.
     * @param absolute The absolute to set
     */
    public void setAbsolute(String absolute) {
        this.absolute = absolute;
    }

    /**
     * Returns the absolute.
     * @return String
     */
    public String getAbsolute() {
        return absolute;
    }

    /**
     * Devuelve la URL completa. Esto significa que devuelve la URL del Skin mas la URL del path. No agrega el
     * context path de la aplicaci�n (<code>request.getContextPath()</code>). Si el path es absoluto, devuelve la
     * url del path sin anteponer la url del Skin.
     * @return
     */
	public String getFullUrl() {
		Context ctx = ContextHolder.getContext();
		String aux = "";	
		boolean isAbsolute = "true".equalsIgnoreCase(this.getAbsolute());
		if(this.getServer()==null) {
		    if(ctx != null && ctx.getServletRequest()!=null) {
				aux+=((HttpServletRequest) ctx.getServletRequest()).getContextPath();
			}
			if(!isAbsolute) {
				aux+=this.getSkin().getUrl();
			}
		} else {
		    Server srv = (Server)this.getSkin().getServers().get(this.getServer());
		    if(srv!=null) {
		        aux = srv.getUrl();
				if(!isAbsolute) {
					aux+=this.getSkin().getUrl();
				}
		    }
		}
		return aux + this.getUrl();
	}

    /**
     * Asigna el nombre del path
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve el nombre del Path
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Asigna el skin al que pertenece el path.
     * @param skin
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    /**
     * Devuelve el Skin al que pertenece el path.
     * @return
     */
    public Skin getSkin() {
        return this.skin;
    }

    /**
     * Asigna el valor de la URL del path.
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Devuelve el valor de la URL
     * @return
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Clona el Path.
     */
    public Object clone()
            throws CloneNotSupportedException {
        Object obj = super.clone();
        return obj;
    }

    /**
     * genera el HTML para mostrar el Path.
     * @return
     */
    public String printHTML() {
        return this.name + ": " + this.getFullUrl();
    }

    /**
     * El toString es el nombre del path
     */
    public String toString() {
        return this.name;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
}
