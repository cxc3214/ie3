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
package net.jcreate.e3.table.util;

import java.util.List;

public abstract class StringUtils {
	  /**
     * The value needs to be a String[]. A String, Null, or List will be
     * converted to a String[]. In addition it will attempt to do a String
     * conversion for other object types.
     * 
     * @param value
     *            The value to convert to an String[]
     * @return A String[] value.
     */
    public static String[] getValueAsArray(Object value) {
        if (value == null) {
            return new String[] {}; // put in a placeholder
        }

        if (value instanceof String[]) {
            return (String[]) value;
        } else if (value instanceof List) {
            List valueList = (List) value;
            return (String[])valueList.toArray(new String[valueList.size()]);
        }

        return new String[] { value.toString() };
    }
    
    public static boolean isNotEmpty(String pStr){
    	return ! isEmpty(pStr); 
    }
    public static boolean isEmpty(String pStr){
    	if ( pStr == null ){
    		return true;
    	}
    	if ( "".equals(pStr) ){
    		return true;
    	}
    	return false;
    }
	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 * @param delim delimiter to use (probably a ",")
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for <code>toString()</code> implementations.
	 * @param arr array to display. Elements may be of any type (toString
	 * will be called on each element).
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
}
