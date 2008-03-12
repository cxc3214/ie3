/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)VelocityTemplateProcessor.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.e3.table.skin.processor;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Properties;

import net.jcreate.xkins.Context;
import net.jcreate.xkins.Template;
import net.jcreate.xkins.XkinProcessor;
import net.jcreate.xkins.Xkins;
import net.jcreate.xkins.XkinsException;
import net.jcreate.xkins.XkinsRuntimeException;
import net.jcreate.xkins.events.XkinsEvent;
import net.jcreate.xkins.events.XkinsEventListener;
import net.jcreate.xkins.events.XkinsLoadEvent;
import net.jcreate.xkins.processor.TemplateProcessor;
import net.jcreate.xkins.resources.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.servlet.VelocityServlet;


/**
 *  * 说明：本项目来源 http://xkins.sourceforge.net/
 * velocity 模板处理器
 * 实现了 XkinsEventListener监听器，当文件发生变换后，他要得到通知
 */
public class VelocityTemplateProcessor
        implements TemplateProcessor, XkinsEventListener {
		//private RuntimeInstance runtimeInstance = new RuntimeInstance(); 
	public static final String APPLICATION = "app";
	public static final String SESSION = "session";
	
    //~ Constructors -------------------------------------------------------------------------------

    /**
     *
     */
    public VelocityTemplateProcessor() {
        super();
    }
    
    private final Log log = LogFactory.getLog( this.getClass() );
    private VelocityEngine ve = null;

    public void init(){
    	ve = VelocityHelper.getVeocityEngine(VelocityHelper.getDefaultProperties());
    }
    
    public void init(Properties pProperties){
    	Properties props = VelocityHelper.getDefaultProperties();
    	props.putAll(pProperties);
    	ve = VelocityHelper.getVeocityEngine(props);	
    }
    

	/**
	 * 初始化velocity
	 */
	public void init(Xkins xkins) throws XkinsException {
		init();
	}

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * 处理模板
     * 将合并后信息输出到os流
     */
    public void process(Template input, Context context, StringWriter pWriter)
            throws XkinsException {
        try {       	
	        final VelocityContext vc = new VelocityContext();
	        vc.put("_e3Tools", new E3Tools() );
	
	        //copy参数
	        if (context.getParameters().size() > 0) {
	            Iterator it = context.getParameters().keySet().iterator();
	            while (it.hasNext()) {
	                String key = (String) it.next();
	                String keyName = key;
	                if (XkinProcessor.JSP_BODY.equals(key)) {
	                    keyName = "bodyContent";
	                }
	
	                vc.put(keyName, context.getParameters().get(key));
	            }
	        }
	
	
			//设置 request, application and session objects
			vc.put(VelocityServlet.REQUEST, context.getServletRequest());
			vc.put(VelocityServlet.RESPONSE, context.getServletResponse());
			vc.put(VelocityTemplateProcessor.APPLICATION, context.getServletContext());
			vc.put(VelocityTemplateProcessor.SESSION, context.getSession());
			
			vc.attachEventCartridge(new EventCartridge(){

				private String getKey(String reference){
					//不是${xxx} 就是$xx,目前只支持这种形式 
					if ( reference.startsWith("${") ){
						return reference.substring(2, reference.length()-1);
					}else {
						return reference.substring(1);
					}
				}
				public Object referenceInsert(String reference, Object value) {
					if ( reference.startsWith("$res_") || 
						 reference.startsWith("${res_")  ){
						String key = getKey(reference);
						/**
						 * @fixme: 要根据key判断是否是dynamic的资源,如果是才进行合并处理 
						 */
						
	                    StringWriter dataWriter = new StringWriter();    
		    			try {
							ve.evaluate(vc,  dataWriter,"SkinMerger.log", (String)value);
						} catch (Exception e) {
							e.printStackTrace();
						}
		    			//对常量进行合并处理,这样常量就可以读取动态数据(譬如:request支类的),也可以引用其他常量
		                return dataWriter.toString();

					}else{
					   return super.referenceInsert(reference, value);
					}
				}
				
			});

			
			
			
	        //copy资源
	        if (input.getAllResources().size() > 0) {
	            Iterator it = input.getAllResources().keySet().iterator();
	            while (it.hasNext()) {
	                String key = (String) it.next();
	                Resource resource = input.getResource(key);
	                String data = resource.getData();//常量值   
	                vc.put("res_" + key, data);
	            }
	        }
	
	        //2008-3-10 黄云辉
	        if (input.getAllResources().size() > 0) {
	            Iterator it = input.getAllResources().keySet().iterator();
	            while (it.hasNext()) {
	                String key = (String) it.next();
	                Resource resource = input.getResource(key);
	                if ( resource.isDynamic() ){
		                String data = resource.getData();//常量值	                
	                    StringWriter dataWriter = new StringWriter();    
		    			ve.evaluate(vc,  dataWriter,"SkinMerger.log", data);
		    			//对常量进行合并处理,这样常量就可以读取动态数据(譬如:request支类的),也可以引用其他常量
		                vc.put("res_" + key, dataWriter.toString());
	                }
	            }
	        }

			
	        
			this.getTemplate(input).merge(vc, pWriter);	
	        //os.write(w.getBuffer().toString().getBytes());

		} catch(Exception ex) {
			throw new XkinsException("合并模板 (" + input.getId() + ") 失败!" + ex.getMessage(), ex);
		}
    }

	/**
	 * 如果配置文件发生变化，则要重新初始化velocity
	 * Verifica si se produjo un reload para vaciar la cach�.
	 */
	public void actionPerformed(XkinsEvent event) {
		if(event instanceof XkinsLoadEvent) {
			synchronized(this) {
				try {
					this.init(((XkinsLoadEvent)event).getXkins());
				} catch(XkinsException xe) {
					throw new XkinsRuntimeException("初始化皮肤失败! " + xe.getMessage(), xe);
				}
			}
		}
	}

	/**
	 * 获取
	 * @param input
	 * @return
	 * @throws XkinsException
	 */
	private org.apache.velocity.Template getTemplate(Template input) throws XkinsException {
		try {
			org.apache.velocity.Template template = ve.getTemplate(input.getId());
			return template;
		} catch (Exception e ) {
			throw new XkinsException("获取模板失败: " + e.getMessage(), e);
		}			 
	}
}
