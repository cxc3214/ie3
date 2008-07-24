package net.jcreate.e3.table.util;

public class Assert {
  public static void notNull(Object pObj){
	  if ( pObj == null ){
		  throw new java.lang.NullPointerException();
	  }
  }
  public static void notNull(Object pObj, String pMsg){
	  if ( pObj == null ){
		  throw new java.lang.NullPointerException(pMsg);
	  }
  }
  
}
