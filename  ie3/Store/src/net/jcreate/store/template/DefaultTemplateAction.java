package net.jcreate.store.template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DefaultTemplateAction implements TemplateAction{
	private Log log = LogFactory.getLog(DefaultTemplateAction.class);
  public DefaultTemplateAction() {
  }

  /**
   * 模板对象在解析模板语句时，当碰到模板变量会执行这个方法.
   * @param pContext TemplateContext 模板上下文，用来保存模板变量
   * @param pParseContext ParseContext
   * @return 模板变量对应的值
   */
  public String perform(ParseContext pParseContext, final TemplateContext pContext){
    String templateName = pParseContext.getTemplateVarName();
	boolean isExist = pContext.containsKey(templateName);
	if ( isExist == false ){
		if ( log.isWarnEnabled() ){
			log.warn("can't find template name:" + templateName + " in TemplateContext" );
		}
	}
    TemplateVar result = pContext. get(templateName);
    if ( result == null )
      return null;
    return String.valueOf(result.getValue());
  }

}

