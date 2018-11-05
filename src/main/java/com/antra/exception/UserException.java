package com.antra.exception;

public abstract class UserException extends RuntimeException {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public UserException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public UserException() {
		super();
	}

}
