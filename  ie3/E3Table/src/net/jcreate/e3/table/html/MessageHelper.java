package net.jcreate.e3.table.html;

import java.util.Locale;

import net.jcreate.e3.table.NoSuchMessageException;
import net.jcreate.e3.table.TableContext;

/**
 * 获取资源信息.
 * @author 黄云辉
 */
public class MessageHelper {
	
	private TableContext tableContext;
	public MessageHelper(TableContext pTableContext){
		tableContext = pTableContext;
	}
	
  public String getMessage(String  pKey) throws NoSuchMessageException{
	  Locale locale = tableContext.getI18nResourceProvider().resolveLocale(tableContext.getWebContext());
	  String result = tableContext.getMessageSource().getMessage(pKey, null, locale);
	  return result;
  }
}
