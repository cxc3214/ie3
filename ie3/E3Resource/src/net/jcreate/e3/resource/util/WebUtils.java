/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.e3.resource.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class WebUtils {
	
  
  
  private WebUtils(){
	  
  }
  public static final String ACCEPTED_ENCODING = "Accept-Encoding";
  public static final String GZIP = "gzip";
  public static final String CONTENT_ENCODING  = "Content-Encoding";
  
  public static boolean isSupportedGzip(HttpServletRequest pRequest){
		String acceptEncoding = pRequest.getHeader(ACCEPTED_ENCODING);
		boolean isSupportedGZIP = (acceptEncoding != null) && (acceptEncoding.indexOf(GZIP) != -1);
		return isSupportedGZIP;
  }
  
  public static boolean isIE6(HttpServletRequest pRequest) {
	  return !(isNotIE6(pRequest));	
	}
  public static  boolean isNotIE6(HttpServletRequest pRequest) {
		   String   agent   =   pRequest.getHeader("User-Agent");   
		   if   (agent.indexOf("MSIE   6.0")   !=   -1)   {
			   return false;
		   }   else   {
			   return true;
		   }	   
	   }
  
  public static void setGzipHeader(HttpServletResponse pResponse){
	  pResponse.setHeader(CONTENT_ENCODING, GZIP);  
  }
  
  
}
