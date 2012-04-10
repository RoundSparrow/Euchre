package com.cynical.android.euchre.exception;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = 581405964734339792L;

	public ConnectionException() {
	}

	public ConnectionException(String detailMessage) {
		super(detailMessage);
	}

}
