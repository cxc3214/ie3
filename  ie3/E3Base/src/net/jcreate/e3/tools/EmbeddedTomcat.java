package net.jcreate.e3.tools;

import java.io.File;
import java.net.InetAddress;

import net.jcreate.e3.util.SystemUtils;
import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Logger;
import org.apache.catalina.logger.SystemOutLogger;
import org.apache.catalina.startup.Embedded;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmbeddedTomcat {
	private final Log log = LogFactory.getLog( EmbeddedTomcat.class );
	public static final int DEFAULT_PORT = 80;
	public static final String DEFAULT_CONTEXT_PATH = "/e3";
	public static final String DEFAULT_WEB_HOME = SystemUtils.getPrjPath() +  "/WebRoot";
	public static final boolean DEFAULT_RELOADABLE = true;

	private int port = DEFAULT_PORT;
	private String contextPath = DEFAULT_CONTEXT_PATH;
	private String webHome = DEFAULT_WEB_HOME;
	private String tomcatHome = null;
	private boolean reloadable = DEFAULT_RELOADABLE;

	public EmbeddedTomcat(){
		tomcatHome = getDefaultTomcatHome();
	}
	public EmbeddedTomcat(String pTomcatHome) {
    	   tomcatHome = pTomcatHome;
	}
	public EmbeddedTomcat(String pTomcatHome, String pWebHome){
		this.tomcatHome = pTomcatHome;
		this.webHome = pWebHome;
	}
	
	public EmbeddedTomcat(String pTomcatHome, String pWebHome, String pContextPath, int pPort ){
		this.tomcatHome = pTomcatHome;
		this.webHome = pWebHome;
		this.contextPath = pContextPath;
		this.port = pPort;
	}
	
	public void start(){
		
		long beginTime = System.currentTimeMillis();
		try {
			doStart();
			System.out.println("-------------------------------------------------------------------");
			System.out.println("欢迎使用E3平台,服务器启动成功!");
			System.out.println("TOMCAT_HOME:" + this.tomcatHome);
			System.out.println("WEB_HOME:" + this.webHome);
			System.out.println("服务器地址:" + "localhost");
			System.out.println("服务端口:" + this.port);
			System.out.println("CONTEXT PATH:" + this.contextPath);
			System.out.println("E3主页:  " + "http://" +"localhost:" + this.port +   this.contextPath);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - beginTime;
			System.out.println("启动时间:" + totalTime + "ms, " + (totalTime/1000) + "s");
			System.out.println("-------------------------------------------------------------------");
		} catch (Throwable e) {
			System.err.println("启动服务器失败!");
			e.printStackTrace();
		}
		  
	} 
	private Embedded tomcat = null;
	
	private void stopTomcat() {
        try {
            tomcat.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void doStart() throws Throwable{
		  tomcat = new Embedded();	  
	 	  tomcat.setDebug(0);
		  tomcat.setLogger(new SystemOutLogger());
		  tomcat.setCatalinaHome(this.tomcatHome);
 	       Engine engine = tomcat.createEngine();
		  engine.setName("e3");
		  Host host = tomcat.createHost("localhost", webHome);
		  engine.addChild(host);
		  engine.setDefaultHost(host.getName());
		  host.addAlias("test");
		  host.setAutoDeploy(true);
 
		  if (!new File(webHome).exists()) {
			  throw new IllegalArgumentException("WebHome·不存在:" + webHome );
		  }
		  Context ctx = tomcat.createContext(this.contextPath, webHome);
		  host.addChild(ctx);		  
		  tomcat.addEngine(engine);
		  tomcat.setDebug(Logger.INFORMATION);
		  ctx.setReloadable(DEFAULT_RELOADABLE);
		  
		  try {
			  Connector connnector = tomcat.createConnector((InetAddress)null, this.port, false);
			  connnector.setEnableLookups(false);
		   tomcat.addConnector(connnector);
		  } catch (Exception e) {
			   e.printStackTrace();			
			   throw e;
		  }
		  try{
		    tomcat.start();
		  }catch(Exception e){
			  throw e;
		  }
		  initShutdownHook();
		
	}
	
	public String getTomcatHome() {
		return tomcatHome;
	}
	public void setTomcatHome(String tomcatHome) {
		this.tomcatHome = tomcatHome;
	}
	private  String getDefaultTomcatHome(){
		return SystemUtils.getPrjPath() + "\\" + "embedtomcat";
	}

	public String getContextPath() {
		return contextPath;
	}

	private void initShutdownHook() {
        Runtime.getRuntime().addShutdownHook(
            new Thread() {
                public void run() {
                    stopTomcat();
                }
            }
        );
    }
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getWebHome() {
		return webHome;
	}

	public void setWebHome(String webHome) {
		this.webHome = webHome;
	}
	public boolean isReloadable() {
		return reloadable;
	}
	public void setReloadable(boolean reloadable) {
		this.reloadable = reloadable;
	}
}
