/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Content.java
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Esta clase representa el contenido de un template
 * @author Guillermo Meyer
 */
public class Content {
	private String url=null;
	private String data=null;
	private Template template=null;

	public Content() {
		super();
	}
	
	/**
	 * receive a string with the content or the url where the template content is
	 * @param data
	 */
	public Content(String data) {
		this();
		this.setData(data);
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * Devuelve el contenido del template.
	 * Si est� vacio y tiene url, levanda de la misma el contenido y lo devuelve.
	 * @return
	 */
	public InputStream getInputStream() {
		if((this.data==null || this.data.equals(""))&& this.url!=null) {
			URL urlToOpen = null;
			try {
				urlToOpen = this.getURLContent();
				if(urlToOpen!=null) {
					XkinsLoader.addConfigFilesTimeStamp(urlToOpen);
					return urlToOpen.openStream();
				}
			} catch(MalformedURLException mue) {
				XkinsLogger.getLogger().error("Error getting content input Stream (MalformedURLException)", mue);
			} catch(IOException io) {
				XkinsLogger.getLogger().error("Error getting content input Stream (IOException)", io);
			}
			return null;
		} else {
			try {
				/** 
				 * @fixme:要求根据配置文件进行转换处理
				 */
				return new ByteArrayInputStream(this.data.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

	private URL getURLContent() throws MalformedURLException {
		URL urlToOpen = null;
		//Abre del servlet context seg�n el path del skin
		if(this.url.startsWith("jndi://")) {
			String path = "/" + this.url.substring(7);
			urlToOpen = this.getClass().getResource(path);
		} else if(this.url.startsWith("http://") || this.url.startsWith("https://")) {			
			urlToOpen = new URL(this.url);
		} else {
			String path = this.getTemplate().getSkin().getUrl() + this.url;
			urlToOpen = new URL("file://" + XkinsLoader.getRealWebPath() + path);
		}				
		//intenta abrir directamente al protocolo
		if(urlToOpen==null)
			urlToOpen = new URL(this.url);				
		
		return urlToOpen;
	}

	/**
	 * Carga el String con los datos del contenido del template
	 * @param string
	 */
	public void setData(String string) {
		this.data=string;		
	}
	/**
	 * @return
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

}
