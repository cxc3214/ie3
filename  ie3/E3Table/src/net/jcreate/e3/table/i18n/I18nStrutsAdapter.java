/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package net.jcreate.e3.table.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import net.jcreate.e3.table.I18nResourceProvider;
import net.jcreate.e3.table.NoSuchMessageException;
import net.jcreate.e3.table.WebContext;
import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;


/**
 * Struts implementation of a resource provider and locale resolver. Uses Struts
 * <code>RequestUtils.getUserLocale()</code> and <code>TagUtils.message()</code> for the lookup.
 * @author Fabrizio Giustina
 * @version $Revision: 1081 $ ($Author: fgiust $)
 */
public class I18nStrutsAdapter implements I18nResourceProvider
{

    /**
     * @see LocaleResolver#resolveLocale(HttpServletRequest)
     */
    public Locale resolveLocale(WebContext pWebContext)
    {
        Locale userLocale = (Locale)pWebContext.getSessionAttribute(Globals.LOCALE_KEY);

        if (userLocale == null)
        {
            // Returns Locale based on Accept-Language header or the server default
            userLocale = pWebContext.getLocale();
        }

        return userLocale;
    }

    
    public String getMessage(String resourceKey, String defaultValue, WebContext pWebContext){
	  String result = getMessageValue(resourceKey, pWebContext);
	  if ( result == null ){
		  return defaultValue;
	  } else {
		  return result; 
	  }
	}
	public String getMessage(String resourceKey, WebContext pWebContext) throws NoSuchMessageException {
		  String result = getMessageValue(resourceKey, pWebContext);
		  if ( result == null ){
			  throw new  NoSuchMessageException(resourceKey);
		  } else {
			  return result; 
		  }		
	}

	private String getMessageValue(String resourceKey,  WebContext pWebContext) {
		if ( resourceKey == null || pWebContext == null){
			return resourceKey;
		}

        // retrieve MessageResources. Don't use TagUtils to mantain Struts 1.1 compatibility
        MessageResources resources = (MessageResources) pWebContext.getRequestAttribute(
            Globals.MESSAGES_KEY
            );

        if (resources == null)
        {
            ModuleConfig moduleConfig = (ModuleConfig) pWebContext.getRequestAttribute(Globals.MODULE_KEY);

            if (moduleConfig == null)
            {
                moduleConfig = (ModuleConfig) pWebContext.getApplicationAttribute(Globals.MODULE_KEY);
                pWebContext.setRequestAttribute(Globals.MODULE_KEY, moduleConfig);
            }

            resources = (MessageResources) pWebContext.getApplicationAttribute(
            		Globals.MESSAGES_KEY + moduleConfig.getPrefix()
                );
        }

        if (resources == null)
        {
            resources = (MessageResources) pWebContext.getApplicationAttribute(
                Globals.MESSAGES_KEY);
        }

        String title = null;
        if (resources != null)
        {
            Locale userLocale = resolveLocale(pWebContext);
            title = resources.getMessage(userLocale, resourceKey);
        }

        return title;
	}




}
