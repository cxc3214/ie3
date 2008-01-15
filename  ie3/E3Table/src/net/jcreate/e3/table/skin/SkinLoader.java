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
package net.jcreate.e3.table.skin;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SkinLoader {
	public Skin load(InputStream pSkinDef) throws LoadSkinException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new LoadSkinException(e);
		}
		Document doc = null;
		try {
			doc = builder.parse(pSkinDef);
		} catch (Exception e) {
			throw new LoadSkinException(e);
		}
		Skin result = new Skin();
		NodeList templates = doc.getElementsByTagName("template");
		for(int i=0; i<templates.getLength(); i++){
			Node node = templates.item(i);
			if ( Node.ELEMENT_NODE != node.getNodeType() ){
				continue;
			}
			Element elm = (Element)node;
			String templateName = elm.getAttribute("name");
			String templateContext = elm.getFirstChild().getNodeValue();
			Template template = new Template();
			template.setName(templateName);
			template.setContent(templateContext);
			result.addTemplate(template);
		}
		return result;
	}
	
	public static void main(String[] args){
		InputStream is =  SkinLoader.class.getResourceAsStream("SkinDef.xml");
		SkinLoader loader = new SkinLoader();
		Skin skin = loader.load(is);
		System.out.println( skin.getTemplate("xkin-default") );
	}
}
