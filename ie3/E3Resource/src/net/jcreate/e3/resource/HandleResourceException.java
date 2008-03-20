package net.jcreate.e3.resource;

/**
 * 进行资源处理出现异常，需要抛出该异常
 * @author 黄云辉
 *
 */
public class HandleResourceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public HandleResourceException() {
		super("处理异常");
	}

	public HandleResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandleResourceException(String message) {
		super(message);
	}

	public HandleResourceException(Throwable cause) {
		super(cause);
	}

}

