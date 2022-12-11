package app.controller;

public class NoSeparatorException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSeparatorException() {
		super();
	}

	public NoSeparatorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoSeparatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSeparatorException(String message) {
		super(message);
	}

	public NoSeparatorException(Throwable cause) {
		super(cause);
	}

	
	
}
