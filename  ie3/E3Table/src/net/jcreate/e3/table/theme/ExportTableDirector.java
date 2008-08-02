package net.jcreate.e3.table.theme;

import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.support.DefaultTableDirector;

/**
 * 负责导出excel,pdf,xml 的控制处理 
 * @author 黄云辉
 *
 */
public class ExportTableDirector extends DefaultTableDirector{

	public ExportTableDirector() {
		super();
	}

	public ExportTableDirector(TableContext tableContext) {
		super(tableContext);
	}

}
