package net.jcreate.e3.resource;

/**
 * 资源异常
 * @author 黄云辉
 *
 */
public class ResourceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceException() {
		super("资源异常");
	}

	public ResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(Throwable cause) {
		super(cause);
	}

}
