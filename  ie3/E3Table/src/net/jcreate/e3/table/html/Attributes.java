/**
 * Copyright (c) 2003 held jointly by the individual authors.
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA. >
 * http://www.gnu.org/copyleft/lesser.html >
 * http://www.opensource.org/licenses/lgpl-license.php
 */
package net.jcreate.e3.table.html;

import java.util.HashMap;
import java.util.Map;

import net.jcreate.e3.table.html.util.JspUtils;



/**
 * Holds a map of attributes.
 * 
 * @author Matthew L. Wilson
 * @version $Revision: 1.3 $ $Date: 2005/03/16 11:07:42 $
 */
public class Attributes implements Attributeable
{
    private Map attributes = new HashMap();

    /**
     * Default constructore.
     */
    public Attributes()
    {

    }

    /**
     * @see net.mlw.vlh.web.tag.support.Attributeable#setAttribute(java.lang.String,
     *      java.lang.String)
     */
    public void setAttribute(String name, String value)
    {
        attributes.put(name, value);
    }

    /**
     * returns a String in the form or nameX="valueX".
     * 
     * @return A String in the form or nameX="valueX".
     */
    public String geAttributesAsString()
    {
        return JspUtils.toAttributesString(attributes);
    }

    public Map getMap()
    {
        return  new HashMap( attributes );
    }

    /**
     * reset this bean.
     */
    public void reset()
    {
        attributes.clear();
    }

    /**
     * Append with the space to existing non null or empty key's value. If not exist key, uses
     * setCellAttribute Ussually used with "class" attributes.
     * 
     * @param key
     * @param style to append
     * @see #setAttribute(String, String)
     */
    public void appendAttribute(String key, String style)
    {
        if (attributes.containsKey(key) == true)
        {
            String current = (String) attributes.get(key);
            if (current!=null && current.length()>0){
                attributes.put(key, current + " " + style);
            } else {
               setAttribute(key, style); 
            }
        }
        else
        {
            setAttribute(key, style);
        }

    }
}