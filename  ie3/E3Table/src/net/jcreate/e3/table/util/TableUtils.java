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
	public static boolean isExportTableRequest(HttpServletRequest pRequest){
		if ( pRequest == null ){
			return false;
		}
		String exportTable = pRequest.getParameter(TableConstants.EXPORT_TABLE_PARAM);
		if ( exportTable == null ){
			return false;
		} else {
			return true;
		}
	}
	
    /**
     * 是否是导出请求参数
     * @param pRequest
     * @return
     */
	public static boolean isExportParamRequest(HttpServletRequest pRequest){
		if ( pRequest == null ){
			return false;
		}
		String exportParam = pRequest.getParameter(TableConstants.EXPORT_PARAM_PARAM);
		return "true".equalsIgnoreCase(exportParam);
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
