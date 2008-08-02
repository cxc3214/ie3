package net.jcreate.e3.table;

public class ExportRequest {
	public static final String EXCEL = "excel";
	public static final String PDF = "pdf";
    private String type= EXCEL;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
