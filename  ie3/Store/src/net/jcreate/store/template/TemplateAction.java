package net.jcreate.store.template;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface TemplateAction {

  /**
   * 模板对象在解析模板语句时，当碰到模板变量会执行这个方法.
   * @param pContext TemplateContext 模板上下文，用来保存模板变量
   * @param pParseContext ParseContext
   * @return 模板变量对应的值
   */
  public String perform(ParseContext pParseContext, final TemplateContext pContext);
}
