package net.jcreate.e3.resource.impl;

import java.util.HashMap;
import java.util.Map;

import net.jcreate.e3.resource.ResourceException;
import net.jcreate.e3.resource.ResourceHandler;
import net.jcreate.e3.resource.handler.CSSMinResourceHandler;
import net.jcreate.e3.resource.handler.GZipResourceHandler;
import net.jcreate.e3.resource.handler.JSMinResourceHandler;
import net.jcreate.e3.resource.handler.NoneResourceHandler;
import net.jcreate.e3.resource.support.HandlerMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultHandlerMapping implements HandlerMapping{
	private final Log logger = LogFactory.getLog( this.getClass() );
    private final static GZipResourceHandler gzip = new GZipResourceHandler();
    private final static NoneResourceHandler none = new NoneResourceHandler();
    private final static CSSMinResourceHandler  cssMin  = new CSSMinResourceHandler();
    private final static JSMinResourceHandler  jsMin  = new JSMinResourceHandler();
    private static Map handlers = new HashMap();
    static{
    	//key的名字必须是小写
    	handlers.put("gzip", gzip);
    	handlers.put("none", none);
    	handlers.put("cssmin", cssMin);
    	handlers.put("jsmin", jsMin);
    }
    
    public void put(String pHandlerName, ResourceHandler pHandler){
    	handlers.put(pHandlerName, pHandler);
    }
    
	public ResourceHandler mapping(String pName) throws ResourceException {
		if ( pName == null ){
			throw new NullPointerException("资源Handler名称不能为空null");
		}
		String handlerName = pName.toLowerCase();
		if ( handlers.containsKey( handlerName ) == false ){
            return null;
		}
		ResourceHandler result = (ResourceHandler)handlers.get(handlerName);
		return result;
	}

}
