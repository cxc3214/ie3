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
package net.jcreate.e3.tools.xmlMerger.support;

import net.jcreate.e3.tools.xmlMerger.ElementMergerPolicy;
import net.jcreate.e3.tools.xmlMerger.PolicyDescription;

public class DefaultElementMergerPolicy implements ElementMergerPolicy{

	private PolicyDescription attributesMergePolicy;
	private PolicyDescription attributeMergePolicy;
	private PolicyDescription textMergePolicy;
	private PolicyDescription bodyMergePolicy;
	
	public PolicyDescription getAttributesMergePolicy() {
		if ( attributesMergePolicy == null ){
			return PolicyDescription.MERGE;
		} else {
			return attributesMergePolicy;
		}
	}

	public PolicyDescription getAttributeMergePolicy(String pAttributeName) {
        if ( attributeMergePolicy == null ){
        	return PolicyDescription.REPLCAE;
        } else {
        	return attributeMergePolicy;
        }
	}

	public PolicyDescription getTextMergePolicy() {
       if ( textMergePolicy == null ){
    	  return PolicyDescription.REPLCAE; 
       } else {
    	   return textMergePolicy;
       }
	}

	public PolicyDescription getBodyMergePolicy() {
		if ( bodyMergePolicy == null ){
			return PolicyDescription.MERGE;
		} else {
			return bodyMergePolicy;
		}
	}

	public void setAttributesMergePolicy(PolicyDescription attributesMergePolicy) {
		this.attributesMergePolicy = attributesMergePolicy;
	}

	public void setAttributeMergePolicy(PolicyDescription attributeMergePolicy) {
		this.attributeMergePolicy = attributeMergePolicy;
	}

	public void setTextMergePolicy(PolicyDescription textMergePolicy) {
		this.textMergePolicy = textMergePolicy;
	}

}
