/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinsServlet.java
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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de inicializaci�n de los Skins. Llama al XkinsLoader para efectuar la carga.<br>
 * recibe los siguientes par�metros desde el web.xml:<br>
 * <ul>
 *    <li>debug    indica el nivel de debug del digester. El default es 0</li>
 *    <li>config    indica el archivo de configuraci�n. Por default es "/xkins-definition.xml". Pueden indicarse m�iltiples
 *archivos separados por coma.</li>
 *    <li>defaultSkinName    indica el nombre del skin default de la aplicaci�n. del default es "default"</li>
 * </ul>
 * Este servlet se debe llamar en el web.xml con onload !=0. Si se lo llama desde la URL no solo regargar�
 * los skins sino que mostrar� una p�gina con los skins cargados.
 *
 * @author  Guillermo Meyer
 * @version
 */
public class XkinsServlet
        extends HttpServlet {
    //~ Static fields/initializers -----------------------------------------------------------------

    private static XkinsServlet instance = null;

    //~ Instance fields ----------------------------------------------------------------------------

    private String config = null;
    private String defaultSkinName = null;
    private int debug = 0;
    private int autoReload=0;
    private String skinType=null;
	public static final String RELOAD_COMMAND = "reload";
    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Crea un objecto XkinsServlet.
     */
    public XkinsServlet() {
        instance = this;
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Devuelve la instancia del servlet
     * @return
     */
    public static XkinsServlet getInstance() {
        return instance;
    }

    /**
     * Sets the config.
     * @param config The config to set
     */
    public void setConfig(String config) {
        this.config = config;
    }

    /**
     * Returns the config.
     * @return String
     */
    public String getConfig() {
        return config;
    }

    /**
     * Sets the debug.
     * @param debug The debug to set
     */
    public void setDebug(int debug) {
        this.debug = debug;
    }

    /**
     * Returns the debug.
     * @return int
     */
    public int getDebug() {
        return debug;
    }

    /**
     * Sets the defaultSkinName.
     * @param defaultSkinName The defaultSkinName to set
     */
    public void setDefaultSkinName(String defaultSkinName) {
        this.defaultSkinName = defaultSkinName;
    }

    /**
     * Returns the defaultSkinName.
     * @return String
     */
    public String getDefaultSkinName() {
        return defaultSkinName;
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "XkinsServlet";
    }

    /** Destroys the servlet.
     */
    public void destroy() {
    }

    /** Initializes the servlet.
     */
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        this.setDebug((config.getInitParameter("debug") != null)
                      ? Integer.parseInt(config.getInitParameter("debug")) : 0);
        this.setConfig((config.getInitParameter("config") != null)
                       ? config.getInitParameter("config") : XkinsLoader.getConfig());
        this.setDefaultSkinName((config.getInitParameter("defaultSkinName") != null)
                                ? config.getInitParameter("defaultSkinName")
                                : Skin.ATTR_DEFAULT_SKIN_NAME);
		this.setSkinType(config.getInitParameter("skinType"));
		try {
			this.setAutoReload((config.getInitParameter("autoReload") != null)
						  ? Integer.parseInt(config.getInitParameter("autoReload")): 0);
		} catch(Exception e) {
			this.setAutoReload(0);
		}

        initSkins(config);
    }

    /**
     * Inicializa la carga de skins, tomando como archivo de configuraci�n el inputStream enviado por par�metro.
     * @param in
     */
    public void initSkins(InputStream in) {
        try {
            XkinsLoader xs = new XkinsLoader();
            xs.setDebug(this.getDebug());
			xs.setServletContext(this.getServletContext());
			xs.setSkinType(this.getSkinType());
            Xkins xks = xs.loadSkins(in);
            this.getServletConfig().getServletContext().setAttribute(Xkins.ATTR_SKINS, xks);
            this.getServletConfig().getServletContext().setAttribute(Skin.ATTR_DEFAULT_SKIN_NAME,
                                                                     this.getDefaultSkinName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	private void printToOut(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Xkins</title>");
		out.println("</head>");
		out.println("<body><h1>Xkins cargados OK</h1><code>");		
		out.println(((Xkins) this.getServletConfig().getServletContext().getAttribute(Xkins.ATTR_SKINS))
					.printHTML());
		out.println("</code></body>");
		out.println("</html>");
		out.close();			
	}

    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Inicializa la carga de Skins usando el this.getServletConfig()
     */
    protected void initSkins() {
        this.initSkins(this.getServletConfig());
    }

    /**
     * Inicializa la carga de skins.
     * @param config
     */
    protected void initSkins(ServletConfig config) {
        try {
            XkinsLoader xs = new XkinsLoader();
            xs.setDebug(this.getDebug());
            xs.setConfig(this.getConfig());
			xs.setAutoReload(this.getAutoReload());
			xs.setSkinType(this.getSkinType());
			xs.setServletContext(this.getServletContext());
            config.getServletContext().setAttribute(Xkins.ATTR_SKINS, xs.loadSkins());
            config.getServletContext().setAttribute(Skin.ATTR_DEFAULT_SKIN_NAME,
                                                    this.getDefaultSkinName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	private void processTemplate(HttpServletRequest request, HttpServletResponse response, String templateName) 
			throws IOException {

		XkinProcessor xkp = new XkinProcessor(request, response);
		//Si viene el par�metro de skinName, seteo ese skin
		if(request.getParameter("skinName")!=null) {
			if(xkp.getSkin(request.getParameter("skinName"))!=null)			
				xkp.setCurrentSkinName(request.getParameter("skinName"));			
		}
		//Procesa el tempalte definido en el command
		
		xkp.setTemplateName(templateName);
		Enumeration enumer = request.getParameterNames();
		while(enumer.hasMoreElements()) {
			String name = (String)enumer.nextElement();
			xkp.addParameter(name, request.getParameter(name));
		}
		response.getWriter().print(xkp.processContent());
		
	}

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		String command = request.getServletPath();
		command=command.substring(1, command.indexOf('.'));
		if(RELOAD_COMMAND.equals(command)) {
			this.initSkins();
			//para crear el context en el thread local
			XkinProcessor xkp = new XkinProcessor(request, response);
			this.printToOut(response);
		} else {
			this.processTemplate(request, response, command);
		}
    }
	/**
	 * @return
	 */
	public int getAutoReload() {
		return autoReload;
	}

	/**
	 * @param i
	 */
	public void setAutoReload(int i) {
		autoReload = i;
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
