package net.jcreate.e3.resource.impl;

import net.jcreate.e3.resource.ResourceException;

/**
 * 配置异常
 * @author 黄云辉
 *
 */
public class ConfigeException extends ResourceException{

	private static final long serialVersionUID = 1L;

	public ConfigeException() {
		super();
	}

	public ConfigeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigeException(String message) {
		super(message);
	}

	public ConfigeException(Throwable cause) {
		super(cause);
	}

}
