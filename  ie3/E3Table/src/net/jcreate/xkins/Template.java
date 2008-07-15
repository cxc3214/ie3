/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Template.java
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import net.jcreate.xkins.resources.Resource;


public class Template
        implements Cloneable {
    //~ Instance fields ----------------------------------------------------------------------------

    private Map resources = null;
    //模板的处理器
    private Processor processor = null;
    //模式所属皮肤
    private Skin skin = null;
    //
    private String cachedContent = null;
    //模板内容
    private Content content = null;
    //模板的名字
    private String name = null;
    
    private boolean hasCheckedParentResources = false;
    private String srcSkin=null; 
    private String group=null;
	private Template delegate=null;

	public static final String ID_SEP_CHAR = ".";

    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Template() {
    	super();
    	this.setDelegate(this);
    }
    
    public Template(String content) {
    	this();
    	this.getDelegate().setContent(new Content(content));
    	
    }

	public Template(Class templateProcessor) {
		this();
		this.getDelegate().setProcessor(new Processor(templateProcessor));
	}

	public Template(String content, Class templateProcessor) {
		this(content);
		this.getDelegate().setProcessor(new Processor(templateProcessor));
	}

	public Template(Template delegate) {
		super();
		this.setDelegate(delegate);
	}


    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Devuelve todos los recursos, inclusive los del padre y los del skin si extiende.
     * @return
     */
    public Map getAllResources() {
        Map ret = new HashMap();
        if (!hasCheckedParentResources && (this.getDelegate().getSkin().isExtending())) { //si extiende, busca en el template padre
            hasCheckedParentResources = true;

            Template tmp = this.getDelegate().getSkin().getExtendedSkin().getTemplate(this.getDelegate().getName());
            if (tmp != null) {
                Map parentRes = tmp.getAllResources();
                Iterator it = parentRes.keySet().iterator();
                while (it.hasNext()) {
                    String keyRes = (String) it.next();
                    ret.put(keyRes, this.getDelegate().getResource(keyRes));
                }
            }
        }

        //copia los del skin
        ret.putAll(this.getDelegate().getSkin().getAllResources());
        //pisa con los propios
        ret.putAll(this.getDelegate().getResources());
        return ret;
    }

    /**
     * Sets the cachedContent.
     * @param cachedContent The cachedContent to set
     */
    public void setCachedContent(String cachedContent) {
		this.getDelegate().cachedContent = cachedContent;
    }

    /**
     * Devuelve el contenido cacheado.
     * @return Returns a String
     */
    public String getCachedContent() {
        return cachedContent;
    }

    /**
     * Asigna el contenido del template
     * @param s
     */
    public void setContent(Content c) {
		this.getDelegate().content = c;
    }
    
    /**
     * Dada esta url, crea un Content y lo asigna al template
     * @param url
     */
    public void setContentURL(String url) {
		if(this.getDelegate().content == null) {
			Content cnt = new Content();
			cnt.setUrl(url);
			this.getDelegate().setContent(cnt);    	
		}
    }

    /**
     * Devuelve el contenido del template. Verifica si tiene contenido. Si no tiene, verifica si el
     * skin al que pertenece el template tiene padre (extends)-. Si tiene padre le pide el contenido al
     * template de mismo nombre del padre. Luego, lo guarda para la pr�xima vez
     * no tener que volver a buscarlo.  Esto permite efectuar la herencia de templates entre skins.
     * @return
     */
    public InputStream getContent() {
        Content obj = this.getDelegate().content;
        if (obj != null) {
        	InputStream ret = obj.getInputStream();
        	if(ret!=null)
        		return ret;
        }
        return ((this.getDelegate().getSkin().isExtending())
                ? this.getDelegate().getSkin().getExtendedSkin().getTemplate(this.getDelegate().name).getContent() : null);
    }

    /**
     * Asigna el nombre del template
     * @param s
     */
    public void setName(String s) {
		this.getDelegate().name = s;
    }

    /**
     * Devuelve el nombre del template
     * @return
     */
    public String getName() {
        return this.getDelegate().name;
    }

    /**
     * Permite setear el classname para el template processor  (TemplateProcessor) que por excepci�n
     * se utilizar� para procesar este template.
     * @param string
     */
    public void setProcessor(Processor proc) {
        processor = proc;
    }

    /**
     * Deveuvle el nombre de la clase que implementa TempalteProcessor.
     * @return
     */
    public Processor getProcessor() {
        if (processor == null) {
            //se fija si tiene padre para pedirselo
            if (this.getDelegate().getSkin().isExtending()) { //si extiende, busca en el template padre

                Template tmp = this.getDelegate().getSkin().getExtendedSkin().getTemplate(this.getDelegate().getName());
                if (tmp != null) {
                    processor = tmp.getProcessor();
                }
            }
        }
        return processor;
    }

    /**
     * Devuelve un recurso del template dado el nombre. Si no existe el recurso en el tempaltee, verifica si el
     * skin al que pertenece el template tiene padre (extends). Si tiene padre le pide el recurso al
     * template de mismo nombre del padre. Luego, clona el recurso y lo guarda para la pr�xima vez
     * no tener que volver a buscarlo. Esto permite efectuar la herencia de templates entre skins.
     * @param name
     * @return
     */
    public Resource getResource(String name) {
        Resource obj = (Resource) this.getDelegate().getResources().get(name);
        if (obj != null) {
            return obj;
        } else { //busca el resource en el skin

            Resource r = null;
            obj = this.getDelegate().getSkin().getResource(name);
            if (obj != null) { //si lo encuentra no lo copia, simplemente lo usa
                return obj;
            }

            if (obj == null) { //busca en el padre
                obj = ((this.getDelegate().getSkin().isExtending())
                       ? this.getDelegate().getSkin().getExtendedSkin().getTemplate(this.getDelegate().name).getResource(name) : null);
            }

            if (obj != null) {
                //clona el Resource
                try {
                    r = (Resource) obj.clone();
                    r.setTemplate(this);
					this.getDelegate().getResources().put(name, r); //cachea la referencia para la pr�xima vez...
                } catch (CloneNotSupportedException cnse) {
                    cnse.printStackTrace();
                }
            }
            return r;
        }
    }

    /**
     * Devuelve los recursos actuales del template.
     * @return
     */
    public Map getResources() {
        if (this.getDelegate().resources == null) {
			this.getDelegate().resources = new Hashtable();
        }
        return this.getDelegate().resources;
    }

    /**
     * Asigna el skin al que pertenece el tempalte.
     * @param skin
     */
    public void setSkin(Skin skin) {
		this.getDelegate().skin = skin;
    }

    /**
     * Devuelve el skin al que pertenece el template.
     * @return
     */
    public Skin getSkin() {
    	if(this.getDelegate().skin == null)
    		return new Skin(Skin.UNDEFINED_NAME);
        return this.getDelegate().skin;
    }

    /**
     * Agrega un recurso al template. Pueden ser constantes o elementos.
     * @param res
     */
    public void addResource(Resource res) {
		this.getDelegate().getResources().put(res.getName(), res);
    }

    /**
     * Agrega N recursos al template. Pueden ser constantes o elementos.
     * @param res
     */
    public void addResources(Map res) {
		this.getDelegate().getResources().putAll(res);
    }

    /**
     * clonado del tempalte para ser usado en la herencia. Clona el template y los recursos que tiene.
     */
    public Object clone()
            throws CloneNotSupportedException {
        //clona el template y los resoources
        Template newTemplate = new Template();
        newTemplate.setName(this.getDelegate().name);
        newTemplate.setContent(this.getDelegate().content);
        if ((this.getDelegate().resources != null) && (this.getDelegate().resources.size() > 0)) {
            Iterator it = this.getDelegate().resources.keySet().iterator();
            while (it.hasNext()) {
                Resource r = (Resource) this.getDelegate().getResource((String) it.next()).clone();
                r.setTemplate(newTemplate);
                newTemplate.addResource(r);
            }
        }
        return newTemplate;
    }

    /**
     * Devuelve c�digo HTML para visualizar el template.
     * @return
     */
    public String printHTML() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getDelegate().getName());
        if (this.getDelegate().getProcessor() != null) {
            sb.append("<h2>Processor:</h2>");
            sb.append(this.getDelegate().getProcessor().getType());
        }
        sb.append("<h2>Contenido:</h2>");
//        sb.append(this.getDelegate().reemplazar(this.getDelegate().reemplazar(this.getDelegate().getContent(), ">", "&gt;"), "<", "&lt;"));
        sb.append("<br>");

        Iterator it;
        sb.append("<h2>Recursos:</h2>");
        if ((this.getDelegate().resources != null) && (this.getDelegate().resources.size() > 0)) {
            it = this.getDelegate().resources.keySet().iterator();
            while (it.hasNext()) {
                sb.append(this.getDelegate().getResource(((String) it.next())).printHTML());
                sb.append("<br>");
            }
        }
        sb.append("<hr>");
        return sb.toString();
    }

    /**
     * el toString devuelve el name.
     */
    public String toString() {
        return this.getDelegate().name;
    }

    private String reemplazar(String src, String s1, String s2) {
        if (s2 != null) {
            StringBuffer sb = new StringBuffer(src);
            int i;
            while ((i = src.indexOf(s1)) != -1) {
                sb.replace(i, i + s1.length(), s2);
                src = sb.toString(); //recarga el string
            }
        }
        return src;
    }

	/**
	 * Forma de acceder al context static pero desde instancia
	 * @return
	 */
	public Context getContext() {
		return ContextHolder.getContext();
	}

	/**
	 * Devuelve el id del template formado por el nombre del skin y el nombre del template
	 * @return
	 */
	public String getId(){		
		return this.getDelegate().getSkin().getName() + ID_SEP_CHAR + this.getDelegate().getName();
	}
	/**
	 * @return
	 */
	public String getSrcSkin() {
		return srcSkin;
	}

	/**
	 * @param string
	 */
	public void setSrcSkin(String string) {
		srcSkin = string;
	}

	/**
	 * @return
	 */
	public Template getDelegate() {
		return delegate;
	}

	/**
	 * @param template
	 */
	public void setDelegate(Template template) {
		delegate = template;
	}

	public static String getNameFromID(String id) {
		return id.substring(id.indexOf(ID_SEP_CHAR) + 1);
	}

	/**
	 * @return Returns the group.
	 */
	public String getGroup() {
        if (this.getDelegate().group == null) {
            //se fija si tiene padre para pedirselo
            if (this.getDelegate().getSkin().isExtending()) { //si extiende, busca en el template padre
                Template tmp = this.getDelegate().getSkin().getExtendedSkin().getTemplate(this.getDelegate().getName());
                if (tmp != null) {
                	this.getDelegate().group = tmp.getGroup();
                }
            }
        }
		return this.getDelegate().group;
	}
	/**
	 * @param group The group to set.
	 */
	public void setGroup(String group) {
		this.getDelegate().group = group;
	}
}
