package net.jcreate.home.taglib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.jcreate.home.dict.DictItem;
import net.jcreate.home.dict.DictService;

public class DictTag  extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private String styleId;	
	private String onchange;
	private String property = null;
	private String dictCode;
	/**
	 * @todo: 设置默认样式
	 */
	private String styleClass;
	
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}	
	
	
	public void release() {
		this.styleId = null;
		this.property = null;
		this.property = null;
		this.dictCode = null;
		this.styleClass = null;
		super.release();
	}
	
	private String createOptions() throws Exception{
		StringBuffer sb = new StringBuffer();
		/**
		 * @fixme: 以后从cache中获取字典数据 
		 */
		List dictItems = DictService.getInstance().getDictItems(this.dictCode);
		
		for(int i=0; i<dictItems.size(); i++){
			DictItem dictItem = (DictItem)dictItems.get(i);
			if ( dictItem == null ){
				continue;
			}
			/**
			 * @todo: 读property的值,自动选种当前项
			 */
			sb.append("<option value=\"").append(dictItem.getDictItemCode()).append("\" >").
			append(dictItem.getDictItemName()).
			append("</option>").append("\n");
		}
		return sb.toString();

	}
	
	public int doEndTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        try{
        	StringBuffer sb = new StringBuffer();
        	sb.append("<select name=\"" + this.getProperty() + "\" ");
        	sb.append( "id=\"" + this.getId() + "\" ");
        	if(this.getStyleClass()!=null){
        		sb.append(" class = \"" + this.getStyleClass() + "\" ");
        	}
        	if(this.getOnchange()!=null){
        		sb.append(" onchange = \"" + this.getOnchange() + "\" ");
        	}
        	sb.append(">");
        	sb.append( createOptions() );
       		sb.append("</select>");
       		pageContext.getOut().print(sb.toString());
        }catch(Exception ex){
            throw new JspException("获取字典: " + this.dictCode + " 细项失败!" + ex.getMessage(), ex);   
        }
		return (SKIP_BODY);
	}
}
