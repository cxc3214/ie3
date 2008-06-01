package net.jcreate.e3.table.html;

public class HTMLForm {
	//表单action
	private String action;
	//target
	private String target = "_self";
	private String method = "post";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
