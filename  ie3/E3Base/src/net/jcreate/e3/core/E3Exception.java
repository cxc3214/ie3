package net.jcreate.e3.core;

/**
 * E3平台下所有异常都必须从该类派生
 * @author new
 *
 */
public class E3Exception extends Exception{
	private static final long serialVersionUID = 1L;

	public E3Exception() {
		super();
	}

	public E3Exception(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public E3Exception(String arg0) {
		super(arg0);
	}

	public E3Exception(Throwable arg0) {
		super(arg0);
	}
	
}
