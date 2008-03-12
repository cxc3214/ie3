package net.jcreate.e3.table.skin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jcreate.xkins.Skin;
import net.jcreate.xkins.XkinProcessor;
import net.jcreate.xkins.Xkins;
import net.jcreate.xkins.XkinsLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 这些代码是来自xkins组件,在它基础上修改了一些东西.
 * @author 黄云辉
 *
 */
public class LoadSkinsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static LoadSkinsServlet instance = null;
    private static final Log logger = LogFactory.getLog( LoadSkinsServlet.class );    

    //~ Instance fields ----------------------------------------------------------------------------


    private int debug = 0;
    private int autoReload= LoadSkinsListener.DEFAULT_AUTO_RELOAD;
    /**
     * 皮肤扫描路径.如果存在多个皮肤路径，以逗号/分号分隔.
     */
    private String skinPath;
    
	public static final String RELOAD_COMMAND = "reload";
    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Crea un objecto XkinsServlet.
     */
    public LoadSkinsServlet() {
        instance = this;
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Devuelve la instancia del servlet
     * @return
     */
    public static LoadSkinsServlet getInstance() {
        return instance;
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
        String skinPath = config.getInitParameter("skinPath");
        
        
        String webHome = config.getServletContext().getRealPath("/");
        
        /**
         * 创建皮肤定义文件:
         */
		SkinDefFileCreator.create(
				webHome,
				SkinUtil.getSkinPaths(skinPath) ,
				webHome +LoadSkinsListener.SKIN_DEF_FILE
		);

		try {
			this.setAutoReload((config.getInitParameter("autoReload") != null)
						  ? Integer.parseInt(config.getInitParameter("autoReload")): 0);
		} catch(Exception e) {
			this.setAutoReload(0);
		}

        initSkins(config);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void initSkins() {
        this.initSkins(this.getServletConfig());
    }

    /**
     * 初始化皮肤
     * @param config
     */
    protected void initSkins(ServletConfig config) {
        try {
            XkinsLoader xs = new XkinsLoader();
            xs.setDebug(this.getDebug());
            xs.setConfig(LoadSkinsListener.SKIN_DEF_PATH);
			xs.setAutoReload(this.getAutoReload());
			xs.setServletContext(this.getServletContext());
			XkinsLoader.setRealWebPath( config.getServletContext().getRealPath("/") );
			logger.info("开始装载e3.table皮肤!");			
            config.getServletContext().setAttribute(Xkins.ATTR_SKINS, xs.loadSkins());
  		    logger.info("成功装载e3.table皮肤!");
        } catch (Exception ex) {
        	String webHome = config.getServletContext().getRealPath("/");        	
			final String MSG =
				"初始化e3.table皮肤失败!皮肤定义文件路径:" + webHome +LoadSkinsListener.SKIN_DEF_FILE;
			logger.error(MSG, ex);
			if ( logger.isDebugEnabled() ){
		      ex.printStackTrace();	
			}

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


	public String getSkinPath() {
		return skinPath;
	}

	public void setSkinPath(String skinPath) {
		this.skinPath = skinPath;
	}

}
