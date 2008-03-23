package net.jcreate.home.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 不输出任何信息,只将数据保存到Map中,提供给Layout标签使用
 * @author 黄云辉
 *
 */
public class BoxTag  extends BodyTagSupport {	
	private static final long serialVersionUID = 1L;

	public static final String LAYOUT_PREFIX = "net.jcreate.store.taglib.";
	public static final String DEFAULT_LAYOUT = "default";
	
    private String id;    
    private String layoutId = DEFAULT_LAYOUT;
    private String type = "object";//object/list,默认object
    
	public int doEndTag() throws JspException {
        final String bodyContent = this.bodyContent == null ? "" : this.bodyContent.getString();
        Map paramsMap = (Map)this.pageContext.getAttribute(LAYOUT_PREFIX + layoutId);
        if ( paramsMap == null ){
        	paramsMap = new HashMap();
        	this.pageContext.setAttribute(LAYOUT_PREFIX + layoutId, paramsMap);
        }
        if ( "object".equalsIgnoreCase(type)){
            paramsMap.put(id, bodyContent);        	
        }else if ( "list".equalsIgnoreCase(type) ){
        	List boxList = null;
        	if ( paramsMap.containsKey(id) == false){
        		boxList = new ArrayList();
        		paramsMap.put(id, boxList);
        	}else {
        		boxList = (List)paramsMap.get(id);
        	}
        	boxList.add(bodyContent);
        } else {
        	throw new JspException("不支持的type:" + this.type);
        }

		return super.doEndTag();
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public void release() {
		super.release();
		this.id = null;
		this.type = null;
		this.layoutId = null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
}
