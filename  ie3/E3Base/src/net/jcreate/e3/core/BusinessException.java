package net.jcreate.e3.core;


public class BusinessException extends E3Exception{

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BusinessException(String arg0) {
		super(arg0);
	}

	public BusinessException(Throwable arg0) {
		super(arg0);
	}
}
