/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Xkins.java
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

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import net.jcreate.xkins.events.XkinsEvent;
import net.jcreate.xkins.events.XkinsEventListener;


/**
 * 皮肤容器,
 * @author 黄云辉
 *
 */
public class Xkins {
    //~ Static fields/initializers -----------------------------------------------------------------

    public static final String ATTR_SKINS = Xkins.class.getName();

    //~ Instance fields ----------------------------------------------------------------------------

    //皮肤map,key皮肤名称,value skin对象
    private Map skins = null;
    //监听器列表
	private Collection listeners;
	private String type=null;	
	private String skinType=null;
	//处理器
	private Map processors;
	
    //~ Constructors -------------------------------------------------------------------------------

    /** Creates a new instance of XkinsInit */
    public Xkins() {
    }

    /**
     * 获取指定名称的皮肤对象
     * @param name
     * @return
     */
    public Skin getSkin(String name) {
    	if(name==null){
    		return null;
    	}    		
        return (Skin) this.getSkins().get(name);
    }

    /**
     * 获取所有皮肤
     * @return
     */
    public Map getSkins() {
		if (this.skins == null)
			this.skins = new Hashtable();
		
        return this.skins;
    }

    /**
     * 添加一个皮肤
     * @param s
     */
	public void addSkin(Skin s) {
		s.setXkins(this);
		this.addSkin(s, true);
	}
	

	/**
	 * 添加皮肤
	 * @param s　
	 * @param overwrite　是否覆盖
	 */
    public void addSkin(Skin s, boolean overwrite) {
    	Skin sk = (Skin) this.getSkins().get(s.getName());
        if (sk != null) {
            //el skin ya existe, 
            //le agrega al skin los templates, paths y resources generales.
            s.copyToSkin(sk, overwrite);
        } else {
			this.getSkins().put(s.getName(), s);
        }
    }

    /**
     * 打印皮肤定义
     * @return
     */
    public String printHTML() {
        StringBuffer sb = new StringBuffer();
        if ((this.skins != null) && (this.skins.size() > 0)) {
            Iterator it = this.skins.keySet().iterator();
            while (it.hasNext()) {
                sb.append("\n\n" + this.getSkin(((String) it.next())).printHTML());
            }
        }

        //Devuelve lo correcto para verse en HTML
        return sb.toString();
    }

    /**
     * 添加reload监听器
     * @param xel
     */
    public void addEventListener(XkinsEventListener xel) {
    	this.getListeners().add(xel);	
    }

    /**
     * 获取监听器列表
     * @return
     */
	private Collection getListeners() {
		if(this.listeners==null)
			this.listeners=new Vector();
		
		return this.listeners;
	}

	/**
	 * 发送监听事件
	 * @param event
	 */
    public void sendEvent(XkinsEvent event) {
    	Iterator it = this.getListeners().iterator();
    	while(it.hasNext())
			((XkinsEventListener)it.next()).actionPerformed(event);
    }
    

    /**
     * 清除皮肤
     */
    public void clear() {
    	this.getSkins().clear();
    }

    /**
     * 检查皮肤定义文件是否修改.  
     * @param id
     * @return
     */
	public boolean isTemplateModified(String id) {
		String skinName = id.substring(0, id.indexOf('.'));
		Skin skin = this.getSkin(skinName);
		return skin!=null && skin.isTemplateModified(id);
	}
	
	/**
	 * 获取默认的皮肤名称
	 */
	public String getDefaultSkinName() {
		Iterator it = this.getSkins().keySet().iterator();
		while(it.hasNext()) {
			String skinName = (String)it.next();
			if(this.getSkin(skinName).isDefaultSkin())
				return skinName;
		}
		return null;
	}

	/**
	 * 获取默认皮肤
	 * @return
	 */
	public Skin getDefaultSkin() {
		return this.getSkin(this.getDefaultSkinName());
	}
	/**
	 * Clase para todos los Skins de este Xkin
	 * @return
	 */
	public String getType() {
		if(type==null)
			return Skin.class.getName();
		else
			return type;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @return
	 */
	public String getSkinType() {
		return skinType;
	}

	/**
	 * @param string
	 */
	public void setSkinType(String string) {
		skinType = string;
	}

	/**
	 * 添加处理器
	 * @param processor
	 */
	public void addProcessor(Processor processor) {
		this.getProcessors().put(processor.getId(), processor);		
	}
	
	public void addProcessors(Map processors) {
		this.getProcessors().putAll(processors);		
	}	
	public Map getProcessors() {
		if(this.processors==null)
			this.processors=new Hashtable();
		
		return this.processors;
	}
}
