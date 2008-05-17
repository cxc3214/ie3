package net.jcreate.e3.table.skin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.jcreate.xkins.Xkins;
import net.jcreate.xkins.XkinsLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *  e3.table.skinPath    配置皮肤扫描路径
 *  e3.table.autoReload  配置皮肤文件修改检查时间间隔
 * @author 黄云辉
 *  TODO:
 *   现在的策略比较简单，如果支持jsp2.0的容器，直接使用默认设置导入配置.不需要在web.xml文件里做任何培植.
 *   如果web.xml配置LoadSkinsListener或者LoadSkinsServlet时,会执行多次初始化.这是不合理的,以后
 *   要解决这个问题.  
 */
public class LoadSkinsListener implements ServletContextListener {
    
   public static final String SKIN_PATH_PARAM = "e3.table.skinPath";
   public static final String AUTO_RELOAD_PARAM = "e3.table.autoReload";
   /**
    * 相对classes的路径
    */
   public static final String SKIN_DEF_PATH = "/net-jcreate-e3-table-skin-def.xml";
    
   /**
    * 相对WEB HOME 路径
    */
   public static final String SKIN_DEF_FILE = "/WEB-INF/classes" + SKIN_DEF_PATH;
   public static final int DEFAULT_AUTO_RELOAD = 2000;
 
   
    /**
     * 皮肤扫描路径.如果存在多个皮肤路径，以逗号/分号分隔.
     */
    private String skinPath;
    private int autoReload = DEFAULT_AUTO_RELOAD;    
    private static final Log logger = LogFactory.getLog( LoadSkinsListener.class );
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		ServletContext servletContext = pServletContextEvent.getServletContext();
		final String WEB_HOME = servletContext.getRealPath("/");
		
		String strAutoReload = servletContext.getInitParameter(SKIN_PATH_PARAM);
		if ( strAutoReload != null ){
			try{
			  autoReload = Integer.parseInt(strAutoReload);
			}catch(Exception ex){
				logger.warn("参数e3.skin.autoReload的值:" + strAutoReload + " 不是整,系统使用默认值!");
				autoReload = 0;	
			}
		}
		
		skinPath = servletContext.getInitParameter(AUTO_RELOAD_PARAM);
		
        
        
		if ( WEB_HOME != null ){
        /**
         * 创建皮肤定义文件:
         */
		SkinDefFileCreator.create(
				WEB_HOME,
				SkinUtil.getSkinPaths(skinPath) ,
				WEB_HOME +LoadSkinsListener.SKIN_DEF_FILE
		);
		}
        XkinsLoader xs = new XkinsLoader();
        xs.setDebug(0);
		xs.setAutoReload(autoReload);
		xs.setServletContext(pServletContextEvent.getServletContext());
		xs.setConfig(SKIN_DEF_PATH);
		XkinsLoader.setRealWebPath( WEB_HOME );
		try{
		  logger.info("开始装载e3.table皮肤!" + SKIN_DEF_PATH);
		  servletContext.setAttribute(Xkins.ATTR_SKINS, xs.loadSkins());
		  logger.info("成功装载e3.table皮肤!");
		}catch(Exception ex){
			final String MSG =
				"初始化e3.table皮肤失败!皮肤定义文件路径:" + WEB_HOME +LoadSkinsListener.SKIN_DEF_FILE;
			logger.error(MSG, ex);
			if ( logger.isDebugEnabled() ){
		      ex.printStackTrace();	
			}
		}
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public String getSkinPath() {
		return skinPath;
	}

	public void setSkinPath(String skinPath) {
		this.skinPath = skinPath;
	}


	public int getAutoReload() {
		return autoReload;
	}

	public void setAutoReload(int autoReload) {
		this.autoReload = autoReload;
	}


}
