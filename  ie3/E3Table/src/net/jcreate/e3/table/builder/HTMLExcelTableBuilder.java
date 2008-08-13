package net.jcreate.e3.table.builder;

public class HTMLExcelTableBuilder extends FastSkinHTMLTableBuilder{

	public HTMLExcelTableBuilder() {
		super();
	}

	public String getMimeType() {
		return "application/msexcel";
	}
	
	

}
