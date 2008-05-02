package net.jcreate.e3.commons.id;


public class IDException extends java.lang.RuntimeException{

	private static final long serialVersionUID = 1L;

	public IDException() {
		super("ID异常!");
	}

	public IDException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDException(String message) {
		super(message);
	}

	public IDException(Throwable cause) {
		super(cause);
	}

}
