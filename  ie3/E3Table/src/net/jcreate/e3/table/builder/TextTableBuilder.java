package net.jcreate.e3.table.builder;


import java.io.Writer;
import net.jcreate.e3.table.TableBuilder;

public interface TextTableBuilder extends TableBuilder {
    public void export(Writer out) throws ExportTableException;
}
