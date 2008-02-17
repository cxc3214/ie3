package net.jcreate.e3.table.decorator;

import net.jcreate.e3.table.Cell;

public class MaxLenDecorator extends AbstractDecorator {

	private int maxLen;
	public int getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(int maxLen) {
		this.maxLen = maxLen;
	}

	public Object decorate(Object pValue, Cell pCell) throws Exception {
		if ( pCell == null ){
			return null;			
		}
		if ( pValue == null ){
			return null;
		}
		return chop(pValue.toString(), maxLen);
 
	}
	
    public static String chop(final String pSrc, int pMaxLen){
    	if ( pSrc == null ){
    		return  null;
    	}
    	final int MIN_LEN = 4;
    	if ( pMaxLen < MIN_LEN ){
    		return pSrc;
    	}
    	final String POSTFIX = "...";
    	if (  getLen(pSrc)  > pMaxLen ){
    		/**
    		 * @fixme: 这
    		 */
    		return pSrc.substring(0, java.lang.Math.min(pSrc.length(), pMaxLen)) + POSTFIX;
    	} else {
    		return pSrc;
    	}
    	
    }
//    
//    private String cut(final String pSrc, final int pCutLen){
//      if ( pSrc == null ){
//    	  return null;
//      }
//      if ( pCutLen < 1 ){
//    	  return pSrc;
//      }
//      
//      if ( pCutLen > pSrc.length() ){
//    	  return "";
//      }
//      int index = pSrc.length() - pCutLen;
//      int count = 0;
//      for(int i=index; i<index+pCutLen; i++){
//      	char c = pSrc.charAt(i);
//    	if ( c< '\u00ff' ){
//    	} else {
//           count++; 
//    	} 
//      }
//      int result = pCutLen;
//      result = result - count;
//      
//      
//      
//    }
    
    /**
     * 获取 pSrc的长度，双字节字符的长度算2不是1 
     * @param pSrc
     * @return
     */
    private static int getLen(final String pSrc){
    	int count=0;
        for(int i=0; i<pSrc.length(); i++){
        	char c = pSrc.charAt(i);
        	if ( c< '\u00ff' ){
        		count++;
        	} else {
        		count = count + 2;
        	}
        }
    	return count;
    }
    
}
