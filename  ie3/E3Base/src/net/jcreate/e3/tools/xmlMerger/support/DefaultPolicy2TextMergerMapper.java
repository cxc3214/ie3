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

import net.jcreate.e3.tools.xmlMerger.Policy2TextMergerMapper;
import net.jcreate.e3.tools.xmlMerger.PolicyDescription;
import net.jcreate.e3.tools.xmlMerger.TextMerger;

public class DefaultPolicy2TextMergerMapper implements Policy2TextMergerMapper{
    private final TextMerger REPLCAE_MERGER = new ReplaceTextMerger();
    private final TextMerger IGNROE_MERGER = new  IgnoreTextMerger();
    
	public TextMerger map(PolicyDescription pPolicyDescription) {
		if ( pPolicyDescription == null ){
			return REPLCAE_MERGER;
		}else if ( PolicyDescription.IGNORE.equals(pPolicyDescription) ){
			return IGNROE_MERGER;
		} else if ( PolicyDescription.REPLCAE.equals(pPolicyDescription) ){
			return REPLCAE_MERGER;
		} else{
	        throw new IllegalArgumentException("unsupported policydescription:" + pPolicyDescription );		
		}
	}

}
