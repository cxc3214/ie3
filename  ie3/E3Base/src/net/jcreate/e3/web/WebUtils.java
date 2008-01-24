package net.jcreate.e3.web;
 

import org.springframework.util.Assert;

public class WebUtils {
  private WebUtils(){
	  
  }
  private static String webAppRoot = null;
  
  public static String getWebAppRoot(){
	  return webAppRoot;
  }
  
  static void setWebAppRoot(String pWebAppRoot){
	  Assert.notNull(pWebAppRoot, "webAppRoot must not be null");
	  webAppRoot = pWebAppRoot; 
  }
  
}
