/* ====================================================================
 * Copyright (c) 2003 Guillermo Meyer.
 * Project: Xkins
 * (#)XkinProcessor.java
 *
 * Permission is granted to copy, distribute and/or modify this document
 * under the terms of the GNU Free Documentation License, Version 1.1 or
 * any later version published by the Free Software Foundation; with no
 * Invariant Sections, with no Front-Cover Texts.
 * A copy of the license is included in the section entitled
 * "GNU Free Documentation License".
 * ====================================================================
 */
package net.jcreate.xkins;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.jcreate.xkins.events.XkinsEventListener;
import net.jcreate.xkins.processor.TemplateProcessor;


/**
 * 用户处理模板生成处理.
 * @author 黄云辉
 *
 */
public class XkinProcessor extends ContextHolder {
    public static final String JSP_BODY = "ar.com.koalas.xkins.XkinProcessor.bodyContent";

    /**
     * 默认的请求处理器
     */
    public static final String DEFAULT_PROCESSOR_NAME = "ar.com.koalas.xkins.processor.TemplateProcessorImpl";
    /**
     * 默认的请求处理器
     */
    private static TemplateProcessor defaultProcessor = null;
    private static Map processors = null;
	
    //~ Instance fields ----------------------------------------------------------------------------

    protected Skin skin = null;
    protected String skinName = null;
    protected Template templ = null;
    protected Xkins xs = null;
	protected String templateName=null;
	
    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Constructor que crea un nuevo Context().
     */
    public XkinProcessor() {
		this.setContext(new Context());
		XkinProcessor.setLocalContext(this.getContext());
    }

    /**
     * Este contructor recibe el nombre del template y el pageContext. Usado generalmente en los taglibs.
     * Del pageContext extrae el <code>HttpServletRequest</code> y el <code>ServletContext</code>.
     * Asume el skin seteado actualmente en la session.
     * @param templateName
     * @param pctx
     */
    public XkinProcessor(String templateName, PageContext pctx) {
        this(null, templateName, pctx);
    }

    /**
     * Este contructor recibe el pageContext. Usado generalmente en los taglibs.
     * Del pageContext extrae el <code>HttpServletRequest</code> y el <code>ServletContext</code>.
     * Asume el skin seteado actualmente en la session.
     * @param pctx
     */
    public XkinProcessor(PageContext pctx) {
        this(null, null, pctx);
    }

    /**
     * Este contructor recibe el nombre del skin, el nombre del template y el pageContext.
     * Usado generalmente en los taglibs.
     * Del pageContext extrae el <code>HttpServletRequest</code> y el <code>ServletContext</code>.
     * @param skinName
     * @param templateName
     * @param pctx
     */
    public XkinProcessor(String skinName, String templateName, PageContext pctx) {
        this(skinName, templateName, pctx.getRequest(), pctx.getResponse());
    }

    /**
     * Este contructor recibe el nombre del template, el request y el servletContext.
     * Usado desde donde no tengo el pageContext, por ejemplo desde.
     * Asume el skin seteado actualmente en la session.
     * @param templateName
     * @param request
     * @param sc7
     */
    public XkinProcessor(String templateName, ServletRequest request, ServletResponse response) {
        this(null, templateName, request, response);
    }

    /**
     * Este contructor recibe el request y el servletContext. Usado generalmente en los servlets donde
     * no tengo el pageContext.
     * Asume el skin seteado actualmente en la session.
     * @param request
     * @param sc
     */
    public XkinProcessor(ServletRequest request, ServletResponse response) {
        this(null, null, request, response);
    }

	public XkinProcessor(ServletRequest request) {
		this(null, null, request, null);
	}

    /**
     * Este contructor recibe el nombre del skin, el nombre del template, el request y el servletContext.
     * Usado generalmente en los servlets donde no tengo el pageContext.
     * @param skinName
     * @param templateName
     * @param request
     * @param sc
     */
    public XkinProcessor(String p_skinName, String templateName, ServletRequest request,
							ServletResponse response) {
		try {
			//获取皮肤名称
			HttpSession session = ((HttpServletRequest)request).getSession();
	        if (p_skinName == null) {
	            this.skinName = (String) session.getAttribute(Skin.ATTR_SKIN_NAME);
	            if (this.skinName == null) {
					this.skinName = getDefaultSkinName(request);
	            }
	        } else {
	            this.skinName = p_skinName;
	        }
	
	        /**
	         * 获取xkins对象.
	         */
	        this.xs = (Xkins) session.getServletContext().getAttribute(Xkins.ATTR_SKINS);
	        
	        if(this.xs==null) throw new Exception("No Xkins are defined.");
	        	
	        this.setContext(new Context(request, response));
			XkinProcessor.setLocalContext(this.getContext());
	        this.skin = this.xs.getSkin(this.skinName);
			//si es null, defaultea
			if(this.skin==null) 
				this.skin = this.xs.getSkin(XkinProcessor.getDefaultSkinName(request));
				
			if(this.skin==null) throw new Exception("Skin not found: " + this.skinName);

			this.templateName = templateName;
			
	        if (templateName != null) {
	            this.templ = this.skin.getTemplate(templateName);
	        }
		} catch(Exception e) {
			XkinsLogger.getLogger().error("Error initializing XkinProcessor.", e);		
			throw new XkinsRuntimeException("Error initializing XkinProcessor. " + e.getMessage(), e);
		}
    }

	public XkinProcessor(Template template) {
		this();
		this.setTemplate(template);
	}


    //~ Methods ------------------------------------------------------------------------------------

    public static Skin getCurrentSkin(PageContext pctx) {		
        String skinName = XkinProcessor.getCurrentSkinName(pctx);
        Xkins xs = (Xkins) pctx.getServletConfig().getServletContext().getAttribute(Xkins.ATTR_SKINS);
		XkinProcessor.setLocalContext(new Context(pctx));        
        return xs.getSkin(skinName);
    }

    public static Skin getCurrentSkin(HttpServletRequest request) {
        String skinName = XkinProcessor.getCurrentSkinName(request);

        Xkins xs = (Xkins) request.getSession().getServletContext().getAttribute(Xkins.ATTR_SKINS);
		XkinProcessor.setLocalContext(new Context(request, null));
        return xs.getSkin(skinName);
    }

    /**
     * 设置当前皮肤
     * @param request
     * @param skinName
     */
    public static void setCurrentSkinName(HttpServletRequest request, String skinName) {
        request.getSession().setAttribute(Skin.ATTR_SKIN_NAME, skinName);
    }

    /**
     * 设置当前皮肤
     * @param pctx
     * @return
     */
    public static String getCurrentSkinName(PageContext pctx) {
        return XkinProcessor.getCurrentSkinName((HttpServletRequest) pctx.getRequest());
    }

    /**
     * 获取当前皮肤名称
     * @param request
     * @return
     */
    public static String getCurrentSkinName(HttpServletRequest request) {
        String skinName = (String) request.getSession().getAttribute(Skin.ATTR_SKIN_NAME);
        if (skinName == null) {
            skinName = getDefaultSkinName(request);
        }
        return skinName;
    }


/**
 * 获取参数
 * @return
 */
    public Map getParameters() {
        return this.getContext().getParameters();
    }

/**
 * 获取皮肤对象
 * @param pctx
 * @param skinName
 * @return
 */
    public static Skin getSkin(PageContext pctx, String skinName) {
        Xkins xs = (Xkins) pctx.getServletConfig().getServletContext().getAttribute(Xkins.ATTR_SKINS);
		XkinProcessor.setLocalContext(new Context(pctx));
        return xs.getSkin(skinName);
    }

    /**
     * 获取皮肤
     * @param request
     * @param skinName
     * @return
     */
    public static Skin getSkin(ServletRequest request, String skinName) {
        Xkins xs = (Xkins) ((HttpServletRequest)request).getSession().getServletContext().getAttribute(Xkins.ATTR_SKINS);
		XkinProcessor.setLocalContext(new Context());
        return xs.getSkin(skinName);
    }

    /**
     * 设置模板对象
     * @param templ
     * @return
     */
    public XkinProcessor setTemplate(Template templ) {
        this.templ = templ;
        return this;
    }

    /**
     * 设置模板对象
     * @param templateName
     * @return
     */
    public XkinProcessor setTemplateName(String templateName) {
    	if(this.getCurrentSkin()==null) {
			XkinsLogger.getLogger().error("Current Skin not found.");
    		throw new XkinsRuntimeException("Current Skin not found.");
    	} 
		if(templateName==null) {
			XkinsLogger.getLogger().error("Template name is null.");
			throw new XkinsRuntimeException("Template name is null.");
		} 
        this.templ = this.getCurrentSkin().getTemplate(templateName);
		if(this.templ==null) {
			XkinsLogger.getLogger().error("Template " + templateName + " is not found in skin " + this.getCurrentSkin().getName() + ".");
			throw new XkinsRuntimeException("Template " + templateName + " is not found in skin " + this.getCurrentSkin().getName() + ".");
		} 
        return this;
    }


    /**
     * 添加bodyContent参数 
     * @param value
     */
    public void addBodycontent(String value) {
        this.getParameters().put(JSP_BODY, value);
    }


    /**
     * 添加参数
     * @param name
     * @param value
     */public void addParameter(String name, Object value) {
    	if(name != null && value!= null)
        	this.getParameters().put(name, value);
    }

    /**
     * 添加模板参数
     * @param parameters
     */
    public void addParameters(Map parameters) {
        this.getParameters().putAll(parameters);
    }

    /**
     * 清除参数
     */
    public void clearParameters() {
        this.getParameters().clear();
    }

    /**
     * 设置当前皮肤
     * @param skin
     */
    public void setCurrentSkin(Skin skin) {
        this.skin = skin;
        this.skinName = skin.getName();
    }

    /**
     * 获取当前皮肤对象
     * @return
     */
    public Skin getCurrentSkin() {
    	if(this.skin==null) {
	        String p_skinName = this.skinName; 
	                
	        if (p_skinName == null) {
				p_skinName = (String) XkinProcessor.getLocalContext().getSession().getAttribute(Skin.ATTR_SKIN_NAME);
				if (p_skinName == null) {
					p_skinName = getDefaultSkinName(XkinProcessor.getLocalContext().getServletRequest());
				}
	        }
	
	        Xkins xs = (Xkins) XkinProcessor.getLocalContext().getServletContext().getAttribute(Xkins.ATTR_SKINS);
	        Skin skin = xs.getSkin(p_skinName);
	        if(skin==null) {
				XkinsLogger.getLogger().error("Skin " + p_skinName + " not defined.");
	        	throw new XkinsRuntimeException("Skin " + p_skinName + " not defined.");
	        }
	        return skin;
		} else {
			return this.skin;
		}
    }

    /**
     * 设置当前皮肤
     * @param skinName
     */
    public void setCurrentSkinName(String skinName) {
        XkinProcessor.getLocalContext().getSession().setAttribute(Skin.ATTR_SKIN_NAME, skinName);
    }

    /**
     * 获取请求处理器
     * @return
     */
    public static Map getProcessors() {
        if (processors == null) {
            processors = new HashMap();
        }

        return processors;
    }

    /**
     * 获取指定皮肤
     * @param skinName
     * @return
     */
    public Skin getSkin(String skinName) {
        Xkins xs = (Xkins) XkinProcessor.getLocalContext().getServletContext().getAttribute(Xkins.ATTR_SKINS);
        return xs.getSkin(skinName);
    }

    /**
     * 获取xkins对象
     * @return
     */
    public Xkins getXkins() {
        Xkins xs = (Xkins) XkinProcessor.getLocalContext().getServletContext().getAttribute(Xkins.ATTR_SKINS);
        return xs;
    }

    /**
     * 返回当前模板处理内容
     * @return
     */
    public String processContent() {
    	StringWriter baos = new StringWriter();
        this.processContent(baos);
        return baos.toString();
    }

    /**
     * 输出当前模板信息
     * @param os
     */
    public void processContent(StringWriter os) {
        if (this.templ == null) {
			XkinsLogger.getLogger().warn("Template is null or not defined in skin: " + this.skinName + "." + this.templateName);
			return;
        }

        try {
            if (templ.getCachedContent() != null) {
                os.write(templ.getCachedContent());
            } else {
                this.process(templ, os);
            }
        } catch (Exception e) {
			XkinsLogger.getLogger().error("Exception while processing content: " + e.toString(), e);
            throw new XkinsRuntimeException("Exception while processing content. "+e.toString(), e);
        }
    }

    /**
     * 获取一个模板对象的模板处理器
     * 
     * @param input 模板对象.
     * @return
     */
    protected static String getTemplateProcessorClassName(Template input) {
    	//模板处理器有3个级别的.模板,皮肤,XKINS;
		if (input.getProcessor() != null) {
			if(input.getProcessor().getType()==null) {
				Object id = input.getProcessor().getId();
				return ((Processor)(input.getSkin().getXkins().getProcessors().get(id))).getType();
			} else {
				return input.getProcessor().getType();	
			}			
		}
		if (input.getSkin().getProcessor() != null) {
			if(input.getSkin().getProcessor().getType()==null) {
				Object id = input.getSkin().getProcessor().getId();
				return ((Processor)(input.getSkin().getXkins().getProcessors().get(id))).getType();
			} else {
				return input.getSkin().getProcessor().getType();	
			}			
		}
		return DEFAULT_PROCESSOR_NAME;
    }

    /**
     * 生成模板
     * @param input
     * @param os
     * @throws XkinsException
     */
    protected void process(Template input, StringWriter os)
            throws XkinsException {        
        this.getContext().setXkins(XkinProcessor.getXkinsFromTemplate(input));
		XkinProcessor.setLocalContext(this.getContext());
        XkinProcessor.getTemplateProcessor(input).process(input, this.getContext(), os);
    }

    
	private static Xkins getXkinsFromTemplate(Template input) {
		return input.getSkin()!=null && input.getSkin().getXkins()!=null?input.getSkin().getXkins():null;
	}

	/**
	 * 获取模板处理器
	 * @param input
	 * @return
	 */
    private static TemplateProcessor getTemplateProcessor(Template input) {
        try {            
            //me fijo si ya lo cree para no volver a crearlo.
            String tpcn = XkinProcessor.getTemplateProcessorClassName(input);
            TemplateProcessor tp = (TemplateProcessor) getProcessors().get(tpcn);
            if (tp == null) {
                synchronized(getProcessors()) {
	                //lo creo y lo cacheo.
	                tp = (TemplateProcessor) Class.forName(tpcn).newInstance();
	                tp.init(XkinProcessor.getXkinsFromTemplate(input));
	                getProcessors().put(tpcn, tp);
	                //Si es listener de eventos lo registro.
	                if(tp instanceof XkinsEventListener) {
	                	Xkins xkins = XkinProcessor.getXkinsFromTemplate(input); 
	                	if(xkins!=null) {
	                		xkins.addEventListener((XkinsEventListener)tp);
	                	}	 
	                }
				}
            }            
            return tp;
        } catch (Exception ex) {
			XkinsLogger.getLogger().error("Error Loading Template Processor.", ex);
            throw new XkinsRuntimeException("Error Loading Template Processor: " + ex.toString(), ex);
        }
    }
    
    /**
     * 获取默认皮肤名称.
     * @param request
     * @return
     */
    public static String getDefaultSkinName(ServletRequest request) {    	
		String dsn = ((Xkins)((HttpServletRequest)request).getSession().getServletContext().getAttribute(Xkins.ATTR_SKINS)).getDefaultSkinName();
		if(dsn==null) {
			dsn = (String)((HttpServletRequest)request).getSession().getServletContext().getAttribute(Skin.ATTR_DEFAULT_SKIN_NAME);			
		}
    	return dsn;
    }
}
