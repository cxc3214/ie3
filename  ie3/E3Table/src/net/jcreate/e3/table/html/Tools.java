package net.jcreate.e3.table.html;

import net.jcreate.e3.table.util.StringUtils;

public class Tools {

	public Object escapeJs(Object pValue){
		if ( pValue instanceof String == false ){
			return pValue;
		}
		return StringUtils.escapeJavaScript((String)pValue);
	}
}
