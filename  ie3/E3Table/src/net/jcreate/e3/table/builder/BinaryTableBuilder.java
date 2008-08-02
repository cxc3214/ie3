package net.jcreate.e3.table.builder;

import java.io.OutputStream;

import net.jcreate.e3.table.TableBuilder;

public interface BinaryTableBuilder extends TableBuilder {
	public void export(OutputStream out) throws ExportTableException;
}
