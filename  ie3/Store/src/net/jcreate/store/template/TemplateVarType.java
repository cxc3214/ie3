package net.jcreate.store.template;

/**
 * <p>Title: 模板变量类型</p>
 * <p>Description: </p>
 * <p>java数据类型和模板变量类型的对应关系</p>
 * <p>java数据类型     模板变量类型</p>
 * <p>   Short->           NUMBER    </p>
 * <p>   Byte->            NUMBER    </p>
 * <p>   Integer->         NUMBER    </p>
 * <p>   Long->            NUMBER    </p>
 * <p>   Double->          NUMBER    </p>
 * <p>   String->          MSG       </p>
 * <p>   java.sql.Date->   MSG       </p>
 * <p>   java.util.Date->   MSG       </p>
 * <p>   java.lang.String->MSG       </p>
 * 说明:
 *    这种映射关系不是固定的,你可以根据需求改变这种映射关系,
 *    譬如说,你可以把Short类型映射为MSG.
 *
 * <p>Copyright: Copyright (c) 2003</p>
 * @author 黄云辉
 * @version 1.0
 */

public class TemplateVarType {
  private final String typeName;
  private TemplateVarType(String pTypeName) {
    this.typeName = pTypeName;
  }

  public String toString(){
    return this.typeName;
  }

  /**
   * 获取对象对应的模板变量类别
   * @param pObj
   * @return
   */
  public static TemplateVarType getInstance(Object pObj){
    if ( pObj == null )
      return TemplateVarType.NUMBER;
    Class type = pObj.getClass();
    if ( java.lang.String.class == type &&
         pObj != null )
      return TemplateVarType.MSG;

    if ( java.sql.Date.class == type &&
         pObj != null )
      return TemplateVarType.MSG;

    if ( java.util.Date.class == type &&
         pObj != null )
      return TemplateVarType.MSG;

    return TemplateVarType.NUMBER;
  }

  /**
   * 数字型
   */
  public static final TemplateVarType NUMBER = new TemplateVarType("type.number");

  /**
   * 消息型
   */
  public static final TemplateVarType MSG = new TemplateVarType("type.msg");


}
