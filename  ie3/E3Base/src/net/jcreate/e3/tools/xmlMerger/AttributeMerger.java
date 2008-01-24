package net.jcreate.e3.tools.xmlMerger;

import org.dom4j.Attribute;

public interface AttributeMerger {
  public void merge(Attribute pTargetAttribute, Attribute pSrcAttribute); 
}
