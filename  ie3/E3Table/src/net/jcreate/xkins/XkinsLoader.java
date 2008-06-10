/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsLoader.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import net.jcreate.xkins.events.XkinsLoadEvent;
import net.jcreate.xkins.resources.ConstantResource;
import net.jcreate.xkins.resources.ElementResource;
import net.jcreate.xkins.resources.Resource;

import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * Esta clase se encarga de cargar los Skins en el ServletContext. Utiliza el Digester de jakarta commons para efectuar
 * el parseo de los archivos de configuraci�n de los skins.
 * @author Guillermo Meyer
 */
public class XkinsLoader {
	//~ Static fields/initializers -----------------------------------------------------------------
	private static String config = "/xkin-definition.xml";

	private final Log log = LogFactory.getLog( XkinsLoader.class);
	//~ Instance fields ----------------------------------------------------------------------------

	private final String CONSTANT_CLASS_NAME = ConstantResource.class.getName();
	private final String ELEMENT_CLASS_NAME = ElementResource.class.getName();
	private final String PATH_CLASS_NAME = Path.class.getName();
	private final String PROCESSOR_CLASS_NAME = Processor.class.getName();
	private final String SERVER_CLASS_NAME = Server.class.getName();
	private final String RES_CLASS_NAME = Resource.class.getName();
	private final String SKIN_CLASS_NAME = Skin.class.getName();
	private final String TEMPLATE_CLASS_NAME = Template.class.getName();
	private final String XKINS_CLASS_NAME = Xkins.class.getName();
	private final String CONTENT_CLASS_NAME = Content.class.getName();
	private String dtd = "xkin-definition.dtd";
	private String registration = "-//Koalas Xkins//DTD Xkins Definition Configuration 1.0//EN";
	private int debug = 0;
	private ServletContext servletContext=null;
	private int autoReload=0;
	private static Map configFilesTimeStamp = null;
	private static Map definitionFilesTimeStamp = null;
	private Xkins xkinsLoaded = null;
	private static String realWebPath = null;
	private String skinType=null; //determina el tipo de Skin que debe controlarse

	//~ Constructors -------------------------------------------------------------------------------

	/** Creates a new instance of XkinsInit */
	public XkinsLoader() {
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param p_config DOCUMENT ME!
	 */
	public void setConfig(String p_config) {
		config = p_config;
	}

	/**
	 * Returns the config.
	 * @return String
	 */
	public static String getConfig() {
		return config;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param d DOCUMENT ME!
	 */
	public void setDebug(int d) {
		this.debug = d;
	}

	/**
	 * Carga los skins seg�n lo indicado en el par�metro.
	 * @param p_config
	 * @return
	 * @throws XkinsException
	 */
	public Xkins loadSkins(String p_config)
			throws XkinsException {
		config = p_config;
		return this.loadSkins();
	}

	/**
	 * Carga los skins indicados en el o los archivos de configuraci�n. Los nombres de los archivos deben indicarse
	 * separados por coma.
	 * @return
	 * @throws XkinsException
	 */
	public Xkins loadSkins()
			throws XkinsException {
		
		try {
			log.info("Loading Xkins from files " + XkinsLoader.getConfig() + "...");
			Iterator it = this.getConfigFiles();
			Xkins xk = new Xkins();
			while (it.hasNext()) {
				URL url = (URL)it.next();
				if (url != null) {
					//if has servletcontext, uses this one to generate the real path, otherwise uses the url that is comming
					setRealWebPath(this.getServletContext()!=null?this.getServletContext().getRealPath("/"):url.toString());
					log.info("Loading " + url);
					xk = this.loadSkins(url.openStream(), xk);				
					addConfigFilesTimeStamp(url);
				} else {
					log.warn("URL not defined.");
				}
			}
        	
			if(this.getAutoReload()>0 && !getConfigFilesTimeStamp().isEmpty()) {
			    //creates and sets the thread as a daemon
			    AutoReloader reloader = new AutoReloader();
			    reloader.setDaemon(true);
				reloader.start();
			}

			if(this.getSkinType()!=null) {
				//le guarda el type
				xk.setSkinType(this.getSkinType());
				
				//Verifica los tipos
				Iterator itSk = xk.getSkins().keySet().iterator();
				Collection toRemove = new ArrayList();
				while(itSk.hasNext()) {
					String skinName = (String)itSk.next();
					String type = xk.getSkin(skinName).getType();
					if(!type.startsWith(this.getSkinType())) {
						log.warn("Xkin Type " + type + " does not match with specified one: " + this.getSkinType() + ". This Skin will not be loaded.");											
						toRemove.add(skinName);
					}
				}
				itSk = toRemove.iterator();
				while(itSk.hasNext())
					xk.getSkins().remove((String)itSk.next());
			}	

			if(xk.getSkins().size()==0)
			log.warn("No skins are defined.");

			//Me guardo el xs.
			this.xkinsLoaded = xk;
	
			//Verifico los warnings para ver si el hijo tiene templates que el padre no. Solo si se indica skinType
			if(this.getSkinType()!=null) {
				Iterator itXk = xk.getSkins().keySet().iterator(); 
				while(itXk.hasNext()) { 
					String skinName = (String)itXk.next(); 
					Skin sk = (Skin)xk.getSkins().get(skinName); 
	
					if(sk.isExtending()) { 
						Skin skPadre = sk.getExtendedSkin(); 
						Iterator itK = sk.getTemplates().keySet().iterator(); 
						while(itK.hasNext()) {                                          
							String tmpName = (String)itK.next(); 
							Object obj = skPadre.getTemplate(tmpName); 
							if(obj==null) { 
								log.warn("The template " + tmpName + " is not present in skin " + skPadre.getName() + ", that is extended by " + sk.getName());
							} 
						} 
					} 
				} 
			}
			log.info("Loaded.");
			return xk;
		} catch(IOException io) {
			log.error("Error loading Xkins.", io);
			throw new XkinsException("Error cargando los Xkins: " + io);
		}
	}

	/**
	 * Agrega el archivo con su time stamp actual
	 * @param url
	 */
	public static void addConfigFilesTimeStamp(URL url) {
		File file = new File(url.getFile());
		getConfigFilesTimeStamp().put(file.getPath(), new Long(file.lastModified()));
	}
	
	public static void addDefinitionFilesTimeStamp(File file) {
		if ( file == null ){
			return;
		}
		getDefinitionFilesTimeStamp().put(file.getPath(), new Long(file.lastModified()));
	}

	/**
	 * Devuelve un Iterator con URL de cada configuration file
	 * @return
	 */
	private Iterator getConfigFiles() {
		return new Iterator() {
			StringTokenizer st = new StringTokenizer(XkinsLoader.getConfig(), ",");
			
			public boolean hasNext() {
				return st.hasMoreTokens();
			}

			public Object next() {
			    //tries to look for the file in the WEB-INF directory
			    String configFile = st.nextToken().trim();
			    URL url=null;
                try {
                	if ( servletContext != null ){//当不在web环境下用时，就需要这样处理,否则出异常
                      url = servletContext.getResource(configFile);
                	}
                    if(url==null) {
                        url = XkinsLoader.this.getClass().getResource(configFile);
                    }
                } catch (MalformedURLException e) {
                    url = XkinsLoader.this.getClass().getResource(configFile);
                }
				return url;
			}
			
			public void remove() {
			}
		};
	}

	/**
	 * Carga los skins desde el inputStream indicado. El inputStream debe ser un archivo de configuraci�n de skins.
	 * @param in
	 * @return
	 * @throws XkinsException
	 */
	public Xkins loadSkins(InputStream in)
			throws XkinsException {
		Xkins xk = new Xkins();
		return this.loadSkins(in, xk);
	}

	/**
	 * Carga los skins. M�todo privado que utilizan los dem�s. Usa el Digester.
	 * @param in
	 * @param xk
	 * @return
	 * @throws XkinsException
	 */
	private Xkins loadSkins(InputStream in, Xkins xk)
			throws XkinsException {
		try {
			Digester digester = new Digester();
			Xkins xkLoading = new Xkins();
			URL url = this.getClass().getResource(this.dtd);
			if (url != null) {
				digester.register(this.registration, url.toString());
				//digester.setValidating(true);
			}
			digester.push(xkLoading);
			digester.addSetProperties("xkins");            
			//Crea los Skins
			digester.addFactoryCreate("xkins/skin", new SkinFactory(xkLoading));
			digester.addSetProperties("xkins/skin");
			digester.addSetProperty("xkins/skin/set-property", "property", "value");
			digester.addSetTop("xkins/skin", "setXkins", XKINS_CLASS_NAME);

			digester.addObjectCreate("xkins/global-processor", PROCESSOR_CLASS_NAME);
			digester.addSetProperties("xkins/global-processor");
			digester.addSetNext("xkins/global-processor", "addProcessor", PROCESSOR_CLASS_NAME);			
			
			this.skinDigester(digester, "xkins/");

			//Agrega el skin
			digester.addSetNext("xkins/skin", "addSkin", SKIN_CLASS_NAME);
			try {
				// Parse the input stream to initialize our database
				digester.parse(in);
				in.close();
			} catch (SAXException e) {
				System.out.println("出错:" + e.getMessage());
				System.out.println("出错:" + e);
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
					}
				}
			}
			this.loadSkinsDefinition(xkLoading);
			//copio los xkins cargados al xk
			Iterator it = xkLoading.getSkins().keySet().iterator();
			while(it.hasNext()) {
				String skinName = (String)it.next();
				Skin sk = (Skin)xkLoading.getSkins().get(skinName);
				sk.setXkins(xk);
				xk.addSkin(sk);
			}
			XkinsLoadEvent xle = new XkinsLoadEvent(this);
			xle.setXkins(xk);
			xk.sendEvent(xle);
			xk.addProcessors(xkLoading.getProcessors());
			return xk;
		} catch (Throwable thr) {
			thr.printStackTrace();
			throw new XkinsException(thr);
		}
	}
	
	/**
	 * Crea el Skin seg�n el type definido globalmente o en el skin.
	 * @author Guillermo Meyer
	 */
	final class SkinFactory extends AbstractObjectCreationFactory {
		private Xkins xkLoading=null;		
		public SkinFactory(Xkins xkins) {
			xkLoading=xkins;
		}
		public Object createObject(Attributes attributes) {
			String className = attributes.getValue("type");
			if (className == null) {
				className = xkLoading.getType();
			}

			Object skin = null;
			try {
				skin = Class.forName(className).newInstance();
			} catch (Exception e) {
				log.error("Error instantiating Skin class. ", e);
			}
			return skin;
		}

	}

	
	private void loadSkinsDefinition(Xkins xk) throws XkinsException {
		//Procesa cada Skin para levantar su definici�n si la tuviere y deszipea si lo estuviera
		Iterator it = xk.getSkins().keySet().iterator();
		while(it.hasNext()) {
			String skinName = (String)it.next();
			Skin sk = (Skin)xk.getSkins().get(skinName);
			Skin skLoaded = this.loadDefinition(sk);
			if(skLoaded!=null) {
				xk.addSkin(skLoaded);
			}				
		}
	}

	private InputStream getSkinStream(String pSkinDefinition){
		if ( pSkinDefinition == null ){
			return null;
		}
		InputStream result = null;
		log.info("pSkinDefinition=" + pSkinDefinition);
		result = this.servletContext.getResourceAsStream(pSkinDefinition);
		if ( result == null ){
			result = XkinsLoader.class.getResourceAsStream(pSkinDefinition);
		}
		return result;
	}
	private File getSkinFile(String pSkinDefinition){
		if ( pSkinDefinition == null ){
			return null;
		}
		File result = null;
		if ( getRealWebPath() == null ){
			return null;
		}
		String path = getRealWebPath() + pSkinDefinition;
		result = new File(path);
		return result;
		
	}
	
	
	/**
	 * Carga el Skin desde la definici�n si la tiene aparte.
	 */
	public Skin loadDefinition(Skin skin)
			throws XkinsException {
		if(skin.getDefinition() == null) {
			return null;
		}
		log.info("开始装载皮肤:" + skin.getSkinDefinition());
		try {
					log.info("Loading Definition from file " + skin.getSkinDefinition() + "...");					
					addDefinitionFilesTimeStamp(getSkinFile(skin.getSkinDefinition()));
					InputStream in = getSkinStream( skin.getSkinDefinition() );
					Digester digester = new Digester();
					URL url = this.getClass().getResource(this.dtd);
					if (url != null) {
						digester.register(this.registration, url.toString());
						//digester.setValidating(true);
					}
					digester.push(skin);
					this.skinDigester(digester, "");
					try {
						// Parse the input stream to initialize our database
						digester.parse(in);
						in.close();
					} catch (SAXException e) {
						e.printStackTrace();
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (Exception e) {
								log.error("Error Loading skin Definition.", e);
							}
						}
					}
					XkinsLoadEvent xle = new XkinsLoadEvent(this);
					xle.setXkins(skin.getXkins());
					skin.getXkins().sendEvent(xle);
					return skin;
		} catch (Throwable thr) {
			log.error("Error Loading Definition.", thr);			
			throw new XkinsException(thr.getMessage());
		}
	}
	
	private void skinDigester(Digester digester, String prefix) {
		//Crea los Paths
		digester.addSetProperties("skin");
		digester.addObjectCreate(prefix + "skin/path", PATH_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/path");
		digester.addSetTop(prefix + "skin/path", "setSkin", SKIN_CLASS_NAME);
		digester.addSetNext(prefix + "skin/path", "addPath", PATH_CLASS_NAME);
		//Crea los resources globales element
		digester.addObjectCreate(prefix + "skin/element", ELEMENT_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/element");
		digester.addSetTop(prefix + "skin/element", "setSkin", SKIN_CLASS_NAME);
		digester.addSetNext(prefix + "skin/element", "addResource", RES_CLASS_NAME);
		//Crea los resources globales constant
		digester.addObjectCreate(prefix + "skin/constant", CONSTANT_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/constant");
		//digester.addSetProperty(prefix + "skin/constant", "value", "value");
		digester.addBeanPropertySetter(prefix + "skin/constant/value", "value");
		//设置常量值
		
		digester.addSetTop(prefix + "skin/constant", "setSkin", SKIN_CLASS_NAME);
		digester.addSetNext(prefix + "skin/constant", "addResource", RES_CLASS_NAME);
		//setea el processor global de skin
		digester.addObjectCreate(prefix + "skin/processor", PROCESSOR_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/processor");
		digester.addSetNext(prefix + "skin/processor", "setProcessor", PROCESSOR_CLASS_NAME);
		
		//crea los servers
		digester.addObjectCreate("skin/server", SERVER_CLASS_NAME);
		digester.addSetProperties("skin/server");
		digester.addSetNext("skin/server", "addServer", SERVER_CLASS_NAME);			

		//Crea los Templates
		digester.addObjectCreate(prefix + "skin/template", TEMPLATE_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/template");
		digester.addSetTop(prefix + "skin/template", "setSkin", SKIN_CLASS_NAME);
		//crea el content
		digester.addObjectCreate(prefix + "skin/template/content", CONTENT_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/template/content");
		digester.addCallMethod(prefix + "skin/template/content", "setData", 1);
		digester.addCallParam(prefix + "skin/template/content", 0);
		digester.addSetNext(prefix + "skin/template/content", "setContent", CONTENT_CLASS_NAME);
		digester.addSetTop(prefix + "skin/template/content", "setTemplate", TEMPLATE_CLASS_NAME);
				
		//setea el processor		
		digester.addObjectCreate(prefix + "skin/template/processor", PROCESSOR_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/template/processor");
		digester.addSetNext(prefix + "skin/template/processor", "setProcessor",
							PROCESSOR_CLASS_NAME);
		//Crea los resources elements
		digester.addObjectCreate(prefix + "skin/template/element", ELEMENT_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/template/element");
		digester.addSetTop(prefix + "skin/template/element", "setTemplate", TEMPLATE_CLASS_NAME);
		digester.addSetNext(prefix + "skin/template/element", "addResource", RES_CLASS_NAME);
		//Crea los resources constants
		digester.addObjectCreate(prefix + "skin/template/constant", CONSTANT_CLASS_NAME);
		digester.addSetProperties(prefix + "skin/template/constant");
		//digester.addSetProperty(prefix + "skin/template/constant", "value", "value");
		//设置常量值

		digester.addSetTop(prefix + "skin/template/constant", "setTemplate", TEMPLATE_CLASS_NAME);
		digester.addSetNext(prefix + "skin/template/constant", "addResource", RES_CLASS_NAME);
		//Agrega el template
		digester.addSetNext(prefix + "skin/template", "addTemplate", TEMPLATE_CLASS_NAME);
	}
	/**
	 * @return
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param context
	 */
	public void setServletContext(ServletContext context) {
		servletContext = context;
	}
	
	/**
	 * @return
	 */
	public int getAutoReload() {
		return autoReload;
	}

	/**
	 * Asegura que como m�nimo sea 1500 milisegundos.
	 * @param i
	 */
	public void setAutoReload(int i) {
		if(i>0 && i<1500)
			i=1500;
		autoReload = i;
	}

	private class AutoReloader extends Thread {
		private final Log log = LogFactory.getLog( AutoReloader.class );
		public void run() {
			while(true) {
				try {
					//Verifica los archivos de los skins, si cambiaron la fecha-hora, los recarga.
					//con que al menos haya un archivo modificado, recarga todo.			
					Iterator it = XkinsLoader.getConfigFilesTimeStamp().keySet().iterator();					
					boolean hasChanged = false;
					while (it.hasNext() && !hasChanged) {
						String fileName = (String)it.next();
						File file = new File(fileName);						
						if(this.hasChanged(file, XkinsLoader.getConfigFilesTimeStamp())) {
							if(!hasChanged) {
								hasChanged=true;
							}							
						}
					}
					
					it = XkinsLoader.getDefinitionFilesTimeStamp().keySet().iterator();
					while (it.hasNext() && !hasChanged) {
						String fileName = (String)it.next();
						File file = new File(fileName);				
						if(this.hasChanged(file, XkinsLoader.getDefinitionFilesTimeStamp())) {
							if(!hasChanged) {
								hasChanged=true;
							}	
						}
					}
					if(hasChanged) {
						//recarga todos los xkins!
						it = XkinsLoader.getConfigFilesTimeStamp().keySet().iterator();
						XkinsLoader.this.xkinsLoaded.getSkins().clear();	
						while (it.hasNext()) {
							String fileName = (String)it.next();
							File file = new File(fileName);						
							log.info("Reloading configuration file " + fileName);														
							XkinsLoader.this.loadSkins(new FileInputStream(file), XkinsLoader.this.xkinsLoaded);
							log.info("Configuration file " + fileName + " loaded.");
						}							
					}
					Thread.sleep(XkinsLoader.this.getAutoReload());
				} catch(InterruptedException ie) {
					//Nada
				} catch(Exception e) {
					//Fallo el loadSkins...
					log.error("Error Loading Xkins", e);					
				}
			}			
		}
		
		private boolean hasChanged(File file, Map files) throws XkinsException {
			Long lastModified = new Long(file.lastModified());
			Long lastModifiedOrig = (Long)files.get(file.getPath());
			boolean ret = lastModifiedOrig!=null && lastModifiedOrig.longValue() != lastModified.longValue();
			if(ret) {
				log.info("模板文件文件发生变化:" + file.getAbsolutePath());
				files.put(file.getPath(), lastModified);
			}
			return ret; 
		}
	}

	/**
	 * @return
	 */
	public static Map getConfigFilesTimeStamp() {
		if(configFilesTimeStamp==null)
			configFilesTimeStamp = new Hashtable();
		return configFilesTimeStamp;
	}

	/**
	 * @param map
	 */
	public static void setConfigFilesTimeStamp(Map map) {
		configFilesTimeStamp = map;
	}

	/**
	 * @return
	 */
	public static String getRealWebPath() {
		return realWebPath;
	}

	/**
	 * @param string
	 */
	public static void setRealWebPath(String string) {
		if(realWebPath ==null && string !=null) {
			//Recorta desde file: hasta WEB-INF
			if(string.indexOf("/WEB-INF")>0) {
				realWebPath = string.substring((string.startsWith("file:")?5:0), string.indexOf("/WEB-INF"));
			} else {
				realWebPath=string;
			}
		}
	}

	/**
	 * @return
	 */
	public static Map getDefinitionFilesTimeStamp() {
		if(definitionFilesTimeStamp==null)
			definitionFilesTimeStamp = new Hashtable();

		return definitionFilesTimeStamp;
	}

	/**
	 * @param map
	 */
	public static void setDefinitionFilesTimeStamp(Map map) {
		definitionFilesTimeStamp = map;
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

}