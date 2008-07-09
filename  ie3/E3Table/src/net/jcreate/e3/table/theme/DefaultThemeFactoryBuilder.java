package net.jcreate.e3.table.theme;

import net.jcreate.e3.table.TableContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultThemeFactoryBuilder implements ThemeFactoryBuilder {
	
	  private final Log logger = LogFactory.getLog( this.getClass() );
	  
	  private final TableContext tableContext;
	  public DefaultThemeFactoryBuilder(TableContext pTableContext){
	    this.tableContext = pTableContext;	  
	  }	
	  
	 private static final String STD_THEME = "E3001";
	 private static final String EXT_THEME = "E3002";
	 public ThemeFactory build(String pTheme) throws ThemeException{
		 logger.debug("构造主题工厂:" + pTheme);		 
		 if ( pTheme == null ){
			 return null;
		 }
		 ThemeFactory result = null;
		 if ( STD_THEME.equals(pTheme) ){
			 result = new StdThemeFactory(tableContext);			 
		 } else if ( EXT_THEME.equals(pTheme) ){
			 result = new ExtThemeFactory(tableContext);
		 } else {
			 final String msg =
				 "不支持的的主题:" + pTheme;
			 throw new ThemeException(msg);
		 }		 
	    return result;	 
	 }
}
