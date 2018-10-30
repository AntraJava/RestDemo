package com.antra.util;

public class UserException extends Exception {

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
