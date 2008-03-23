package net.jcreate.e3.samples.tree;

public class User {
	private String id;// 用户ID
	private String name;// 用户名称
	private String orgId;//所属机构ID
	
	public User(){
		
	}

	public User(String pId, String pName, String pOrgId){
		this.id = pId;
		this.name = pName;
		this.orgId = pOrgId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
}
