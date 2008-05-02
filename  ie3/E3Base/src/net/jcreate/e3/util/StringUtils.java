package net.jcreate.e3.util;

public abstract class StringUtils {

	  /**
	   * 判断字符串pStr是否为空.
	   * 判断规则:
	   *  当pStr为null或者是长度为0的字符串就为空，否则为非空.
	   *  
	   * @param pStr 待判断的字符串
	   * @return 如果是空返回true,否则返回false
	   */
	  public static boolean isEmpty(String pStr){
		  return false;
	  }
	  
	  /**
	   * 判断字符串pStr是否为非空.
	   * 判断规则:
	   *   当pStr为null或者是长度为0的字符串就为空，否则为非空.
	   * 判断字符串pStr是否非空.
	   * @param pStr 待判断的字符串
	   * @return 如果是非空返回true,否则返回false
	   */
	  public static boolean isNotEmpty(String pStr){
		  return ! isEmpty(pStr);
	  }
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
