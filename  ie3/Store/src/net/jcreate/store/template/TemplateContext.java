package net.jcreate.store.template;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TemplateContext {
  public TemplateContext() {
  }

  private  Map templateMap = new HashMap();


  /**
    * 设置模板变量
    * @param pValue 模板变量对象
    */
   public void put(TemplateVar pValue){
     if ( pValue == null )
       throw new java.lang.NullPointerException();
     String templateVarName = pValue.getName();
     if ( templateVarName == null )
       throw new java.lang.NullPointerException("模板变量名不能为空null");
     templateMap.put(templateVarName, pValue);
   }

   /**
    *
    * @param pObj Object
    */
   public void put(Object pObj){
     if ( pObj == null )
          throw new java.lang.NullPointerException();

        Class pClass = pObj.getClass();
        Field[] fields = ReflectUtil.getAllField(pClass);
        int len = fields.length;

        for(int i=0; i<len; i++){
          String fieldName = fields[i].getName();
          Class  fieldType = fields[i].getType();

          String targetMethod= null;
          if (fieldType == boolean.class)
            targetMethod="is" + fieldName.substring(0,1).toUpperCase()
                              + fieldName.substring(1,fieldName.length());
          else
            targetMethod="get" + fieldName.substring(0,1).toUpperCase()
                               + fieldName.substring(1,fieldName.length());

          Object returnValue = null;
          try {
              Class[] args = {};
              Object[] params = {};
              Method m = pClass.getMethod(targetMethod, args);
              returnValue = m.invoke(pObj,params);
          }catch(Exception e){
			  //do nothing.
          }
          put(fieldName, returnValue);
        }
   }


   /**
    * @param pTemplateVarName : 模板变量名
    * @param pValue           : 模板变量值
    * @param pType            : 模板变量类别
    */
   public void put(String pTemplateVarName,
                   Object pValue,
                   TemplateVarType pType){
     if ( pTemplateVarName == null )
       throw new java.lang.NullPointerException("模板变量名不能为空null");
     TemplateVar aTemplateVar = new TemplateVar();
     aTemplateVar.setName(pTemplateVarName);
     aTemplateVar.setValue(pValue);
     aTemplateVar.setType(pType);
     templateMap.put(pTemplateVarName, aTemplateVar);
   }

   /**
    * @param pTemplateVarName : 模板变量名
    * @param pValue           : 模板变量值
    * 根据模板值自动获取模板变量类型
    *
    * 通常情况下,只要调用这个方法,而无需指定模板类别.
    * 但在下面这种情况下,就必须指定模板变量类别.
    * 如:
    *   select * from ${where}
    * 对于这种模板SQL,你就必须指定模板变量类别为TemplateVarType.NUMBER,
    * 因为模板变量where的java类型为String, 系统自动会将他的模板类型映射为
    * TemplateVarType.MSG,而TemplateVarType.MSG类型的模板变量值会在java变量
    * 值的基础上加上一对单引号'',
    * 假设模板变量的java值为 id = '1'
    * 那没有指定模板变量类型的情况下,SQL语句的值为select * from 'id ='1'',这不是
    * 我们想要的结果.所以必须手动指定其类型为TemplateVarType.NUMBER
    * 结果为:select * from id ='1'
    *
    */
   public void put(String pTemplateVarName,
                   Object pValue){
       TemplateVarType varType = TemplateVarType.getInstance(pValue);
       put(pTemplateVarName, pValue, varType);
   }


  /**
   * 获取模板值
   * @param pTemplateVarName：模板变量名
   * @return    ：值
   */
  public  Object getValue(String pTemplateVarName){
    TemplateVar templateVar = get(pTemplateVarName);
    return templateVar.getValue();
  }

  /**
     * 获取模板变量对象
     * @param pTemplateVarName : 模板变量名字
     * @return
     */
    public TemplateVar get(String pTemplateVarName){
      if ( this.containsKey( pTemplateVarName) == false ){
      //模板变量不存在时,返回 { 模板变量,${模板变量},TemplateVarType.NUMBER }
        TemplateVar temp = new TemplateVar();
        temp.setName(pTemplateVarName);
        temp.setValue("${" + pTemplateVarName + "}");
        temp.setType(TemplateVarType.NUMBER);
        return temp;
      }
      Object result = templateMap.get(pTemplateVarName);
      return  (TemplateVar)result;
    }


  /**
     * 获取模板变量的模板值
     * @param pTemplateVarName
     * @return
     */
    public String getTemplateVarValue(String pTemplateVarName){
      TemplateVar templateVar = get(pTemplateVarName);
      return templateVar.getTemplateValue();
    }


  /**
   * 删除对象
   * @param pTemplateVarName ：键
   */
  public  void remove(final String pTemplateVarName){
    templateMap.remove(pTemplateVarName);
  }

  /**
   * 是否包含主键
   * @param pTemplateVarName ：键
   * @return ： 如果存在返回true,否则返回false.
   */
  public  boolean containsKey(String pTemplateVarName){
    return templateMap.containsKey(pTemplateVarName);
  }

  /**
   * 清除所有对象
   */
  public  void clear(){
    templateMap.clear();
  }

  /**
   * 键-值 数
   * @return ：键-值个数
   */
  public  int size(){
    return templateMap.size();
  }
  
  public String toString(){
	  StringBuffer sb = new StringBuffer();
	 Set keys = templateMap.keySet();
	 Iterator keyIterator = keys.iterator();
	 while( keyIterator.hasNext()  ){
		 String key = (String)keyIterator.next();
		 Object value =  templateMap.get(key);
		 TemplateVar templateVar = (TemplateVar)value;
		 sb.append(key + "=" + templateVar.getValue()).append("\r\n");
	 }
	 return sb.toString();
  }

}
