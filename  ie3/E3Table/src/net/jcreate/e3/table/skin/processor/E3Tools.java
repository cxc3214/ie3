package net.jcreate.e3.table.skin.processor;

public class E3Tools {

	public static int toInt(String pStr){
		if ( pStr == null ){
			return 0;
		}
		return Integer.parseInt(pStr);
	}
}
