package net.jcreate.e3.table.theme;

import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.TableDirector;
import net.jcreate.e3.table.builder.HTMLExcelTableBuilder;

public class ExportThemeFactory implements ThemeFactory {
	
	  private final TableContext tableContext;
	  public ExportThemeFactory(TableContext pTableContext){
	    this.tableContext = pTableContext;	  
	  }
	  public TableBuilder createBuilder()  throws ThemeException{
		  /**
		   * TODO: 其他builder,根据skin的值，选择不同的builder.
		   */
		  HTMLExcelTableBuilder builder = new HTMLExcelTableBuilder();
		  return builder;
	  }
	  
	  public TableDirector createDirector()  throws ThemeException{
		  ExportTableDirector director = new ExportTableDirector(tableContext);
		  return director;
	  }

}
