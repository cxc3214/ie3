package net.jcreate.e3.tools.xmlMerger;

import org.dom4j.Document;

/**
 * 将src 的文档 和TargetDocument文档进行合并. 
 * @author 黄云辉
 *
 */
public interface XMLMerger {
  public void merge(Document pTargetDocument, Document pSrcDocument) throws MergeXMLException;
}
