/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 欢迎加入 E3平台联盟QQ群:21523645 
 */
package net.jcreate.e3.table.decorator;

import net.jcreate.e3.table.Cell;
import net.jcreate.e3.table.util.TagConstants;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 该代码来自 displaytag.
 * 注意:改类是无状态的
 * @author 黄云辉
 *
 */
public class URLDecorator extends AbstractDecorator {
	
	public static final URLDecorator me = new URLDecorator();

	  /**
     * "://".
     */
    private static final String URL_DELIM = "://"; //$NON-NLS-1$

    /**
     * Urls.
     */
    private static final String[] URLS_PREFIXES = //
    new String[]{"http", "https", "ftp"}; //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
	public Object decorate(Object pValue, Cell pCell) throws Exception {
		 if (pValue == null)
	        {
	            return null;
	        }
	        String work = pValue.toString();

	        int urlBegin;
	        StringBuffer buffer = new StringBuffer();

	        // First check for email addresses.
	        while ((urlBegin = work.indexOf('@')) != -1)
	        {
	            int start = 0;
	            int end = work.length() - 1;

	            // scan backwards...
	            for (int j = urlBegin; j >= 0; j--)
	            {
	                if (Character.isWhitespace(work.charAt(j)))
	                {
	                    start = j + 1;
	                    break;
	                }
	            }

	            // scan forwards...
	            for (int j = urlBegin; j <= end; j++)
	            {
	                if (Character.isWhitespace(work.charAt(j)))
	                {
	                    end = j - 1;
	                    break;
	                }
	            }

	            String email = work.substring(start, end + 1);

	            buffer.append(work.substring(0, start)).append("<a href=\"mailto:") //$NON-NLS-1$
	                .append(email + "\">") //$NON-NLS-1$
	                .append(email).append("</a>"); //$NON-NLS-1$

	            if (end == work.length())
	            {
	                work = TagConstants.EMPTY_STRING;
	            }
	            else
	            {
	                work = work.substring(end + 1);
	            }
	        }

	        work = buffer.toString() + work;
	        buffer = new StringBuffer();

	        // Now check for urls...
	        while ((urlBegin = work.indexOf(URL_DELIM)) != -1)
	        {

	            // scan backwards...
	            int fullUrlBegin = urlBegin;
	            StringBuffer prefixBuffer = new StringBuffer(10);
	            for (int j = fullUrlBegin - 1; j >= 0; j--)
	            {
	                if (Character.isWhitespace(work.charAt(j)))
	                {
	                    fullUrlBegin = j + 1;
	                    break;
	                }
	                fullUrlBegin = j;
	                prefixBuffer.append(work.charAt(j));
	            }

	            if (!ArrayUtils.contains(URLS_PREFIXES, StringUtils.reverse(prefixBuffer.toString())))
	            {

	                buffer.append(work.substring(0, urlBegin + 3));
	                work = work.substring(urlBegin + 3);
	                continue;
	            }

	            int urlEnd = work.length();

	            // scan forwards...
	            for (int j = urlBegin; j < urlEnd; j++)
	            {
	                if (Character.isWhitespace(work.charAt(j)))
	                {
	                    urlEnd = j;
	                    break;
	                }
	            }

	            String url = work.substring(fullUrlBegin, urlEnd);

	            buffer.append(work.substring(0, fullUrlBegin)).append("<a href=\"")//$NON-NLS-1$
	                .append(url).append("\">")//$NON-NLS-1$
	                .append(url).append("</a>"); //$NON-NLS-1$

	            if (urlEnd >= work.length())
	            {
	                work = TagConstants.EMPTY_STRING;
	            }
	            else
	            {
	                work = work.substring(urlEnd);
	            }
	        }

	        buffer.append(work);
	        return buffer.toString();
	}

}
