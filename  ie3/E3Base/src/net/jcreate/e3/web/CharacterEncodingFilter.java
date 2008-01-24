package net.jcreate.e3.web;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter extends OncePerRequestFilter{
	
	  public static final String DEFAULT_ENCODING = "utf-8";
	  protected  void doFilterInternal(
				HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException{
    String strEncoding = filterConfig.getInitParameter("encoding");
    if ( strEncoding == null ){//
      strEncoding = DEFAULT_ENCODING;
    }
    strEncoding = strEncoding.trim();
    if ( strEncoding.length() == 0 ){
      strEncoding = DEFAULT_ENCODING;
    }
      request.setCharacterEncoding(strEncoding);
      filterChain.doFilter(request, response);
  }


}
