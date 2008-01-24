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

package net.jcreate.e3.templateEngine.support;

/**
 * 
 * @author 黄云辉
 *
 */
public class TemplateType {
	
	private String type;
	private String description;

	public static final TemplateType VELOCITY = new TemplateType("Velocity",
			"Velocity engine");
	public static final TemplateType FREE_MARKER = new TemplateType(
			"FreeMarker", "FreeMarker engine");
	public static final TemplateType WEB_MACRO = new TemplateType("WebMacro",
			"WebMacro engine");
	public static final TemplateType JXP = new TemplateType("Jxp", "Jxp engine");

	private TemplateType(String pType, String pDescription) {
		this.type = pType;
		this.description = pDescription;
	}

	public int hashCode() {
		return this.type.hashCode();
	}

	public boolean equals(Object pObj) {
		if (pObj instanceof TemplateType) {
			return false;
		}
		TemplateType objTemplateType = (TemplateType) pObj;
		return this.type.equals(objTemplateType.type);
	}

	public String toString() {
		return this.description;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
}
