package net.jcreate.e3.tools.xmlMerger.test;

import java.io.IOException;
import java.io.InputStream;

import net.jcreate.e3.tools.xmlMerger.XMLMerger;
import net.jcreate.e3.tools.xmlMerger.support.DefaultXMLMerger;
import net.jcreate.e3.tools.xmlMerger.util.XMLHelper;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class Hello {
  public static void main(String[] args){
	  InputStream is = Hello.class.getResourceAsStream("build.xml");
	  InputStream is2 = Hello.class.getResourceAsStream("build2.xml");
	  try {
		  Document doc = XMLHelper.getDocument(is);		  
		  Document doc2 = XMLHelper.getDocument(is2);		  
          XMLMerger merger = new DefaultXMLMerger();
          merger.merge(doc, doc2);
		  System.out.println(doc.asXML());
	} catch (DocumentException e) {
		e.printStackTrace();
	}finally{
	  try {
		is.close();
		is2.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
  }
}
