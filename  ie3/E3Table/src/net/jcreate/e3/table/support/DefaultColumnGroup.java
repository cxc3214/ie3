package net.jcreate.e3.table.support;

import java.util.ArrayList;
import java.util.List;

import net.jcreate.e3.table.ColumnGroup;
import net.jcreate.e3.table.html.HTMLColumn;

public class DefaultColumnGroup implements ColumnGroup {
	/**
	 * 组标题
	 */
	private String title;
	/**
	 * 标题key
	 */
	private String titleKey;
	/**
	 * 嵌套的子组. value是ColumnGroup
	 */
	private List children = new ArrayList();
	/**
	 * 父亲
	 */
	private ColumnGroup parent = null;

	/**
	 * 组包含的列. value是HTMLColumn
	 */
	private List columns = new ArrayList();

	/**
	 * 组的级别,最顶上的组级为1,下一级是2,以此类推.
	 */
	private int level = 1;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	public List getChildren() {
		return children;
	}

	public void detachGroup(ColumnGroup pGroup){
		if ( pGroup == null ){
			return;
		}
		if ( this.children.contains(pGroup) == false ){
			return;
		}
		this.children.remove(pGroup);
		pGroup.setParent(null);
	}
	
	public int getIndex(ColumnGroup pGroup){
		return this.children.indexOf(pGroup);
	}
	
	public void setParent(ColumnGroup pParentGroup){
		if ( pParentGroup == this.parent ){
		return;
	}
	if ( this.parent != null ){//解除跟以前父亲菜单的关系.
		this.parent.detachGroup(this);
	}
	if (pParentGroup != null) {
		int index = pParentGroup.getIndex(this);
		if (index == -1) {
			pParentGroup.addGroup(this);
		}
	}
	this.parent = pParentGroup;
		
	}
	
	public ColumnGroup getParent() {
		return parent;
	}
	
	public void addGroup(ColumnGroup pGroup){
		if ( pGroup == null ){
			return;
		}
		if ( this.children.contains(pGroup) == false ){
			this.children.add(pGroup);
			pGroup.setParent(this);
			//设置子group的级别
			pGroup.setLevel(this.level+1);
		}
		
	}


	public List getColumns() {
		return columns;
	}

	/**
	 * 递归添加
	 * @param pColumn
	 */
	public void addColumn(HTMLColumn pColumn){
		this.columns.add(pColumn);
		ColumnGroup parentGroup = this.getParent();
		if ( parentGroup != null){
			parentGroup.addColumn(pColumn);
		}
		pColumn.setColumnGroup(this);
	}

	public void setColumns(List columns) {
		this.columns = columns;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static void main(String[] args){
		HTMLColumn a = new HTMLColumn();
		a.setProperty("userName");
		a.setTitle("用户名");
		
		HTMLColumn b = new HTMLColumn();
		b.setProperty("userID");
		b.setTitle("用户ID");
		
		HTMLColumn c = new HTMLColumn();
		b.setProperty("remark");
		b.setTitle("备注");
		
		ColumnGroup group = new DefaultColumnGroup();
		group.setTitle("用户信息");
		group.addColumn(a);
		group.addColumn(b);
		
		ColumnGroup group2 = new DefaultColumnGroup();
		group2.setTitle("其他信息");
		group2.addColumn(c);
//		
		ColumnGroup group3 = new DefaultColumnGroup();
		group3.setTitle("测试");
		group3.addGroup(group);
		group3.addGroup(group2);
		
		group2.addGroup(group);
		System.out.println( group3.getLevel());
		System.out.println( group2.getLevel());
		System.out.println( group.getLevel());
		
		
	}
}
