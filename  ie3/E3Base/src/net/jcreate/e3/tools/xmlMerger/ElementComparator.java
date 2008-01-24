package net.jcreate.e3.tools.xmlMerger;

import org.dom4j.Element;



/**
 * 负责2个同名元素的比较，如果相等返回true,否则返回false
 * @author 黄云辉
 *
 */
public interface ElementComparator {
  public boolean compare(Element pTargetElement, Element pSrcElement);
}
