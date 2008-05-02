package net.jcreate.e3.commons.id;

/**
 * 创建ID异常
 * @author 黄云辉
 *
 */
public class CreateIDException extends IDException{

	private static final long serialVersionUID = 1L;

	public CreateIDException() {
		super();
	}

	public CreateIDException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreateIDException(String message) {
		super(message);
	}

	public CreateIDException(Throwable cause) {
		super(cause);
	}

}
