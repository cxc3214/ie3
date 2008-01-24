package net.jcreate.e3.tools.xmlMerger;

import org.dom4j.Element;

public interface TextMerger {
  public void merge(Element pTargetElement, Element pSrcElement) throws Exception; 
}
