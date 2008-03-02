/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)Skin.java
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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import net.jcreate.xkins.resources.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.collections.Predicate;


/**
 * Representacion de un Skin. Un Skin contiene paths, resources y tempaltes y posee todos los elementos
 * necesarios para la UI de una aplicaci�n.<br>
 * El skin tiene los siguientes atributos:
 * <ul>
 *     <li>name, con el nombre del Skin</li>
 *     <li>url, el path relativo al context path donde se ubicar�n los archivos para ese Skin</li>
 *     <li>extends, con el nombre del skin que es extendido por el actual.</li>
 * </ul>
 * Un Skin puede extender otro Skin, heredando sus templates, paths y templates. <br>
 * En una herencia de Skins, se puede sobreescribir algunas partes o todo lo del skin. Por ejemplo:
 * <pre>
 * &lt;skin name="default" url="/skins/default"&gt;
 *         &lt;path name="images" url="/images"/&gt;
 *         &lt;template name="table"&gt;
 *             &lt;!-- pro:width --&gt;
 *             &lt;content&gt;&lt;![CDATA[
 *                 &lt;table border="0" cellspacing="1" cellpadding="1" bgcolor="${res:bgcolor}" width="${pro:width}"&gt;
 *                     ${jsp:bodyContent}
 *                     &lt;img src="${res:imagen}"/&gt;
 *                 &lt;/table&gt;
 *             ]]&gt;&lt;/content&gt;
 *         &lt;element name="imagen" path="images" url=<b>"/img.jpg"</b>/&gt;
 *        &lt;constant name="bgcolor" value=<b>"#556677"</b>/&gt;
 *         &lt;/template&gt;
 * &lt;/skin&gt;
 * &lt;skin name="segundo" url="/skins/segundo" extends="default"&gt;
 *         &lt;template name="table"&gt;
 *             &lt;element name="imagen" path="images" url=<b>"/segImg.gif"</b>/&gt;
 *            &lt;constant name="bgcolor" value=<b>"#554497"</b>/&gt;
 *         &lt;/template&gt;
 * &lt;/skin&gt;
 * </pre>
 * Al procesar el template "table" con el skin default se ver�a este HTML:
 * <pre>
 *         &lt;table border="0" cellspacing="1" cellpadding="1" bgcolor=<b>"#556677"</b> width="75%"&gt;
 *             Esto es el contenido original del taglib
 *             &lt;img src=<b>"/skins/default/images/img.jpg"</b>/&gt;
 *         &lt;/table&gt;
 * </pre>
 * al procesarlo con el skin "segundo", el resultado ser�a:
 * <pre>
 *         &lt;table border="0" cellspacing="1" cellpadding="1" bgcolor=<b>"#554497"</b> width="75%"&gt;
 *             Esto es el contenido original del taglib
 *             &lt;img src=<b>"/skins/segundo/images/segImg.gif"</b>/&gt;
 *         &lt;/table&gt;
 * </pre>
 *
 * Los skins se definen en el archivo de definici�n.
 * @author Guillermo Meyer
 */
public class Skin {
    //~ Static fields/initializers -----------------------------------------------------------------

    /**
     * Constante utilizada para ubicar el attribute de la sesi�n que contiene el nombre del skin actual.
     */
    public static final String ATTR_SKIN_NAME = "ar.com.koalas.xkins.SkinName";
	public static final String UNDEFINED_NAME = "undefined";
    /**
     * Constante utilizada para ubicar el attributo de la sesi�n que contiene el nombre por default del Skin.
     */
    public static final String ATTR_DEFAULT_SKIN_NAME = "ar.com.koalas.xkins.DefaultSkinName";

    //~ Instance fields ----------------------------------------------------------------------------

    private Map paths = null;
    private Map resources = null;
    private Map templates = null;
    private Processor processor = null;
    private String _extends = null;
    private String name = null;
    private String url = null;
    private Xkins xkins = null;
    private boolean hasCheckedParentResources = false;
    private boolean hasCheckedParentServers = false;
	private String definition = null;
	private Map templatesModified = null;
	private boolean defaultSkin=false;
	private Map servers;

    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Skin() {
    	super();
    }
	
	public Skin(String name) {
		this();
		this.name=name;
	}
	
	public Skin(String name, Class templateProcessor) {
		this(name);
		this.setProcessor(new Processor(templateProcessor));
	}

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Devuelve todos los recursos, inclusive los del padre si extiende.
     * @return
     */
    public Map getAllResources() {
        Map ret = new HashMap();
        ret.putAll(this.getResources());
        if (!hasCheckedParentResources && (this.isExtending())) { //si extiende, busca en el template padre
            hasCheckedParentResources = true;

            Map parentRes = this.getExtendedSkin().getAllResources();
            Iterator it = parentRes.keySet().iterator();
            while (it.hasNext()) {
                String keyRes = (String) it.next();
                ret.put(keyRes, this.getResource(keyRes));
            }
        }
        return ret;
    }

    /**
     * Asigna el nombre del skin que es extendido por el skin actual.
     * @param _extends
     */
    public void setExtends(String _extends) {
        this._extends = _extends;
    }

    /**
     * Devuelve el nombre del skin que es extendido por el skin actual.
     * @return
     */
    public String getExtends() {
        return this._extends;
    }

	/**
	 * indica si el skin extiende a otro
	 * @return
	 */
	public boolean isExtending() {
		return this.getExtends()!=null;
	}

	/**
	 * Devuelve el Skin que extiende el actual. Si no extiene, se devuelve a si mismo
	 * @return
	 */
	public Skin getExtendedSkin(){
		if(this.isExtending()) {
			if(this.getXkins().getSkin(this.getExtends())!=null)
				return this.getXkins().getSkin(this.getExtends());
			else {
				XkinsLogger.getLogger().error("Skin " + this.getName() + " should extend to " + this.getExtends() + " but this last one does not exists. Extends attribute will be cleaned.");
				this.setExtends(null);
			}
		} 
		return this;
	}

    /**
     * Asigna el nombre del Skin
     * @param s
     */
    public void setName(String s) {
        this.name = s;
    }

    /**
     * Devuelve el nombre del Skin
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Devuelve un Path dado el nombre. si no lo encuentra en el skin y el skin tiene extends, le pide
     * al skin padre el path. Si el padre le puede dar el path, lo clona y lo guarda para la
     * pr�xima vez. Asi se implementa la herencia.
     * @see Path
     * @param name
     * @return
     */
    public Path getPath(String name) {
        Object obj = this.getPaths().get(name);
        if (obj != null) {
            return (Path) obj;
        } else {
            obj = ((this.isExtending())
                   ? this.getExtendedSkin().getPath(name) : null);

            Path p = null;
            if (obj != null) {
                //clona el path
                try {
                    p = (Path) ((Path) obj).clone();
                    p.setSkin(this);
                    this.getPaths().put(name, p); //cachea la referencia para la pr�xima vez...
                } catch (CloneNotSupportedException cnse) {
                    cnse.printStackTrace();
                }
            }

            return p;
        }
    }

    /**
     * Devuelve todos los paths del Skin. No incluye los paths del extends si declara extends.
     * @return
     */
    public Map getPaths() {
        if (this.paths == null) {
            this.paths = new Hashtable();
        }
        return this.paths;
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
            if (this.isExtending()) { //si extiende, busca en el skin padre

                Skin sk = this.getExtendedSkin();
                processor = sk.getProcessor();
            }
        }
        return processor;
    }

    /**
     * Devuelve un Resource dado el nombre. Si no lo encuentra y tiene extends, le pide el resource al Skin padre.
     * Si lo encuentra en el padre, lo clona y lo guarda para la pr�xima vez. Esto permite heredar resources entre Skins.
     * @see Resource
     * @param name
     * @return
     */
    public Resource getResource(String name) {
        Resource obj = (Resource) this.getResources().get(name);
        if (obj != null) {
            return obj;
        } else {
            obj = ((this.isExtending())
                   ? this.getExtendedSkin().getResource(name) : null);

            Resource r = null;
            if (obj != null) {
                //clona el Resource
                try {
                    r = (Resource) obj.clone();
                    r.setTemplate(null); //implica globa resource
                    r.setSkin(this);
                    this.getResources().put(name, r); //cachea la referencia para la pr�xima vez...
                } catch (CloneNotSupportedException cnse) {
                    cnse.printStackTrace();
                }
            }
            return r;
        }
    }

    /**
     * Devuelve los resources del skin. Si tiene extends, no devuelve los resources del padre.
     * @return
     */
    public Map getResources() {
        if (this.resources == null) {
            this.resources = new Hashtable();
        }
        return this.resources;
    }

    /**
     * Devuelve el template del skin dado el nombre. Si no lo encuentra y tiene extends, le pide al skin padre
     * el template. Si lo encuentra, lo clona y lo guarda para la pr�xima vez. De esta manera se implementa la herencia
     * de templates.
     * @param name
     * @return
     */
    public Template getTemplate(String name) {
        Object obj = this.getTemplates().get(name);
        if (obj != null) {
        	Template tmpObj = (Template) obj;
        	//verifica la composici�n de los tempaltes
        	if(this.getXkins()!=null && this.getXkins().getSkin(tmpObj.getSrcSkin())!=null) {
				Template srcTemplate = this.getXkins().getSkin(tmpObj.getSrcSkin()).getTemplate(name);
				if(srcTemplate!=null) {
					tmpObj.setDelegate(srcTemplate);					
				}
        	}
            return tmpObj;
        } else {
            obj = ((this.isExtending())
                   ? this.getExtendedSkin().getTemplate(name) : null);

            Template tmpl = null;
            if (obj != null) {
                //clona el template
                try {
                    tmpl = (Template) ((Template) obj).clone();
                    tmpl.setSkin(this);
                    this.getTemplates().put(name, tmpl); //cachea la referencia para la pr�xima vez...
                    //marca como modificado
                    this.addTemplateModified(tmpl);				
                } catch (CloneNotSupportedException cnse) {
                    cnse.printStackTrace();
                }
            }
            return tmpl;
        }
    }

    /**
     * Devuelve los templates disponibles en el skin. No incluye los del padre si tiene extends.
     * @return
     */
    public Map getTemplates() {
        if (this.templates == null) {
            this.templates = new Hashtable();
        }

        return this.templates;
    }

    /**
     * Asigna la URL del skin. La URL es el path relativo donde se encontrar�n los archivos usados por el Skin.<br>
     * Por ejemplo, si URL es "/skins/default", los archivos se buscar�n dentro de esa URL.
     * @param s
     */
    public void setUrl(String s) {
        this.url = s;
    }

    /**
     * devuelve la URL del skin.
     * @return
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Asigna la referencia a la colecci�n de todos los Skins disponibles.
     * @param xkins
     */
    public void setXkins(Xkins xkins) {
        this.xkins = xkins;
    }

    /**
     * Devuelve la referencia a todos los skins disponibles.
     * @return
     */
    public Xkins getXkins() {
        return this.xkins;
    }

    /**
     * Agrega un path al skin. Si el path ya existe en el skin lo sobreescribe.
     * @see Path
     * @param p
     */
    public void addPath(Path p) {
        this.getPaths().put(p.getName(), p);
    }

	public void addPaths(Map paths) {
		this.getPaths().putAll(paths);
	}

    /**
     * Agrega un resource al skin. Si el resource ya existe, lo sobreescribe.
     * @see Resource
     * @param res
     */
    public void addResource(Resource res) {
        this.getResources().put(res.getName(), res);
    }

    /**
     * Agrega m�ltiples resources.
     * @see Resource
     * @param res
     */
    public void addResources(Map res) {
        this.getResources().putAll(res);
    }

    /**
     * Agrega un tempalte al skin. Si el template existe lo sobreescribe.
     * @param elem
     */
    public void addTemplate(Template elem) {
        elem.setSkin(this);
        this.getTemplates().put(elem.getName(), elem);
        this.addTemplateModified(elem);
    }

    /**
     * Copia los templates, resources y paths del skin actuial al destino. Varifica que no existan las cosas que
     * copia, sobreescribiendo o no seg�n corresponda. No copia el processor
     * @param dest
     * @param overwrite
     */
    public void copyToSkin(Skin dest, boolean overwrite) {
        //Copio los paths
        Iterator it = this.getPaths().keySet().iterator();
        String name = null;
        while (it.hasNext()) {
            name = (String) it.next();
            if ((dest.getPaths().get(name) == null) || overwrite) {
                dest.addPath((Path) this.getPaths().get(name));
            }
        }

        //Copio los resources
        it = this.getResources().keySet().iterator();
        while (it.hasNext()) {
            name = (String) it.next();
            if ((dest.getResources().get(name) == null) || overwrite) {
                dest.addResource((Resource) this.getResources().get(name));
            }
        }

        //Copio los templates
        it = this.getTemplates().keySet().iterator();
        while (it.hasNext()) {
            name = (String) it.next();
            if ((dest.getTemplates().get(name) == null) || overwrite) {
                dest.addTemplate((Template) this.getTemplates().get(name));
            }
        }
    }

    /**
     * Devuelve un string con HTML para la visualizaci�n del skin.
     * @return
     */
    public String printHTML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>Skin: [");
        sb.append(this.getName());
        sb.append("]</h1>");
        if (this.getProcessor() != null) {
            sb.append("<h2>Processor:</h2>");
            sb.append(this.getProcessor().getType());
        }
        sb.append("<h2>Paths:</h2>");

        Iterator it;

        if ((this.paths != null) && (this.paths.size() > 0)) {
            it = this.paths.keySet().iterator();
            while (it.hasNext()) {
                sb.append(this.getPath(((String) it.next())).printHTML());
                sb.append("<br>");
            }
        }

        sb.append("<h2>Templates:</h2>");
        if ((this.templates != null) && (this.templates.size() > 0)) {
            it = this.templates.keySet().iterator();
            while (it.hasNext()) {
                String tmpName = (String) it.next();
                sb.append(this.getTemplate(tmpName).printHTML());
                sb.append("<br>");
            }
        }
        return sb.toString();
    }

    /**
     * el toString devuelve el name del skin.
     */
    public String toString() {
        return this.name;        
    }
	/**
	 * @return
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * @param string
	 */
	public void setDefinition(String string) {
		definition = string;
	}
	
	/**
	 * Carga la definici�n en caso de estar en otro file
	 *
	 */
	public void loadDefinition() {
		
	}

	public String getSkinDefinition() {
		if(this.getDefinition()==null)
			return null;
		else
			return this.getUrl() + this.getDefinition();
	}

	/**
	 * @return
	 */
	private Map getTemplatesModified() {
		if(templatesModified==null)
			templatesModified = new FastHashMap();
		return templatesModified;
	}

	/**
	 * @param map
	 */
	private void setTemplatesModified(Map map) {
		templatesModified = map;
	}
	
	public void addTemplateModified(Template tmp) {
		this.getTemplatesModified().put(tmp.getId(), tmp.getId());
	}

	/**
	 * Indica si el template con ese id est� modificado o no.
	 * @param id
	 * @return
	 */
	public boolean isTemplateModified(String id) {
		this.getTemplate(Template.getNameFromID(id));		
		Object obj = this.getTemplatesModified().remove(id); 
		return obj!=null;
	}
	/**
	 * el tipo de Skin es el nombre de su padre + "." + nombre
	 * @return
	 */
	public String getType() {
		String parent = "";
		if(this.isExtending())
			parent = this.getExtendedSkin().getType();
		
		return parent + this.getName();
	}
	/**
	 * Indica si el skin es default
	 * @return
	 */
	public boolean isDefaultSkin() {
		return defaultSkin;
	}

	/**
	 * @param b
	 */
	public void setDefaultSkin(boolean b) {
		defaultSkin = b;
	}
	
	/**
	 * Devuelve una Collection de templates con los que contengan el group dado en el skin.  
	 * @param groupName
	 * @return
	 */	
	public Collection findTemplatesByGroup(final String groupName) {		
		return CollectionUtils.select(this.templates.values(), new Predicate() {
			public boolean evaluate(Object arg0) {
				String group=((Template)arg0).getGroup();
				return groupName.equals(group);
			}
		});	
	}

	public void addServer(Server server) {
		this.getServers().put(server.getName(), server);		
	}
	
	public void addServer(Map servers) {
		this.getServers().putAll(servers);		
	}	
	public Map getServers() {
		if(this.servers==null)
			this.servers=new Hashtable();

		if(this.isExtending() && !hasCheckedParentServers) {
		    Map parent = this.getExtendedSkin().getServers();
		    Map backupServers = new Hashtable(this.servers);
		    this.servers.putAll(parent);
		    this.servers.putAll(backupServers);
		    backupServers=null;
		    hasCheckedParentServers=true;
		}
		return this.servers;
	}

	

}
