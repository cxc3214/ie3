/**
 * Copyright (c) 2003 held jointly by the individual authors.            
 *                                                                          
 * This library is free software; you can redistribute it and/or modify it    
 * under the terms of the GNU Lesser General Public License as published      
 * by the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.                                            
 *                                                                            
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; with out even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU Lesser General Public License for more details.                                                  
 *                                                                           
 * You should have received a copy of the GNU Lesser General Public License   
 * along with this library;  if not, write to the Free Software Foundation,   
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.              
 *                                                                            
 * > http://www.gnu.org/copyleft/lesser.html                                  
 * > http://www.opensource.org/licenses/lgpl-license.php
 */
package net.jcreate.e3.table.html.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.jcreate.e3.table.html.Attributeable;
import net.jcreate.e3.table.html.util.JspUtils;

public class AttributeTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspException {
	    Attributeable parent = (Attributeable) JspUtils.getParent(this, Attributeable.class);
		if ( parent == null ){
			return super.doEndTag();
		}
		String propertyValue = null;
		if ( value == null ){
			BodyContent context = this.bodyContent;
			if ( context != null ){
			  propertyValue = context.getString();
			  context.clearBody();
			  this.setBodyContent(null);//tomcat5.028好象不会自动清除,所以我们显示设置为nulll
			}
		}else{
			propertyValue = value;
		}
		parent.setAttribute(name, propertyValue);
		return super.doEndTag();
		
	}

	
}