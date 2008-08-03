package net.jcreate.e3.table.util;

import javax.servlet.http.HttpServletRequest;

import net.jcreate.e3.table.support.TableConstants;

public class TableUtils {
  private TableUtils(){
	  
  }
  

    /**
     * 是否是导出excel,pdf等请求
     * @param pRequest
     * @return
     */
	public static boolean isExportRequest(HttpServletRequest pRequest){
		if ( pRequest == null ){
			return false;
		}
		String exportType = pRequest.getParameter(TableConstants.EXPORT_TYPE_PARAM);
		if ( exportType == null ){
			return false;
		} else {
			return true;
		}
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
