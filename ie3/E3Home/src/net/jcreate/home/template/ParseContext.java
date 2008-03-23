package net.jcreate.home.template;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ParseContext {
  private String templateVarName;//模板变量名
  private int index;//当前处于分析状态的模板变量是第几个模板变量
  private String templateMsg;//被分析的模板字符串
  public ParseContext() {
  }
  public String getTemplateVarName() {
    return templateVarName;
  }
  public void setTemplateVarName(String templateVarName) {
    this.templateVarName = templateVarName;
  }
  public int getIndex() {
    return index;
  }
  public void setIndex(int index) {
    this.index = index;
  }
  public String getTemplateMsg() {
    return templateMsg;
  }
  public void setTemplateMsg(String templateMsg) {
    this.templateMsg = templateMsg;
  }

}
