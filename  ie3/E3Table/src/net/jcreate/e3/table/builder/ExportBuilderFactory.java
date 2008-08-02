package net.jcreate.e3.table.builder;

import net.jcreate.e3.table.ExportRequest;
import net.jcreate.e3.table.TableBuilder;

public class ExportBuilderFactory {
  public TableBuilder getInstance(ExportRequest pExportRequest){
	  return new ExcelTableBuilder();
  }
}
