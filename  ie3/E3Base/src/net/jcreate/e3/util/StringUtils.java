package net.jcreate.e3.util;

public abstract class StringUtils {

    public static boolean isNotEmpty(String pStr){
    	return ! isEmpty(pStr); 
    }
    public static boolean isEmpty(String pStr){
    	if ( pStr == null ){
    		return true;
    	}
    	if ( "".equals(pStr) ){
    		return true;
    	}
    	return false;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
