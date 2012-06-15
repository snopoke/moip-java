package com.addynamo.moip;

public class PaymentException extends Exception {

	private static final long serialVersionUID = 6700887973328833120L;

	public PaymentException() {
		super();
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
	}
}
