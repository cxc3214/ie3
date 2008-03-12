package net.jcreate.e3.table.skin;

import java.util.ArrayList;
import java.util.List;

public class SkinUtil {
	
  private SkinUtil(){
	  
  }
  public static final String SYS_PATH = "/e3/table/skins";
  public static String[] getSkinPaths(final String pSkinPath){
	  String skinPath = pSkinPath;
      if ( pSkinPath == null ){
    	  skinPath = SYS_PATH ;
      }else{
      	boolean isIncludedSysPath = pSkinPath.indexOf(SYS_PATH) != -1; 
          if ( isIncludedSysPath == false  ){
        	  skinPath = pSkinPath + "," + SYS_PATH;
          }else{
        	  skinPath = pSkinPath;
          }
      }
  	List result = new ArrayList();
	java.util.StringTokenizer st = new java.util.StringTokenizer(skinPath, ";,");
	while( st.hasMoreTokens() ){
		String token = st.nextToken();
		result.add(token);
	}
	return (String[])result.toArray(new String[result.size()]);
	  
  }
}
