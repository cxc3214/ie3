package net.jcreate.e3.table.ajax;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.table.support.TableConstants;

public class AjaxUtil {

	private AjaxUtil(){
		
	}
	
	public static void main(String[] args){
		String refreshZone = "xx";
		String s =
			"<!--e3.begin of refresh zone:"+refreshZone+ "-->" +
			"xxxxxxxxxxxxxxxx" +
			"<!--e3.end of refresh zone:"+refreshZone+ "-->";
		String result = getAjaxData(refreshZone, s);
		System.out.println(result);
			
	}
	
	public static String getAjaxData(String refreshZone, String pResponseData){
		if ( pResponseData == null ){
			return "";
		}
		if ( refreshZone == null ){
			return pResponseData;
		}
		String beginTag = "<!--e3.begin of refresh zone:"+refreshZone+ "-->";
		String endTag = "<!--e3.end of refresh zone:"+refreshZone+ "-->";
		int beginIndex = pResponseData.indexOf(beginTag);
		int endIndex = pResponseData.indexOf(endTag);
		if ( beginIndex == - 1  || endIndex == -1 ) {
			return pResponseData;
		}
		return pResponseData.substring(beginIndex+beginTag.length(), endIndex);
	}
	
	public static boolean isAjaxRequest(HttpServletRequest pRequest){
		if ( pRequest == null ){
			return false;
		}
		String refreshZone = pRequest.getParameter(TableConstants.REFRESH_ZONE_PARAM);
		if ( refreshZone == null ){
			return false;
		} else {
			return true;
		}
	}
	
}
