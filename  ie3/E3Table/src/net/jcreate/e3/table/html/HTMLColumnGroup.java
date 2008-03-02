package net.jcreate.e3.table.html;

import java.util.ArrayList;
import java.util.List;

public class HTMLColumnGroup {
	/**
	 * 组标题
	 */
  private String title;
  /**
   * 标题key
   */
  private String titleKey;
  /**
   * 嵌套的子组. value是HTMLColumnGroup
   */
  private List children = new ArrayList();
  /**
   * 父亲
   */
  private HTMLColumnGroup parent = null;
  
  /**
   * 组包含的列. value是HTMLColumn
   */
  private List columns = new ArrayList();
  
  /**
   * 组的级别,最顶上的组级为1,下一级是2,以此类推.
   */
  private int level = 1;
  
  
}
