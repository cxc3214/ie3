package net.jcreate.e3.core;

/**
 * E3平台所有运行时异常都要求从该类派生
 * @author new
 *
 */
public class E3RuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public E3RuntimeException() {
		super();
	}

	public E3RuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public E3RuntimeException(String arg0) {
		super(arg0);
	}

	public E3RuntimeException(Throwable arg0) {
		super(arg0);
	}

}
