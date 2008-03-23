package net.jcreate.e3.samples.tree;

public class Org {
	private String id;
	private String parentId;
	private String name;
	private int viewOrder;

	public Org(){
		
	}
	
	public Org(String pId, String pParentId, String pName, int pViewOrder){
		this.id = pId;
		this.parentId = pParentId;
		this.name = pName;
		this.viewOrder = pViewOrder;
	}
	public int getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
