package net.jcreate.e3.table.theme;

import net.jcreate.e3.table.TableBuilder;
import net.jcreate.e3.table.TableDirector;


public interface ThemeFactory {
  public TableBuilder createBuilder()  throws ThemeException;;
  public TableDirector createDirector()  throws ThemeException;;  
}
