package net.jcreate.e3.tools.xmlMerger.util;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class XMLHelper {
  private XMLHelper() {
  }

  public static Document getDocument(InputStream pIS) throws DocumentException {
    SAXReader reader = new SAXReader();
    Document document;
    document = reader.read(pIS);
    return document;
  }

  public static Document getDocument(Reader pReader) throws DocumentException {
    SAXReader reader = new SAXReader();
    Document document;
    document = reader.read(pReader);
    return document;
  }

  public static Document getDocument(File pFile) throws DocumentException {
    SAXReader reader = new SAXReader();
    Document document;
    document = reader.read(pFile);
    return document;
  }

  public static Attribute getAttribute(Element pElement, String pAttributeName){
    Attribute attr = (Attribute)pElement.selectSingleNode("./@" + pAttributeName);
    return attr;
  }

  public static String getAttributeValue(Element pElement, String pAttributeName){
	  if ( pElement == null ){
		  return null;
	  }
    Attribute result = getAttribute(pElement, pAttributeName);
    if ( result == null )
      return null;
    else
      return result.getStringValue();
  }

  public static Element[] getChildrenElment(Element pElement, String pName){
    List result = pElement.selectNodes("./" + pName);
    if ( result == null ){
      return new Element[0];
    }
    return (Element[])result.toArray(new Element[0]);
  }
  public static Element getChildElement(Element pElement, String pName){
	  if ( pElement == null ){
		  return null;
	  }
      Element childElm = (Element)pElement.selectSingleNode("./" + pName);
      return childElm;

    }

    public static String getChildElementValue(Element pElement, String pName){
      Element result = getChildElement(pElement, pName);
      if ( result == null ){
        return null;
      }
      return result.getStringValue();
    }



 private static final Log log = LogFactory.getLog(XMLHelper.class);
}