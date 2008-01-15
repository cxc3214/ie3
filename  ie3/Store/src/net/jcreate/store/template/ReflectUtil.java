package net.jcreate.store.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ReflectUtil {
	/**
	 * 
	 */
	private static final ThreadLocal excludeModles = new ThreadLocal();
	
	public static void setExcludeModles(Set pModels){
		excludeModles.set(pModels);
	}
	
	public static Set getExcludeModles(){
		Set modles = (Set)excludeModles.get();
		if (modles == null){
			modles =  java.util.Collections.EMPTY_SET;
		}
	    return modles;
	}
	
    private static boolean contains(Class pClass){
		if ( pClass == null)
			return false;
	  Set excludeModles = getExcludeModles();
	  return excludeModles.contains(pClass.getName());
    }
	
	private ReflectUtil(){
		
	}

	/**
	 * 返回所有字段，包括父类的.
	 * 注意：不返回同名字段.
	 * @param pClass
	 * @return
	 */
	public static Field[] getAllField(Class pClass){
		if ( contains(pClass) == true )
			return new Field[0];
		
		final Set allFields = new HashSet();
		  class FieldUtils{
			public  void getFields(Class pClass){
				Field[] fields = pClass.getDeclaredFields();
			  allFields.addAll(Arrays.asList(fields));
			  Class superClass = pClass.getSuperclass();
			  if ( superClass != null && contains(superClass) == false )
				  getFields(superClass);  
			}
		}
		  FieldUtils fieldUtil = new FieldUtils();
		  fieldUtil.getFields(pClass);
		  Set result = new HashSet();
		  List fieldNames = new ArrayList();
		  Iterator allFieldIterator = allFields.iterator();
		  while( allFieldIterator.hasNext() ){
			  Field field = (Field)allFieldIterator.next();
			  String fieldName = field.getName();
			  //同名字段.
			  if ( fieldNames.contains(fieldName) ){
				  continue;
			  }
			  //class$0是个特殊字段.
			  if ( "class$0".equals(fieldName) ){
				  continue;
			  }
			  fieldNames.add(fieldName);			  
			  result.add(field);
		  }
			  //allField.g
		  //}
		return (Field[])result.toArray(new Field[0]);
	}
	
	public static void main(String[] args) {
	}
}
