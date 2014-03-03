package com.excilys.computerdb.business.dao;

public class DbSessionException extends Exception {

	public DbSessionException() {
	}

	public DbSessionException(String message) {
		super(message);
	}

	public DbSessionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DbSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbSessionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
