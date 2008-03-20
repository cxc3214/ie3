package net.jcreate.e3.resource.loader;

import net.jcreate.e3.resource.ResourceException;

/**
 * 没有找到资源异常
 *
 * @author 黄云辉
 *
 */
public class NotFoundResourceException extends ResourceException {

	private static final long serialVersionUID = 1L;

	public NotFoundResourceException() {
		super();
	}

	public NotFoundResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundResourceException(String message) {
		super(message);
	}

	public NotFoundResourceException(Throwable cause) {
		super(cause);
	}

}
