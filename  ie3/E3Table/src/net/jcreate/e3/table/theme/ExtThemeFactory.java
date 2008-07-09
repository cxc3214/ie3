package net.jcreate.e3.table.theme;

import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.TableDirector;
import net.jcreate.e3.table.html.FastSkinHTMLTableBuilder;
import net.jcreate.e3.table.support.DefaultTableDirector;

public class ExtThemeFactory implements ThemeFactory {
	
	  private final TableContext tableContext;
	  public ExtThemeFactory(TableContext pTableContext){
	    this.tableContext = pTableContext;	  
	  }
	  public TableBuilder createBuilder()  throws ThemeException{
		  FastSkinHTMLTableBuilder builder = new FastSkinHTMLTableBuilder();
		  return builder;
	  }
	  
	  public TableDirector createDirector()  throws ThemeException{
		  DefaultTableDirector director = new DefaultTableDirector(tableContext);
		  return director;
	  }

}
