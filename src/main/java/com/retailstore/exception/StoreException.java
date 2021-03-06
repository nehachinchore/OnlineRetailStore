package com.retailstore.exception;

public class StoreException extends Exception {
	private static final long serialVersionUID = 159980388443887267L;

	private String errorCode;

	public StoreException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
