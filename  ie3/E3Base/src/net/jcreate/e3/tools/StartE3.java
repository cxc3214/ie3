package net.jcreate.e3.tools;
 

import net.jcreate.e3.util.SystemUtils;

/**
 * 启动Tomcat服务器.
 * @author new 
 */
public class StartE3 {
	 public static void main(String[] args) {
		 EmbeddedTomcat tomcat = new EmbeddedTomcat(
				 SystemUtils.getPrjPath() + "/embedtomcat",
				 SystemUtils.getPrjPath() + "/WebRoot",
				 "/e3",
				 8080
		 );
		 tomcat.start();
		 
	 }
}
