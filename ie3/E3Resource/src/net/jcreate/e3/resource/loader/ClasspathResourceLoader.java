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
package net.jcreate.e3.resource.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.jcreate.e3.resource.LoadResoruceException;
import net.jcreate.e3.resource.Resource;
import net.jcreate.e3.resource.ResourceLoader;
import net.jcreate.e3.resource.support.DefaultResource;
import net.jcreate.e3.resource.util.ClassUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 到classpath环境里读取资源文件.
 * @author 黄云辉
 *
 */
public class ClasspathResourceLoader  implements ResourceLoader{

    private final Log logger = LogFactory.getLog( this.getClass() );
    private int cacheSize; 

    public ClasspathResourceLoader(){
    	cacheSize = 1024 * 2;
    }
    
    public ClasspathResourceLoader(int pCacheSize){
    	cacheSize = pCacheSize;
    }
    
	public long getLastModified(String pUri) {
		return 0;
	}

	/**
	 * 如果要进行url转换处理，覆盖该方法.
	 * @param pUri
	 * @return
	 */
	protected String urlMapping(String pUri){
		return pUri;
	}
	public Resource load(String pUri) throws LoadResoruceException {
		if ( pUri == null ){
			throw new NullPointerException("资源URI为空");
		}
		if ( logger.isDebugEnabled() ){
			logger.debug("正在装载资源文件:" + pUri + " ...");
		}
		
		String uri = urlMapping(pUri);
		InputStream is = ClassUtils.getResourceAsStream(this.getClass(), uri);
		if ( is  == null ){
			final String MSG = "没有找到资源文件:" + uri ;
			logger.error(MSG);
			throw new NotFoundResourceException(MSG);		
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
		byte[] buf = new byte[ cacheSize ];
        int len;
	    try {
			while ((len = is.read(buf)) > 0) {
			  outputStream.write(buf, 0, len);
			}
		} catch (IOException e) {
			throw new LoadResoruceException("读取资源文件:" + uri + "失败!" , e);
		}
	    
	    byte[] data = outputStream.toByteArray(); 
	    if ( logger.isDebugEnabled() ){
		  logger.debug("装载资源成功:" + uri);
		}
	    DefaultResource result = new DefaultResource(uri,data);
	    result.setLastModified( 0 );
		return result;

	}
	
	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	

}
