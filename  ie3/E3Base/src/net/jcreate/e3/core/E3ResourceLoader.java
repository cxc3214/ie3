package net.jcreate.e3.core;

import java.io.File;
import java.io.Reader;

import net.jcreate.e3.web.WebUtils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class E3ResourceLoader extends DefaultResourceLoader{
	
	
	private final String E3_URL_PREFIX = "e3:";	
	
	public Resource getResource(String location) {
		Assert.notNull(location, "location is required");
		
		if (location.startsWith(E3_URL_PREFIX)) {
			return getE3Resource(location);
		}
		else {
			return super.getResource(location);
	   }
	}
	protected String getWebRoot(){
		return "WebRoot/";
	}
	
	
   protected Resource getE3Resource(String location){	   
	   String webPath = WebUtils.getWebAppRoot();
	   if ( webPath == null ){
		   webPath = getWebRoot();//不在web环境下
	   }
	   File dir = new File(webPath);
	   if ( dir.exists() == false){//WebRoot不存在.
		   webPath="";
	   }
	   int index = location.indexOf(":");
	   final String noPrefixLocation = location.substring(index+1);
	   String filePath = webPath + noPrefixLocation;
	   File file = new File( filePath);
	   if ( file.exists() == false ){
		   return super.getResource(noPrefixLocation);
	   } else {
	      return new FileSystemResource(filePath);
	   }
   }
	
	public E3ResourceLoader() {
		super();
	}
	public E3ResourceLoader(ClassLoader arg0) {
		super(arg0);
	}

	
}
