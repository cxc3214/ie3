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
package net.jcreate.xkins.processor;

import java.io.IOException;
import java.io.OutputStream;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeSingleton;
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
	        VelocityContext vc = new VelocityContext();
	
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
	
	        //copy资源
	        if (input.getAllResources().size() > 0) {
	            Iterator it = input.getAllResources().keySet().iterator();
	            while (it.hasNext()) {
	                String key = (String) it.next();
	                vc.put("res_" + key, input.getResource(key).getData());
	            }
	        }
	
			//设置 request, application and session objects
			vc.put(VelocityServlet.REQUEST, context.getServletRequest());
			vc.put(VelocityServlet.RESPONSE, context.getServletResponse());
			vc.put(VelocityTemplateProcessor.APPLICATION, context.getServletContext());
			vc.put(VelocityTemplateProcessor.SESSION, context.getSession());
			
	        //StringWriter w = new StringWriter();	        
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
