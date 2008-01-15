/**
 * @author:  黄云辉
 * @company: 珠海进创科技
 * @date:    2003-10-20
 */
package net.jcreate.store.template;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TemplateVar {
  private String name;//模板变量名字
    private Object value;//模板变量值
    private TemplateVarType type;//模板变量类别

    public TemplateVar() {
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public Object getValue() {
      return value;
    }
    public void setValue(Object value) {
      this.value = value;
    }
    public TemplateVarType getType() {
      return type;
    }
    public void setType(TemplateVarType type) {
      this.type = type;
    }

    /**
     * 模板变量的模板值,注意:模板变量的值和模板变量的模板值不一样
     * 模板变量的值就是模板变量的实际值,而模板变量的模板值经过调整
     * 如:模板变量为keyId, 值为20, 类别 TemplateVarType.NUMBER,那么模板值为20
     *                    值为20, 类别 TemplateVarType.MSG    那么模板值为'20'
     * @return
     */
    public String getTemplateValue() {
      String result = null;
      if ( this.value == null )
        return null;
      if ( this.type == null ){//没有设置模板变量类型
        this.type = TemplateVarType.getInstance(this.value);
      }
      if ( TemplateVarType.NUMBER.equals(this.type ) )
           return String.valueOf(this.value);
      return "'" + String.valueOf(this.value) + "'";
    }
	
	public String toString(){
		return "name=" + name  +
		       "value=" + value;
	}

}
