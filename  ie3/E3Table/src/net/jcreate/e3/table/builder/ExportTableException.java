package net.jcreate.e3.table.builder;

import net.jcreate.e3.table.TableException;

public class ExportTableException extends TableException{

	private static final long serialVersionUID = 1L;

	public ExportTableException() {
		super();
	}

	public ExportTableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExportTableException(String message) {
		super(message);
	}

	public ExportTableException(Throwable cause) {
		super(cause);
	}

}
