package com.webserver.http;

public class EmptyRequestException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 * 空请求
	 * 当HttpRequest初始化过程中发现该请求实际上
	 * 是一个空请求会抛出空指针异常
	 * 
	 */

	public EmptyRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
