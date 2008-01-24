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
package net.jcreate.e3.tools.xmlMerger;

import java.util.Map;

public class PolicyDescription {
	
	public final static  PolicyDescription IGNORE = new PolicyDescription("IGNORE");
	public final static  PolicyDescription REPLCAE = new PolicyDescription("REPLCAE");
	public final static  PolicyDescription MERGE = new PolicyDescription("MERGE");
	
	private String name;

	private Map extendedAttributes;
	
	public PolicyDescription(){
		
	}
	
	public PolicyDescription(String pName){
		this.name = pName;
	}

	public Map getExtendedAttributes() {
		return extendedAttributes;
	}
	
	public void put(String pKey, Object pValue){
		extendedAttributes.put(pKey, pValue);
	}
	
	public Object get(String pKey){
		return extendedAttributes.get(pKey);
	}

	public void setExtendedAttributes(Map extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if ( obj instanceof PolicyDescription == false){
			return false;
		}
		PolicyDescription objPolicy = (PolicyDescription)obj;
        if ( name == null ){
        	return super.equals(obj);	
        } else {
        	return name.equals( objPolicy.getName() );
        }
		
	}
	public int hashCode() {
        if ( this.name == null ){
        	return super.hashCode(); 
        } else {
         return this.name.hashCode();	
        }		
	}
	
	public String toString(){
		return String.valueOf(name);
	}
	
 
}
