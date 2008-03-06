package net.jcreate.e3.table;

import java.util.List;

import net.jcreate.e3.table.html.HTMLColumn;

/**
 * column分组对象
 * @author 黄云辉
 *
 */
public interface ColumnGroup {
	/**
	 * 标题
	 * @return
	 */
    public String getTitle();    
    public void setTitle(String pTitle);
    /**
     * 标题key
     * @return
     */
	public String getTitleKey();
	public void setTitleKey(String titleKey);
	
	/**
	 * 组的级别，最顶层是1，第二层是2，依次类推
	 * @return
	 */
	public int getLevel();
	public void setLevel(int level);
	
	/**
	 * 子分组索引号
	 * @param pGroup
	 * @return
	 */
	public int getIndex(ColumnGroup pGroup);
	/**
	 * 设置父亲分组
	 * @param pParentGroup
	 */
	public void setParent(ColumnGroup pParentGroup);
	/**
	 * 获取父亲分组
	 * @return
	 */
	public ColumnGroup getParent();
	public List getChildren();	
	
	/**
	 * 添加子分组,需要设置分组pGroup的级别
	 * @param pGroup
	 */
	public void addGroup(ColumnGroup pGroup);
	/**
	 * 删除一个分组
	 * @param pGroup
	 */
	public void detachGroup(ColumnGroup pGroup);
	
	/**
	 * 获取分组包含的column.
	 * @return
	 */
	public List getColumns();
	
	/**
	 * 添加类，需要递归调用父亲的addColumn 
	 * @param pColumn
	 */
	public void addColumn(HTMLColumn pColumn);

}
