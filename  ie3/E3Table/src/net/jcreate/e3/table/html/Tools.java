package net.jcreate.e3.table.html;

import net.jcreate.e3.table.util.StringUtils;

public class Tools {

	public Object escapeJs(Object pValue){
		if ( pValue instanceof String == false ){
			return pValue;
		}
		return StringUtils.escapeJavaScript((String)pValue);
	}
	
	public String width(String pWidth){
		return width(pWidth, '\'');
	}
	public String width(String pWidth, char pChar){
		if ( isNum(pWidth) ){
			return pWidth;
		}
		return pChar + pWidth + pChar;
	}
	public boolean isNum(String pWidth){
		if ( pWidth == null ){
			return false;
		}
		boolean isNum = true;		
		try{
    		Double.parseDouble(pWidth);
		}catch(Exception ex){
			isNum = false;
		}
		return isNum;
	}
}
